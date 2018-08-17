package frame;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AboutFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = -7940509083403245915L;

	public AboutFrame() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	//<editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jlSource = new javax.swing.JLabel();
		jlProject = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("О программе");
		setLocationByPlatform(true);
		setType(java.awt.Window.Type.POPUP);

		jlSource.setText(
				"<html>\n<a href=\"https://github.com/kinnder/eeg-dataprocessor\">https://github.com/kinnder/eeg-dataprocessor</a>\n</html>");
		jlSource.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		jlSource.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jlSourceMouseClicked(evt);
			}
		});

		jlProject.setText("dataprocessor 0.0.0");
		jlProject.setText(getClass().getPackage().getImplementationTitle() + " "
				+ getClass().getPackage().getImplementationVersion());

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jlSource, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jlProject))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jlProject)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jlSource, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(28, Short.MAX_VALUE)));

		pack();
	}//</editor-fold>//GEN-END:initComponents

	private void jlSourceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlSourceMouseClicked
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/kinnder/eeg-dataprocessor"));
		} catch (URISyntaxException | IOException ex) {
			Logger.getLogger(AboutFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}//GEN-LAST:event_jlSourceMouseClicked

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jlProject;
	private javax.swing.JLabel jlSource;
	// End of variables declaration//GEN-END:variables
}
