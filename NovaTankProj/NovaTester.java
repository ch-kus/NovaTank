package NovaTankProj;

import javax.swing.JFrame;

public class NovaTester {

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Level5 nt = new Level5(100,500,"","","","","");
		//Obstacles obs = new Obstacles(300, 50, 50, 10);
		jf.add(nt);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(700, 1000);
		jf.setResizable(false);
	}
}
