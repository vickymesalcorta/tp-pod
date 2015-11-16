package ar.edu.itba.pod.query;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;

public abstract class Query<R> {
    private final Job<String, ImdbEntry> job;

    public Query(Job<String, ImdbEntry> job) {
        this.job = job;
    }

    public R evaluate(OutputStream os)
            throws InterruptedException, ExecutionException, IOException {
        R result = getFuture().get();
        printResult(os, result);
        return result;
    }

    protected abstract ICompletableFuture<R> getFuture();

    protected abstract void printResult(OutputStream os, R result)
            throws IOException;

    protected Job<String, ImdbEntry> getJob() {
        return job;
    }
}
