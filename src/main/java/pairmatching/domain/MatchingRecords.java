package pairmatching.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MatchingRecords {
	private Map<Matching, Pairs> matchingRecords = new HashMap<>();

	public void add(final Matching matching, final Pairs pairs) {
		matchingRecords.put(matching, pairs);
	}

	public Map<Matching, Pairs> findAll() {
		return Collections.unmodifiableMap(matchingRecords);
	}

	public boolean hasRecord(Matching matching) {
		System.out.println(matchingRecords.containsKey(matching));
		return matchingRecords.containsKey(matching);
	}

	public void modifyRecord(Matching matching, Pairs pairs) {
		matchingRecords.put(matching, pairs);
	}
}
