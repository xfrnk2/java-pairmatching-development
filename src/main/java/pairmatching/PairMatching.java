package pairmatching;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.common.Course;
import pairmatching.common.MainCategory;
import pairmatching.controller.MatchingController;
import pairmatching.util.CrewNameReader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatching {
	private static final OutputView outputView = new OutputView();
	private static final InputView inputView = new InputView();
	private static final CrewNameReader crewnameReader = new CrewNameReader();
	private static final MatchingController matchingController = new MatchingController(inputView, outputView, crewnameReader);

	private static final String MATCH = "1";
	private static final String RETRIEVE = "2";
	private static final String RESET = "3";


	public void run() {
		while (true) {
			try {
				outputView.printMenus();
				String option = askOptionChoice();
				if (option.equals(MATCH)) {
					matchingController.match();
				} else if (option.equals(RETRIEVE)) {
					matchingController.retrieve();
				} else if (option.equals(RESET)) {
					matchingController.resetRecords();
				}
			} catch (IllegalArgumentException e) {
				outputView.printError(e.getMessage());
			}
		}
	}

	private String askOptionChoice() {
		List<MainCategory> mainCategoryList = Arrays.stream(MainCategory.values()).collect(Collectors.toList());
		return inputView.selectMainCategory(mainCategoryList);
	}
}
