import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Base extends Canvas {

	private static final long serialVersionUID = 1L;
	private static final Dimension SIZE = new Dimension(1024, 1024 / 16 * 10);
	private static JFrame frame;
	
	public Base() {
		createWindow();
	}
	
	public void createWindow() {
		frame = new JFrame("Enigma");
		frame.setSize(SIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(new Game(frame, SIZE.width, SIZE.height));
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Base();
	}
}
