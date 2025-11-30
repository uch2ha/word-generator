package uch2ha.processor;

import java.util.Set;
import java.util.stream.Collectors;

public class WordCapitalizationProcessor {

	private static final WordCapitalizationProcessor INSTANCE = new WordCapitalizationProcessor();

	public static WordCapitalizationProcessor getInstance() {
		return INSTANCE;
	}

	/**
	 * Capitalizes the first character of each word in the provided set.
	 *
	 * @param words
	 * 		the set of words to capitalize
	 * @return a new set of words with the first character capitalized
	 */
	public Set<String> capitalizeFirstLetters(Set<String> words) {
		return words.stream()
				.map(this::capitalizeFirstLetter)
				.collect(Collectors.toSet());
	}

	private String capitalizeFirstLetter(String word) {
		if (word == null || word.isEmpty()) {
			throw new RuntimeException("Word is null or empty in 'capitalizeFirstLetter'");
		}
		return Character.toUpperCase(word.charAt(0)) + word.substring(1);
	}
}
