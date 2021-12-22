package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.common.AnswerType;
import pairmatching.common.MainCategory;

public class InputView {

	private static final int MATCHING_INFO_NUMBER_OF_TYPES = 3;
	private static final String COMMA = ",";
	private static final String ERROR = "[ERROR] ";
	private static final String ERROR_INVALID_OPTION = ERROR + "존재하지 않는 명령입니다.";
	private static final String ERROR_INVALID_NUMBER_OF_TYPES = ERROR + "과정, 레벨, 미션 단위로 입력해야 합니다.";
	private static final String SELECT_MATCHING_INFO_REQUEST = "과정, 레벨, 미션을 선택하세요.\n"
		+ "ex) 백엔드, 레벨1, 자동차경주";

	public String selectMainCategory(List<MainCategory> mainCategoryList) {
		List<String> mainCategoryMethods = mainCategoryList.stream()
			.map(MainCategory::getCategory)
			.collect(Collectors.toList());
		String option = Console.readLine();
		if (!mainCategoryMethods.contains(option)) {
			throw new IllegalArgumentException(ERROR_INVALID_OPTION);
		}
		return option;
	}

	public List<String> enterMatchingInfo() {
		System.out.println(SELECT_MATCHING_INFO_REQUEST);
		List<String> info = Splitter.on(COMMA)
			.trimResults()
			.omitEmptyStrings()
			.splitToList(Console.readLine());
		if (info.size() != MATCHING_INFO_NUMBER_OF_TYPES) {
			throw new IllegalArgumentException(ERROR_INVALID_NUMBER_OF_TYPES);
		}
		return info;
	}

	public String selectRematch() {
		String option = Console.readLine();
		if (Arrays.stream(AnswerType.values()).map(AnswerType::getAnswer).noneMatch(option::equals)) {
			throw new IllegalArgumentException(ERROR_INVALID_OPTION);
		}
		return option;
	}
}