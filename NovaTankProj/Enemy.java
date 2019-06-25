package NovaTankProj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Enemy extends JPanel{

	private int x;
	private int y;
	  
	private int health;
	int h;
	
	private BufferedImage en;
	
	int num;
	
	public Enemy(int x, int y, int health, int tankDamage) {
		
		this.x = x;
		this.y = y;
		this.health = health;

		try {
			en = ImageIO.read(new File("image/enemy.png"));
		}
		
		catch (IOException e) {
			
		}
		
		num = health / tankDamage;
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
	
	public BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
	    BufferedImage bi = null;
	    try {
	        ImageIcon ii = new ImageIcon(filename);//path to image
	        bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = (Graphics2D) bi.createGraphics();
	        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	        g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return bi;
	}
}
