package uch2ha;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uch2ha.model.GeneratorConfig;
import uch2ha.service.BaseWordService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class.getName());

	private static final Instant initTime = Instant.now();

	// config with default values
	private static String configPathString;
	private static String outputFolderPathString;
	private static Path outputFolderPath;
	private static boolean isCalculationMode;
	private static boolean isLogDebugRAM;
	private static long maxFileSizeInGb;

	private static long totalRuWordsAmountForCalculation = 0;
	private static long totalEnWordsAmountForCalculation = 0;

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			setupDefaultParameters();
			logger.info("No command-line arguments provided. Using default parameters.");
		} else {
			parseCliArgs(args);
		}

		outputFolderPath = Files.createDirectories(Path.of(outputFolderPathString));

		// Parse config from JSON
		ObjectMapper mapper = new ObjectMapper();
		GeneratorConfig config = mapper.readValue(new File(configPathString), GeneratorConfig.class);

		logger.info("Starting word generator for config {} (save words: {})...", config.getName(),
				!isCalculationMode);

		DependencyInjectionContainer container = new DependencyInjectionContainer();
		BaseWordService baseWordService = container.getEntryPoint();
		baseWordService.generateAndSave(config);

		logger.info("Generation complete! Took: {}s", Duration.between(initTime, Instant.now()).toSeconds());
		logCalculationStats();
		System.exit(0);
	}

	private static void setupDefaultParameters() {
		configPathString = "./configs/example.json";
		outputFolderPathString = "./word-generator-output";
		maxFileSizeInGb = 50L;
		isCalculationMode = false;
		isLogDebugRAM = false;
	}

	private static void parseCliArgs(String[] args) {
		if (args.length != 5) {
			logger.error(
					"Usage: java -jar <jar_file> <config.json path> <output-folder> <maxFileSizeInGb> <isCalculationMode> <isLogDebugRAM>");
			System.exit(1);
		}
		configPathString = args[0];
		outputFolderPathString = args[1];
		maxFileSizeInGb = Long.parseLong(args[2]);
		isCalculationMode = Boolean.parseBoolean(args[3]);
		isLogDebugRAM = Boolean.parseBoolean(args[4]);
		outputFolderPath = Path.of(outputFolderPathString);
	}

	private static void logCalculationStats() {
		long totalWords = totalEnWordsAmountForCalculation + totalRuWordsAmountForCalculation;

		logger.info("CALC STATS | Total: {} words | EN: {} | RU: {}",
				totalWords,
				totalEnWordsAmountForCalculation,
				totalRuWordsAmountForCalculation);
	}

	// GETTERS and SETTERS

	public static Instant getInitTime() {
		return initTime;
	}

	public static Path getOutputFolderPath() {
		return outputFolderPath;
	}

	public static long getMaxFileSizeInBytes() {
		// convert to bytes
		return maxFileSizeInGb * 1024 * 1024 * 1024;
	}

	public static boolean isIsLogDebugRAM() {
		return isLogDebugRAM;
	}

	public static boolean isIsCalculationMode() {
		return isCalculationMode;
	}

	public static long getTotalRuWordsAmountForCalculation() {
		return totalRuWordsAmountForCalculation;
	}

	public static void setTotalRuWordsAmountForCalculation(long totalRuWordsAmountForCalculation) {
		Main.totalRuWordsAmountForCalculation = totalRuWordsAmountForCalculation;
	}

	public static long getTotalEnWordsAmountForCalculation() {
		return totalEnWordsAmountForCalculation;
	}

	public static void setTotalEnWordsAmountForCalculation(long totalEnWordsAmountForCalculation) {
		Main.totalEnWordsAmountForCalculation = totalEnWordsAmountForCalculation;
	}

}