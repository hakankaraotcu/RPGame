package dev.pixel.tiles;

import java.awt.Graphics;

import dev.pixel.math.AABB;

public abstract class TileMap {

	public abstract void render(Graphics g, AABB cam);
}
