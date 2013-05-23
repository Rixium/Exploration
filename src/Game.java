import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Thread thread;
	private boolean running;
	private int frames = 0;
	private int updates = 0;
	public static int width;
	public static int height;
	
	public static World map;
	public static Image[] blocks = new Image[100];
	private final int BLOCKSIZE = 16;
	public static Random random = new Random();
	public static Image playerImg;
	public static int worldSize = 200;
	
	public Game(JFrame frame, int width, int height) {
		this.frame = frame;
		Game.width = width;
		Game.height = height;
		frame.addKeyListener(new KeyHandler());
		loadResources();
		init();
		start();
	}
	
	public void loadResources() {
		for(int i = 0; i < blocks.length; i++) {
			Image src = new ImageIcon("res/blockSheet.png").getImage();
			blocks[i] = createImage(new FilteredImageSource(src.getSource(), new CropImageFilter(i * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE)));
		}
		playerImg = new ImageIcon("res/player.png").getImage();
	}
	
	public void init() {
		map = new World(worldSize);
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if(running) {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000 / 60D;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			repaint();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("Enigma | FPS: " + frames + " | UPS: " + updates);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public void update() {
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		map.paint(g);
		g.setColor(Color.RED);
		g.drawString(map.getBlocks() + " blocks.", 30, 30);
	}
	
}
