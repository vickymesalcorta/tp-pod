package ar.edu.itba.pod.query.query4;

import java.util.Arrays;
import java.util.regex.Pattern;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.Movie;

public class MapDirectorsByActors implements Mapper<String, Movie, String, String> {

    private static final long serialVersionUID = 1839804259264613924L;

    private static Pattern PATTERN = Pattern.compile(",[ ]?");
    
    @Override
    public void map(String movieId, Movie movie, Context<String, String> context) {
        if (!movie.getType().equals("movie")) {
            return;
        }
        String[] actors = PATTERN.split(movie.getActors());
        String director = movie.getDirector();
        System.out.println(director);
        Arrays.stream(actors)
                .forEach(actor -> context.emit(director, actor));
    }

}
