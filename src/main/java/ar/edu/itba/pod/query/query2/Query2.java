package ar.edu.itba.pod.query.query2;

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

public class Query2 extends Query<Map<Integer, List<ImdbEntry>>> {
    private final int minYear;

    public Query2(Job<String, ImdbEntry> job, int minYear) {
        super(job);
        this.minYear = minYear;
    }

    @Override
    protected ICompletableFuture<Map<Integer, List<ImdbEntry>>> getFuture() {
        return getJob().mapper(new MapMoviesAfterYear(minYear))
                .reducer(new ReduceBestMetascore()).submit();
    }

    @Override
    protected void printResult(OutputStream os,
            Map<Integer, List<ImdbEntry>> moviesByYear) throws IOException {
        for (Entry<Integer, List<ImdbEntry>> entry : moviesByYear.entrySet()) {
            int year = entry.getKey();
            List<ImdbEntry> moviesForYear = entry.getValue();

            String bestMetascoresString = "Year: " + year + ", Titles: ["
                    + moviesForYear.stream()
                            .map(imdbEntry -> imdbEntry.getTitle())
                            .collect(Collectors.joining(", "))
                    + "]\n";
            os.write(bestMetascoresString.getBytes());
        }
    }
}
