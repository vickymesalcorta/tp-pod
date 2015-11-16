package ar.edu.itba.pod.query.query3;

import java.util.SortedSet;
import java.util.TreeSet;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class ReduceMostPopularDuo implements ReducerFactory<SortedSet<String>, String, SortedSet<String>> {

	private static final long serialVersionUID = 8060612431925507658L;

	@Override
	public Reducer<String, SortedSet<String>> newReducer(SortedSet<String> key) {
		return new Reducer<String, SortedSet<String>>() {
			private SortedSet<String> movieTitles = new TreeSet<String>();

			@Override
			public void reduce(String movieTitle) {
				movieTitles.add(movieTitle);
			}

			@Override
			public SortedSet<String> finalizeReduce() {
				return movieTitles;
			}
		};
	}
}
