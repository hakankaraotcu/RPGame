package dev.pixel.math;


public class AABB {
	
	private Vector2f pos;
	private float xOffset = 0;
	private float yOffset = 0;
	private float width;
	private float height;
	private float radius;
	private int size;
	
	public AABB(Vector2f pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		
		size = Math.max(width, height);
	}
	
	public AABB(Vector2f pos, int radius) {
		this.pos = pos;
		this.radius = radius;
		
		size = radius;
	}
	
	public boolean collides(AABB bBox) {
		float ax = ((pos.getWorldVar().x + (xOffset)) + (this.width / 2));
		float ay = ((pos.getWorldVar().y + (yOffset)) + (this.width / 2));
		float bx = ((bBox.getPos().getWorldVar().x + (bBox.getXOffset())) + (bBox.getWidth() / 2));
		float by = ((bBox.getPos().getWorldVar().y + (bBox.getYOffset())) + (bBox.getHeight() / 2));
		
		if(Math.abs(ax - bx) < (this.width / 2) + (bBox.getWidth()/ 2)){
			if(Math.abs(ay - by) < (this.height / 2) + (bBox.getHeight() / 2)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean colCircleBox(AABB aBox) {
		float dx = Math.max(aBox.getPos().getWorldVar().x + aBox.getXOffset(), Math.min(pos.getWorldVar().x + (radius / 2), aBox.getPos().getWorldVar().x + aBox.getXOffset() + aBox.getWidth()));
		float dy = Math.max(aBox.getPos().getWorldVar().y + aBox.getYOffset(), Math.min(pos.getWorldVar().y + (radius / 2), aBox.getPos().getWorldVar().y + aBox.getXOffset() + aBox.getHeight()));
		
		dx = pos.getWorldVar().x + (radius / 2) - dx;
		dy = pos.getWorldVar().y + (radius / 2) - dy;
		
		if(Math.sqrt(dx * dx + dy * dy) < radius / 2) {
			return true;
		}
		return false;
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getXOffset() {
		return xOffset;
	}
	
	public float getYOffset() {
		return yOffset;
	}
	
	public void setBox(Vector2f pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		
		size = Math.max(width, height);
	}
	
	public void setCircle(Vector2f pos, int radius) {
		this.pos = pos;
		this.radius = radius;
		
		size = radius;
	}
	
	public void setWidth(float f) {
		width = f;
	}
	
	public void setHeight(float f) {
		height = f;
	}
	
	public void setXOffset(float f) {
		xOffset = f;
	}
	
	public void setYOffset(float f) {
		yOffset = f;
	}
}
