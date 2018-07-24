package application;

import frame.EEGDataProcessorFrame;

public class Application {

	public static void main(String[] args) {
		/* Set the look and feel */
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(EEGDataProcessorFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}

		ApplicationData applicationData = new ApplicationData();

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new EEGDataProcessorFrame(applicationData).setVisible(true);
		});
	}
}
