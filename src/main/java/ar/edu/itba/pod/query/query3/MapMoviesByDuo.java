package ar.edu.itba.pod.query.query3;

import java.util.SortedSet;
import java.util.TreeSet;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.ImdbEntry;

public class MapMoviesByDuo implements Mapper<String, ImdbEntry, SortedSet<String>, String> {

    private static final long serialVersionUID = 1153946250047650634L;

    @Override
    public void map(String id, ImdbEntry imdbEntry, Context<SortedSet<String>, String> context) {
        if (!imdbEntry.isMovie()) {
            return;
        }
        for (int i = 0; i < imdbEntry.getActors().length - 1; i++) {
            for (int j = i + 1; j < imdbEntry.getActors().length; j++) {
                SortedSet<String> duo = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
                duo.add(imdbEntry.getActors()[i]);
                duo.add(imdbEntry.getActors()[j]);
                context.emit(duo, imdbEntry.getTitle());
            }
        }
    }

}
