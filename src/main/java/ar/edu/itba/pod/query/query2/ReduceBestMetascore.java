package ar.edu.itba.pod.query.query2;

import java.util.ArrayList;
import java.util.List;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import ar.edu.itba.pod.model.Movie;

public class ReduceBestMetascore implements ReducerFactory<Integer, Movie, List<Movie>> {
    private static final long serialVersionUID = -7396379425201972905L;

    @Override
    public Reducer<Movie, List<Movie>> newReducer(Integer year) {
        return new Reducer<Movie, List<Movie>>() {

            private int bestMetascore = 0;
            List<Movie> movies = new ArrayList<Movie>();

            @Override
            public void reduce(Movie movie) {
                int metascore = Integer.valueOf(movie.getMetascore());
                if (metascore > bestMetascore) {
                    bestMetascore = metascore;
                    movies = new ArrayList<Movie>();
                }
                if (metascore >= bestMetascore) {
                    movies.add(movie);
                }
            }

            @Override
            public List<Movie> finalizeReduce() {
                return movies;
            }
        };
    }
}
