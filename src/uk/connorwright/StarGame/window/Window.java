package uk.connorwright.StarGame.window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;
import uk.connorwright.StarGame.audio.Jukebox;
import uk.connorwright.StarGame.util.Strings;
import uk.connorwright.StarGame.window.commands.God;
import uk.connorwright.StarGame.window.commands.Restart;
import uk.connorwright.StarGame.window.commands.SetScore;
import uk.connorwright.StarGame.window.commands.dev.NextState;
import uk.connorwright.StarGame.window.commands.help.DisplayCommands;
import uk.connorwright.StarGame.window.console.TextAreaOutputStream;

public class Window {
	final static JTextField commandField = new JTextField(40);
	private static JTextArea commandOutput = new JTextArea(15, 30);
	private static TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
			commandOutput, Strings.CONSOLE);
	public static String command;
	public static final int HEIGHT = 700;
	public static final int WIDTH = 305;

	public static void makeFrame() {
		JFrame commandWindow = new JFrame(Strings.ENTER_COMMAND);
		commandWindow.setLayout(new GridLayout(1, 1));
		commandWindow.setSize(new Dimension(HEIGHT, WIDTH));
		commandWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		commandWindow.setResizable(false);
		commandWindow.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setSize(HEIGHT, WIDTH);
		JLabel label = new JLabel();
		label.setText(Strings.COMMAND_LABEL);
		JButton submit = new JButton();
		submit.setText(Strings.COMMAND_SUBMIT);
		submit.setSize(30, 20);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				Jukebox.selectEffect.play();
				command = commandField.getText();
				if (command.equalsIgnoreCase(Strings.COMMAND_GOD)) {
					System.out.println(Strings.COMMAND_GOD_OUTPUT);
					God.godMode();
					clearField();

				}

				if (command.equalsIgnoreCase(Strings.COMMAND_RESTART)) {
					System.out.println(Strings.COMMAND_RESTART_OUTPUT);
					Restart.restartGame();
					clearField();
				}

				if (command.equalsIgnoreCase(Strings.COMMAND_OVERRIDE)) {
					System.out.println(Strings.COMMAND_RESTART_OUTPUT);
					clearField();
				}

				if (command.equalsIgnoreCase(Strings.COMMAND_NEXTSTATE)) {
					if (Game.State == STATE.MENU) {
						System.out.println(Strings.COMMAND_NEXTSTATE_OUTPUT_GAME);
						NextState.toggleState();
					} else if (Game.State == STATE.GAME) {
						System.out.println(Strings.COMMAND_NEXTSTATE_OUTPUT_MENU);
						NextState.toggleState();
					}

					clearField();

				}

				if (command.equalsIgnoreCase(Strings.COMMAND_HELP)) {
					DisplayCommands.showHelp();

					clearField();
				}

				if (command.equalsIgnoreCase(Strings.COMMAND_EXIT)
						|| command.equalsIgnoreCase(Strings.COMMAND_QUIT)) {
					System.exit(0);
				}

				if (command.equalsIgnoreCase(Strings.COMMAND_SCORE)) {
					SetScore.addScore1();

					clearField();
				}


			}
		});
		panel.add(label);
		panel.add(commandField);
		panel.add(submit);
		System.setOut(new PrintStream(taOutputStream));
		commandOutput.setEditable(false);
		panel.add(new JScrollPane(commandOutput,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		commandWindow.getContentPane().add(panel);
		commandWindow.setVisible(true);
	}

	public static void submit_ActionPerformed(ActionEvent evt) {
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				makeFrame();
			}
		});
	}

	private static void clearField() {
		commandField.selectAll();
		commandField.cut();
	}
}