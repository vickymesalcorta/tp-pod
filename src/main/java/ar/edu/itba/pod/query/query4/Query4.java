package ar.edu.itba.pod.query.query4;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.query.Query;

public class Query4 extends Query<Map<String, List<String>>> {
    public Query4(Job<String, ImdbEntry> job) {
        super(job);
    }

    @Override
    protected ICompletableFuture<Map<String, List<String>>> getFuture() {
        return getJob().mapper(new MapActorsByDirectors())
                .reducer(new ReduceFetishActorsByDirectors()).submit();
    }

    @Override
    protected void printResult(OutputStream os,
            Map<String, List<String>> fetishActorsByDirector)
                    throws IOException {
        for (Entry<String, List<String>> entry : fetishActorsByDirector
                .entrySet()) {
            String director = entry.getKey();
            List<String> fetishActors = entry.getValue();

            String fetishActorsForDirectorString = "Director: " + director
                    + ", Actors: ["
                    + fetishActors.stream().collect(Collectors.joining(", ")) + "]\n";

            os.write(fetishActorsForDirectorString.getBytes());
        }
    }
}
