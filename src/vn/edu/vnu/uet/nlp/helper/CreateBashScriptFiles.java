package vn.edu.vnu.uet.nlp.helper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CreateBashScriptFiles {

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			Path file = Paths.get("release/train_" + i + ".sh");
			BufferedWriter bw = Files.newBufferedWriter(file, StandardOpenOption.CREATE);
			bw = Files.newBufferedWriter(file, StandardOpenOption.WRITE);
			bw = Files.newBufferedWriter(file, StandardOpenOption.TRUNCATE_EXISTING);

			bw.write(
					"java -Xmx4g -XX:+UseConcMarkSweepGC -jar clearnlp.jar " + i + " > log/log_train_" + i +".txt 2>&1");

			bw.flush();
			bw.close();
		}
	}

}
