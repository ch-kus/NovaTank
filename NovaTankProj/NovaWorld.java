package NovaTankProj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NovaWorld 
{
	
	static Clip playing;
	
	public NovaWorld(File sound) 
	{
		
		try 
		{
	        playing = AudioSystem.getClip();
	        playing.open(AudioSystem.getAudioInputStream(sound));
			playing.start();
	    	playing.loop(Clip.LOOP_CONTINUOUSLY);
	        
	        Thread.sleep(playing.getMicrosecondLength() / 1000);
	    } 
		
		catch (Exception e) 
		{
	    	
	    }
	}
	
	public static void main(String[] args) 
	{
		
		final NovaWorld player1;
		
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(700, 700);
		mainFrame.setTitle("Home Screen");
		
		  
		JPanel mainPanel = new JPanel();
		
		File mainSong = new File("sound/maintheme.wav");
		JButton startButton = new JButton("Start");


		mainFrame.setVisible(true);

		ImageIcon homeScrn = new ImageIcon("image/homescrn 2.png");
		JLabel lab = new JLabel();
		lab.setIcon(homeScrn);

		mainPanel.add(startButton);
		mainPanel.add(lab);
		mainFrame.add(mainPanel);
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		
		Controller mainController = new Controller(new NovaTank(10, 10));
		
		startButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	mainController.playSound(new File("sound/click.wav"));

		    	JFrame directionsGuide = new JFrame();
				directionsGuide.setSize(700, 770);
				directionsGuide.setTitle("Directions Guide");
				        
				JPanel directionsPanel = new JPanel();

				JButton fight = new JButton ("FIGHT!");
			
				directionsGuide.add(directionsPanel);
				directionsGuide.setVisible(true);

				ImageIcon i = new ImageIcon("image/backfin.png");
				JLabel l = new JLabel();
				l.setIcon(i);
				
				directionsPanel.add(fight);
				directionsPanel.add(l);
				directionsPanel.add(fight);

				directionsGuide.setVisible(true);
				directionsGuide.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				directionsGuide.setResizable(false);
				
			    fight.addActionListener (new ActionListener() 
			    {
										
					public void actionPerformed(ActionEvent e) 
					{
						
						mainController.playSound(new File("sound/click.wav"));
						
						JFrame playGame = new JFrame();
						
						NovaTank level1 = new NovaTank(25, 500);

						playGame.add(level1);
						
						playGame.setVisible(true);
						directionsGuide.setVisible(false);
						
						playGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						playGame.setSize(700, 700);
						playGame.setTitle("Level 1: The Guard");
						playGame.setResizable(false);	
					}
				});
		    }
		});
		
		player1 = new NovaWorld(mainSong);
	}
	
}

