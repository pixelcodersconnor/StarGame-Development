package uk.connorwright.StarGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import uk.connorwright.StarGame.util.Strings;

public class Menu {


	Random random = new Random();

	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100,
			50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100,
			50);
	public Rectangle bugsButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100,
			50);

	public JTextComponent info = new JTextArea();
	public JTextComponent randtext = new JTextArea();

	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Font space = new Font("SpaceBang", Font.BOLD, 60);
		g.setFont(space);
		g.setColor(Color.white);
		g.drawString("StarGame", 150, 100);

		Font space1 = new Font("SpaceBang", Font.BOLD, 30);
		g.setFont(space1);
		g.drawString(Strings.PLAY, playButton.x + 8, playButton.y + 35);
		g2d.draw(playButton);

		g.drawString(Strings.QUIT, quitButton.x, quitButton.y + 35);
		g2d.draw(quitButton);

		Font spaceFooter = new Font("Arial", Font.ITALIC, 16);
		g.setFont(spaceFooter);
		g.drawString(Strings.FOOTER, 475, 470);

		Font r1 = new Font("Arial", Font.ITALIC, 14);
		g.setFont(r1);

		Font version = new Font("SpaceBang", Font.ITALIC, 17);
		g.setFont(version);
		g.drawString(Strings.VERSION_TEXT + Strings.VERSION, 10, 470);

		Font bugs = new Font("SpaceBang", Font.BOLD, 30);
		g.setFont(bugs);
		g.drawString(Strings.BUGS, bugsButton.x + 5, bugsButton.y + 35);
		g2d.draw(bugsButton);

		g.setFont(version);
		g.drawString(Strings.BETA, 440, 126);

	}

}
