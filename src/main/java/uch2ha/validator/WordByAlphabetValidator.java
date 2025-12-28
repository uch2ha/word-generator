package uch2ha.validator;

import java.util.Set;

public class WordByAlphabetValidator {

	public void validate(Set<String> words, Set<String> alphabetSet) {
		for (String word : words) {
			isWordFromAlphabet(word, alphabetSet);
		}
	}

	public boolean validateWord(String word, Set<String> alphabetSet) {
		try {
			isWordFromAlphabet(word, alphabetSet);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private void isWordFromAlphabet(String word, Set<String> alphabetSet) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);

			// Skip symbols (anything that's not a letter)
			if (!Character.isLetter(c)) {
				continue;
			}

			String ch = String.valueOf(c);
			if (!alphabetSet.contains(ch)) {
				throw new IllegalArgumentException("Invalid character '" + ch + "' found in word: '" + word + "'");
			}
		}
	}
}
