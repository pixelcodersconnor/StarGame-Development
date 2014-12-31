// Game made by Connor Wright
package uk.connorwright.StarGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uk.connorwright.StarGame.audio.Jukebox;
import uk.connorwright.StarGame.entities.EntityA;
import uk.connorwright.StarGame.entities.EntityB;
import uk.connorwright.StarGame.util.Strings;
import uk.connorwright.StarGame.window.Window;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// define the variables
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public boolean running = false;
	private static Thread thread;
	private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private Player p;
	private Controller c;
	private Textures tex;
	private BufferedImage background = null;
	private boolean is_shooting = false;
	BufferedImageLoader loader = new BufferedImageLoader();
	private int enemy_count = 1;
	public static int enemy_kill = 0;
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	private Menu menu;
	public int times_died = 0;
	public static int HEALTH = 100 * 2;
	public static final String FONT_LOCATION = "/font/";
	public static boolean lost = false;
	private Font font2;
	public static int score;
	public int number;
	public Rectangle rec = new Rectangle(this.getBounds());

	public static enum STATE {
		MENU, GAME, LAUNCHER, BUGS,
	};

	public static STATE State = STATE.MENU;

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_kill() {
		return enemy_kill;
	}

	public void setEnemy_kill(int enemy_kill) {
		Game.enemy_kill = enemy_kill;
	}

	// temp draw to screen
	private BufferedImage player;

	// begin init method
	public void init() {

		requestFocus();
		Game.loadFonts();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(200, 200, tex, this, c);
		menu = new Menu();

		checkMenu();

		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		ea = c.getEntityA();
		eb = c.getEntityB();

		c.addEnemy(enemy_count);

	}

	private static void checkMenu() {

		Jukebox.menumusic.loop();
	}

	public static void loadFonts() {
		Fonts.addFont(new Fonts("/SpaceBang.ttf"));
	}

	// begin start loop (mainly for applet)
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	// begin stop loop (mainly for applet)
	public synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.exit(1);

	}

	// begin run method
	@Override
	public void run() {
		init();
		while (running) {
			long lastTime = System.nanoTime();
			final double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			int updates = 0;
			int frames = 0;
			long timer = System.currentTimeMillis();
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				if (delta > -1) {
					tick();
					updates++;
					delta--;
				}
				render();
				frames++;

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println(updates + Strings.TICK_COUNTER
							+ Strings.FPS_COUNTER + frames);
					updates = 0;
					frames = 0;
				}

			}

		}
		stop();

	}

	// begin tick method
	private void tick() {

		if (State == STATE.GAME) {
			p.tick();
			c.tick();

		}

		if (enemy_kill >= enemy_count) {
			enemy_count += 1;
			enemy_kill = 0;
			c.addEnemy(enemy_count);
		}

		if (HEALTH <= 0) {
			Jukebox.loseSound.play();
			times_died++;
			State = STATE.MENU;
			resetGame();

		}

		if (score <= -5) {
			Jukebox.loseSound.play();
			times_died++;
			State = STATE.MENU;
			resetGame();

		}

		if (times_died >= 5) {
			times_died = 0;
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int healthBoostMsg = JOptionPane.showConfirmDialog(null,
					Strings.HEALTH_BOOST, Strings.HEALTH_BOOST_TITLE,
					dialogButton);
			if (healthBoostMsg == JOptionPane.YES_OPTION) {
				// System.out.println("Extra health");
				HEALTH = 300;

			}
		}

	}

	private void resetGame() {

		enemy_count = 0;
		enemy_kill = 0;
		HEALTH = 200;
		score = 0;
	}

	// begin render method
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// ///////////////<RENDERING>/////////////////////

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		g.drawImage(player, 100, 100, this);

		g.drawImage(background, 0, 0, null);

		if (State == STATE.GAME) {
			p.render(g);
			c.render(g);

			g.setColor(Color.white);
			g.drawString(Strings.HEALTH + HEALTH, 25, 40);

			g.setColor(Color.white);
			g.drawString(Strings.SCORE + score, 565, 40);
		} else if (State == STATE.MENU) {
			menu.render(g);
			drawSplash(g);
		}

		g.dispose();
		bs.show();
	}

	private void drawSplash(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(font2);
		g.drawString(itemDisplayed, 150, 120);

	}

	// ///////////////</RENDERING>/////////////////////

	// Define the splash texts which will be displayed within the game menu

	private String SPLASHTEXTS[] = { "Buggy", "New Game", "Roll up roll up",
			"Made from scratch", "Awesome", "is epic", "Hello", "BETA", "Woof",
			"Meow", "Oink", "SHAUN", "Easter eggs galore", "Do a barrell roll",
			"FTW", "SNAKE", "null", "undefined", "Hello world", };

	private String itemDisplayed = chooseItem();

	private String chooseItem() {
		Random r = new SecureRandom();
		int i = r.nextInt(SPLASHTEXTS.length);
		return SPLASHTEXTS[i];
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
			} else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				p.setVelY(-5);
			} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
				Jukebox.laserFired.play();
			} else if (key == KeyEvent.VK_0) {
				Game.HEALTH = 0;
			} else if (key == KeyEvent.VK_ESCAPE) {
				// Bring up the menu GUI
				uk.connorwright.StarGame.menu.Menu.main(null);
			} else if (key == KeyEvent.VK_I) {
				HEALTH = 1000;
			} else if (key == KeyEvent.VK_E) {
				Random random1 = new Random();
				int result = random1.nextInt(2 + 1);

				if (result != 1 && result != 2) {
					result = 1;
				}

				if (result == 1) {
					Jukebox.shaunEgg1.play();
				}

				if (result == 2) {
					Jukebox.shaunEgg2.play();

				}

			} else if (key == KeyEvent.VK_T) {
				Window.makeFrame();
			} else if (key == KeyEvent.VK_1) {
				HEALTH = 20;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;

		}
	}

	// begin main method
	public static void main(String[] args) {
		Splash();

	}

	// initialise the splash screen
	public static void Splash() {
		SplashScreen splash = new SplashScreen(3000);
		splash.showSplash();
		startGame();
	}

	// start game
	public static void startGame() {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(Strings.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	// begin bufferedimage method
	public BufferedImage getSpriteSheet() {
		return spriteSheet;

	}
}
