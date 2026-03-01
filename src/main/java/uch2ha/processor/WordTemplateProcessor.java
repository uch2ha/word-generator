package uch2ha.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordTemplateProcessor {

	private static final Logger logger = LogManager.getLogger(WordTemplateProcessor.class.getName());

	private static final Pattern tokenPattern = Pattern.compile("\\$([a-zA-Z]+)(\\[(\\d+)])?");

	public Set<String> processTemplate(String template, List<List<String>> core, List<String> symbols) {
		Set<String> result = new HashSet<>();

		List<List<String>> components = new ArrayList<>();
		Matcher matcher = tokenPattern.matcher(template);
		int lastEnd = 0;

		while (matcher.find()) {
			if (matcher.start() > lastEnd) {
				String literal = template.substring(lastEnd, matcher.start());
				components.add(List.of(literal));
			}

			String key = matcher.group(1);
			String indexStr = matcher.group(3);

			switch (key) {
				case "core" -> {
					if (indexStr == null) {
						logger.error("Invalid use of $core without index. Skipping this part.");
						return Set.of();
					}
					int index = Integer.parseInt(indexStr);
					if (index >= core.size()) {
						logger.error("core '{}' not found, skipping this part.", index);
						return Set.of();
					}
					components.add(core.get(index));
				}
				case "s" -> components.add(symbols);
				default -> {
					logger.error("Unknown template key: {}", key);
					return Set.of();
				}
			}

			lastEnd = matcher.end();
		}

		if (lastEnd < template.length()) {
			components.add(List.of(template.substring(lastEnd)));
		}

		buildCombinations(components, 0, new StringBuilder(), result);
		return result;
	}

	private void buildCombinations(List<List<String>> components, int index, StringBuilder current,
			Set<String> result) {
		if (index == components.size()) {
			result.add(current.toString());
			return;
		}
		for (String part : components.get(index)) {
			int len = current.length();
			current.append(part);
			buildCombinations(components, index + 1, current, result);
			current.setLength(len);
		}
	}
}
