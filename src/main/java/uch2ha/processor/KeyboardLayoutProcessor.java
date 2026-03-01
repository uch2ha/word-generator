package uch2ha.processor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeyboardLayoutProcessor {

	public Set<String> convertSet(Set<String> input, Map<String, String> layoutMap) {
		Set<String> converted = new HashSet<>();
		for (String word : input) {
			converted.add(convertWord(word, layoutMap));
		}
		return converted;
	}

	private String convertWord(String word, Map<String, String> layoutMap) {
		StringBuilder sb = new StringBuilder();
		for (char c : word.toCharArray()) {
			String s = String.valueOf(c);

			if (!Character.isLetter(c)) {
				sb.append(s);
				continue;
			}

			String convertedChar = getConvertedChar(s, layoutMap);

			if (convertedChar == null) {
				throw new RuntimeException(s + " not found, in layout map. Word '" + word + "'. skipping this part");
			}

			sb.append(convertedChar);
		}

		return sb.toString();
	}

	private String getConvertedChar(String ch, Map<String, String> layoutMap) {
		if (Character.isUpperCase(ch.charAt(0))) {
			String lower = ch.toLowerCase();
			String mapped = layoutMap.get(lower);
			return mapped != null ? mapped.toUpperCase() : null;
		} else {
			return layoutMap.get(ch);
		}
	}
}
