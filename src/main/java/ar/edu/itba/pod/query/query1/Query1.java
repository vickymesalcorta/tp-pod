package ar.edu.itba.pod.query.query1;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.query.Query;

public class Query1 extends Query<List<Entry<String, Integer>>> {
    private final int limit;

    public Query1(Job<String, ImdbEntry> job, int limit) {
        super(job);
        this.limit = limit;
    }

    @Override
    protected ICompletableFuture<List<Entry<String, Integer>>> getFuture() {
        return getJob().mapper(new MapVotesByActors())
                .reducer(new ReduceMostPopularActors())
                .submit(new CollateMostPopularActors(limit));
    }

    @Override
    protected void printResult(OutputStream os,
            List<Entry<String, Integer>> mostPopularActorsByVotes)
                    throws IOException {
        for (Entry<String, Integer> entry : mostPopularActorsByVotes) {
            String actor = entry.getKey();
            Integer votes = entry.getValue();
            String actorAndVotesString = "Actor: " + actor + ", Votes: " + votes + "\n";
            os.write(actorAndVotesString.getBytes());
        }
    }
}
