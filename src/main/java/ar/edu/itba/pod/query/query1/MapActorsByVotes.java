package ar.edu.itba.pod.query.query1;

import java.util.Arrays;
import java.util.regex.Pattern;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.Movie;

public class MapActorsByVotes implements Mapper<String, Movie, String, Integer> {
    private static final long serialVersionUID = -1047922788261365399L;

    private static Pattern PATTERN = Pattern.compile(",[ ]?");

    @Override
    public void map(String id, Movie movie, Context<String, Integer> context) {
        if (!movie.getType().equals("movie")) {
            return;
        }
        String[] actors = PATTERN.split(movie.getActors());
        Arrays.stream(actors)
                .forEach(actor -> context.emit(actor, Integer.valueOf(movie.getImdbVotes().replace(",", ""))));
    }
}
