package pairmatching.view;

import java.util.ArrayList;
import java.util.List;

import pairmatching.common.AnswerType;
import pairmatching.common.Course;
import pairmatching.common.Level;
import pairmatching.common.MainCategory;
import pairmatching.common.Mission;
import pairmatching.domain.Pair;

public class OutputView {
	private static final String SELECT_MAIN_OPTION = "기능을 선택하세요.";
	private static final String CONNECTION = " - ";
	private static final String OUTLINE_START = "#############################################";
	private static final String OUTLINE_END = "############################################";
	private static final String ITEM_DELIMITER = " | ";
	private static final String ITEM_FORMAT = "   - ";
	private static final String SEMICOLON = ":";
	private static final String MISSION = "미션: ";
	private static final String COURSE = "과정: ";
	private static final String BLANK = " ";
	private static final String MATCHING_LIST = "페어 매칭 결과입니다.";
	private static final String RESET_COMPLETED = "초기화 되었습니다.";
	private static final String SELECT_REMATCH_OPTION = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
		+ AnswerType.YES.getAnswer() + ITEM_DELIMITER + AnswerType.NO.getAnswer();

	public void printError(String error) {
		System.out.println(error);
	}

	public void printMenus() {
		System.out.println(SELECT_MAIN_OPTION);
		for (MainCategory category : MainCategory.values()) {
			System.out.println(category.getCategory() + CONNECTION + category.getDescription());
		}
	}

	public void printCoursesAndMissions() {
		System.out.println(OUTLINE_START);
		printCourses();
		System.out.println(MISSION);
		printLevels();
		System.out.println(OUTLINE_END);
	}

	private void printCourses() {
		System.out.print(COURSE);
		List<String> courses = new ArrayList<>();
		for (Course course : Course.values()) {
			courses.add(course.getCourse());
		}
		System.out.println(String.join(ITEM_DELIMITER, courses));
	}

	private void printLevels() {
		for (Level level : Level.values()) {
			System.out.print(ITEM_FORMAT + level.getLevel() + SEMICOLON + BLANK);
			printMissions(level.getLevel());
		}

	}

	private void printMissions(String level) {
		List<String> missions = new ArrayList<>();
		for (Mission mission : Mission.values()) {
			if (level.equals(mission.getLevel())) {
				missions.add(mission.getMission());
			}
		}
		System.out.println(String.join(ITEM_DELIMITER, missions));
	}

	public void printMatchingResult(List<Pair> pairList) {
		System.out.println(MATCHING_LIST);
		for (Pair pair : pairList) {
			System.out.println(pair);
		}
	}

	public void printSelectRematchOptionRequest() {
		System.out.println(SELECT_REMATCH_OPTION);
	}

	public void printResetCompletedMessage () {
		System.out.println(RESET_COMPLETED);
	}
}
