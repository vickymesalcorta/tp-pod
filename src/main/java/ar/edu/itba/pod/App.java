package ar.edu.itba.pod;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import ar.edu.itba.pod.model.Movie;
import ar.edu.itba.pod.query.query2.Query2;
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

        // Switch de queries y llamar a la que corresponda
        Query2 query = new Query2(job, 2000);
        Map<Integer, List<Movie>> moviesByYear = query.evaluate();
        moviesByYear.forEach(
                (year, movies) -> movies.forEach(
                        movie -> System.out.println("Year: " + year + ", Title:" + movie.getTitle())));
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
