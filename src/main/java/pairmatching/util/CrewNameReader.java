package pairmatching.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrewNameReader {
	private static final String ERROR = "[ERROR] ";
	private static final String ERROR_INVALID_LOCATION = ERROR +
		"크루 명단 파일의 경로가 잘못되었습니다.";

	public List<String> initializeCrewNameList(String path) {
		return getCrewNamesByPath(path);
	}

	private List<String> getCrewNamesByPath(String path) {
		try {
			return CrewNameReader.readFileByLine(path);
		} catch (IOException e) {
			throw new IllegalArgumentException(ERROR_INVALID_LOCATION);
		}
	}

	private static List<String> readFileByLine(String path) throws IOException {
		List<String> textList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			textList.add(line);
		}
		br.close();
		return textList;
	}
}
