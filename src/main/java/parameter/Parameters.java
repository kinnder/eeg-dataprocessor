package parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Parameters {

	public Parameters() {
	}

	public void save(File file) {
		Document xmlDoc = new Document();

		Element root = new Element("dataprocessor");
		xmlDoc.setRootElement(root);

		Element parameters = new Element("parameters");
		root.addContent(parameters);

		parameters.addContent(new Element("stimulusTime").addContent(Long.toString(stimulusTime)));
		parameters.addContent(new Element("interval_left").addContent(Long.toString(interval_left)));
		parameters.addContent(new Element("interval_right").addContent(Long.toString(interval_right)));
		parameters.addContent(new Element("triggerTime_average").addContent(Long.toString(triggerTime_average)));
		parameters.addContent(new Element("duration_min").addContent(Long.toString(duration_min)));
		parameters.addContent(new Element("indicationsFileTimeStep").addContent(Long.toString(indicationsFileTimeStep)));

		try {
			Format fmt = Format.getPrettyFormat();

			XMLOutputter serializer = new XMLOutputter(fmt);
			serializer.output(xmlDoc, new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(File file) {
		SAXBuilder builder = new SAXBuilder();

		try {
			Document xmlDoc = builder.build(file);

			Element root = xmlDoc.getRootElement();

			Element parameters = root.getChild("parameters");

			stimulusTime = Long.parseLong(parameters.getChildText("stimulusTime"));
			interval_left = Long.parseLong(parameters.getChildText("interval_left"));
			interval_right = Long.parseLong(parameters.getChildText("interval_right"));
			triggerTime_average = Long.parseLong(parameters.getChildText("triggerTime_average"));
			duration_min = Long.parseLong(parameters.getChildText("duration_min"));
			indicationsFileTimeStep = Long.parseLong(parameters.getChildText("indicationsFileTimeStep"));

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private long stimulusTime;

	public void setStimulusTime(long value) {
		stimulusTime = value;
	}

	public long getStimulusTime() {
		return stimulusTime;
	}

	private long interval_left;

	public void setIntervalLeft(long value) {
		interval_left = value;
	}

	public long getIntervalLeft() {
		return interval_left;
	}

	private long interval_right;

	public void setIntervalRight(long value) {
		interval_right = value;
	}

	public long getIntervalRight() {
		return interval_right;
	}

	private long duration_min;

	public void setDurationMin(long value) {
		duration_min = value;
	}

	public long getDurationMin() {
		return duration_min;
	}

	private long triggerTime_average;

	public void setTriggerTimeAverage(long value) {
		triggerTime_average = value;
	}

	public long getTriggerTimeAverage() {
		return triggerTime_average;
	}

	private long indicationsFileTimeStep;

	public void setIndicationsFileTimeStep(long value) {
		indicationsFileTimeStep = value;
	}

	public long getIndicationsFileTimeStep() {
		return indicationsFileTimeStep;
	}

	public static void main(String args[]) {
		Parameters p = new Parameters();

		// p.save(new File("text.xml"));
		p.load(new File("text.xml"));

		System.out.println(p.stimulusTime);
		System.out.println(p.interval_left);
		System.out.println(p.interval_right);
		System.out.println(p.triggerTime_average);
		System.out.println(p.duration_min);
		System.out.println(p.indicationsFileTimeStep);
	}
}
