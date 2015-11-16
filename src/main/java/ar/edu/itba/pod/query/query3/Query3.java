package ar.edu.itba.pod.query.query3;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.query.Query;

public class Query3 extends Query<List<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>>> {

    public Query3(Job<String, ImdbEntry> job) {
        super(job);
    }

    @Override
    protected ICompletableFuture<List<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>>> getFuture() {
        return getJob().mapper(new MapMoviesByDuo()).reducer(new ReduceMostPopularDuo())
                .submit(new CollateMostPopularDuos());
    }

}
