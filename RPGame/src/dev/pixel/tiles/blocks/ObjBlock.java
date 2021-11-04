package dev.pixel.tiles.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;

public class ObjBlock extends Block {

	public ObjBlock(BufferedImage texture, Vector2f pos, int width, int height) {
		super(texture, pos, width, height);
	}

	public boolean isSolid() {
		return true;
	}
	
	public void render(Graphics g) {
		super.render(g);
		/*g.setColor(Color.WHITE);
		g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, width, height);*/
	}

	@Override
	public boolean update(AABB p) {
		return true;
	}

	@Override
	public boolean isInside(AABB p) {
		return false;
	}
	
}

