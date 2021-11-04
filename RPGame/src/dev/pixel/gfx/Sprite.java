package dev.pixel.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dev.pixel.math.Vector2f;

public class Sprite {

	private BufferedImage SPRITESHEET = null;
	private BufferedImage[][] spriteArray;
	private final int TILE_SIZE = 32;
	public int width;
	public int height;
	private int spriteWidth;
	private int spriteHeight;
	
	private static Font currentFont;
	
	public Sprite(String file) {
		width = TILE_SIZE;
		height = TILE_SIZE;
		
		System.out.println("Loading: " + file + "...");
		SPRITESHEET = loadSprite(file);
		
		spriteWidth = SPRITESHEET.getWidth() / width;
		spriteHeight = SPRITESHEET.getHeight() / height;
		loadSpriteArray();
	}
	
	public Sprite(String file, int width, int height) {
		this.width = width;
		this.height = height;
		
		System.out.println("Loading: " + file + "...");
		SPRITESHEET = loadSprite(file);
		
		spriteWidth = SPRITESHEET.getWidth() / width;
		spriteHeight = SPRITESHEET.getHeight() / height;
		loadSpriteArray();
	}
	
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int width) {
		this.width = width;
		spriteWidth = SPRITESHEET.getWidth() / width;
	}
	
	public void setHeight(int height) {
		this.height = height;
		spriteHeight = SPRITESHEET.getHeight() / height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage loadSprite(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		}catch(Exception e) {
			System.out.println("Error: could not load file: " + file);
		}
		
		return sprite;
	}
	
	public void loadSpriteArray() {
		spriteArray = new BufferedImage[spriteHeight][spriteWidth];
		
		for(int y = 0;y < spriteHeight;y++) {
			for(int x = 0;x < spriteWidth;x++) {
				spriteArray[y][x] = getSprite(x, y);
			}
		}
	}
	
	public BufferedImage getSpriteSheet() {
		return SPRITESHEET;
	}
	
	public BufferedImage getSprite(int x, int y) {
		return SPRITESHEET.getSubimage(x * width, y * height, width, height);
	}
	
	public BufferedImage[] getSpriteArray(int i) {
		return spriteArray[i];
	}
	
	public BufferedImage[][] getSpriteArray2(int i){
		return spriteArray;
	}
	
	public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
		float x = pos.x;
		float y = pos.y;
		
		for(int i = 0; i < img.size(); i++) {
			if(img.get(i) != null) {
				g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
			}
			x += xOffset;
			y += yOffset;
		}
	}
	
	public static void drawArray(Graphics2D g, String word, Vector2f pos, int size) {
		drawArray(g, currentFont, word, pos, size, size ,size, 0);
	}
	
	public static void drawArray(Graphics2D g, String word, Vector2f pos, int size, int xOffset) {
		drawArray(g, currentFont, word, pos, size, size ,xOffset, 0);
	}
	
	public static void drawArray(Graphics2D g, String word, Vector2f pos, int width, int height, int xOffset) {
		drawArray(g, currentFont, word, pos, width, height, xOffset, 0);
	}
	
	public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int size, int xOffset) {
		drawArray(g, f, word, pos, size, size ,xOffset, 0);
	}
	
	public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset) {
		float x = pos.x;
		float y = pos.y;
		
		currentFont = f;
		
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) != 32) {
				g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
			}
			x += xOffset;
			//y += yOffset;
		}
	}
}
