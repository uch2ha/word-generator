package uch2ha;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uch2ha.model.GeneratorConfig;
import uch2ha.service.BaseWordService;
import uch2ha.util.TimestampUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class.getName());

	public static String outputFolder;
	public static String initTime = TimestampUtil.getTimestamp();
	public static boolean isCalculationMode = false;

	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			// Default for testing
			args = new String[]{"configs/example.json", "output"};
		}

		if (args.length < 2) {
			System.err.println("Usage: java WordGenerator <config.json> <output-folder>");
			System.exit(1);
		}

		String configPath = args[0];
		outputFolder = args[1];

		Files.createDirectories(Path.of(outputFolder));

		// Parse config from JSON
		ObjectMapper mapper = new ObjectMapper();
		GeneratorConfig config = mapper.readValue(new File(configPath), GeneratorConfig.class);

		logger.info("Starting word generator for config {}...", config.getName());

		logger.info("DMITRY 1 {}", config.getCoreRu());
		logger.info("DMITRY 2 {}", config.getCoreSplitSymbols());
		logger.info("DMITRY 3 {}", config.isGenerateEnAsRu());

		BaseWordService baseWordService = BaseWordService.getInstance();
		baseWordService.generateAndSave(config);

		logger.info("Generation complete!");
		System.exit(0);
	}
}
