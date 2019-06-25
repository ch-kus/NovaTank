package NovaTankProj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.html.ImageView;


public class Level2 extends JPanel implements ActionListener, KeyListener{

	Timer t = new Timer(5, this);
	File f = new File("sound/fire.WAV");
	File f3 = new File("sound/gameover.WAV");
	File f4 = new File("sound/homescreen.WAV");
	
	private boolean condition;
	private int health, damage;
	
	int x = 0;
	int y = 0;
	double velX = 0;
	double velY = 0;
	
	private BufferedImage image, bac;
	private int deter;
	private Controller c = new Controller(this);
	private Controller2 c2 = new Controller2(this);

	private String facing;
	
	private ArrayList<Rectangle> obs = new ArrayList<Rectangle>();
	private Rectangle player;
	private Clip playing;
	private boolean poweredUp = false;
	
	public void imageReader(String str) {
		
		try  
		{
			image = ImageIO.read(new File(str));
			
			if (str.equals("image/tankLeft.png")) 
			{
				facing = "left";
			}
			
			if (str.equals("image/tankRight.png")) 
			{
				facing = "right";
			}
		} 
    		
		catch (IOException e1){}
	}
	
	public void imageReader2(String str) 
	{
		
		try  
		{
			bac = ImageIO.read(new File(str));
		} 
    		
		catch (IOException e1){}
	}
	
	public boolean cond = true;
	int y2 = 350;
	
	int x2 = 300;
	
	FlyingRectangle fr = new FlyingRectangle(150, y2, 150, 30);
	FlyingRectangle fr2 = new FlyingRectangle(x2, 150, 300, 30);
	boolean cond2 = true;
	boolean cond3 = true;
	boolean beforeCollected = true;
	
	Enemy en;
	PowerUp powerUp = new PowerUp(630, 436);
	
