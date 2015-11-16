package ar.edu.itba.pod.query.query3;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hazelcast.mapreduce.Collator;

public class CollateMostPopularDuos implements
		Collator<Map.Entry<SortedSet<String>, SortedSet<String>>, List<Map.Entry<SortedSet<String>, SortedSet<String>>>> {

	@Override
	public List<Map.Entry<SortedSet<String>, SortedSet<String>>> collate(
			Iterable<Entry<SortedSet<String>, SortedSet<String>>> values) {
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
