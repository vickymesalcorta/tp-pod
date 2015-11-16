package ar.edu.itba.pod.query.query3;

import java.io.Serializable;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import ar.edu.itba.pod.model.ImdbEntry;

public class ReduceMostPopularDuo implements ReducerFactory<SortedSet<String>, ImdbEntry, SortedSet<ImdbEntry>> {

    private static final long serialVersionUID = 8060612431925507658L;

    @Override
    public Reducer<ImdbEntry, SortedSet<ImdbEntry>> newReducer(SortedSet<String> key) {
        return new Reducer<ImdbEntry, SortedSet<ImdbEntry>>(){
            
            private SortedSet<ImdbEntry> movies = new TreeSet<ImdbEntry>(new MoviesByTitleComparator());
            
            @Override
            public void reduce(ImdbEntry movie) {
                movies.add(movie);
            }

            @Override
            public SortedSet<ImdbEntry> finalizeReduce() {
                return movies;
            }
            
        };
    }
    
    public static class MoviesByTitleComparator implements Comparator<ImdbEntry>, Serializable{

        private static final long serialVersionUID = 1L;

        @Override
        public int compare(ImdbEntry o1, ImdbEntry o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
        
    }
}
