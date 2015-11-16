package ar.edu.itba.pod.query.query3;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hazelcast.mapreduce.Collator;

import ar.edu.itba.pod.model.ImdbEntry;

public class CollateMostPopularDuos implements
        Collator<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>, List<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>>> {

    @Override
    public List<Map.Entry<SortedSet<String>, SortedSet<ImdbEntry>>> collate(
            Iterable<Entry<SortedSet<String>, SortedSet<ImdbEntry>>> values) {
        return StreamSupport.stream(values.spliterator(), false).sorted((entry1, entry2) -> {
            int comp = Integer.compare(entry2.getValue().size(), entry1.getValue().size());
            if (comp == 0) {
                comp = entry1.getKey().first().compareTo(entry2.getKey().first());
                if (comp == 0) {
                    comp = entry1.getKey().last().compareTo(entry2.getKey().last());
                }
            }
            return comp;
        }).collect(Collectors.toList());
    }
}
