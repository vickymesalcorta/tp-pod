package ar.edu.itba.pod.query.query1;

import java.util.Arrays;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.ImdbEntry;

public class MapVotesByActors
        implements Mapper<String, ImdbEntry, String, Integer> {

    private static final long serialVersionUID = -1047922788261365399L;

    @Override
    public void map(String id, ImdbEntry entry,
            Context<String, Integer> context) {
        if (!entry.isMovie()) {
            return;
        }
        Arrays.stream(entry.getActors())
                .forEach(actor -> context.emit(actor, entry.getImdbVotes()));
    }
}
