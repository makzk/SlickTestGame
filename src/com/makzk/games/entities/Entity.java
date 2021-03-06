package com.makzk.games.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import com.makzk.games.Main;
import com.makzk.games.elements.Level;
import com.makzk.games.util.Camera;
import com.makzk.games.util.Direction;
import org.newdawn.slick.Image;

public abstract class Entity implements Comparable<Entity> {
	protected GameContainer gc;
	protected Main game;
	protected float speedX = 0;
	protected float speedY = 0;
	protected Color color = Color.transparent;

    protected Image bgImage = null;
	protected boolean keepOnScreen = false;
	protected boolean gravity = false;
	protected boolean solid = true;
	protected boolean onGround = false;
	protected Level level;
	protected float gravityImpulse = .03f;
	protected boolean wall = false;
	protected float nextX = 0;
	protected float nextY = 0;
	protected boolean enabled = true;
	protected long lastTimeOnGround = 0;
    protected boolean isStatic = true;
    protected int zIndex = 0;

	public Entity(GameContainer gc, Main game) {
		this.gc = gc;
		this.game = game;
	}

    public int compareTo(Entity other) {
        return ((Integer)this.zIndex).compareTo(other.getzIndex());
    }

	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color == null ? Color.transparent : color; }

	public boolean isGravityAffected() { return gravity; }
	public boolean isSolid() { return solid; }
    public void setSolid(boolean solid) { this.solid = solid; }
	public boolean isTouchingWall() { return wall; }
	public boolean isOnGround() { return onGround; }

	public float getSpeedX() { return speedX; }
	public void setSpeedX(float speedX) { this.speedX = speedX; }
	public float getSpeedY() { return speedY; }
	public void setSpeedY(float speedY) { this.speedY = speedY; }

	public void setLevel(Level level) { this.level = level; }
	public Level getLevel(){ return level; } 
	
	public void setEnabled(boolean enabled) { this.enabled = enabled; }
	public boolean isEnabled() { return enabled; }

    public Image getBgImage() {  return bgImage; }
    public void setBgImage(Image bgImage) { this.bgImage = bgImage; }

    public abstract void reset();
	
	public abstract void onCollision(Direction dir, Entity other);
	public abstract float getX();
	public abstract float getY();
	public abstract void setX(float x);
	public abstract void setY(float y);
	public abstract float getMaxX();
	public abstract float getMaxY();
	public abstract void draw();
	public abstract void draw(Camera cam);
	public abstract void move(int delta);
	public abstract void move(int delta, Level lv);

    public boolean isStatic() { return isStatic; }
    public void setStatic(boolean isStatic) { this.isStatic = isStatic; }

    public int getzIndex() { return zIndex; }
    public void setzIndex(int zIndex) { this.zIndex = zIndex; }
}
