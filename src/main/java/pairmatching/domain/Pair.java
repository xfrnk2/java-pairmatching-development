package pairmatching.domain;

import java.util.List;

public class Pair {
	private static final String DELIMITER = " : ";
	private final List<String> members;


	public Pair(List<String> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return String.join(DELIMITER, members);
	}
}
