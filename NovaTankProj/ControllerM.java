package NovaTankProj;

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ControllerM {

	private int numBullets;
	
	private LinkedList<MortarShot> b = new LinkedList<MortarShot>();
	
	MortarShot TempBullet;
	
	Object nt;
	
	public ControllerM(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (MortarShot bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (MortarShot bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(MortarShot block) {
		b.add(block);
		numBullets--;
	}
	
	public void removeBullet(MortarShot block) {
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
