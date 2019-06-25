package NovaTankProj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DeadBot extends JPanel{

	private int x;
	private int y;
	  
	private int health;
	int h;
	
	private BufferedImage en;
	
	public DeadBot(int x, int y, int health) {
		
		this.x = x;
		this.y = y;
		this.health = health;

		try {
			en = ImageIO.read(new File("image/deadBot.png"));
		}
		
		catch (IOException e) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(en, x, y, this);
	}
	
	public Rectangle returnBounds() {
		return new Rectangle(x, y, 192, 192);
	}
	
	public void healthReduce(int i) {
		health -= i;
		h = health;
	}
	
	public int getHealth() {
		return h;
	}
	
	public void setNotVisible() {
		this.setVisible(false);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isAlive() {
		return h > 0;
	}
}
