package ar.edu.itba.pod.query.query2;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.Movie;

public class MapMoviesAfterYear implements Mapper<String, Movie, Integer, Movie> {
    private static final long serialVersionUID = -2770978103406523861L;

    private int year = 2000;

    public MapMoviesAfterYear(int year) {
        this.year = year;
    }

    @Override
    public void map(String id, Movie movie, Context<Integer, Movie> context) {
        if (!movie.getType().equals("movie")) {
            return;
        }
        Integer movieYear = Integer.valueOf(movie.getYear());
        if (movieYear >= this.year) {
            context.emit(movieYear, movie);
        }
    }
}
