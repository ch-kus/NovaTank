package NovaTankProj;

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Controller3 {

	private int numBullets;
	
	private LinkedList<ElectroBullet> b = new LinkedList<ElectroBullet>();
	
	ElectroBullet TempBullet;
	
	Object nt;
	
	File f = new File("sound/Grenade.WAV");
	
	public Controller3(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (ElectroBullet bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (ElectroBullet bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(ElectroBullet block) {
		b.add(block);
		numBullets--;
	}
	
	public void removeBullet(ElectroBullet block) {
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
