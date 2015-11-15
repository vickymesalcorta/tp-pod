package ar.edu.itba.pod.query.query4;

import java.util.Arrays;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.Movie;

public class MapDirectorsByActors
        implements Mapper<String, Movie, String, String> {

    private static final long serialVersionUID = 1839804259264613924L;

    @Override
    public void map(String movieId, Movie movie,
            Context<String, String> context) {
        if (!movie.isMovie()) {
            return;
        }
        String[] actors = movie.getActorsArray();
        String director = movie.getDirector();
        Arrays.stream(actors).forEach(actor -> context.emit(director, actor));
    }
}
