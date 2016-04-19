package vn.edu.vnu.uet.nlp.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BuildXMLDecodeFiles {

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			File dir = new File("resources/models/model_" + i);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			Path file = Paths.get("resources/configure/decode_" + i + ".xml");
			BufferedWriter bw = Files.newBufferedWriter(file, StandardOpenOption.CREATE);
			bw = Files.newBufferedWriter(file, StandardOpenOption.WRITE);
			bw = Files.newBufferedWriter(file, StandardOpenOption.TRUNCATE_EXISTING);

			bw.write("<configuration>\n" + "<language>vietnamese</language>\n" + "<reader type=\"raw\">\n"
					+ "<column index=\"1\" field=\"form\" />\n" + "</reader>\n" + "<global>\n"
					+ "<distributional_semantics>clusters.vi.obj.txt.xz</distributional_semantics>\n" + "</global>\n"
					+ "<model>\n" + "<pos>model_" + i + ".xz</pos>\n" + "</model>\n" + "</configuration>");

			bw.flush();
			bw.close();
		}
	}

}
