package vn.edu.vnu.uet.nlp.postagger.experiments;

import edu.emory.clir.clearnlp.bin.NLPTrain;

public class Train {

	public static void main(String[] args) {
		int num = 0;

		String configFile = "resources/configure/config_train_pos_vi.xml";
		String featuresFile = "resources/features/feature_vi_pos.xml";

		String trainFile = "fdata_tsv/train_" + num + "_tsv.txt";
		String testFile = "fdata_tsv/test_" + num + "_tsv.txt";

		String modelFile = "resources/models/model_" + num + "/model_" + num + ".xz";

		String[] arguments = new String[] { "-mode", "pos", "-c", configFile, "-f", featuresFile, "-t", trainFile, "-d",
				testFile, "-m", modelFile };

		NLPTrain.main(arguments);
	}

}
