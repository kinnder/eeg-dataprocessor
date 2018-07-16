package features;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import domain.Sample;

public class AnalyzeSampleFile {
	public void action() {
		final String path = "data//";
		final String samplesFileName = path + "BNDmetki.txt";

		long duration_longest = Long.MIN_VALUE;
		long duration_shortest = Long.MAX_VALUE;
		long interval_right = Long.MAX_VALUE;
		long interval_left = Long.MAX_VALUE;

		try (BufferedReader samplesFile = new BufferedReader(new FileReader(samplesFileName))) {
			String sampleAsString;
			if ((sampleAsString = samplesFile.readLine()) == null) {
				System.out.println("no samples");
				return;
			}
			Sample sample_current = new Sample(sampleAsString);

			READING_SAMPLES: while (true) {
				Sample sample_next = null;
				if ((sampleAsString = samplesFile.readLine()) != null) {
					sample_next = new Sample(sampleAsString);
				}

				long triggerTime = sample_current.getTriggerTime();
				if (triggerTime > 0 && triggerTime < interval_left) {
					interval_left = triggerTime;
				}

				if (sample_next != null) {
					long duration = sample_next.getStartTime() - sample_current.getStartTime();
					if (duration > duration_longest) {
						duration_longest = duration;
					} else if (duration < duration_shortest) {
						duration_shortest = duration;
					}
					if (triggerTime > 0 && ((duration - triggerTime) < interval_right)) {
						interval_right = triggerTime;
					}
				}

				if (sample_next == null) {
					break READING_SAMPLES;
				}
				sample_current = sample_next;
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
