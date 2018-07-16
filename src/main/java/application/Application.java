package application;

import features.AnalyzeSampleFile;
import features.PrepareSeparateTestFiles;

public class Application {

	public static void main(String[] args) {
		PrepareSeparateTestFiles feature = new PrepareSeparateTestFiles();
//		AnalyzeSampleFile feature = new AnalyzeSampleFile();

		feature.action();
	}
}
