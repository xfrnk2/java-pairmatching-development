package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pairs {
	private List<Pair> pairs = new ArrayList<>();

	public Pairs() {
	}

	public void add(Pair pair) {
		pairs.add(pair);
	}

	public List<Pair> findAll() {
		return Collections.unmodifiableList(pairs);
	}
}
