package vn.edu.vnu.uet.nlp.postagger.experiments;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vn.edu.vnu.uet.nlp.postagger.UETTagger;

public class Test {

	static Accuracy test(List<String> lines, Set<String> dic, int index) {
		List<String> actualLabel = new ArrayList<String>();
		List<String> predictLabel = new ArrayList<String>();
		List<String> words = new ArrayList<String>();

		UETTagger tagger = new UETTagger("resources/configure/decode_" + index + ".xml");

		for (String line : lines) {
			String[] wordTags = line.split("\\s+");
			StringBuffer sb = new StringBuffer();

			for (String wordTag : wordTags) {
				int in = wordTag.lastIndexOf('/');
				if (in == 0) {
					continue;
				}
				String word = wordTag.substring(0, in);
				String tag = wordTag.substring(in + 1);

				actualLabel.add(tag);
				words.add(word);

				sb.append(word + " ");
			}

			String actualLine = sb.toString().trim();
			String taggedLine = tagger.tagString(actualLine);

			String[] predictWordTags = taggedLine.split("\\s+");

			for (String wordTag : predictWordTags) {
				int in = wordTag.lastIndexOf('/');
				String tag = wordTag.substring(in + 1);

				predictLabel.add(tag);
			}

		}

		int wordNum = actualLabel.size();

		int rightTag = 0;
		int rightUnknownTag = 0;
		int numUnknownWord = 0;

		if (actualLabel.size() == predictLabel.size() && predictLabel.size() == words.size()) {

			for (int i = 0; i < wordNum; i++) {
				String word = words.get(i);
				String actualTag = actualLabel.get(i);
				String predictTag = predictLabel.get(i);

				if (!dic.contains(word)) {
					numUnknownWord++;
				}

				if (actualTag.equals(predictTag)) {
					rightTag++;
					if (!dic.contains(word)) {
						rightUnknownTag++;
					}
				}
			}

			System.out.println("Total words: " + wordNum);
			System.out.println("Right words: " + rightTag);
			System.out.println("Unknown words: " + numUnknownWord);
			System.out.println("Right unknown words: " + rightUnknownTag);

			double acc = 100.0 * rightTag / wordNum;
			double unkAcc = 100.0 * rightUnknownTag / numUnknownWord;
			System.out.println("\n" + acc + "%\t" + unkAcc + "%");

		} else {
			System.out.println(words.size());
			System.out.println(actualLabel.size());
			System.out.println(predictLabel.size());
		}

		return new Accuracy(wordNum, rightTag, rightUnknownTag, numUnknownWord);
	}

	public static void main(String[] args) throws IOException {
		Path input = Paths.get("fdata/test_0.txt");
		BufferedReader br = Files.newBufferedReader(input, StandardCharsets.UTF_8);

		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = br.readLine()) != null) {
			line = line.trim();

			if (line.isEmpty()) {
				continue;
			}

			lines.add(line);
		}
		br.close();

		Set<String> dic = new HashSet<String>();

		input = Paths.get("fdata/train_0.txt");
		br = Files.newBufferedReader(input, StandardCharsets.UTF_8);
		while ((line = br.readLine()) != null) {
			line = line.trim();

			if (line.isEmpty()) {
				continue;
			}

			String[] wordTags = line.split("\\s+");
			for (String wordTag : wordTags) {
				int index = wordTag.lastIndexOf('/');
				if (index == 0) {
					continue;
				}
				String word = wordTag.substring(0, index);
				dic.add(word);
			}
		}

		test(lines, dic, 0);

	}

}
