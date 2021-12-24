package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;

public class MatchingRecords {
	private static final String ERROR = "[ERROR] ";
	private static final String ERROR_NOT_EXISTENT_MATCHING = ERROR + "존재하지 않는 기록입니다.";

	private Map<Matching, Pairs> matchingRecords = new HashMap<>();

	public void add(final Matching matching, final Pairs pairs) {
		matchingRecords.put(matching, pairs);
	}

	public Pairs findByMatchingInfo (final Matching matchingInfo) {
		if (!matchingRecords.containsKey(matchingInfo)) {
			throw new IllegalArgumentException(ERROR_NOT_EXISTENT_MATCHING);
		}
		return matchingRecords.get(matchingInfo);
	}

	public boolean hasRecord(final Matching matching) {
		System.out.println(matchingRecords.containsKey(matching));
		return matchingRecords.containsKey(matching);
	}

	public void modifyRecord(final Matching matching, final Pairs pairs) {
		matchingRecords.put(matching, pairs);
	}

	public void reset() {
		matchingRecords = new HashMap<>();
	}
}
