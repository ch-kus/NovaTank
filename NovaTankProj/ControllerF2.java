package NovaTankProj;

import java.awt.Graphics;
import java.util.LinkedList;

public class ControllerF2 {

	private LinkedList<FrostShot2> b = new LinkedList<FrostShot2>();
	
	FrostShot2 TempBullet;
	
	Object nt;
	
	public ControllerF2(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (FrostShot2 bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (FrostShot2 bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(FrostShot2 block) {
		b.add(block);
	}
	
	public void removeBullet(FrostShot2 block) {
		b.add(block);
	}
}
