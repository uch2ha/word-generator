package uch2ha.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeneratorConfig {

	@JsonProperty("name")
	private String name;

	@JsonProperty("coreRu")
	private List<List<String>> coreRu;

	@JsonProperty("coreEn")
	private List<List<String>> coreEn;

	@JsonProperty("coreSplitSymbols")
	private List<String> coreSplitSymbols;

	@JsonProperty("suffixSymbols")
	private List<String> suffixSymbols;

	@JsonProperty("templates")
	private List<String> templates;

	@JsonProperty("maxSuffixLength")
	private int maxSuffixLength = 3;

	@JsonProperty("minSuffixLength")
	private int minSuffixLength = 0;

	@JsonProperty("generateRu")
	private boolean generateRu = true;

	@JsonProperty("generateEn")
	private boolean generateEn = true;

	@JsonProperty("generateRuAsEn")
	private boolean generateRuAsEn = true;

	@JsonProperty("generateEnAsRu")
	private boolean generateEnAsRu = true;

	@JsonProperty("generateWithCapitalization")
	private boolean generateWithCapitalization = true;

	@JsonProperty("endingSymbols")
	private List<String> endingSymbols;

	public GeneratorConfig() {
	}

	public String getName() {
		return name;
	}

	public List<List<String>> getCoreRu() {
		return coreRu;
	}

	public List<List<String>> getCoreEn() {
		return coreEn;
	}

	public List<String> getCoreSplitSymbols() {
		return coreSplitSymbols;
	}

	public List<String> getSuffixSymbols() {
		return suffixSymbols;
	}

	public List<String> getTemplates() {
		return templates;
	}

	public int getMaxSuffixLength() {
		return maxSuffixLength;
	}

	public int getMinSuffixLength() {
		return minSuffixLength;
	}

	public boolean isGenerateRu() {
		return generateRu;
	}

	public boolean isGenerateEn() {
		return generateEn;
	}

	public boolean isGenerateRuAsEn() {
		return generateRuAsEn;
	}

	public boolean isGenerateEnAsRu() {
		return generateEnAsRu;
	}

	public boolean isGenerateWithCapitalization() {
		return generateWithCapitalization;
	}

	public List<String> getEndingSymbols() {
		return endingSymbols;
	}
}
