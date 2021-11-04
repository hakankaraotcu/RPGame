package dev.pixel.tiles;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dev.pixel.gfx.Sprite;
import dev.pixel.utils.Camera;

public class TileManager {

	public static ArrayList<TileMap> tileManager;
	public Camera cam;
	
	public TileManager() {
		tileManager = new ArrayList<TileMap>();
	}
	
	public TileManager(String path, Camera cam) {
		tileManager = new ArrayList<TileMap>();
		addTile(path, 64, 64, cam);
	}
	
	public TileManager(String path, int blockWidth, int blockHeight, Camera cam) {
		tileManager = new ArrayList<TileMap>();
		addTile(path, blockWidth, blockHeight, cam);
	}
	
	private void addTile(String path, int blockWidth, int blockHeight, Camera cam) {
		this.cam = cam;
		String imagePath;
		
		int width = 0;
		int height = 0;
		int tileWidth;
		int tileHeight;
		int tileColumns;
		int layers = 0;
		Sprite sprite;
		
		String[] data = new String[10];
		
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
			doc.getDocumentElement().normalize();
			
			NodeList list = doc.getElementsByTagName("tileset");
			Node node = list.item(0);
			Element eElement = (Element) node;
			
			imagePath = eElement.getAttribute("name");
			tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
			tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
			tileColumns = Integer.parseInt(eElement.getAttribute("columns"));
			sprite = new Sprite("worlds/" + imagePath + ".png", tileWidth, tileHeight);
			
			list = doc.getElementsByTagName("layer");
			layers = list.getLength();
			
			for(int i = 0;i < layers;i++) {
				node = list.item(i);
				eElement = (Element) node;
				if(i <= 0) {
					width = Integer.parseInt(eElement.getAttribute("width"));
					height = Integer.parseInt(eElement.getAttribute("height"));
				}
				
				data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
				
				if(i >= 1) {
					tileManager.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}else {
					tileManager.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}
				
				cam.setLimit(width * blockWidth, height * blockHeight);
			}
		}catch(Exception e) {
			System.out.println("Error - TILEMANAGER: can not read tilemap");
		}
	}
	
	public void render(Graphics g) {
		if(cam == null) {
			return;
		}
		for(int i = 0;i < tileManager.size();i++) {
			tileManager.get(i).render(g, cam.getBounds());
		}
	}
}
