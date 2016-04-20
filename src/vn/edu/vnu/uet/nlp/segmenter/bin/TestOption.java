package vn.edu.vnu.uet.nlp.segmenter.bin;

import java.io.File;

import org.kohsuke.args4j.Option;

public class TestOption {
	@Option(name = "-m", usage = "the folder of segmenter model (required)")
	File modelsPath;
	
	@Option(name = "-i", usage = "your test file (required)")
	File inFile;
}
