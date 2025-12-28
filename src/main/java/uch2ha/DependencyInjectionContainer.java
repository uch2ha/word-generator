package uch2ha;

import uch2ha.generator.WordBaseGenerator;
import uch2ha.processor.KeyboardLayoutProcessor;
import uch2ha.processor.WordCapitalizationProcessor;
import uch2ha.processor.WordTemplateProcessor;
import uch2ha.saver.ResultWordSaver;
import uch2ha.service.BaseWordService;
import uch2ha.service.WordWithSuffixService;
import uch2ha.validator.WordByAlphabetValidator;

public class DependencyInjectionContainer {

	private final WordTemplateProcessor wordTemplateProcessor;
	private final KeyboardLayoutProcessor keyboardLayoutProcessor;
	private final WordCapitalizationProcessor wordCapitalizationProcessor;
	private final WordByAlphabetValidator wordByAlphabetValidator;
	private final ResultWordSaver resultWordSaver;
	private final WordBaseGenerator wordBaseGenerator;
	private final WordWithSuffixService wordWithSuffixService;
	private final BaseWordService baseWordService;

	public DependencyInjectionContainer() {
		wordTemplateProcessor = new WordTemplateProcessor();
		keyboardLayoutProcessor = new KeyboardLayoutProcessor();
		wordCapitalizationProcessor = new WordCapitalizationProcessor();
		wordByAlphabetValidator = new WordByAlphabetValidator();
		resultWordSaver = new ResultWordSaver();

		wordBaseGenerator = new WordBaseGenerator(
				wordTemplateProcessor,
				keyboardLayoutProcessor,
				wordCapitalizationProcessor,
				wordByAlphabetValidator
		);

		wordWithSuffixService = new WordWithSuffixService(
				resultWordSaver,
				wordByAlphabetValidator
		);

		baseWordService = new BaseWordService(
				wordBaseGenerator,
				wordWithSuffixService
		);
	}

	public BaseWordService getEntryPoint() {
		return baseWordService;
	}
}
