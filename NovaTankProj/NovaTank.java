package NovaTankProj;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author ch_kus
 * NovaTank class represents the first level of NovaTank where the player must 
 * use his tank to kill the guard and progress to the next level.
 */
@SuppressWarnings("serial")
public class NovaTank extends JPanel implements ActionListener, KeyListener
{
	
	Timer timer = new Timer(5, this);
	
	//Files store all the different sounds needed in this level
	File f = new File("sound/fire.WAV");
	File f3 = new File("sound/gameover.WAV");
	File f4 = new File("sound/homescreen.WAV");
	File sound2 = new File("sound/maintheme.WAV");
	
	//Instance variables that store basic tank information
	private int health;
	private boolean condition;
	
	private int x = 0;
	private int y = 0;
	double velX = 0;
	double velY = 0;
	private int damage;
	
	//BufferedImages for both tank and the background of level
	private BufferedImage image;
	private BufferedImage bac;
	
	//Variables to compute which tank to draw
	private int determine;
	private int swap = 0;
	
	//Controllers for bullet of tank
	private Controller c = new Controller(this);
	private Controller2 c2 = new Controller2(this);

	//Direction tank is facing
	private String facing;
	
	//Array list to hold all the obstacles in this level
	private ArrayList<Rectangle> obs = new ArrayList<Rectangle>();
	private Rectangle player;
	private Clip playing;
	
	//Boolean condition that calls dead frame if tank exits bounds
	public boolean terminatingCondition = true;
	
	//Conditions that evaluate how Flying Rectangle object will move
	private boolean flyingRectCondition = true;
	private int flyingRectYCoor = 350;
	
	FlyingRectangle fr = new FlyingRectangle(380, flyingRectYCoor, 150, 30);
	
	//Checks if enemy is alive or not
	private boolean enemyAlive;
	
	//Checks the power of tank shot and computes number of shots before enemy dies
	private int shootingFrequency = 0;
	
	//Checks if enemy is dead or not
	private boolean enemyDead = true;
	
