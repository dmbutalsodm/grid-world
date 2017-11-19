import java.util.Scanner;

public class World {
	private Cell[][] cells;
	private int height;
	private int width;

	public World(int width, int height) {
		cells = new Cell[height][width];
		this.height = height;
		this.width = width;
		for(Cell[] cellSubArray : cells) {
			for(int i = 0 ; i < cellSubArray.length ; i++) {
				cellSubArray[i] = new Cell();
			}
		}
		setCells(cells);
	}

	private void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public void printWorld() {
		String worldRow = "";
		for(Cell[] row : cells) {
			worldRow = "";
			for(Cell cell : row) {
				worldRow += cell.getDisplayAs();
			}
			System.out.println(worldRow);
		}
	}
	
	public boolean move(int[] oldPos, int[] newPos) {
		if(newPos[0] > this.height-1 || newPos[1] > this.width-1 || newPos[0] < 0 || newPos[1] < 0) {
			NotificationMessage.setMessage("You bumped into a wall.");
			return false;
		}
		if(cells[newPos[0]][newPos[1]].getOccupant() != null) {
			NotificationMessage.setMessage("You bumped into a " + cells[newPos[0]][newPos[1]].getOccupant().getObjectName() + ".");
			return false;
		}
		cells[newPos[0]][newPos[1]].setOccupant(cells[oldPos[0]][oldPos[1]].getOccupant());
		cells[oldPos[0]][oldPos[1]].setOccupant(null);
		return true;
	}

	public void spawn(Occupant occupant, int[] pos) {
		cells[pos[0]][pos[1]].setOccupant(occupant);
		occupant.setPosition(pos);
	}

	public void endCycle() {
		String str = "";
		for(int i = 0 ; i < 50 ; i++) str += "\n";
		System.out.println(str);
		System.out.println(NotificationMessage.getMessage());
		printWorld();
	}

	public Occupant getTargetNeighbor(int[] currPos, String facingDirection) {
		switch(facingDirection) {
			case "down":
				return cells[currPos[0]+1][currPos[1]].getOccupant();
			case "up":
				return cells[currPos[0]-1][currPos[1]].getOccupant();
			case "left":
				return cells[currPos[0]][currPos[1]-1].getOccupant();
			case "right":
				return cells[currPos[0]][currPos[1]+1].getOccupant();
			default: 
				return null;
		}
	}

	public static void main(String[] args) {
		World mainland = new World(10, 5);
		PlayerCharacter pc = new PlayerCharacter(mainland);
		mainland.spawn(pc, new int[] {0, 0});
		mainland.spawn(new Chest(mainland), new int[] {3, 5});
		mainland.printWorld();
		Scanner reader = new Scanner(System.in);
		while (true) {
			pc.task(reader.next());
		}
	}
}
