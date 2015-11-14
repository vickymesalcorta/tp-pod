package ar.edu.itba.pod;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import ar.edu.itba.pod.model.Movie;
import ar.edu.itba.pod.query.query1.Query1;
import ar.edu.itba.pod.query.query2.Query2;
import ar.edu.itba.pod.query.query4.Query4;
import io.advantageous.boon.json.JsonFactory;
import io.advantageous.boon.json.ObjectMapper;

public class App {

    private static final String MAP_NAME = "DataMap";
    private static final String[] DATA_FILES_PATHS = new String[] { "imdb-40.json" };
    private static final String JOB_TRACKER = "JobTracker";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ClientConfig conf = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(conf);

        IMap<String, Movie> map = client.getMap(MAP_NAME);
        for (String path : DATA_FILES_PATHS) {
            readMoviesIntoMap(path, map);
        }

        JobTracker tracker = client.getJobTracker(JOB_TRACKER);
        Job<String, Movie> job = tracker.newJob(KeyValueSource.fromMap(map));

        int queryN = 4;
        // Switch de queries y llamar a la que corresponda
        switch (queryN) {
        case 1:
            Query1 query1 = new Query1(job, 5);
            List<Entry<String, Integer>> mostPopularActorsByVotes = query1.evaluate();
            mostPopularActorsByVotes
                    .forEach(entry -> System.out.println("Actor: " + entry.getKey() + ", Votes: " + entry.getValue()));
            break;
        case 2:
            Query2 query2 = new Query2(job, 2000);
            Map<Integer, List<Movie>> moviesByYear = query2.evaluate();
            moviesByYear.forEach((year, movies) -> movies
                    .forEach(movie -> System.out.println("Year: " + year + ", Title: " + movie.getTitle())));
            break;
        case 3:
            break;
        case 4:
            Query4 query4 = new Query4(job);
            Map<String, List<String>> fetishActorsByDirector = query4.evaluate();
            fetishActorsByDirector.forEach((director, actors) -> {
                String actorListString = actors.stream().map(actor -> actor + ",").collect(Collectors.joining());
                System.out.println("Director: " + director + ", Actors: ["
                        + actorListString.substring(0, actorListString.length() - 2) + "]");
            });
            break;
        }
    }

    private static void readMoviesIntoMap(String path, IMap<String, Movie> map) {
        try (InputStream is = App.class.getClassLoader().getResourceAsStream(path);
             InputStreamReader isr = new InputStreamReader(is);
             LineNumberReader reader = new LineNumberReader(isr);) {
            ObjectMapper mapper = JsonFactory.create();

            String line;
            while ((line = reader.readLine()) != null) {
                switch (line) {
                case "[":
                    break;
                case "]":
                    break;
                default:
                    line = line.split("},")[0];
                    Movie movie = mapper.fromJson(line, Movie.class);
                    map.set(movie.getImdbId(), movie);
                }
            }
        } catch (IOException exc) {
            // TODO
        }
    }
}
