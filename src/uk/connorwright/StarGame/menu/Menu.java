package uk.connorwright.StarGame.menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import uk.connorwright.StarGame.Game;
import uk.connorwright.StarGame.Game.STATE;
import uk.connorwright.StarGame.audio.Jukebox;
import uk.connorwright.StarGame.util.Strings;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JButton jButton1 = new JButton();
	private final JButton jButton2 = new JButton();
	private final JButton jButton3 = new JButton();
	private final JButton jButton4 = new JButton();

	public static boolean credtis;
	public static boolean running = false;
	private int credits = 0;

	@SuppressWarnings("static-access")
	public Menu(String title) {

		super(title);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 650;
		int frameHeight = 500;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);

		jButton1.setBounds(168, 80, 305, 57);
		jButton1.setText(Strings.TWEET_SCORE);
		jButton1.setMargin(new Insets(2, 2, 2, 2));
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				jButton1_ActionPerformed(evt);
			}
		});
		jButton1.setBackground(Color.WHITE);
		jButton1.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN,
				Color.BLACK));
		cp.add(jButton1);
		jButton2.setBounds(168, 168, 305, 57);
		jButton2.setText(Strings.HEALTH + Game.HEALTH + "");
		jButton2.setMargin(new Insets(2, 2, 2, 2));
		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println(Strings.HEALTH + Game.HEALTH);
			}
		});
		jButton2.setBackground(Color.WHITE);
		jButton2.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN,
				Color.BLACK));
		cp.add(jButton2);
		jButton3.setBounds(168, 256, 305, 57);
		jButton3.setText(Strings.CREDITS);
		jButton3.setMargin(new Insets(2, 2, 2, 2));
		jButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				jButton3_ActionPerformed(evt);
			}
		});
		jButton3.setBackground(Color.WHITE);
		jButton3.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN,
				Color.BLACK));
		cp.add(jButton3);
		cp.setBackground((Color.BLACK.brighter().WHITE.darker()));

		jButton4.setBackground(Color.WHITE);
		jButton4.setBorder(BorderFactory.createEtchedBorder(0, Color.GREEN,
				Color.BLACK));
		cp.add(jButton4);
		jButton4.setBounds(168, 346, 305, 57);
		jButton4.setText(Strings.START_GAME);
		jButton4.setMargin(new Insets(2, 2, 2, 2));
		jButton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				jButton4_ActionPerformed(evt);
			}
		});

		setVisible(true);
	}

	public void jButton1_ActionPerformed(ActionEvent evt) { // GO TO MAIN MENU
		// try {
		// TweetScore.Score();
		// } catch (TwitterException e) {
		// e.printStackTrace();
		// }
	}

	public void jButton3_ActionPerformed(ActionEvent evt) { // CREDITS
		credtis = true;
		if (credits == 0) {
			jButton3.setText(Strings.CRED1);
			Jukebox.selectEffect.play();
		}
		if (credits == 1) {
			jButton3.setText(Strings.CRED2);
			Jukebox.selectEffect.play();
		}
		if (credits == 2) {
			jButton3.setText(Strings.CRED3);
			Jukebox.selectEffect.play();
		}
		if (credits == 3) {
			jButton3.setText(Strings.CRED4);
			Jukebox.selectEffect.play();
		}
		if (credits == 4) {
			jButton3.setText(Strings.TBC);
			Jukebox.selectEffect.play();
		}
		if (credits == 5) {
			jButton3.setText(Strings.TBC);
			Jukebox.selectEffect.play();
		}
		if (credits == 6) {
			jButton3.setText(Strings.TBC);
			Jukebox.selectEffect.play();
		}
		if (credits == 7) {
			Jukebox.selectEffect.play();
			closeMenu();
		}
		credits++;
	}

	public void jButton4_ActionPerformed(ActionEvent evt) {
		// if there is no game running, start a new instance of the game!
		if (!running && Game.State != STATE.MENU) {
			Jukebox.selectEffect.play();
			Game.main(null);
		}

	}

	public void closeMenu() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

	}

	public static void main(String[] args) {
		new Menu(Strings.MENU_TITLE);
	}

}