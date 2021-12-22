package pairmatching.common;

public enum AnswerType {
	YES("네"),
	NO("아니오");

	private final String answer;

	AnswerType(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}
}
