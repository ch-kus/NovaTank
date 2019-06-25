package NovaTankProj;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PowerUp extends JPanel{

	private int x;
	private int y;
	
	private BufferedImage img;
	
	public PowerUp(int x, int y) {
		this.x = x;
		this.y = y;
		imageRead();
	}
	
	public void imageRead() {
		try {
			img = ImageIO.read(new File("image/novaCrate.png"));
		}
		
		catch (IOException e) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, x, y, this);
	}
	
	public boolean isCollected(NovaTank nt) {
		if (x == nt.getX() && y == nt.getY()) {
			return true;
		}
		
		else return false;
	}
	
	public boolean isCollected(Level2 nt) {
		if (nt.getX() >= x && nt.getY() == y) {
			return true;
		}
		
		else return false;
	}
	
	public boolean isCollected(Level3 nt) {
		if (x == nt.getX() && y == nt.getY()) {
			return true;
		}
		
		else return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
}
