package NovaTankProj;

import java.awt.Graphics;
import java.util.LinkedList;

public class ControllerM2 {

	private LinkedList<MortarShot2> b = new LinkedList<MortarShot2>();
	
	MortarShot2 TempBullet;
	
	Object nt;
	
	public ControllerM2(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (MortarShot2 bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (MortarShot2 bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(MortarShot2 block) {
		b.add(block);
	}
	
	public void removeBullet(MortarShot2 block) {
		b.add(block);
	}
}
