package ar.edu.itba.pod.query.query1;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hazelcast.mapreduce.Collator;

public class CollateNMostPopularActors
        implements Collator<Map.Entry<String, Integer>, List<Map.Entry<String, Integer>>> {
    private int n;

    public CollateNMostPopularActors(int n) {
        this.n = n;
    }

    @Override
    public List<Map.Entry<String, Integer>> collate(Iterable<Entry<String, Integer>> values) {
        return StreamSupport.stream(values.spliterator(), false).sorted((actorVotes1, actorVotes2) -> {
            int comp = Integer.compare(actorVotes2.getValue(), actorVotes1.getValue());
            if (comp == 0) {
                comp = actorVotes1.getKey().compareTo(actorVotes2.getKey());
            }
            return comp;
        }).limit(n).collect(Collectors.toList());
    }
}
