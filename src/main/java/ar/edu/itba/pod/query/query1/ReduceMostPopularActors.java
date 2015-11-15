package ar.edu.itba.pod.query.query1;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ReduceMostPopularActors
        implements ReducerFactory<String, Integer, Integer> {

    private static final long serialVersionUID = -4426457058094255984L;

    @Override
    public Reducer<Integer, Integer> newReducer(String actor) {
        return new Reducer<Integer, Integer>() {
            int totalVotes = 0;

            @Override
            public void reduce(Integer vote) {
                totalVotes += vote;
            }

            @Override
            public Integer finalizeReduce() {
                return totalVotes;
            }
        };
    }
}
