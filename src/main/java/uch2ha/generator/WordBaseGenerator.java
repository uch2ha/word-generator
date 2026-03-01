package uch2ha.generator;

import uch2ha.model.GeneratorConfig;
import uch2ha.model.WordBaseGenerationResult;
import uch2ha.processor.KeyboardLayoutProcessor;
import uch2ha.processor.WordCapitalizationProcessor;
import uch2ha.processor.WordTemplateProcessor;
import uch2ha.util.KeyboardLayoutUtil;
import uch2ha.validator.WordByAlphabetValidator;

import java.util.HashSet;
import java.util.Set;

public class WordBaseGenerator {

	private final WordTemplateProcessor wordTemplateProcessor;
	private final KeyboardLayoutProcessor keyboardLayoutProcessor;
	private final WordCapitalizationProcessor wordCapitalizationProcessor;
	private final WordByAlphabetValidator wordByAlphabetValidator;

	public WordBaseGenerator(WordTemplateProcessor wordTemplateProcessor,
							 KeyboardLayoutProcessor keyboardLayoutProcessor,
							 WordCapitalizationProcessor wordCapitalizationProcessor,
							 WordByAlphabetValidator wordByAlphabetValidator) {
		this.wordTemplateProcessor = wordTemplateProcessor;
		this.keyboardLayoutProcessor = keyboardLayoutProcessor;
		this.wordCapitalizationProcessor = wordCapitalizationProcessor;
		this.wordByAlphabetValidator = wordByAlphabetValidator;
	}

	public WordBaseGenerationResult generate(GeneratorConfig config) {
		Set<String> ruWords = new HashSet<>();
		Set<String> enWords = new HashSet<>();
		Set<String> ruAsEnWords = new HashSet<>();
		Set<String> enAsRuWords = new HashSet<>();

		for (String template : config.getTemplates()) {
			if (config.isGenerateRu()) {
				ruWords.addAll(wordTemplateProcessor.processTemplate(
						template, config.getCoreRu(), config.getCoreSplitSymbols()));
			}
			if (config.isGenerateEn()) {
				enWords.addAll(wordTemplateProcessor.processTemplate(
						template, config.getCoreEn(), config.getCoreSplitSymbols()));
			}
		}

		if (config.isGenerateRuAsEn()) {
			ruAsEnWords.addAll(keyboardLayoutProcessor.convertSet(ruWords, KeyboardLayoutUtil.ruToEnMap));
		}
		if (config.isGenerateEnAsRu()) {
			enAsRuWords.addAll(keyboardLayoutProcessor.convertSet(enWords, KeyboardLayoutUtil.enToRuMap));
		}

		Set<String> combinedRu = new HashSet<>();
		combinedRu.addAll(ruWords);
		combinedRu.addAll(enAsRuWords);

		Set<String> combinedEn = new HashSet<>();
		combinedEn.addAll(enWords);
		combinedEn.addAll(ruAsEnWords);

		if (config.isGenerateWithCapitalization()) {
			combinedRu.addAll(wordCapitalizationProcessor.capitalizeFirstLetters(combinedRu));
			combinedEn.addAll(wordCapitalizationProcessor.capitalizeFirstLetters(combinedEn));
		}

		wordByAlphabetValidator.validate(combinedRu, KeyboardLayoutUtil.ruSet);
		wordByAlphabetValidator.validate(combinedEn, KeyboardLayoutUtil.enSet);

		return new WordBaseGenerationResult(combinedRu, combinedEn);
	}
}