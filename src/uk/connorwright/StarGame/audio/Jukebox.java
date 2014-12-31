package uk.connorwright.StarGame.audio;

import java.applet.Applet;
import java.applet.AudioClip;

public class Jukebox {

	public static final Jukebox laserFired = new Jukebox("/music/laser.wav");
	public static final Jukebox selectEffect = new Jukebox("/music/select.wav");
	public static final Jukebox loseSound = new Jukebox("/music/lose.wav");
	public static final Jukebox enemyHit = new Jukebox(
			"/music/sfx/enemy_hit.wav");
	public static final Jukebox gameWin = new Jukebox("/music/sfx/game_win.wav");
	public static final Jukebox playerHit = new Jukebox(
			"/music/sfx/enemy_hit.wav");
	public static final Jukebox shaunEgg1 = new Jukebox(
			"/music/easter_eggs/shaun_1.wav");
	public static final Jukebox shaunEgg2 = new Jukebox(
			"/music/easter_eggs/shaun_2.wav");
	public static final Jukebox menumusic = new Jukebox("/music/menu_music.wav");

	private AudioClip clip;

	public Jukebox(String filename) {
		try {
			clip = Applet.newAudioClip(Jukebox.class.getResource(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				@Override
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void stop() {
		try {
			new Thread() {
				@Override
				public void run() {
					clip.stop();
				}
			}.start();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void loop() {
		try {
			new Thread() {
				@Override
				public void run() {
					clip.loop();
				}
			}.start();
		} catch (Exception exce) {
			exce.printStackTrace();
		}
	}

}
