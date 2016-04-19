package vn.edu.vnu.uet.nlp.postag.experiments;

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

public class TenTest {

	public static void main(String[] args) throws IOException {
		Accuracy[] result = new Accuracy[10];

		for (int i = 0; i < 10; i++) {
			Path input = Paths.get("fdata/test_" + i + ".txt");
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

			input = Paths.get("fdata/train_" + i + ".txt");
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

			result[i] = Test.test(lines, dic, i);
		}

		System.out.println("\n\n");
		for (int i = 0; i < 10; i++) {
			System.out.println(result[i]);
		}
	}

}
