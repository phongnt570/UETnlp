package vn.edu.vnu.uet.nlp.segmenter.bin;

import java.io.File;

import org.kohsuke.args4j.Option;

public class TrainOption {
	@Option(name = "-i", usage = "path to the training data (file/directory) (require)")
	File inFile;

	@Option(name = "-e", usage = "file extension, only use when -i is a folder (default: .seg)")
	String ext = ".seg";
	
	@Option(name = "-m", usage = "the folder you want to save WS model (require)")
	File modelsPath;
}
