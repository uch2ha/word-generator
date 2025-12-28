package uch2ha.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uch2ha.generator.WordBaseGenerator;
import uch2ha.model.GeneratorConfig;
import uch2ha.model.Lang;
import uch2ha.model.WordBaseGenerationResult;
import uch2ha.util.KeyboardLayoutUtil;

import java.io.IOException;
import java.util.Set;

public class BaseWordService {

	private static final Logger logger = LogManager.getLogger(BaseWordService.class.getName());

	private final WordBaseGenerator wordBaseGenerator;
	private final WordWithSuffixService wordWithSuffixService;

	public BaseWordService(WordBaseGenerator wordBaseGenerator, WordWithSuffixService wordWithSuffixService) {
		this.wordBaseGenerator = wordBaseGenerator;
		this.wordWithSuffixService = wordWithSuffixService;
	}

	public void generateAndSave(GeneratorConfig config) throws IOException {
		logger.info("Generating words...");

		WordBaseGenerationResult baseWords = wordBaseGenerator.generate(config);

		logger.info("Base words generated: {} ru, {} en", baseWords.getCombinedRu().size(),
				baseWords.getCombinedEn().size());

		// RU
		if (config.isGenerateRu()) {
			Set<String> ruBaseWords = baseWords.getCombinedRu();
			if (ruBaseWords.isEmpty()) {
				logger.info("No RU base words generated.");
			} else {
				wordWithSuffixService.process(ruBaseWords, KeyboardLayoutUtil.ruSet, config, Lang.RU);
				ruBaseWords.clear();
			}
		}

		// EN
		if (config.isGenerateRu()) {
			Set<String> enBaseWords = baseWords.getCombinedEn();
			if (enBaseWords.isEmpty()) {
				logger.info("No EN base words generated.");
			} else {
				wordWithSuffixService.process(enBaseWords, KeyboardLayoutUtil.enSet, config, Lang.EN);
				enBaseWords.clear();
			}
		}
	}
}