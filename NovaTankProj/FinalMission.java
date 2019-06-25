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


public class FinalMission extends JPanel implements ActionListener, KeyListener{

	Timer t = new Timer(5, this);
	File f = new File("sound/fire.WAV");
	File f3 = new File("sound/gameover.WAV");
	File f4 = new File("sound/homescreen.WAV");
	
	private boolean condition;
	
	int x = 0;
	int y = 0;
	double velX = 0;
	double velY = 0;
	
	private BufferedImage image, bac, flag;
	
	private int deter;
	private Controller c = new Controller(this);
	private Controller2 c2 = new Controller2(this);
	private Controller3 c3 = new Controller3(this);
	private Controller4 c4 = new Controller4(this);
	private ControllerM c5 = new ControllerM(this);
	private ControllerM2 c6 = new ControllerM2(this);
	private ControllerF c7 = new ControllerF(this);
	private ControllerF2 c8 = new ControllerF2(this);

	private String facing;
	
	private ArrayList<Rectangle> obs = new ArrayList<Rectangle>();
	private Rectangle player;
	private Clip playing;
	
	private int health, damage;
	PowerUp p = new PowerUp(500, 125);
	
	public void imageReader(String str) {
		
		try  
		{
			image = ImageIO.read(new File(str));
			
			if (str.equals("image/tankLeft.png") || str.equals("image/tankLeft2 .png")) 
			{
				facing = "left";
			}
			
			if (str.equals("image/tankRight.png") || str.equals("image/tankRight2.png")) 
			{
				facing = "right";
			}
			
			if (str.equals("image/supertankL.png") || str.equals("image/superL2.png")) 
			{
				facing = "left";
			}
			
			if (str.equals("image/supertankR.png") || str.equals("image/superR2.png")) 
			{
				facing = "right";
			}
			
			if (str.equals("image/galavanizerL.png") || str.equals("image/galavanizerL2.png")) 
			{
				facing = "left";
			}
			
			if (str.equals("image/galavanizer.png") || str.equals("image/galavanizerR2.png")) 
			{
				facing = "right";
			}
			
			if (str.equals("image/icetankL.png") || str.equals("image/icetankL2.png")) 
			{
				facing = "left";
			}
			
			if (str.equals("image/icetankR.png") || str.equals("image/icetankR2.png")) 
			{
				facing = "right";
			}
			
			if (str.equals("image/heavyL.png") || str.equals("image/heavyL2.png")) 
			{
				facing = "left";
			}
			
			if (str.equals("image/heavyR.png") || str.equals("image/heavyR2.png")) 
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
	
	public void image() 
	{
		
		try  
		{
			flag = ImageIO.read(new File("image/flag.png"));
		} 
    		
		catch (IOException e1){}
	}
	
	public boolean cond = true;
	int x3 = 0;
	
	int x2 = 0;
	int y2 = 300;
	
    //FlyingRectangle fr = new FlyingRectangle(x3, 300, 150, 20);
	//FlyingRectangle fr2 = new FlyingRectangle(x2, 150, 150, 20);
	//FlyingRectangle fr3 = new FlyingRectangle(550, y2, 150, 20);
	
	boolean cond2 = true;
	boolean cond3 = true;
	boolean cond4, conD = true;
	
	String pass;
	String pass2;
	String pass3;
	String pass4;
	String power;
	
	Enemy en, en2;
	PowerUp pw;
	
	public boolean isVertical(Rectangle rec) {
		if (rec.getWidth() < 30) {
			return true;
		}
		
		return false;
	}
	
	boolean condition6 = false;
	boolean condition8 = false;
	boolean powered2 = false;
	boolean powered3 = false;
	
	long val = 1000;
	boolean condition7 = false;
	boolean isCollected = false;
	boolean con = true;
	
	Clip playing2;
	
	public void method(File sound) {
		try {
	        playing2 = AudioSystem.getClip();
	        playing2.open(AudioSystem.getAudioInputStream(sound));
			playing2.start();
	    	playing2.loop(Clip.LOOP_CONTINUOUSLY);
	        
	        Thread.sleep(playing.getMicrosecondLength()/1000);
	    } catch (Exception e) {}
	}
	
	int i5 = 0;
	
	public FinalMission(int damage2, int health2, String state, String state2, String state3, String state4, String powered) {
		
		damage = damage2;
		health = health2;
		
		if (powered.equals("yes")) {
			health = 1500;
		}
		
		if (powered.equals("no")) {
			health = 750;
		}
		
		en = new Enemy(350, 255, 300, damage);
		//en2 = new Enemy(520, 468, 600, damage);
		pw = new PowerUp(205, 485);
		
		power = powered;
		pass = state;
		pass2 = state2;
		pass3 = state3;
		pass4 = state4;
		
		player = new Rectangle();
		//player.setLocation(x + 25, y + 32 + 123);
		//player.setSize(158, 123);
		
		player.setLocation(x, y);
		player.setSize(20, 140);
		
	    //obs.add(new Rectangle(300, 500, 400, 30));
		
		obs.add(new Rectangle(0, 580, 140, 20));
		obs.add(new Rectangle(140, 560, 130, 20));
		obs.add(new Rectangle(270, 540, 130, 20));
		obs.add(new Rectangle(400, 520, 130, 20));
		obs.add(new Rectangle(530, 500, 170, 20));
		//obs.add(new Rectangle(0, 660, 700, 20));
		//obs.add(fr);
		//obs.add(fr2);
		//obs.add(fr3);
		
		
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		imageReader2("image/fin.jpg");
		imageReader(pass);
		
		Thread t = new Thread() 
        {
 	
			public void run()
			{
				while (cond) 
				{  	
					if (i5 % 198 == 0) {
						method(new File("sound/fin.WAV"));
						i5++;
					}

					if (power.equals("yes")) {
						powered3 = true;
					}
					
					//if (en.isVisible() && x >= 190 && (y > 300 && y < 450) || (en2.isVisible() && x >= 370 && (y > 320))) {
					//	c.playSound(new File("sound/touched.WAV"));
					//	healthRed();
					//}
					//repaint();
					
					if (!en.isVisible() && condition6) {
						obs.remove(obs.get(7));
						
						condition6 = false;
						condition7 = true;
					}

					/*if (db.isVisible() && player.intersects(obs.get(6)) && condition7) {
						System.out.println("");
						c.playSound(new File("sound/powerup.WAV"));
						
						if (power.equals("no")) {
							imageReader("image/supertankR.png");
							
							powered2 = true;
							isCollected = true;
						}
						
						else {
							imageReader("image/galavanizer.png");
							powered3 = true;
							isCollected = true;
						}
						
						condition7 = false;
						
					}*/

					for (Rectangle r : obs) 
					{
						    
							if (player.intersects(r) && isVertical(r)) {
								x = (int)r.getX();
							}
							if (player.intersects(r) && !isVertical(r)) 
							{
								//System.out.println("intersect");
								//System.out.println((int)r.getY());
								y = (int)r.getY() - 158 ;
							}
		
					}
		
					/*
					if (x3 < 400 && cond2) {
						x3 += 2;
						fr.setLocation(x3, 300);
					}
					
					else {
						cond2 = false;
						x3 -= 2;
						fr.setLocation(x3, 300);
						
						if (x3 == 0) {
							cond2 = true;
							
						}
					}
					
					/*if (x2 < 550 && cond3) {
						x2 += 3;
						fr2.setLocation(x2, 150);
					}
					
					else {
						cond3 = false;
						x2 -= 3;
						fr2.setLocation(x2, 150);
						
						if (x2 == 150) {
							cond3 = true;
							
						}
					}*/
					/*
					if (y2 < 450 && cond4) {
						y2 += 2;
						fr3.setLocation(550, y2);
					}
					
					else {
						cond4 = false;
						y2 -= 2;
						fr3.setLocation(550, y2);
						
						if (y2 == 300) {
							cond4 = true;
							
						}
					}*/
				
					y += 10;
					
					boolean var = true;
					
					repaint();
					
					if (isDead() || health < 0) {
						
						if (NovaWorld.playing.isActive()) {
							NovaWorld.playing.stop();
						}
						
						if (playing2.isActive()) {
							playing2.stop();
						}
						
						JFrame f = new JFrame();
						f.setSize(1500, 1000);
						ImageIcon i = new ImageIcon("image/game.png");
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
						
						if (powered3) {
							pass = "image/galavanizer.png";
						}
						
						else {
							pass = "image/supertankR.png";
						}
						
						if (powered3) {
							pass2 = "image/galavanizerR2.png";
						}
						
						else {
							pass2 = "image/super2.png";
						}
						
						if (powered3) {
							pass3 = "image/galavanizerL.png";
						}
						
						else {
							pass3 = "image/supertankL.png";
						}
						
						if (powered3) {
							pass4 = "image/galavanizerL2.png";
						}
						
						else {
							pass4 = "image/superL2.png";
						}
						
						if (powered3) {
							pass5 = "yes";
						}
						
						else {
							pass5 = "no";
						}
						
						Level4 nt = new Level4(damage, health, pass, pass2, pass3, pass4, pass5);
						
						//Obstacles obs = new Obstacles(300, 50, 50, 10);
						jf.add(nt);
						jf.setVisible(true);
						jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						jf.setSize(700, 700);
						jf.setResizable(false);
						cond = false;
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
				imageReader(pass);
			}
			
			else if (i == 1)
			{
				imageReader(pass2);
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
				imageReader(pass3);
			}
			
			else if (i == 1)
			{
				imageReader(pass4);
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
				if (i2%30 == 0) {
					
					if (pass.equals("image/heavyR.png")
							   || pass.equals("image/heavyR2.png"))
					{
						c5.addBullet(new MortarShot(x + 180, y - 20, 30, this));
						c3.playSound(new File("sound/mortar.WAV"));
					}
				}

				
				if (i2%20 == 0) {
					
				if (pass.equals("image/galavanizer.png")
						   || pass.equals("image/galavanizerR2.png"))
				{
					c3.addBullet(new ElectroBullet(x + 180, y - 20, 30, this));
					c3.playSound(new File("sound/electricshot.WAV"));
					
					if (y > 450) {
						//en2.healthReduce(damage);
					}
				}
				
				if (i2%1 == 0) {
					
					if (pass.equals("image/icetankR.png")
							   || pass.equals("image/icetankR2.png"))
					{
						c7.addBullet(new FrostShot(x + 180, y - 40, 30, this));
						c3.playSound(new File("sound/ice.WAV"));
						
						if (y > 450) {
							//en2.healthReduce(damage);
						}
					}
				}
			
				}
				
			
			}
        
			i2++;
			
		}
	} 

	public void determine(int deter) 
	{
		
		if (deter == 1) 
		{
			imageReader(pass);
		}
		
		else if (deter == 2)
		{
			imageReader(pass4);
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
		c3.tick();
		c4.tick();
		c5.tick();
		c6.tick();
		c7.tick();
		c8.tick();
		
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
	
	DeadBot db = new DeadBot(350, 255, 300);
	DeadBot db2 = new DeadBot(520, 468, 600);
	
	boolean cond5 = true;
	boolean cond6 = true;
	
	public void paintComponent(Graphics g)
	{   
	   super.paintComponent(g);
	   g.drawImage(bac, 0, 0, this);
	   
	   //image();
	   //g.drawImage(flag, 535, 130, this);
	   if (pass.equals("image/galavanizer.png")
			   || pass.equals("image/galavanizerR2.png")
			   || pass.equals("image/galavanizerL.png")
			   || pass.equals("image/galavanizerL2.png")) {
		   g.drawImage(image, x - 25, y - 35, this);
	   }
	   
	   else if (pass.equals("image/heavyR.png")
			   || pass.equals("image/heavyR2.png")
			   || pass.equals("image/heavyL.png")
			   || pass.equals("image/heavyL2.png")) {
		   g.drawImage(image, x - 25, y - 95, this);
	   }
	   
	   else if (pass.equals("image/icetankR.png")
			   || pass.equals("image/icetankR2.png")
			   || pass.equals("image/icetankL.png")
			   || pass.equals("image/icetankL2.png")) {
		   g.drawImage(image, x - 25, y - 147, this);
	   }
	  
	   else {
		   g.drawImage(image, x - 25, y - 15, this);
	   }
	   
	   c.render(g);
	   c2.render(g);
	   c3.render(g);
	   c4.render(g);
	   c5.render(g);
	   c6.render(g);
	   c7.render(g);
	   c8.render(g);
	   
	   
	   if (pass.equals("image/galavanizer.png")
			   || pass.equals("image/galavanizerR2.png")
			   || pass.equals("image/galavanizerL.png")
			   || pass.equals("image/galavanizerL2.png")) {
		   g.drawImage(image, x - 25, y - 35, this);
	   }
	   
	   else if (pass.equals("image/heavyR.png")
			   || pass.equals("image/heavyR2.png")
			   || pass.equals("image/heavyL.png")
			   || pass.equals("image/heavyL2.png")) {
		   g.drawImage(image, x - 25, y - 95, this);
	   }
	   
	   else if (pass.equals("image/icetankR.png")
			   || pass.equals("image/icetankR2.png")
			   || pass.equals("image/icetankL.png")
			   || pass.equals("image/icetankL2.png")) {
		   g.drawImage(image, x - 25, y - 147, this);
	   }
	  
	   else {
		   g.drawImage(image, x - 25, y - 15, this);
	   } 
	   
	  // en.paintComponent(g);
	  // en2.paintComponent(g);
	   
	   if (pass.equals("image/galavanizer.png")
			   || pass.equals("image/galavanizerR2.png")
			   || pass.equals("image/galavanizerL.png")
			   || pass.equals("image/galavanizerL2.png")) {
		   g.drawImage(image, x - 25, y - 35, this);
	   }
	   
	   else if (pass.equals("image/heavyR.png")
			   || pass.equals("image/heavyR2.png")
			   || pass.equals("image/heavyL.png")
			   || pass.equals("image/heavyL2.png")) {
		   g.drawImage(image, x - 25, y - 95, this);
	   }
	   
	   else if (pass.equals("image/icetankR.png")
			   || pass.equals("image/icetankR2.png")
			   || pass.equals("image/icetankL.png")
			   || pass.equals("image/icetankL2.png")) {
		   g.drawImage(image, x - 25, y - 147, this);
	   }
	  
	   else {
		   g.drawImage(image, x - 25, y - 15, this);
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
		if (x > 680) {
			return true;
			
		}
		
		return false;
	}
	
	public void healthRed() {
		health = -1;
	}
}
