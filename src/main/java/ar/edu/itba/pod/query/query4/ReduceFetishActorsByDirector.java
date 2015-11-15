package ar.edu.itba.pod.query.query4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ReduceFetishActorsByDirector
        implements ReducerFactory<String, String, List<String>> {

    private static final long serialVersionUID = 7278272641482253338L;

    @Override
    public Reducer<String, List<String>> newReducer(String key) {
        return new Reducer<String, List<String>>() {
            private Map<String, Integer> appearancesByActor = new HashMap<String, Integer>();
            private int maxAppearances = 0;

            @Override
            public void reduce(String actor) {
                int appearances = appearancesByActor.getOrDefault(actor, 0);
                appearancesByActor.put(actor, ++appearances);
                maxAppearances = Math.max(maxAppearances, appearances);
            }

            @Override
            public List<String> finalizeReduce() {
                return appearancesByActor.entrySet().stream()
                        .filter(entry -> entry.getValue() == maxAppearances)
                        .sorted((entry1, entry2) -> entry1.getKey()
                                .compareTo(entry2.getKey()))
                        .map(Map.Entry::getKey).collect(Collectors.toList());
            }
        };
    }
}
