package NovaTankProj;

import java.awt.Rectangle;

public class FlyingRectangle extends Rectangle{
	
	private int x, y, width, height;
	
	public FlyingRectangle(int x, int y, int width, int height ) {
		
		super(x, y, width, height);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void move() {
		y = y + 5;
	}
}
