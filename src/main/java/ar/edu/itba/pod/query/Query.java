package ar.edu.itba.pod.query;

import java.util.concurrent.ExecutionException;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;

public abstract class Query<R> {
    private Job<String, ImdbEntry> job;

    public Query(Job<String, ImdbEntry> job) {
        this.job = job;
    }

    public R evaluate() throws InterruptedException, ExecutionException {
        ICompletableFuture<R> future = getFuture();
        R result = future.get();
        return result;
    }

    protected abstract ICompletableFuture<R> getFuture();

    protected Job<String, ImdbEntry> getJob() {
        return job;
    }
}
