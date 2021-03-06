package uk.connorwright.StarGame;

import java.awt.Graphics;
import java.awt.Rectangle;

import uk.connorwright.StarGame.audio.Jukebox;
import uk.connorwright.StarGame.entities.EntityA;
import uk.connorwright.StarGame.entities.EntityB;

public class Player extends GameObject implements EntityA {

	// define the variables

	private double velX = 0;
	private double velY = 0;
	Game game;
	Controller controller;

	private final Textures tex;

	public Player(double x, double y, Textures tex, Game game,
			Controller controller) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;

	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (x <= 0)
			x = 0;
		if (x >= 640)
			x = 640;
		if (y <= 0)
			y = 0;
		if (y >= 480 - 15)
			y = 480 - 15;
		for (int i = 0; i < game.eb.size(); i++) {
			EntityB tempEnt = game.eb.get(i);

			if (Physics.Collision(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				Jukebox.playerHit.play();
				Game.HEALTH -= 10;
				game.setEnemy_kill(game.getEnemy_kill() + 1);
			}
		}

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 15, 15);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(tex.player, (int) x, (int) y, null);
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

}
