import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

	public static void main(String... args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		JFrame j = new JFrame();
		j.setTitle("RBTree");

		try 
		{
			j.setIconImage(ImageIO.read(GUITree.class.getResource("/resources/RBTree.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(new GUITree());
		j.pack();
		j.setVisible(true);
	}

}
