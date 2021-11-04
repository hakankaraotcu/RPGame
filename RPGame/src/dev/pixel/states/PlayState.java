package dev.pixel.states;

import java.awt.Graphics2D;

import dev.pixel.entities.Enemy;
import dev.pixel.entities.Player;
import dev.pixel.game.GamePanel;
import dev.pixel.gfx.Font;
import dev.pixel.gfx.Sprite;
import dev.pixel.math.AABB;
import dev.pixel.math.Vector2f;
import dev.pixel.tiles.TileManager;
import dev.pixel.utils.Camera;
import dev.pixel.utils.KeyHandler;
import dev.pixel.utils.MouseHandler;

public class PlayState extends GameState{
	
	private Font font;
	private Player player;
	private Enemy enemy;
	private TileManager tileManager;
	private Camera cam;
	
	public static Vector2f map;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		map = new Vector2f();
		Vector2f.setWorldVar(map.x, map.y);
		
		cam = new Camera(new AABB(new Vector2f(0, 0), GamePanel.width + 64, GamePanel.height + 64));
		
		tileManager = new TileManager("worlds/tilemap.xml", cam);
		font = new Font("fonts/font.png", 10, 10);
		player = new Player(new Sprite("entity/linkformatted.png"),new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 64);
		enemy = new Enemy(new Sprite("entity/littlegirl.png", 48, 48), new Vector2f(0 + (GamePanel.width / 2) - 32 + 150, 0 + (GamePanel.height / 2) - 32 + 150), 64);
		cam.target(player);
	}

	@Override
	public void update() {
		Vector2f.setWorldVar(map.x, map.y);
		player.update(enemy);
		enemy.update(player);
		cam.update();
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
		cam.input(mouse, key);
	}

	@Override
	public void render(Graphics2D g) {
		tileManager.render(g);
		Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 192, 32), 32, 24);
		player.render(g);
		enemy.render(g);
		cam.render(g);
	}

}
