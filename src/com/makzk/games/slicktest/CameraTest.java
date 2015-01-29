package com.makzk.games.slicktest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.Log;

import com.makzk.games.Level;
import com.makzk.games.entities.Player;
import com.makzk.games.util.Camera;

public class CameraTest extends BasicGame {
	Level level;
	Camera cam;
	Player player;
	
	public CameraTest() {
		super("Camera Test");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		Log.info("Init level");
		level = new Level(gc, 2000);
		level.addRect(0, 440, level.getWidth(), 10, Color.gray);
		level.addRect(200, 350, 20, 100);
		level.addRect(220, 350, 100, 20);
		level.addRect(320, 270, 20, 100);
		level.addRect(340, 270, 100, 20);
		level.addRect(440, 340, 100, 15);

		level.addRect(800, 270, 300, 20);
		level.addRect(1100, 350, 300, 15);
		level.addRect(1200, 100, 150, 10);
		level.addRect(1100, 200, 150, 10);
		level.addRect(1400, 400, 300, 10);
		level.addRect(1500, 300, 300, 10);

		cam = new Camera(gc, level);
		
		Log.info("Init player");
		Rectangle r = new Rectangle(50, 300, 20, 50);
		player = new Player(gc, r);

		Log.info("Done");
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		level.drawAll(g, cam);
		player.draw(cam);
		
		g.setColor(Color.white);
		g.drawString(String.format("[PJPos] X: %.1f Y: %.1f", player.getRect().getX(), player.getRect().getY()), 10, 70);
		g.drawString(String.format("[CamPos] X: %.2f Y: %.2f", cam.getX(), cam.getY()), 10, 30);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input in = container.getInput();
		if(in.isKeyDown(Input.KEY_LEFT)) {
			cam.moveX(-.5f * delta);
		} else if(in.isKeyDown(Input.KEY_RIGHT)) {
			cam.moveX(.5f * delta);
		}

		player.move(level, delta);
	}

	public void keyPressed(int key, char c) {
		// Reiniciar la posición del jugador
		if(key == Input.KEY_R) {
			player.reset();
		}
	};

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new CameraTest());
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		} catch (SlickException e) {
			Log.error(null, e);
		}
	}
}
