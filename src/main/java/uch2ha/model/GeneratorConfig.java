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

	public GeneratorConfig() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<List<String>> getCoreRu() {
		return coreRu;
	}

	public void setCoreRu(List<List<String>> coreRu) {
		this.coreRu = coreRu;
	}

	public List<List<String>> getCoreEn() {
		return coreEn;
	}

	public void setCoreEn(List<List<String>> coreEn) {
		this.coreEn = coreEn;
	}

	public List<String> getCoreSplitSymbols() {
		return coreSplitSymbols;
	}

	public void setCoreSplitSymbols(List<String> coreSplitSymbols) {
		this.coreSplitSymbols = coreSplitSymbols;
	}

	public List<String> getSuffixSymbols() {
		return suffixSymbols;
	}

	public void setSuffixSymbols(List<String> suffixSymbols) {
		this.suffixSymbols = suffixSymbols;
	}

	public List<String> getTemplates() {
		return templates;
	}

	public void setTemplates(List<String> templates) {
		this.templates = templates;
	}

	public int getMaxSuffixLength() {
		return maxSuffixLength;
	}

	public void setMaxSuffixLength(int maxSuffixLength) {
		this.maxSuffixLength = maxSuffixLength;
	}

	public int getMinSuffixLength() {
		return minSuffixLength;
	}

	public void setMinSuffixLength(int minSuffixLength) {
		this.minSuffixLength = minSuffixLength;
	}

	public boolean isGenerateRu() {
		return generateRu;
	}

	public void setGenerateRu(boolean generateRu) {
		this.generateRu = generateRu;
	}

	public boolean isGenerateEn() {
		return generateEn;
	}

	public void setGenerateEn(boolean generateEn) {
		this.generateEn = generateEn;
	}

	public boolean isGenerateRuAsEn() {
		return generateRuAsEn;
	}

	public void setGenerateRuAsEn(boolean generateRuAsEn) {
		this.generateRuAsEn = generateRuAsEn;
	}

	public boolean isGenerateEnAsRu() {
		return generateEnAsRu;
	}

	public void setGenerateEnAsRu(boolean generateEnAsRu) {
		this.generateEnAsRu = generateEnAsRu;
	}

	public boolean isGenerateWithCapitalization() {
		return generateWithCapitalization;
	}

	public void setGenerateWithCapitalization(boolean generateWithCapitalization) {
		this.generateWithCapitalization = generateWithCapitalization;
	}
}
