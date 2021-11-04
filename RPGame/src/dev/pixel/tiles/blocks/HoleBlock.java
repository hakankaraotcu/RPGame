package dev.pixel.tiles.blocks;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;

public class HoleBlock extends Block {

	public HoleBlock(BufferedImage texture, Vector2f pos, int width, int height) {
		super(texture, pos, width, height);
	}

	public boolean isSolid() {
		return false;
	}
	
	public void render(Graphics g) {
		super.render(g);
		/*g.setColor(Color.GREEN);
		g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, width, height);*/
	}

	@Override
	public boolean update(AABB p) {
		return false;
	}
	
	public boolean isInside(AABB p) {
		if(p.getPos().x + p.getXOffset() < pos.x) {
			return false;
		}
		if(p.getPos().y + p.getYOffset() < pos.y) {
			return false;
		}
		if(width + pos.x < p.getWidth() + (p.getPos().x + p.getXOffset())) {
			return false;
		}
		if(height + pos.y < p.getHeight() + (p.getPos().y + p.getYOffset())) {
			return false;
		}
		return true;
	}
	
	
}

