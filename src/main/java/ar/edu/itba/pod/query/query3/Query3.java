package ar.edu.itba.pod.query.query3;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.query.Query;

public class Query3 extends
        Query<List<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>>> {

    public Query3(Job<String, ImdbEntry> job) {
        super(job);
    }

    @Override
    protected ICompletableFuture<List<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>>> getFuture() {
        return getJob().mapper(new MapMoviesByDuo())
                .reducer(new ReduceMostPopularDuo())
                .submit(new CollateMostPopularDuos());
    }

    @Override
    protected void printResult(OutputStream os,
            List<Entry<SortedSet<String>, SortedSet<ImdbEntry>>> moviesByDuo)
                    throws IOException {
        for (Entry<SortedSet<String>, SortedSet<ImdbEntry>> entry : moviesByDuo) {
            SortedSet<String> duo = entry.getKey();
            SortedSet<ImdbEntry> movies = entry.getValue();
            String moviesByDuoString = "Duo: ["
                    + duo.stream().collect(Collectors.joining(", "))
                    + "], Movies: ["
                    + movies.stream().map(movie -> movie.getTitle())
                            .collect(Collectors.joining(", "))
                    + "]\n";
            os.write(moviesByDuoString.getBytes());
        }
    }
}
