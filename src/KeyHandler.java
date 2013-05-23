import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class KeyHandler implements KeyListener, MouseMotionListener, MouseListener {

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			if (World.xOffset < -1) {
				World.xOffset++;
			}
		}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			if (World.xOffset > -(Game.worldSize - Game.width / 32) + 1) {
				World.xOffset--;
			}
		}
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			if (World.yOffset < 0) {
				World.yOffset++;
			}
		}
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			if (World.yOffset > -(Game.worldSize - Game.height / 32)) {
				World.yOffset--;
			}
		}
		if(key == KeyEvent.VK_F1) {
		}
		if(key == KeyEvent.VK_SPACE){
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
		}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
		}
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
		}
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {

	}

}