	public Level2(int health2, int damage2) {
		
		this.health = health2;
		this.damage = damage2;
		
		en = new Enemy(450, 308, 300, damage);
		
		player = new Rectangle();
		//player.setLocation(x + 25, y + 32 + 123);
		//player.setSize(158, 123);
		
		player.setLocation(x, y);
		player.setSize(20, 140);
		
	    obs.add(new Rectangle(300, 500, 400, 30));
		//obs.add(new Rectangle(300, 150, 150, 30));
		obs.add(new Rectangle(0, 350, 150, 30));
		
		//obs.add(new Rectangle(0, 150, 700, 30));
		//obs.add(new Rectangle(0, 150, 700, 30));
		
		obs.add(fr);
		obs.add(fr2);
		
		
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		imageReader2("image/back.png");
		imageReader("image/tankRight.png");
		
		Thread t = new Thread() 
        {
 	
			public void run()
			{
				while (cond) 
				{  	
					//repaint();
					if (isCollect() && beforeCollected) {
						c.playSound(new File("sound/powerup.WAV"));
						poweredUp = true;
						imageReader("image/supertankR.png");
						beforeCollected = false;
					}
					
					if (en.getHealth() < 0) {
						en.setVisible(false);
					}
					
					if (en.isVisible() && x >= 310 && (y > 350)) {
						c.playSound(new File("sound/touched.WAV"));
						healthRed();
					}
					
					for (Rectangle r : obs) 
					{
							if (player.intersects(r)) 
							{
								//System.out.println("intersect");
								//System.out.println((int)r.getY());
								y = (int)r.getY() - 158 ;
								
							}
		
					}
		
					if (y2 > 150 && cond2) {
						y2 -= 2;
						fr.setLocation(150, y2);
					}
					
					else {
						cond2 = false;
						y2 += 2;
						fr.setLocation(150, y2);
						
						if (y2 == 500) {
							cond2 = true;
							
						}
					}
					
					if (x2 < 550 && cond3) {
						x2 += 2;
						fr2.setLocation(x2, 150);
					}
					
					else {
						cond3 = false;
						x2 -= 2;
						fr2.setLocation(x2, 150);
						
						if (x2 == 300) {
							cond3 = true;
							
						}
					}
				
					y += 10;
					
					boolean var = true;
					
					repaint();
					
					if (isDead() || (x >= 700 && y >= 500) || (health < 0)) {
						
						if (NovaWorld.playing.isActive()) {
							NovaWorld.playing.stop();
						}
						
						JFrame f = new JFrame();
						f.setSize(700, 700);
						ImageIcon i = new ImageIcon("image/gameover.png");
						JLabel l = new JLabel();
						l.setIcon(i);
						  
						JPanel p = new JPanel();
						p.add(l);
						//JPanel p = new JPanel(new JLabel(new ImageIcon("background.jpg"))); 
						f.add(p);
						f.setVisible(true);
						f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						f.setResizable(false);

						c.playSound(f3);
						System.exit(0);
					}
					
					if (reachedEnd()) {
						
						JFrame jf = new JFrame();
						String pass, pass2, pass3, pass4, pass5;
						
						if (poweredUp) {
							pass = "image/supertankR.png";
						}
						
						else {
							pass = "image/tankRight.png";
						}
						
						if (poweredUp) {
							pass2 = "image/superR2.png";
						}
						
						else {
							pass2 = "image/tankRight2.png";
						}
						
						if (poweredUp) {
							pass3 = "image/supertankL.png";
						}
						
						else {
							pass3 = "image/tankLeft.png";
						}
						
						if (poweredUp) {
							pass4 = "image/superL2.png";
						}
						
						else {
							pass4 = "image/tankLeft2 .png";
						}
						
						if (poweredUp) {
							pass5 = "yes";
						}
						
						else {
							pass5 = "no";
						}
						
						Level3 nt = new Level3(damage, health, pass, pass2, pass3, pass4, pass5);
						
						//Obstacles obs = new Obstacles(300, 50, 50, 10);
						jf.add(nt);
						jf.setVisible(true);
						jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						jf.setSize(700, 700);
						jf.setResizable(false);
						cond = false;
					}
					
					else {
						isDead();
					}
					
					try 
	        		{
					   Thread.sleep(1000/50);
					} 
					
					catch (InterruptedException ex) 
					{ 
						
					}
				}
			}
		};
		
		t.start();
		
		//y+=10;
		repaint();
			
	
	}
	
	        
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub	
	}

	private int i = 0;
	private int i2 = 0;
	
	@Override
	public void keyPressed(KeyEvent e) 
	{

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_RIGHT) 
		{
			
			condition = true;
			
			velX = 15;
			
			if (i == 0) 
			{
				imageReader("image/tankRight.png");
				
				if (poweredUp) {
					imageReader("image/supertankR.png");
				}
			}
			
			else if (i == 1)
			{
				imageReader("image/tankRight2.png");
				
				if (poweredUp) {
					imageReader("image/superR2.png");
				}
			}
			
			i++;
			
			if (i > 1) 
			{
				i = 0;
			}
			
			x += velX;
			deter = 1;
			
			repaint();
			
		}
		
		if (code == KeyEvent.VK_LEFT) 
		{
			
			velX = -15;
			
			if (i == 0) 
			{
				imageReader("image/tankLeft.png");
				
				if (poweredUp) {
					imageReader("image/supertankL.png");
				}
			}
			
			else if (i == 1)
			{
				imageReader("image/tankLeft2 .png");
				
				if (poweredUp) {
					imageReader("image/superL2.png");
				}
			}
			
			i++;
			
			if (i > 1) 
			{
				i = 0;
			}
		
			x += velX;
			deter = 2;
			repaint();
		}
		
		if (code == KeyEvent.VK_SPACE) 
		{
			
			if (i2%10 == 0) 
			{
				if (facing.equals("right")) 
				{
					c.addBullet(new Bullet(x + 160, y + 40, 30, this));
					c.playSound(f);
					
					if (y > 350 && y < 500) {
						en.healthReduce(damage);
					}
				}
				
				if (facing.equals("left")) 
				{
					c2.addBullet(new Bullet2(x + 25, y + 40, this));
					c.playSound(f);
				}
			
			
			}

			i2++;
			
		}
	} 

	public void determine(int deter) 
	{
		
		if (deter == 1) 
		{
			imageReader("image/tankRight.png");
			
			if (poweredUp) {
				imageReader("image/supertankR.png");
			}
		}
		
		else if (deter == 2)
		{
			imageReader("image/tankLeft.png");
			
			if (poweredUp) {
				imageReader("image/supertankL.png");
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		velX = 0;
		determine(deter);
		repaint();
		condition = false;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) 
	{
		c.tick();
		c2.tick();
		player.setLocation(x + 25, y + 26);
		repaint(); 
	}
	
	public void setX(int xCoor) 
	{
		x = xCoor;
	}
	  
	public void setY(int yCoor) 
	{
		y = yCoor;
	}
	
	public int getX(int xCoor) 
	{
		return x;
	}
	
	public int getY(int yCoor) 
	{
		return y;
	}
	
	boolean cond4 = true;
	boolean condition3 = true;
	
	DeadBot db = new DeadBot(450, 308, 300);
	boolean condition2 = false;
	
	public void paintComponent(Graphics g)
	{   
	   super.paintComponent(g);
	   g.drawImage(bac, 0, 0, this);
	   g.drawImage(image, x - 25, y, this);
	   c.render(g);
	   c2.render(g);
	   
	   en.paintComponent(g);
	   g.drawImage(image, x - 25, y, this);
	   
	   if (en.getHealth() < 0)
	   {
		  db.paintComponent(g);
		  g.drawImage(image, x - 25, y, this);
		  
		   if (cond4) 
		   {
			   c.playSound(new File("sound/dead2.WAV"));
			   g.drawImage(image, x - 25, y, this);
			   condition2 = true;
		   }
		   
		   cond4 = false;
	   }
	   
	   if (condition2 == true && condition3) {
		   powerUp.paintComponent(g);
		   g.drawImage(image, x - 25, y, this);
	   }
	   
	   for (Rectangle o : obs) 
	   {
		   //g.setColor(new Color(7667712));
		   //g.drawRect(o.x, o.y, o.width, o.height);
		   //g.fillRect(o.x, o.y, o.width, o.height);
		   //imageReader2("image/back.png");
		   //g.drawImage(bac, o.x, o.y, null);
		   g.drawImage(scaleImage(o.width, o.height, "image/novablock.png"), o.x, o.y, null);
	   }
	   
	   if (isCollect() && condition3) {
		   g.drawImage(scaleImage(64, 64, "image/novacollected.png"), powerUp.getX(), powerUp.getY(), null);
		   g.drawImage(image, x - 25, y, this);
		   condition3 = false;
	   }
	   
	   
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
	
	public boolean isDead() {
		if (y > 700) {
			return true;
		}
		
		return false;
	}
	
	public boolean reachedEnd() {
		if (x > 700 && y <= 180) {
			return true;
			
		}
		
		return false;
	}
	
	public void healthRed() {
		health = -1;
	}
	
	public boolean isCollect() {
		if (x >= powerUp.getX() - 50 && y <= powerUp.getY() + 50 &&  y>= 50) {
			damage = 100;
			return true;
		}
		
		return false;
	}
}
