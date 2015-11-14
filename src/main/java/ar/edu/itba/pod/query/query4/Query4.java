package ar.edu.itba.pod.query.query4;

import java.util.List;
import java.util.Map;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.Movie;
import ar.edu.itba.pod.query.Query;

public class Query4 extends Query<Map<String, List<String>>> {

    public Query4(Job<String, Movie> job) {
        super(job);
    }

    @Override
    protected ICompletableFuture<Map<String, List<String>>> getFuture() {
        return getJob().mapper(new MapDirectorsByActors()).reducer(new ReduceFetishActorsByDirector()).submit();
    }

}
