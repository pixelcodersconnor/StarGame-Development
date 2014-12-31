package uk.connorwright.StarGame.launcher.bugs;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import uk.connorwright.StarGame.util.Strings;

public class UpdateGUI {

	public UpdateGUI() {
		JFrame starGameUpdater = new JFrame();
		starGameUpdater.setTitle(Strings.LAUNCHER_TITLE);
		starGameUpdater.getContentPane().setLayout(null);

		JLabel labelStargameUpdate = new JLabel(Strings.LAUNCHER_TITLE);
		labelStargameUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		labelStargameUpdate.setBounds(165, 11, 94, 14);
		starGameUpdater.getContentPane().add(labelStargameUpdate);

	}
}
