package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;

import pairmatching.common.Course;
import pairmatching.common.Level;
import pairmatching.common.Mission;

public class Matching {
	private static final String ERROR = "[ERROR] ";
	private static final String ERROR_NOT_EXISTENT_COURSE = ERROR + "존재하지 않는 코스입니다.";
	private static final String ERROR_NOT_EXISTENT_LEVEL = ERROR + "존재하지 않는 레벨입니다.";
	private static final String ERROR_NOT_EXISTENT_MISSION = ERROR + "존재하지 않는 미션입니다.";
	private final String course;
	private final String level;
	private final String mission;

	public Matching(String course, String level, String mission) {
		validate(course, level, mission);
		this.course = course;
		this.level = level;
		this.mission = mission;
	}

	public String getCourse() {
		return course;
	}

	public String getLevel() {
		return level;
	}

	public String getMission() {
		return mission;
	}

	private void validate(final String course, final String level, final String mission) {
		if (!isValidCourse(course)) {
			throw new IllegalArgumentException(ERROR_NOT_EXISTENT_COURSE);
		} else if (!isValidLevel(level)) {
			throw new IllegalArgumentException(ERROR_NOT_EXISTENT_LEVEL);
		} else if (!isValidMission(mission)) {
			throw new IllegalArgumentException(ERROR_NOT_EXISTENT_MISSION);
		}
	}

	private boolean isValidCourse(final String course) {
		return Arrays.stream(Course.values()).map(Course::getCourse).anyMatch(course::equals);
	}

	private boolean isValidLevel(final String level) {
		return Arrays.stream(Level.values()).map(Level::getLevel).anyMatch(level::equals);
	}

	private boolean isValidMission(final String mission) {
		return Arrays.stream(Mission.values()).map(Mission::getMission).anyMatch(mission::equals);
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (this.getClass() != object.getClass()) {
			return false;
		}
		Matching matching = (Matching)object;
		return course.equals(matching.getCourse()) && level.equals(matching.getLevel()) && mission.equals(
			matching.getMission());
	}

	@Override
	public int hashCode() {
		return Objects.hash(course, level);
	}
}
