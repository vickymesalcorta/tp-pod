package ar.edu.itba.pod.query.query2;

import java.util.List;
import java.util.Map;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.Movie;
import ar.edu.itba.pod.query.Query;

public class Query2 extends Query<Map<Integer, List<Movie>>> {

    private int year;

    public Query2(Job<String, Movie> job, int year) {
        super(job);
        this.year = year;
    }

    @Override
    protected ICompletableFuture<Map<Integer, List<Movie>>> getFuture() {
        return getJob().mapper(new MapMoviesAfterYear(year)).reducer(new ReduceBestMetascore()).submit();
    }

}
