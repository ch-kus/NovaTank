package NovaTankProj;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FrostShot2 {

	private double x;
	private double y;
	
	BufferedImage image;
	
	public void imageReader() {
		
		try {
			image = ImageIO.read(new File("image/frost.png"));
		}
		
		catch (IOException e) {
			
		}
	}
	
	public FrostShot2(double x, double y, Object nt) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		x -= 285;
	}
	
	public void render(Graphics g) {
		imageReader();
		g.drawImage(image, (int) x, (int) y, null);
	}
}
