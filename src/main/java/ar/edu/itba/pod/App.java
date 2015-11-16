package ar.edu.itba.pod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.query.query1.Query1;
import ar.edu.itba.pod.query.query2.Query2;
import ar.edu.itba.pod.query.query3.Query3;
import ar.edu.itba.pod.query.query4.Query4;
import ar.edu.itba.pod.util.ArgumentParser;
import io.advantageous.boon.json.JsonFactory;
import io.advantageous.boon.json.ObjectMapper;

public class App {
    private static final String MAP_NAME = "DataMap";
    private static final String JOB_TRACKER_NAME = "JobTracker";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ArgumentParser parser = ArgumentParser.builder().withMandatoryArgument("query").withMandatoryArgument("path")
                .withOptionalArgument("tope").withOptionalArgument("N").parse(args);
        String path = parser.getStringArgument("path");

        ClientConfig conf = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(conf);

        IMap<String, ImdbEntry> map = client.getMap(MAP_NAME);
        readMoviesIntoMap(path, map);

        JobTracker tracker = client.getJobTracker(JOB_TRACKER_NAME);
        Job<String, ImdbEntry> job = tracker.newJob(KeyValueSource.fromMap(map));

        int queryN = parser.getIntArgument("query");
        // Switch de queries y llamar a la que corresponda
        switch (queryN) {
        case 1:
            int limit = parser.getIntArgument("N");
            Query1 query1 = new Query1(job, limit);
            List<Entry<String, Integer>> mostPopularActorsByVotes = query1.evaluate();
            mostPopularActorsByVotes
                    .forEach(entry -> System.out.println("Actor: " + entry.getKey() + ", Votes: " + entry.getValue()));
            break;
        case 2:
            int minYear = parser.getIntArgument("tope");
            Query2 query2 = new Query2(job, minYear);
            Map<Integer, List<ImdbEntry>> moviesByYear = query2.evaluate();
            moviesByYear.forEach((year, movies) -> movies
                    .forEach(movie -> System.out.println("Year: " + year + ", Title: " + movie.getTitle())));
            break;
        case 3:
            Query3 query3 = new Query3(job);
            List<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>> moviesByDuo = query3.evaluate();
            moviesByDuo.forEach(entry -> {
                System.out.println("Duo: " + entry.getKey().stream().collect(Collectors.joining(", ")) + " Movies: "
                        + entry.getValue().stream().map(movie -> movie.getTitle()).collect(Collectors.joining(", ")));
            });
            break;
        case 4:
            Query4 query4 = new Query4(job);
            Map<String, List<String>> fetishActorsByDirector = query4.evaluate();
            fetishActorsByDirector.forEach((director, actors) -> {
                String actorListString = actors.stream().collect(Collectors.joining(", "));
                System.out.println("Director: " + director + ", Actors: [" + actorListString + "]");
            });
            break;
        }
    }

    private static void readMoviesIntoMap(String path, IMap<String, ImdbEntry> map) {
        try (InputStream is = new FileInputStream(path);
                InputStreamReader isr = new InputStreamReader(is);
                LineNumberReader reader = new LineNumberReader(isr)) {
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
                    ImdbEntry movie = mapper.fromJson(line, ImdbEntry.class);
                    map.set(movie.getImdbId(), movie);
                }
            }
        } catch (IOException exc) {
            // TODO
        }
    }
}
