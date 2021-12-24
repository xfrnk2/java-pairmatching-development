package pairmatching;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	public void run() {
		while (true) {
			try {
				outputView.printMenus();
				String option = askOptionChoice();
				if (option.equals(MainCategory.EXIT.getCategory())) {
					break;
				}
				performOption(option);
			} catch (IllegalArgumentException e) {
				outputView.printError(e.getMessage());
			}
		}
	}

	private void performOption(String option) {
		if (option.equals(MainCategory.MATCH.getCategory())) {
			matchingController.match();
		} else if (option.equals(MainCategory.RETRIEVE.getCategory())) {
			matchingController.retrieve();
		} else if (option.equals(MainCategory.RESET.getCategory())) {
			matchingController.resetRecords();
		}
	}


	private String askOptionChoice() {
		List<MainCategory> mainCategoryList = Arrays.stream(MainCategory.values()).collect(Collectors.toList());
		return inputView.selectMainCategory(mainCategoryList);
	}
}
