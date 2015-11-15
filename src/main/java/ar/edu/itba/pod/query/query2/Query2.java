package ar.edu.itba.pod.query.query2;

import java.util.List;
import java.util.Map;

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
}
