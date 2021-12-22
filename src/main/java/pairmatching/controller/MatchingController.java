package pairmatching.controller;

import java.util.List;

import pairmatching.common.Course;
import pairmatching.util.CrewNameReader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
	private static final CrewNameReader crewNameReader = new CrewNameReader();
	private final InputView inputView;
	private final OutputView outputView;
	private final List<String> backendNameList;
	private final List<String> frontendNameList;

	public MatchingController(final InputView inputView, final OutputView outputView, final CrewNameReader crewnameReader) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.backendNameList = crewnameReader.initializeCrewNameList(Course.BACKEND.getPath());
		this.frontendNameList = crewnameReader.initializeCrewNameList(Course.FRONTEND.getPath());
	}

	public void run() {
		outputView.printCoursesAndMissions();
		System.out.println(backendNameList);
	}

}
