package NovaTankProj;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller4 {

	private LinkedList<ElectroBullet2> b = new LinkedList<ElectroBullet2>();
	
	ElectroBullet2 TempBullet;
	
	Object nt;
	
	public Controller4(Object nt) {
		this.nt = nt;
	}
	
	public void tick() {
		for (ElectroBullet2 bullet : b) {
			TempBullet = bullet;
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (ElectroBullet2 bullet : b) {
			TempBullet = bullet;
			TempBullet.render(g);
		}
	}
	
	public void addBullet(ElectroBullet2 block) {
		b.add(block);
	}
	
	public void removeBullet(ElectroBullet2 block) {
		b.add(block);
	}
}
