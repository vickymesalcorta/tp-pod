package ar.edu.itba.pod.query.query2;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.Movie;

public class MapMoviesAfterYear
        implements Mapper<String, Movie, Integer, Movie> {

    private static final long serialVersionUID = -2770978103406523861L;

    private final int year;

    public MapMoviesAfterYear(int year) {
        this.year = year;
    }

    @Override
    public void map(String id, Movie movie, Context<Integer, Movie> context) {
        if (!movie.isMovie()) {
            return;
        }
        if (movie.getYear() >= this.year) {
            context.emit(movie.getYear(), movie);
        }
    }
}
