package dev.pixel.tiles;

import java.awt.Graphics;

import dev.pixel.gfx.Sprite;
import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;
import dev.pixel.tiles.blocks.Block;
import dev.pixel.tiles.blocks.HoleBlock;
import dev.pixel.tiles.blocks.ObjBlock;

public class TileMapObj extends TileMap{
	
	public static Block[] event_blocks;
	
	private int tileWidth;
	private int tileHeight;
	
	public static int width;
	public static int height;

	public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
		Block tempBlock;
		event_blocks = new Block[width * height];
		
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		TileMapObj.width = width;
		TileMapObj.height = height;
		
		String[] block = data.split(",");
		for(int i = 0; i < (width * height); i++) {
			int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
			if(temp != 0) {
				if(temp == 172) {
					tempBlock = new HoleBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
				}else {
					tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
				}
				event_blocks[i] = tempBlock;
			}
		}
	}
	
	public void render(Graphics g, AABB cam) {
		int x = (int) ((cam.getPos().getCamVar().x) / tileWidth);
		int y = (int) ((cam.getPos().getCamVar().y) / tileHeight);
		for(int i = x; i < x + (cam.getWidth() / tileWidth); i++) {
			for(int j = y; j < y + (cam.getHeight() / tileHeight); j++) {
				if(i + (j * height) > -1 && i + (j * height) < event_blocks.length && event_blocks[i + (j * height)] != null) {
					event_blocks[i + (j * height)].render(g);
				}
			}
		}
	}
}
