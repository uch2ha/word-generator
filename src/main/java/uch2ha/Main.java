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

	public static Instant initTime = Instant.now();
	public static Path outputFolderPath;
	public static boolean isCalculationMode = true;
	public static boolean isLogDebugRAM = false;

	private static long totalRuWordsAmountForCalculation = 0;
	private static long totalEnWordsAmountForCalculation = 0;

	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			// Default for testing
			args = new String[]{"myConfigs/example.json", "output"};
		}

		if (args.length < 2) {
			logger.error("Usage: java WordGenerator <config.json> <output-folder>");
			System.exit(1);
		}

		String configPath = args[0];
		String outputFolder = args[1];

		outputFolderPath = Files.createDirectories(Path.of(outputFolder));

		// Parse config from JSON
		ObjectMapper mapper = new ObjectMapper();
		GeneratorConfig config = mapper.readValue(new File(configPath), GeneratorConfig.class);

		logger.info("Starting word generator for config {} (save words: {})...", config.getName(),
				!isCalculationMode);

		DependencyInjectionContainer container = new DependencyInjectionContainer();
		BaseWordService baseWordService = container.getBaseWordServiceEntryPoint();
		baseWordService.generateAndSave(config);

		logger.info("Generation complete! Took: " + Duration.between(initTime, Instant.now()));
		logCalculationStats();
		System.exit(0);
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

	private static void logCalculationStats() {
		long totalWords = totalEnWordsAmountForCalculation + totalRuWordsAmountForCalculation;

		logger.info("CALC STATS | Total: {} words | EN: {} | RU: {}",
				totalWords,
				totalEnWordsAmountForCalculation,
				totalRuWordsAmountForCalculation);
	}
}