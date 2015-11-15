package ar.edu.itba.pod.query.query1;

import java.util.List;
import java.util.Map;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.query.Query;

public class Query1 extends Query<List<Map.Entry<String, Integer>>> {
    private final int limit;

    public Query1(Job<String, ImdbEntry> job, int limit) {
        super(job);
        this.limit = limit;
    }

    @Override
    protected ICompletableFuture<List<Map.Entry<String, Integer>>> getFuture() {
        return getJob().mapper(new MapVotesByActors())
                .reducer(new ReduceMostPopularActors())
                .submit(new CollateMostPopularActors(limit));
    }
}
