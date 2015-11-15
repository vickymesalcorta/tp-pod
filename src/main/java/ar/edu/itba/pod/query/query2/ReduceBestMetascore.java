package ar.edu.itba.pod.query.query2;

import java.util.ArrayList;
import java.util.List;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import ar.edu.itba.pod.model.ImdbEntry;

public class ReduceBestMetascore
        implements ReducerFactory<Integer, ImdbEntry, List<ImdbEntry>> {

    private static final long serialVersionUID = -7396379425201972905L;

    @Override
    public Reducer<ImdbEntry, List<ImdbEntry>> newReducer(Integer year) {
        return new Reducer<ImdbEntry, List<ImdbEntry>>() {

            private int bestMetascore = 0;
            List<ImdbEntry> movies = new ArrayList<ImdbEntry>();

            @Override
            public void reduce(ImdbEntry movie) {
                int metascore = movie.getMetascore();
                if (metascore > bestMetascore) {
                    bestMetascore = metascore;
                    movies = new ArrayList<ImdbEntry>();
                }
                if (metascore >= bestMetascore) {
                    movies.add(movie);
                }
            }

            @Override
            public List<ImdbEntry> finalizeReduce() {
                return movies;
            }
        };
    }
}
