package uch2ha.saver;

import uch2ha.Main;
import uch2ha.model.Lang;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;

public class ResultWordSaver {

	public ResultFileWriter createWriter(String configName, Lang lang) throws IOException {
		String timestamp = Main.getInitTime().toString().replace(":", "-");
		String langString = lang.name().toLowerCase();
		Path outputDir = Path.of(Main.getOutputFolderPath().toString(), configName, timestamp, langString);
		Files.createDirectories(outputDir);
		return new ResultFileWriter(configName, outputDir, langString);
	}

	public class ResultFileWriter {
		private final String configName;
		private final Path outputDir;
		private final String filePrefix;
		private final long maxFileSize;

		private int fileIndex = 1;
		private long currentSize = 0;
		private BufferedWriter writer;
		private Path currentFile;

		public ResultFileWriter(String configName, Path outputDir, String filePrefix)
				throws IOException {
			this.configName = configName;
			this.outputDir = outputDir;
			this.filePrefix = filePrefix;
			this.maxFileSize = Main.getMaxFileSizeInBytes();
			rotateFile(); // open first file
		}

		public void writeBatch(Set<String> words) throws IOException {
			for (String word : words) {
				byte[] lineBytes = (word + System.lineSeparator()).getBytes();
				if (currentSize + lineBytes.length > maxFileSize) {
					rotateFile();
				}
				writer.write(word);
				writer.newLine();
				currentSize += lineBytes.length;
			}
			writer.flush();
		}

		public void close() throws IOException {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}

		private void rotateFile() throws IOException {
			if (writer != null) {
				writer.close();
			}

			currentFile = outputDir.resolve(buildFileName(configName, fileIndex, filePrefix));
			writer = Files.newBufferedWriter(currentFile, StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
			currentSize = 0;
			fileIndex++;
		}

		private String buildFileName(String configName, int index, String prefix) {
			return String.format("%s_%s_%03d.txt", prefix, configName, index);
		}
	}
}
