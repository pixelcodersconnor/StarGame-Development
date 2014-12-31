package uk.connorwright.StarGame.applet;

import java.applet.Applet;
import java.awt.BorderLayout;

import uk.connorwright.StarGame.Game;

public class SGApplet extends Applet{
	private static final long serialVersionUID = 1L;

	private Game game = new Game();

	@Override
	public void init() {
		setLayout(new BorderLayout());
		add(game);
		this.setSize(600, 550); 
	}

	@Override
	public void start() {
		game.start();
	}

	@Override
	public void stop() {
		game.stop();
		System.exit(1);

	} 

}
