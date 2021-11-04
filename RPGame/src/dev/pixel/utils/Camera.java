package dev.pixel.utils;

import java.awt.Color;
import java.awt.Graphics;

import dev.pixel.entities.Entity;
import dev.pixel.game.GamePanel;
import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;
import dev.pixel.states.PlayState;

public class Camera {

	private AABB collisionCam;
	private AABB bounds;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private float dx;
	private float dy;
	private float maxSpeed = 8f;
	private float acc = 3f;
	private float deacc = 0.3f;
	
	private int widthLimit;
	private int heightLimit;
	
	private int tileSize = 64;
	
	private Entity e;
	
	public Camera(AABB collisionCam) {
		this.collisionCam = collisionCam;
	}
	
	public void update() {
		move();
		if(!e.xCol) {
			if((e.getBounds().getPos().getWorldVar().x + e.getDx()) < Vector2f.getWorldVarX(widthLimit - GamePanel.width / 2) - tileSize && (e.getBounds().getPos().getWorldVar().x + e.getDx()) > Vector2f.getWorldVarX(GamePanel.width / 2 - tileSize)) {
				PlayState.map.x += dx;
				collisionCam.getPos().x += dx;
			}
		}
		if(!e.yCol) {
			if((e.getBounds().getPos().getWorldVar().y + e.getDy()) < Vector2f.getWorldVarY(heightLimit - GamePanel.height / 2) - tileSize && (e.getBounds().getPos().getWorldVar().y + e.getDy()) > Vector2f.getWorldVarY(GamePanel.height / 2 - tileSize)) {
				PlayState.map.y += dy;
				collisionCam.getPos().y += dy;
			}
		}/*
		if(PlayState.map.x < 0) {
			PlayState.map.x = 0;
		}
		if(PlayState.map.y < 0) {
			PlayState.map.y = 0;
		}
		if(PlayState.map.x > widthLimit - GamePanel.width) {
			PlayState.map.x = widthLimit - GamePanel.width;
		}
		if(PlayState.map.y > heightLimit - GamePanel.height) {
			PlayState.map.y = heightLimit - GamePanel.height;
		}*/
	}
	
	public void setLimit(int widthLimit, int heightLimit) {
		this.widthLimit = widthLimit;
		this.heightLimit = heightLimit;
	}
	
	public AABB getBounds() {
		return collisionCam;
	}
	
	public Vector2f getPos() {
		return collisionCam.getPos();
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
	
	public void target(Entity e) {
		this.e = e;
		if(e != null) {
			acc = e.getAcc();
			deacc = e.getDeacc();
			maxSpeed = e.getMaxSpeed();
		}else {
			acc = 3;
			deacc = 0.3f;
			maxSpeed = 8;
		}
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		if(e == null) {
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
		}else {
            if (!e.yCol) {
                if (PlayState.map.y + GamePanel.height / 2 - e.getSize() / 2 + dy > e.getBounds().getPos().y + e.getDy() + 2) {
                    up = true;
                    down = false;
                } else if (PlayState.map.y + GamePanel.height / 2 - e.getSize() / 2 + dy < e.getBounds().getPos().y + e.getDy() - 2) {
                    down = true;
                    up = false;
                } else {
                    dy = 0;
                    up = false;
                    down = false;
                }
            }

            if (!e.xCol) {
                if (PlayState.map.x + GamePanel.width / 2 - e.getSize() / 2 + dy > e.getBounds().getPos().x + e.getDx() + 2) {
                    left = true;
                    right = false;
                } else if (PlayState.map.x + GamePanel.width / 2 - e.getSize() / 2 + dx < e.getBounds().getPos().x + e.getDx() - 2) {
                    right = true;
                    left = false;
                } else {
                    dx = 0;
                    right = false;
                    left = false;
                }
            }
        }
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int) collisionCam.getPos().x, (int) collisionCam.getPos().y, (int) collisionCam.getWidth(), (int) collisionCam.getHeight());
		
		/*g.setColor(Color.magenta); 
        g.drawLine(GamePanel.width / 2, 0, GamePanel.width / 2, GamePanel.height); 
        g.drawLine(0, GamePanel.height / 2, GamePanel.width,GamePanel.height / 2);*/
	}
}
