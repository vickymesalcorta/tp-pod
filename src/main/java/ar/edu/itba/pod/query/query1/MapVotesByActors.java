package ar.edu.itba.pod.query.query1;

import java.util.Arrays;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import ar.edu.itba.pod.model.Movie;

public class MapVotesByActors
        implements Mapper<String, Movie, String, Integer> {

    private static final long serialVersionUID = -1047922788261365399L;

    @Override
    public void map(String id, Movie movie, Context<String, Integer> context) {
        if (!movie.isMovie()) {
            return;
        }
        Arrays.stream(movie.getActorsArray())
                .forEach(actor -> context.emit(actor, movie.getImdbVotes()));
    }
}
