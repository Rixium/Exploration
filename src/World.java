import java.awt.Graphics;

public class World {

	public Block[][] map;
	private Block[][] back;
	private int skyHeight = 20;
	public static int xOffset = -1;
	public static int yOffset;
	public static int size;
	private boolean ready = false;
	
	public World(int size) {
		World.size = size;
		map = new Block[size][size];
		back = new Block[size][size];
		buildMap();
	}
	
	public void buildMap() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(j < skyHeight) {
					map[i][j] = new Block(Material.AIR);
				} else if (j >= skyHeight) {
					map[i][j] = new Block(Material.DIRT);
				}
			}
		}
		buildMountains();
	}
	
	public void buildMountains() {
		int chance = 100;
		int iteration = 0;
		while(iteration < 100) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					if(i > 0 && j > 0  && i < size - 1 && j < size - 1) {
						try {
							if(map[i][j].getID() == Material.DIRT) {
								int getMountain = Game.random.nextInt(chance);
								if(getMountain > 0 && getMountain < 5) {
									map[i][j - 1].setID(Material.DIRT);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			iteration++;
		}
		iteration = 0;
		while(iteration < 10) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					if(i > 0 && j > 0  && i < size - 1 && j < size - 1) {
						try {
							if(map[i + 1][j - 1].getID() == Material.DIRT || map[i - 1][j - 1].getID() == Material.DIRT) {
								map[i][j].setID(Material.DIRT);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			iteration++;
		}
		addGrass();
	}
	
	public void addGrass() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(j > 0 && j < map.length) {
					if(i > 0 && j > 0  && i < size - 1 && j < size - 1) {
						if(map[i][j - 1].getID() == Material.AIR) {
							if(map[i][j + 1].getID() == Material.DIRT) {
								map[i][j].setID(Material.GRASS);
							}
						}
					}
				}
			}
		}
		buildRock();
	}
	
	public void buildRock() {
		int chance = 100;
		int getBlock;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(j > skyHeight) {
					getBlock = Game.random.nextInt(chance);
					if(getBlock > 0 && getBlock < 10) {
						map[i][j].setID(Material.STONE);
					}
				}
			}
		}
		int iteration = 0;
		while(iteration < 20) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					if(map[i][j].getID() == Material.STONE) {
						chance = 20;
						getBlock = Game.random.nextInt(chance);
						if(i > 0 && j > 0  && i < size - 1 && j < size - 1) {
							try {
								switch(getBlock) {
								case 0:
									map[i + 1][j - 1].setID(Material.STONE);
									break;
								case 1:
									map[i + 1][j].setID(Material.STONE);
									break;
								case 2:
									map[i][j + 1].setID(Material.STONE);
									break;
								case 3:
									map[i + 1][j + 1].setID(Material.STONE);
									break;
								case 4:
									map[i - 1][j].setID(Material.STONE);
									break;
								case 5:
									map[i - 1][j - 1].setID(Material.STONE);
									break;
								case 6:
									map[i][j - 1].setID(Material.STONE);
								default:
									break;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			iteration++;
		}
		buildSand();
	}
	
	public void buildCoal() {
		int chance = 2500;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(j > skyHeight) {
					chance -= j * 10;
					int getOre = Game.random.nextInt(chance);
					if(getOre > 0 && getOre < 50) {
						map[i][j].setID(Material.COAL);
					}
					chance = 2500;
				}
			}
		}
		buildIron();
	}
	
	public void buildIron() {
		int chance = 5000;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(j > skyHeight) {
					chance -= j * 10;
					int getOre = Game.random.nextInt(chance);
					if(getOre > 0 && getOre < 50) {
						map[i][j].setID(Material.IRON);
					}
					chance = 5000;
				}
			}
		}
		addRectangles();
		ready = true;
	}
	
	public void addRectangles() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				map[i][j].rect.x = i * 32;
				map[i][j].rect.y = j * 32;
			}
		}
	}
	
	public void update() {
	}
	
	public void buildSand() {
		int iteration = 0;
		int sandCount = 0;
		int chance = 100;
		while(sandCount < 1) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					int getBlock = Game.random.nextInt(chance);
					if(map[i][j].getID() == Material.GRASS) {
						if(getBlock == 1) {
							map[i][j].setID(Material.SAND);
							sandCount++;
						}
					}
				}
			}
		}
		iteration = 0;
		chance = 20;
		while(iteration < 70) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					int growBlock = Game.random.nextInt(chance);
					if(map[i][j].getID() == Material.SAND) {
						if(i > 0 && j > 0  && i < size - 1 && j < size - 1) {
							try {
								switch(growBlock) {
								case 1:
									map[i + 1][j].setID(Material.SAND);
									break;
								case 2:
									map[i][j + 1].setID(Material.SAND);
									break;
								case 3:
									map[i + 1][j + 1].setID(Material.SAND);
									break;
								case 4:
									map[i - 1][j].setID(Material.SAND);
									break;
								default:
									break;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			iteration++;
		}
		addGravel();
	}
	
	public void addGravel() {
		int chance = 100;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				int getBlock = Game.random.nextInt(chance);
				if(map[i][j].getID() == Material.STONE) {
					if(getBlock == 1) {
						map[i][j].setID(Material.GRAVEL);
					}
				}
			}
		}
		int iteration = 0;
		while(iteration < 50) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					int growBlock = Game.random.nextInt(chance);
					if(map[i][j].getID() == Material.GRAVEL) {
						if(i > 0 && j > 0  && i < size - 1 && j < size - 1) {
							try {
								switch(growBlock) {
								case 1:
									map[i + 1][j].setID(Material.GRAVEL);
									break;
								case 2:
									map[i][j + 1].setID(Material.GRAVEL);
									break;
								case 3:
									map[i + 1][j + 1].setID(Material.GRAVEL);
									break;
								case 4:
									map[i - 1][j].setID(Material.GRAVEL);
									break;
								default:
									break;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			iteration++;
		}
		buildCoal();
	}
	
	public void paint(Graphics g) {
		if(ready) {
			for(int i = 0; i < back.length; i++) {
				for(int j = 0; j < back.length; j++) {
						g.drawImage(map[i][j].getImage(), (i + xOffset) * 32, (j + yOffset) * 32, 32, 32, null);
					}
				}
			
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					g.drawImage(map[i][j].getImage(), (i + xOffset) * 32, (j + yOffset) * 32, 32, 32, null);
				}
			}
		}
	}
	
	public int getBlocks() {
		int blockCount = 0;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(map[i][j].getID() != Material.AIR) {
					blockCount++;
				}
			}
		}
		return blockCount;
	}
}
