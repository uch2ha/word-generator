package uch2ha.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtil {

	public static String getTimestamp() {
		return LocalDateTime.now()
				.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
				.replace(":", "-");
	}
}
