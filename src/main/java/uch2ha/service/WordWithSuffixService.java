package uch2ha.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uch2ha.Main;
import uch2ha.iterator.SuffixBatchIterator;
import uch2ha.model.GeneratorConfig;
import uch2ha.model.Lang;
import uch2ha.saver.ResultWordSaver;
import uch2ha.util.KeyboardLayoutUtil;
import uch2ha.util.RamDebugUtil;
import uch2ha.validator.WordByAlphabetValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordWithSuffixService {

	private static final Logger logger = LogManager.getLogger(WordWithSuffixService.class.getName());

	private static final int SUFFIX_BATCH_SIZE = 500_000;

	private final ResultWordSaver resultWordSaver;
	private final WordByAlphabetValidator wordByAlphabetValidator;

	private WordWithSuffixService() {
		wordByAlphabetValidator = WordByAlphabetValidator.getInstance();
		resultWordSaver = new ResultWordSaver();
	}

	private static final WordWithSuffixService INSTANCE = new WordWithSuffixService();

	public static WordWithSuffixService getInstance() {
		return INSTANCE;
	}

	public void process(Set<String> baseWords, Set<String> alphabetSet,
			GeneratorConfig config, Lang lang) throws IOException {
		int maxSuffixLength = config.getMaxSuffixLength();

		List<String> allowedChars = new ArrayList<>();
		allowedChars.addAll(config.getSuffixSymbols());
		allowedChars.addAll(KeyboardLayoutUtil.digits);
		allowedChars.addAll(Lang.EN.equals(lang) ? KeyboardLayoutUtil.enSet : KeyboardLayoutUtil.ruSet);

		ResultWordSaver.ResultFileWriter fileWriter = resultWordSaver
				.createWriter(config.getName(), Main.outputFolderPath, lang.name().toLowerCase());

		int iterationCount = 0;

		for (int currentSuffixLength = config.getMinSuffixLength();
				currentSuffixLength <= maxSuffixLength; currentSuffixLength++) {
			SuffixBatchIterator suffixIterator = new SuffixBatchIterator(allowedChars,
					currentSuffixLength, SUFFIX_BATCH_SIZE);
			int allSuffixCount = (int) Math.pow(allowedChars.size(), currentSuffixLength);

			while (suffixIterator.hasNext()) {
				iterationCount++;
				List<String> suffixBatch = suffixIterator.next();
				logger.info("Processing suffix '{}' batch of size {}/{} for {}", currentSuffixLength,
						suffixBatch.size(), allSuffixCount, lang);

				RamDebugUtil.log();

				int i = 1;
				for (String baseWord : baseWords) {
					i++;
					if (i % 100 == 0) {
						RamDebugUtil.log();
						logger.info("Processing '{}' base word {}/{} -> suffix {}", lang, i, baseWords.size(),
								currentSuffixLength);
					}

					Set<String> candidateWords = new HashSet<>(SUFFIX_BATCH_SIZE);

					for (String suffix : suffixBatch) {
						candidateWords.add(baseWord + suffix);
					}

					wordByAlphabetValidator.validate(candidateWords, alphabetSet);
					// todo? wordDeduplicationValidator.filterOutExistingWords(candidateWords, lang);

					if (candidateWords.isEmpty()) {
						continue;
					}

					if (!Main.isCalculationMode) {
						fileWriter.writeBatch(candidateWords);
					}

					handleStatCalculationLogic(candidateWords, lang);
				}
			}
			logger.info("Finished with suffix length {} for {}", currentSuffixLength, lang);
		}

		logger.info("Iteration count: {}", iterationCount);

		fileWriter.close();
	}

	private void handleStatCalculationLogic(Set<String> candidateWords, Lang lang) {
		if (Lang.EN.equals(lang)) {
			Main.setTotalEnWordsAmountForCalculation(
					Main.getTotalEnWordsAmountForCalculation() + candidateWords.size());
		} else {
			Main.setTotalRuWordsAmountForCalculation(
					Main.getTotalRuWordsAmountForCalculation() + candidateWords.size());
		}
	}
}