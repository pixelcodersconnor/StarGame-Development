package uk.connorwright.StarGame.launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.launcher.bugs.UpdateGUI;
import uk.connorwright.StarGame.util.Strings;

public class Launcher {


	public Launcher() {
		final JFrame frame = new JFrame(Strings.LAUNCHER_TITLE);
		frame.setResizable(false);

		frame.setSize(402, 170);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JButton btnPlay = new JButton("Play!");
		btnPlay.setBounds(0, 53, 395, 23);
		frame.getContentPane().add(btnPlay);

		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.main(null);
				frame.dispose();
			}

		});
		JLabel starGameLabel = new JLabel(Strings.LAUNCHER_TITLE);
		starGameLabel.setBounds(139, 11, 395, 14);
		frame.getContentPane().add(starGameLabel);

		JButton btnQuit = new JButton(Strings.QUIT + "!");
		btnQuit.setBounds(0, 87, 395, 23);
		frame.getContentPane().add(btnQuit);

		JButton btnUpdate = new JButton(Strings.UPDATE);
		btnUpdate.setBounds(0, 119, 395, 23);
		frame.getContentPane().add(btnUpdate);

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateGUI();

			}


		});

		btnQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}

		});


		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new Launcher();
	}
}
