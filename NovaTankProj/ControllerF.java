package NovaTankProj;

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ControllerF{

	private int numBullets;
	
	private LinkedList<FrostShot> b = new LinkedList<FrostShot>();
	
	FrostShot TempBullet;
	
	Object nt;
	
	public ControllerF(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (FrostShot bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (FrostShot bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(FrostShot block) {
		b.add(block);
		numBullets--;
	}
	
	public void removeBullet(FrostShot block) {
		b.add(block);
	}
	
	public void playSound(File sound) {
	    try {
	        Clip c = AudioSystem.getClip();
	        c.open(AudioSystem.getAudioInputStream(sound));
	     
	        	c.start();

	        
	        Thread.sleep(c.getMicrosecondLength()/1000);
	    } catch (Exception e) {}
	}
	
	
	
	
	
}
