package features;

import java.io.FileNotFoundException;
import java.io.IOException;

import domain.Sample;
import domain.SamplesFile;

public class AnalyzeSampleFile {
	public void action() {
		final String path = "data//";
		final String samplesFileName = path + "BNDmetki.txt";

		long duration_longest = Long.MIN_VALUE;
		long duration_shortest = Long.MAX_VALUE;
		long interval_right = Long.MAX_VALUE;
		long interval_left = Long.MAX_VALUE;

		Sample sample_previous = null;
		try (SamplesFile samplesFile = new SamplesFile(samplesFileName)) {
			while (samplesFile.hasNext()) {
				Sample sample_current = samplesFile.nextSample();

				long triggerTime = sample_current.getTriggerTime();
				if (triggerTime > 0 && triggerTime < interval_left) {
					interval_left = triggerTime;
				}

				if (sample_previous != null) {
					long duration = sample_current.getStartTime() - sample_previous.getStartTime();
					if (duration > duration_longest) {
						duration_longest = duration;
					} else if (duration < duration_shortest) {
						duration_shortest = duration;
					}
					if (triggerTime > 0 && ((duration - triggerTime) < interval_right)) {
						interval_right = triggerTime;
					}
				}

				sample_previous = sample_current;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Duration-shortest: " + duration_shortest);
		System.out.println("Duration-longest: " + duration_longest);
		System.out.println("Interval on the left-shortest : " + interval_left);
		System.out.println("Interval on the right-shortest: " + interval_right);
	}
}
