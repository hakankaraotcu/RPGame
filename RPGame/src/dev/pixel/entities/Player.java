package dev.pixel.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import dev.pixel.game.GamePanel;
import dev.pixel.gfx.Sprite;
import dev.pixel.math.Vector2f;
import dev.pixel.states.PlayState;
import dev.pixel.utils.KeyHandler;
import dev.pixel.utils.MouseHandler;

public class Player extends Entity{

	public Player(Sprite sprite, Vector2f orgin, int size) {
		super(sprite, orgin, size);
		acc = 2f;
		maxSpeed = 3f;
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setXOffset(12);
		bounds.setYOffset(40);
	}
	
	private void move() {
		if(up) {
			dy -= acc;
			if(dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		}else {
			if(dy < 0) {
				dy += deacc;
				if(dy > 0) {
					dy = 0;
				}
			}
		}
		if(down) {
			dy += acc;
			if(dy > maxSpeed) {
				dy = maxSpeed;
			}
		}else {
			if(dy > 0) {
				dy -= deacc;
				if(dy < 0) {
					dy = 0;
				}
			}
		}
		if(left) {
			dx -= acc;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}else {
			if(dx < 0) {
				dx += deacc;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		if(right) {
			dx += acc;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}else {
			if(dx > 0) {
				dx -= deacc;
				if(dx < 0) {
					dx = 0;
				}
			}
		}
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		
		if(!fallen) {
			if(key.up.down) {
				up = true;
			}else {
				up = false;
			}
			if(key.down.down) {
				down = true;
			}else {
				down = false;
			}
			if(key.left.down) {
				left = true;
			}else {
				left = false;
			}
			if(key.right.down) {
				right = true;
			}else {
				right = false;
			}
			if(key.attack.down) {
				attack = true;
			}else {
				attack = false;
			}
		}else {
			up = false;
			down = false;
			right = false;
			left = false;
		}
	}
	
	private void resetPosition() {
		pos.x = GamePanel.width / 2 - 32;
		PlayState.map.x = 0;
		
		pos.y = GamePanel.height / 2 - 32;
		PlayState.map.y = 0;
		
		setAnimation(DOWN, sprite.getSpriteArray(DOWN), 10);
	}
	
	public void update(Enemy enemy) {
		super.update();
		
		if(attack && hitBounds.collides(enemy.getBounds())) {
			System.out.println("hit");
		}
		if(!fallen) {
			move();
			if(!tileCollision.collisionTile(dx, 0)) {
				PlayState.map.x += dx;
				pos.x += dx;
			}
			if(!tileCollision.collisionTile(0, dy)) {
				PlayState.map.y += dy;
				pos.y += dy;
			}
		}else {
			if(ani.hasPlayedOnce()) {
				resetPosition();
				dx = 0;
				dy = 0;
				fallen = false;
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
		g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
		
		if(attack) {
			g.setColor(Color.RED);
			g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getYOffset()), (int) hitBounds.getWidth(), (int) hitBounds.getHeight());
		}
	}
}
