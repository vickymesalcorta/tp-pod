package ar.edu.itba.pod.query.query4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ReduceFetishActorsByDirector implements ReducerFactory<String, String, List<String>> {

    private static final long serialVersionUID = 7278272641482253338L;

    @Override
    public Reducer<String, List<String>> newReducer(String key) {
        return new Reducer<String, List<String>>() {
            private Map<String, Integer> appearances = new HashMap<String, Integer>();
            private int maxCount = 0;

            @Override
            public void reduce(String actor) {
                int appearanceCount = appearances.getOrDefault(actor, 0);
                appearances.put(actor, ++appearanceCount);
                maxCount = Math.max(maxCount, appearanceCount);
            }

            @Override
            public List<String> finalizeReduce() {
                return appearances.entrySet().stream().filter(entry -> entry.getValue() == maxCount)
                        .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
            }
        };
    }
}
