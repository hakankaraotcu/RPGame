package dev.pixel.tiles.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;

public abstract class Block {

	protected int width;
	protected int height;
	
	protected BufferedImage texture;
	protected Vector2f pos;
	
	public Block(BufferedImage texture, Vector2f pos,int width, int height) {
		this.texture = texture;
		this.pos = pos;
		this.width = width;
		this.height = height;
	}
	
	public abstract boolean update(AABB p);
	public abstract boolean isInside(AABB p);
	
	public Vector2f getPos() {
		return pos;
	}
	
	public void render(Graphics g) {
		g.drawImage(texture,(int) pos.getWorldVar().x, (int) pos.getWorldVar().y, width, height, null);
	}
}
