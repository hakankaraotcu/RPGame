package dev.pixel.gfx;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Font {

	private BufferedImage FONTSHEET = null;
	private BufferedImage[][] spriteArray;
	private final int TILE_SIZE = 32;
	public int width;
	public int height;
	private int letterWidth;
	private int letterHeight;
	
	public Font(String file) {
		width = TILE_SIZE;
		height = TILE_SIZE;
		
		System.out.println("Loading: " + file + "...");
		FONTSHEET = loadFont(file);
		
		letterWidth = FONTSHEET.getWidth() / width;
		letterHeight = FONTSHEET.getHeight() / height;
		loadFontArray();
	}
	
	public Font(String file, int width, int height) {
		this.width = width;
		this.height = height;
		
		System.out.println("Loading: " + file + "...");
		FONTSHEET = loadFont(file);
		
		letterWidth = FONTSHEET.getWidth() / width;
		letterHeight = FONTSHEET.getHeight() / height;
		loadFontArray();
	}
	
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int width) {
		this.width = width;
		letterWidth = FONTSHEET.getWidth() / width;
	}
	
	public void setHeight(int height) {
		this.height = height;
		letterHeight = FONTSHEET.getHeight() / height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage loadFont(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		}catch(Exception e) {
			System.out.println("Error: could not load file: " + file);
		}
		
		return sprite;
	}
	
	public void loadFontArray() {
		spriteArray = new BufferedImage[letterWidth][letterHeight];
		
		for(int x = 0; x < letterWidth; x++) {
			for(int y = 0; y < letterHeight; y++) {
				spriteArray[x][y] = getLetter(x, y);
			}
		}
	}
	
	public BufferedImage getFontSheet() {
		return FONTSHEET;
	}
	
	public BufferedImage getLetter(int x, int y) {
		return FONTSHEET.getSubimage(x * width, y * height, width, height);
	}
	
	public BufferedImage getFont(char letter) {
		int value = letter;
		int x = value % letterWidth;
		int y = value / letterWidth;
		
		return getLetter(x, y);
	}
}
