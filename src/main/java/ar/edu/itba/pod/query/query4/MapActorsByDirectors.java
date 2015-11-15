package ar.edu.itba.pod.query.query4;

import java.util.Arrays;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.ImdbEntry;

public class MapActorsByDirectors
        implements Mapper<String, ImdbEntry, String, String> {

    private static final long serialVersionUID = 1839804259264613924L;

    @Override
    public void map(String entryId, ImdbEntry entry,
            Context<String, String> context) {
        if (!entry.isMovie()) {
            return;
        }
        String[] actors = entry.getActors();
        String director = entry.getDirector();
        Arrays.stream(actors).forEach(actor -> context.emit(director, actor));
    }
}
