package NovaTankProj;

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Controller {

	private int numBullets;
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	
	Bullet TempBullet;
	
	Object nt;
	
	File f = new File("sound/Grenade.WAV");
	
	public Controller(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (Bullet bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (Bullet bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet block) {
		b.add(block);
		numBullets--;
	}
	
	public void removeBullet(Bullet block) {
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
