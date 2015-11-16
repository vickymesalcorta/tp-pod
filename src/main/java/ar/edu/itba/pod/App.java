package ar.edu.itba.pod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.concurrent.ExecutionException;

import org.joda.time.DateTime;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.parse.ArgumentParser;
import ar.edu.itba.pod.query.Query;
import ar.edu.itba.pod.query.query1.Query1;
import ar.edu.itba.pod.query.query2.Query2;
import ar.edu.itba.pod.query.query3.Query3;
import ar.edu.itba.pod.query.query4.Query4;
import ar.edu.itba.pod.util.Logger;
import io.advantageous.boon.json.JsonFactory;
import io.advantageous.boon.json.ObjectMapper;

public class App {
    private static final String MAP_NAME = "DataMap";
    private static final String JOB_TRACKER_NAME = "JobTracker";

    private static final String QUERY_N_PARAM_ID = "query";
    private static final String PATH_PARAM_ID = "path";
    private static final String MIN_YEAR_PARAM_ID = "tope";
    private static final String ACTOR_LIMIT_PARAM_ID = "N";

    private static DateTime fileParsingStart;
    private static DateTime fileParsingEnd;
    private static DateTime mapReduceJobStart;
    private static DateTime mapReduceJobEnd;

    public static void main(String[] args)
            throws InterruptedException, ExecutionException, IOException {
        ArgumentParser argumentParser = parse(args);
        String path = argumentParser.getStringArgument(PATH_PARAM_ID);

        HazelcastInstance client = HazelcastClient
                .newHazelcastClient(new ClientConfig());

        IMap<String, ImdbEntry> map = client.getMap(MAP_NAME);

        fileParsingStart = DateTime.now();
        readMoviesIntoMap(path, map);
        fileParsingEnd = DateTime.now();

        JobTracker tracker = client.getJobTracker(JOB_TRACKER_NAME);
        Job<String, ImdbEntry> job = tracker
                .newJob(KeyValueSource.fromMap(map));

        Query<?> query = getQuery(argumentParser, job);

        mapReduceJobStart = DateTime.now();
        query.evaluate(System.out);
        mapReduceJobEnd = DateTime.now();

        Logger logger = new Logger(System.out);
        logger.logTimestamp("File parsing start", fileParsingStart);
        logger.logTimestamp("File parsing end", fileParsingEnd);
        logger.logTimestamp("Map-reduce job start", mapReduceJobStart);
        logger.logTimestamp("Map-reduce job end", mapReduceJobEnd);
    }

    private static ArgumentParser parse(String[] args) {
        return ArgumentParser.builder().withMandatoryArgument(QUERY_N_PARAM_ID)
                .withMandatoryArgument(PATH_PARAM_ID)
                .withOptionalArgument(MIN_YEAR_PARAM_ID)
                .withOptionalArgument(ACTOR_LIMIT_PARAM_ID).parse(args);
    }

    private static Query<?> getQuery(ArgumentParser argumentParser,
            Job<String, ImdbEntry> job) {
        int queryN = argumentParser.getIntArgument(QUERY_N_PARAM_ID);

        Query<?> query = null;
        switch (queryN) {
        case 1:
            int limit = argumentParser.getIntArgument(ACTOR_LIMIT_PARAM_ID);
            query = new Query1(job, limit);
            break;
        case 2:
            int minYear = argumentParser.getIntArgument(MIN_YEAR_PARAM_ID);
            query = new Query2(job, minYear);
            break;
        case 3:
            query = new Query3(job);
            break;
        case 4:
            query = new Query4(job);
            break;
        }
        return query;
    }

    private static void readMoviesIntoMap(String path,
            IMap<String, ImdbEntry> map) {
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
