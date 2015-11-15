package ar.edu.itba.pod.query.query1;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hazelcast.mapreduce.Collator;

public class CollateMostPopularActors implements
        Collator<Map.Entry<String, Integer>, List<Map.Entry<String, Integer>>> {

    private final int limit;

    public CollateMostPopularActors(int limit) {
        this.limit = limit;
    }

    @Override
    public List<Map.Entry<String, Integer>> collate(
            Iterable<Entry<String, Integer>> values) {
        return StreamSupport.stream(values.spliterator(), false)
                .sorted((actorVotes1, actorVotes2) -> {
                    int comp = Integer.compare(actorVotes2.getValue(),
                            actorVotes1.getValue());
                    if (comp == 0) {
                        comp = actorVotes1.getKey()
                                .compareTo(actorVotes2.getKey());
                    }
                    return comp;
                }).limit(limit).collect(Collectors.toList());
    }
}
