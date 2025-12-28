package uch2ha.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeyboardLayoutUtil {

	public static final Map<String, String> ruToEnMap = Map.ofEntries(
			Map.entry("й", "q"),
			Map.entry("ц", "w"),
			Map.entry("у", "e"),
			Map.entry("к", "r"),
			Map.entry("е", "t"),
			Map.entry("н", "y"),
			Map.entry("г", "u"),
			Map.entry("ш", "i"),
			Map.entry("щ", "o"),
			Map.entry("з", "p"),
			Map.entry("х", "["),
			Map.entry("ъ", "]"),
			Map.entry("ф", "a"),
			Map.entry("ы", "s"),
			Map.entry("в", "d"),
			Map.entry("а", "f"),
			Map.entry("п", "g"),
			Map.entry("р", "h"),
			Map.entry("о", "j"),
			Map.entry("л", "k"),
			Map.entry("д", "l"),
			Map.entry("ж", ";"),
			Map.entry("э", "'"),
			Map.entry("я", "z"),
			Map.entry("ч", "x"),
			Map.entry("с", "c"),
			Map.entry("м", "v"),
			Map.entry("и", "b"),
			Map.entry("т", "n"),
			Map.entry("ь", "m"),
			Map.entry("б", ","),
			Map.entry("ю", "."),
			Map.entry("ё", "`")
	);

	public static final Map<String, String> enToRuMap = Map.ofEntries(
			Map.entry("q", "й"),
			Map.entry("w", "ц"),
			Map.entry("e", "у"),
			Map.entry("r", "к"),
			Map.entry("t", "е"),
			Map.entry("y", "н"),
			Map.entry("u", "г"),
			Map.entry("i", "ш"),
			Map.entry("o", "щ"),
			Map.entry("p", "з"),
			Map.entry("[", "x"),
			Map.entry("]", "ъ"),
			Map.entry("a", "ф"),
			Map.entry("s", "ы"),
			Map.entry("d", "в"),
			Map.entry("f", "а"),
			Map.entry("g", "п"),
			Map.entry("h", "р"),
			Map.entry("j", "о"),
			Map.entry("k", "л"),
			Map.entry("l", "д"),
			Map.entry(";", "ж"),
			Map.entry("'", "э"),
			Map.entry("z", "я"),
			Map.entry("x", "ч"),
			Map.entry("c", "с"),
			Map.entry("v", "м"),
			Map.entry("b", "и"),
			Map.entry("n", "т"),
			Map.entry("m", "ь"),
			Map.entry(",", "б"),
			Map.entry(".", "ю"),
			Map.entry("`", "ё")
	);

	public static final Set<String> ruSet = new HashSet<>(Set.of(
			"А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П",
			"Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я",
			"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п",
			"р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"
	));

	public static final Set<String> enSet = new HashSet<>(Set.of(
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
	));

	public static final List<String> digits = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
}
