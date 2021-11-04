package dev.pixel.tiles.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;

public class NormBlock extends Block {

	public NormBlock(BufferedImage texture, Vector2f pos, int width, int height) {
		super(texture, pos, width, height);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

	@Override
	public boolean update(AABB p) {
		return false;
	}

	@Override
	public boolean isInside(AABB p) {
		return false;
	}

}
