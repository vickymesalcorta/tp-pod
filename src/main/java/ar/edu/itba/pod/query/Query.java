package ar.edu.itba.pod.query;

import java.util.concurrent.ExecutionException;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.Movie;

public abstract class Query<R> {
    private Job<String, Movie> job;

    public Query(Job<String, Movie> job) {
        this.job = job;
    }

    public R evaluate() throws InterruptedException, ExecutionException {
        ICompletableFuture<R> future = getFuture();
        R result = future.get();
        return result;
    }

    protected abstract ICompletableFuture<R> getFuture();

    protected Job<String, Movie> getJob() {
        return job;
    }
}
