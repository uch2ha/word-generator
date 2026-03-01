package uch2ha.model;

import java.util.Set;

public class WordBaseGenerationResult {

	private Set<String> combinedRu;
	private Set<String> combinedEn;

	public Set<String> getCombinedRu() {
		return combinedRu;
	}

	public void setCombinedRu(Set<String> combinedRu) {
		this.combinedRu = combinedRu;
	}

	public Set<String> getCombinedEn() {
		return combinedEn;
	}

	public void setCombinedEn(Set<String> combinedEn) {
		this.combinedEn = combinedEn;
	}

	public WordBaseGenerationResult(Set<String> combinedRu, Set<String> combinedEn) {
		this.combinedRu = combinedRu;
		this.combinedEn = combinedEn;
	}
}
