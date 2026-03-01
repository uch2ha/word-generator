package uch2ha.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SuffixBatchIterator implements Iterator<List<String>> {

	private final List<String> allowedChars;
	private final int suffixLength;
	private final int batchSize;
	private final int[] position;

	private boolean finished = false;

	public SuffixBatchIterator(List<String> allowedChars, int suffixLength, int batchSize) {
		this.allowedChars = allowedChars;
		this.suffixLength = suffixLength;
		this.batchSize = batchSize;
		this.position = new int[suffixLength]; // all 0 initially
	}

	@Override
	public boolean hasNext() {
		return !finished;
	}

	@Override
	public List<String> next() {
		List<String> batch = new ArrayList<>(batchSize);

		for (int i = 0; i < batchSize && !finished; i++) {
			StringBuilder sb = new StringBuilder();
			for (int idx : position) {
				sb.append(allowedChars.get(idx));
			}
			batch.add(sb.toString());
			incrementPosition();
		}

		return batch;
	}

	private void incrementPosition() {
		for (int i = suffixLength - 1; i >= 0; i--) {
			if (position[i] < allowedChars.size() - 1) {
				position[i]++;
				return;
			} else {
				position[i] = 0;
			}
		}
		finished = true;
	}
}
