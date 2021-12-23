package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.common.Course;
import pairmatching.domain.Matching;
import pairmatching.domain.MatchingRecords;
import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;
import pairmatching.util.CrewNameReader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
	private static final int MATCHING_INFO_COURSE_INDEX = 0;
	private static final int MATCHING_INFO_LEVEL_INDEX = 1;
	private static final int MATCHING_INFO_MISSION_INDEX = 2;
	private static final String YES = "네";
	private static final String NO = "아니오";
	private static final String ERROR = "[ERROR] ";
	private static final String ERROR_INVALID_COURSE_NAME = ERROR + "존재하지 않는 과정명입니다.";
	private static final CrewNameReader crewNameReader = new CrewNameReader();
	private static final MatchingRecords matchingRecords = new MatchingRecords();
	private final InputView inputView;
	private final OutputView outputView;
	private final List<String> backendNameList;
	private final List<String> frontendNameList;

	public MatchingController(final InputView inputView, final OutputView outputView,
		final CrewNameReader crewnameReader) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.backendNameList = crewnameReader.initializeCrewNameList(Course.BACKEND.getPath());
		this.frontendNameList = crewnameReader.initializeCrewNameList(Course.FRONTEND.getPath());
	}

	public void retrieve () {
		outputView.printCoursesAndMissions();
		try {
			Matching matchingInfo = initializeMatchingInfo();
			Pairs pairs = matchingRecords.findByMatchingInfo(matchingInfo);
			outputView.printMatchingResult(pairs.findAll());
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			retrieve();
		}
	}

	public void match() {
		outputView.printCoursesAndMissions();
		try {
			Matching matchingInfo = initializeMatchingInfo();
			List<String> crewNameList = getRandomCrewNameListByCourse(matchingInfo.getCourse());
			if (matchingRecords.hasRecord(matchingInfo)) {
			   rematch(matchingInfo, crewNameList);
		}
			Pairs pairs = createPairs(crewNameList);
			outputView.printMatchingResult(pairs.findAll());
			matchingRecords.add(matchingInfo, pairs);
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
		}
	}

	private void rematch (Matching matchingInfo, List<String> crewNameList) {
	   String option = askRematchChoice();
	   if (option.equals(YES)) {
		   Pairs pairs = createPairs(crewNameList);
		   outputView.printMatchingResult(pairs.findAll());
		   matchingRecords.modifyRecord(matchingInfo, pairs);
	   } else if (option.equals(NO)) {
		   match();
	   }
	}

	private String askRematchChoice() {
		try {
			outputView.printSelectRematchOptionRequest();
			return inputView.selectRematch();
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			return askRematchChoice();
		}
	}

	private Pairs createPairs(List<String> crewNameList) {
		if (isDivisibleByTwo(crewNameList.size())) {
			return createEvenPairs(crewNameList);
		}
		return createOddPairs(crewNameList);
	}

	private Pairs createOddPairs(List<String> crewNameList) {
		Pairs pairs = new Pairs();
		for (int i = 0; i < crewNameList.size() - 1; i += 2) {
			List<String> tempNameList = new ArrayList<>();
			tempNameList.add(crewNameList.get(i));
			tempNameList.add(crewNameList.get(i + 1));
			if (i >= crewNameList.size() - 3) {
				tempNameList.add(crewNameList.get(i + 2));
			}
			Pair pair = new Pair(tempNameList);
			pairs.add(pair);
		}
		return pairs;
	}

	private Pairs createEvenPairs(List<String> crewNameList) {
		Pairs pairs = new Pairs();
		for (int i = 0; i < crewNameList.size() - 1; i += 2) {
			List<String> tempNameList = new ArrayList<>();
			tempNameList.add(crewNameList.get(i));
			tempNameList.add(crewNameList.get(i + 1));
			pairs.add(new Pair(tempNameList));
		}
		return pairs;
	}

	private boolean isDivisibleByTwo(int number) {
		return number % 2 == 0;
	}

	private Matching initializeMatchingInfo() {
		List<String> info = inputView.enterMatchingInfo();
		return new Matching(info.get(MATCHING_INFO_COURSE_INDEX),
			info.get(MATCHING_INFO_LEVEL_INDEX),
			info.get(MATCHING_INFO_MISSION_INDEX));
	}

	private List<String> getRandomCrewNameListByCourse(String course) {
		if (course.equals(Course.BACKEND.getCourse())) {
			return Randoms.shuffle(backendNameList);
		} else if (course.equals(Course.FRONTEND.getCourse())) {
			return Randoms.shuffle(frontendNameList);
		}
		throw new IllegalArgumentException(ERROR_INVALID_COURSE_NAME);
	}
}
