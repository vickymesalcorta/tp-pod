package ar.edu.itba.pod.query.query2;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.ImdbEntry;

public class MapMoviesAfterYear
        implements Mapper<String, ImdbEntry, Integer, ImdbEntry> {

    private static final long serialVersionUID = -2770978103406523861L;

    private final int year;

    public MapMoviesAfterYear(int year) {
        this.year = year;
    }

    @Override
    public void map(String id, ImdbEntry entry,
            Context<Integer, ImdbEntry> context) {
        if (!entry.isMovie()) {
            return;
        }
        if (entry.getYear() >= this.year) {
            context.emit(entry.getYear(), entry);
        }
    }
}