	//Initialization of guard and deadbot(which spawns when enemy dies)
	BufferedImage enemy;
	Enemy en;
	DeadBot db = new DeadBot(523, 152, 200);
	
	
	/**
	 * Method that checks if the player has exited the bounds of the map, 
	 * ensuring that the player is dead.
	 * 
	 * @return player has exited the bounds of the map
	 */
	public boolean isDead() 
	{
		if ( !(x > 700) && y > 700 ) 
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Method that checks if the player has successfully completed the level, 
	 * ensuring that the player has reached the end.
	 * 
	 * @return player has successfully completed level
	 */
	public boolean reachedEnd() 
	{
		if (x > 700)
		{
			return true;	
		}
		
		return false;
	}
	
	/**
	 * Constuctor for NovaTank has many operations
	 * 
	 * @param damage2 sets the damage of the tank
	 * @param health2 sets the health of the tank
	 * 
	 * 1.) Constructs the Guard at coordinates, (523, 152)
	 * 2.) Constructs a rectangle that represents player's bounds
	 * 3.) Adds the different obstacles to Rectangle Arraylist
	 * 4.) Sets the image of background and the tank
	 * 5.) Creates a new thread, and declares the run method which will
	 * execute the game.
	 * 6.) At the end the constuctor calls repaint to draw the level
	 * 
	 */
	public NovaTank(int damage2, int health2) {
		
		this.damage = damage2;
		this.health = health2;
		
		en = new Enemy(523, 152, 200, damage);
		
		player = new Rectangle();
		player.setLocation(x, y);
		player.setSize(80, 140);
		
		obs.add(new Rectangle(0, 650, 380, 30));
		obs.add(new Rectangle(530, 350, 175, 30));
		obs.add(new Rectangle(0, 350, 150, 30));
		obs.add(fr);

		timer.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		imageReader2("image/back.png");
		imageReader("image/tankRight.png");
		
		Thread thread = new Thread() 
        {
 	
			public void run()
			{
				while (true) 
				{  	
					//If tank has shot enemy enough, eventually it will no longer be alive
					if (en.getHealth() <= 0) 
					{
						enemyAlive = false;
					}
					
					else 
					{
						enemyAlive = true;
					}
					
					//Takes each obstacle from obstacles arraylist and checks if the player's bounds intersects 
					//the rectangle, at which point it will reset the player's bounds so the player doesn't
					//sink through the obstacles.
					for (Rectangle r : obs) 
					{
							if (player.intersects(r)) 
							{
								y = (int)r.getY() - 158 ;
							}
					}
		
					//Guard's death will initiate the flying rectangle to appear, which the player will use to 
					//advance to the next level. Also calculates how the flying platform will move.
					if (en.getHealth() < 0) 
					{
						
						if (flyingRectYCoor > 350 && flyingRectCondition) 
						{
							flyingRectYCoor -= 1;
							fr.setLocation(380, flyingRectYCoor);
						}
					
						else 
						{
							flyingRectCondition = false;
							flyingRectYCoor += 1;
							
							fr.setLocation(380, flyingRectYCoor);
						
							if (flyingRectYCoor == 650) 
							{
								flyingRectCondition = true;
							}
						}
					}
				
					y += 10;

					repaint();
					
					//If either of these conditions are met, that means the player
					//should no longer be alive, and thus a new JFrame will pop-up
					//indicating the game is over.
					if (isDead() || health < 0) {
						
						if (NovaWorld.playing.isActive()) {
							NovaWorld.playing.stop();
						}
						
						JFrame dead = new JFrame();
						dead.setSize(700, 700);
						
						ImageIcon i = new ImageIcon("image/gameover.png");
						JLabel l = new JLabel();
						l.setIcon(i);
						  
						JPanel jp = new JPanel();
						jp.add(l);

						dead.add(jp);
						dead.setVisible(true);
						dead.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						dead.setResizable(false);

						c.playSound(f3);
						System.exit(0);
					}

					//If both of these conditions are met, that means the player
					//has passed level one, and thus a new JFrame will pop-up
					//indicating the player progresses to the next level.
					if (reachedEnd() && terminatingCondition) 
					{
						JFrame jf = new JFrame();
						Level2 nt = new Level2(500, 30);

						jf.add(nt);
						jf.setVisible(true);
						jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						jf.setSize(700, 700);
						jf.setResizable(false);
						jf.setTitle("Level 2: Flying Blocks");
						terminatingCondition = false;	
					}
					
					try 
	        		{
					   Thread.sleep(20);
					} 
					
					catch (InterruptedException ex) 
					{ 
						
					}
				}
			}
		};
		
		thread.start();

		repaint();
	}
	
	/**
	 * Initializes the image of the nova tank to which picture should be drawn
	 * @param str holds a String of which picture should be drawn
	 */
	public void imageReader(String str) 
	{	
		
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
	
	/**
	 * Initializes the image of the background that should be drawn
	 * @param str holds a String of which background should be drawn
	 */
	public void imageReader2(String str) 
	{
		
		try  
		{
			bac = ImageIO.read(new File(str));
		} 
    		
		catch (IOException e1){}
	}
	    
	/**
	 * Sets xCoordinate of player.
	 * @param xCoor holds the x coordinate of player
	 */
	public void setX(int xCoor) 
	{
		x = xCoor;
	}
	  
	/**
	 * Sets yCoordinate of player.
	 * @param yCoor holds the y coordinate of player
	 */
	public void setY(int yCoor) 
	{
		y = yCoor;
	}
	
	/**
	 * Gets xCoordinate of player.
	 * @return returns the x coordinate of player
	 */
	public int getX(int xCoor) 
	{
		return x;
	}
	
	/**
	 * Gets yCoordinate of player.
	 * @return returns the y coordinate of player
	 */
	public int getY(int yCoor) 
	{
		return y;
	}
	
	/**
	 * Initializes the image of the enemy that should be drawn
	 * @param str holds a String of which enemy should be drawn
	 */
	public void EnemyReader() 
	{
		try 
		{
			enemy = ImageIO.read(new File("image/deadBot.png"));
		}
		
		catch (IOException e) 
		{
			
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub	
	}
	
	/**
	 * Key pressed method handles all key events.
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_RIGHT) 
		{
			
			condition = true;
			
			velX = 15;
			
			//Alternates between images so moving tank animation is displayed
			if (swap == 0) 
			{
				imageReader("image/tankRight.png");
			}
			
			else if (swap == 1)
			{
				imageReader("image/tankRight2.png");
			}
			
			swap++;
			
			if (swap > 1) 
			{
				swap = 0;
			}
			
			x += velX;
			determine = 1;
			
			repaint();
			
		}
		
		if (code == KeyEvent.VK_LEFT) 
		{
			
			velX = -15;
			
			//Alternates between images so moving tank animation is displayed
			if (swap == 0) 
			{
				imageReader("image/tankLeft.png");
			}
			
			else if (swap == 1)
			{
				imageReader("image/tankLeft2 .png");
			}
			
			swap++;
			
			if (swap > 1) 
			{
				swap = 0;
			}
		
			x += velX;
			determine = 2;
			repaint();
		}
		
		if (code == KeyEvent.VK_SPACE) 
		{
			//Determines how quickly tank fires shot and will fire shot
			//when user presses spacebar.
			if (shootingFrequency % 10 == 0) 
			{
				if (facing.equals("right")) 
				{
					Bullet b = new Bullet(x + 160, y + 40, damage, this);
					
					c.addBullet(b);
					c.playSound(f);
				
					if (y <= 350) {			
						en.healthReduce(damage);
					}
				}
				
				if (facing.equals("left")) 
				{
					c2.addBullet(new Bullet2(x + 25, y + 40, this));
					c.playSound(f);
				}
			}

			shootingFrequency++;
			
		}
	} 

	/**
	 * Determines which image to display when user releases right/left move key
	 * @param deter which case to run when method is called
	 */
	public void determineOrientation(int deter) 
	{
		
		if (deter == 1) 
		{
			imageReader("image/tankRight.png");
		}
		
		else if (deter == 2)
		{
			imageReader("image/tankLeft.png");
		}
	}
	
	/**
	 * Sets image based on determineOrientation method 
	 * and calls repaint
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		velX = 0;
		determineOrientation(determine);
		repaint();
		condition = false;
	}

	/**
	 * Calls the tick methods of left and right bullet controllers and repaints player
	 */
	@Override
	public synchronized void actionPerformed(ActionEvent e) 
	{
		c.tick();
		c2.tick();
		player.setLocation(x + 25, y + 26);
		repaint(); 
	}
	
	/**
	 * Creates and modifies a BufferedImage according to passed patterns
	 * @param WIDTH holds width of image
	 * @param HEIGHT holds height of image
	 * @param filename holds the file name of the image
	 * @return the modified Buffered Image
	 */
	public BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) 
	{
	    BufferedImage input = null;
	    
	    try 
	    {
	        ImageIcon ii = new ImageIcon(filename);//path to image
	        input = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = (Graphics2D) input.createGraphics();
	        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	        g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
	    } 
	    
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        return null;
	    }
	    
	    return input;
	}
	
	public void paintComponent(Graphics g)
	{   
	   super.paintComponent(g);
	   g.drawImage(bac, 0, 0, this); //draws background
	   g.drawImage(image, x - 25, y, this); //draws Player
	   
	   c.render(g); 
	   c2.render(g);
	   
	   en.paintComponent(g); //draws Enemy
	   g.drawImage(image, x - 25, y, this); //redraws Player
	   
	   
	   //When Enemy dies, Deadbot image will take Enemy's place
	   if (en.getHealth() < 0)
	   {
		   db.paintComponent(g);
		   g.drawImage(image, x - 25, y, this);
		   
		   if (enemyDead) 
		   {
			   c.playSound(new File("sound/dead2.WAV"));
		   }
		   
		   enemyDead = false;
	   }
	   
	   //Loops through obstacles arraylist and sets a design for each block
	   for (Rectangle o : obs) 
	   {   
		   if ( !(o instanceof FlyingRectangle) ) 
		   {
			   g.drawImage(scaleImage(o.width, o.height, "image/novablock.png"), o.x, o.y, null);
		   }
		   
		   if (o instanceof FlyingRectangle && en.getHealth() < 0) 
		   {
			   g.drawImage(scaleImage(o.width, o.height, "image/novablock.png"), o.x, o.y, null);
		   }  	   
	   }
	   
	}
	
}
