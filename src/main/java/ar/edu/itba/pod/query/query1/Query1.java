package ar.edu.itba.pod.query.query1;

import java.util.List;
import java.util.Map;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.Movie;
import ar.edu.itba.pod.query.Query;

public class Query1 extends Query<List<Map.Entry<String, Integer>>> {
    private int n;

    public Query1(Job<String, Movie> job, int n) {
        super(job);
        this.n = n;
    }

    @Override
    protected ICompletableFuture<List<Map.Entry<String, Integer>>> getFuture() {
        return getJob().mapper(new MapActorsByVotes()).reducer(new ReduceMostPopularActors())
                .submit(new CollateNMostPopularActors(n));
    }

}
