import java.awt.Image;
import java.awt.Rectangle;


public class Block {

	private int id;
	private Image image;
	private boolean collidable;
	private boolean still = false;
	public Rectangle rect = new Rectangle(0, 0, 32, 32);
	
	public Block(int id) {
		this.id = id;
		setBlock();
	}
	
	public void setBlock() {
		switch(id) {
		case Material.AIR:
			image = Game.blocks[0];
			collidable = false;
			still = true;
			break;
		case Material.GRASS:
			image = Game.blocks[1];
			collidable = true;
			still = true;
			break;
		case Material.DIRT:
			image = Game.blocks[2];
			collidable = true;
			still = true;
			break;
		case Material.STONE:
			image = Game.blocks[3];
			collidable = true;
			still = true;
			break;
		case Material.SAND:
			image = Game.blocks[4];
			collidable = true;
			still = false;
			break;
		case Material.GRAVEL:
			image = Game.blocks[5];
			collidable = true;
			still = false;
			break;
		case Material.WOOD:
			image = Game.blocks[6];
			collidable = false;
			still = true;
			break;
		case Material.COAL:
			image = Game.blocks[7];
			collidable = true;
			still = true;
			break;
		case Material.IRON:
			image = Game.blocks[8];
			collidable = true;
			still = true;
			break;
		default:
			image = Game.blocks[0];
			collidable = false;
			still = true;
			break;
		}
	}

	public Rectangle getRect() {
		return rect;
	}
	
	public void setID(int id) {
		this.id = id;
		setBlock();
	}
	
	public boolean getStill() {
		return still;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean getCollidable() {
		return collidable;
	}
	
	public Image getImage() {
		return image;
	}
}
