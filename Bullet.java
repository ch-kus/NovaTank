package NovaTankProj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {

	private double x;
	private double y;
	private int damage;
	
	BufferedImage image;
	double number, number2;
	
	public void imageReader() {
		
		try {
			image = ImageIO.read(new File("image/bulletR.png"));
		}
		
		catch (IOException e) {
			
		}
	}
	
	public Bullet(double x, double y, int num, Object nt) {
		this.x = x;
		this.y = y;
		damage = num;
	}
	
	public void tick() {
		x += 125;
		number = x;
		number2 = y;
	}
	
	public void render(Graphics g) {
		imageReader();
		g.drawImage(image, (int) x, (int) y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 28, 28);
	}
	
	public int getX() {
		return (int)number;
	}
	
	public int getY() {
		return (int)number2;
	}
	
}
