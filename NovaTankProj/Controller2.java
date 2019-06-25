package NovaTankProj;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller2 {

	private LinkedList<Bullet2> b = new LinkedList<Bullet2>();
	
	Bullet2 TempBullet;
	
	Object nt;
	
	public Controller2(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (Bullet2 bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (Bullet2 bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet2 block) {
		b.add(block);
	}
	
	public void removeBullet(Bullet2 block) {
		b.add(block);
	}
}
