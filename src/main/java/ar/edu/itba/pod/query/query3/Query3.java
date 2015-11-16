package ar.edu.itba.pod.query.query3;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;

import ar.edu.itba.pod.model.ImdbEntry;
import ar.edu.itba.pod.query.Query;

public class Query3 extends Query<List<Map.Entry<SortedSet<String>, SortedSet<String>>>> {

	public Query3(Job<String, ImdbEntry> job) {
		super(job);
	}

	@Override
	protected ICompletableFuture<List<Map.Entry<SortedSet<String>, SortedSet<String>>>> getFuture() {
		return getJob().mapper(new MapMoviesByDuo()).reducer(new ReduceMostPopularDuo())
				.submit(new CollateMostPopularDuos());
	}

	@Override
	protected void printResult(OutputStream os, List<Entry<SortedSet<String>, SortedSet<String>>> movieTitlesByDuo)
			throws IOException {
		for (Entry<SortedSet<String>, SortedSet<String>> entry : movieTitlesByDuo) {
			SortedSet<String> duo = entry.getKey();
			SortedSet<String> movies = entry.getValue();

			String duoString = duo.stream().collect(Collectors.joining(", "));
			String titlesString = movies.stream().collect(Collectors.joining(", "));

			String moviesByDuoString = "Duo: [" + duoString + "], Movies: [" + titlesString + "]\n";
			os.write(moviesByDuoString.getBytes());
		}
	}
}
