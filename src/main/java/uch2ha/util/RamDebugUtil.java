package uch2ha.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uch2ha.Main;

public class RamDebugUtil {

	private static final Logger logger = LogManager.getLogger(RamDebugUtil.class.getName());

	private static final long MB = (long) 1024 * 1024;

	private RamDebugUtil() {
	}

	public static void log() {
		Runtime runtime = Runtime.getRuntime();

		long totalMemory = runtime.totalMemory();     // total memory currently in use by JVM
		long freeMemory = runtime.freeMemory();       // free memory within that total
		long usedMemory = totalMemory - freeMemory;   // actual memory your app is using
		long maxMemory = runtime.maxMemory();         // max memory JVM will ever use

		if (Main.isLogDebugRAM) {
			logger.info("Memory [Used: {} MB, Free: {} MB, Allocated: {} MB, Max: {} MB]",
					usedMemory / MB, freeMemory / MB, totalMemory / MB, maxMemory / MB);
		}
	}
}
