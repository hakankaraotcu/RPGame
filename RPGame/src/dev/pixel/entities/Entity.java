package dev.pixel.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.pixel.gfx.Animations;
import dev.pixel.gfx.Sprite;
import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;
import dev.pixel.utils.TileCollision;

public abstract class Entity {
	
	protected final int FALLEN = 4;
	protected final int UP = 3;
	protected final int DOWN = 2;
	protected final int LEFT = 1;
	protected final int RIGHT = 0;
	protected int currentAnimation;
	
	protected Animations ani;
	protected Sprite sprite;
	protected Vector2f pos;
	protected int size;
	
	protected boolean up;
	protected boolean down;
	protected boolean right;
	protected boolean left;
	protected boolean attack;
	protected boolean fallen;
	protected int attackSpeed;
	protected int attackDuration;
	
	public boolean xCol = false;
	public boolean yCol = false;
	
	protected float dx;
	protected float dy;
	
	protected float maxSpeed = 5f;
	protected float acc = 4f;
	protected float deacc = 0.3f;
	
	protected AABB hitBounds;
	protected AABB bounds;
	
	protected TileCollision tileCollision;

	public Entity(Sprite sprite, Vector2f orgin, int size) {
		this.sprite = sprite;
		pos = orgin;
		this.size = size;
		
		bounds = new AABB(orgin, size, size);
		hitBounds = new AABB(orgin, size, size);
		hitBounds.setYOffset(size / 2);
		
		ani = new Animations();
		setAnimation(DOWN, sprite.getSpriteArray(DOWN), 10);
		
		tileCollision = new TileCollision(this);
	}
	
	public void setAnimation(int i, BufferedImage[] frames, int delay) {
		currentAnimation = i;
		ani.setFrames(frames);
		ani.setDelay(delay);
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void setFallen(boolean b) {
		fallen = b;
	}
	
	public void setSize(int i) {
		size = i;
	}
	
	public void setMaxSpeed(float f) {
		maxSpeed = f;
	}
	
	public void setAcc(float f) {
		acc = f;
	}
	
	public void setDeacc(float f) {
		deacc = f;
	}
	
	public float getAcc() {
		return acc;
	}
	
	public float getDeacc() {
		return deacc;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
	
	public AABB getBounds() {
		return bounds;
	}
	
	public int getSize() {
		return size;
	}
	
	public Animations getAnimation() {
		return ani;
	}
	
	public void animate() {
		if(up) {
			if(currentAnimation != UP || ani.getDelay() == -1) {
				setAnimation(UP, sprite.getSpriteArray(UP), 5);
			}
		}
		else if(down) {
			if(currentAnimation != DOWN || ani.getDelay() == -1) {
				setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
			}
		}
		else if(left) {
			if(currentAnimation != LEFT || ani.getDelay() == -1) {
				setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
			}
		}
		else if(right) {
			if(currentAnimation != RIGHT || ani.getDelay() == -1) {
				setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
			}
		}else if(fallen){
			if(currentAnimation != FALLEN || ani.getDelay() == -1) {
				setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
			}
		}else {
			setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
		}
	}
	
	private void setHitBoxDirection() {
		if(up) {
			hitBounds.setYOffset(-size / 2);
			hitBounds.setXOffset(0);
		}
		else if(down) {
			hitBounds.setYOffset(size / 2);
			hitBounds.setXOffset(0);
		}
		else if(left) {
			hitBounds.setXOffset(-size / 2);
			hitBounds.setYOffset(0);
		}
		else if(right) {
			hitBounds.setXOffset(size / 2);
			hitBounds.setYOffset(0);
		}
	}
	
	public void update() {
		animate();
		setHitBoxDirection();
		ani.update();
	}
	
	public abstract void render(Graphics2D g);

	public float getDx() {
		return dx;
	}

	public float getDy() {
		return dy;
	}
}
