package world;

import java.util.Scanner;
import occupants.Occupant;

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
			misc.NotificationMessage.setMessage("You bumped into a wall.");
			return false;
		}
		if(cells[newPos[0]][newPos[1]].getOccupant() != null) {
			misc.NotificationMessage.setMessage("You bumped into a " + cells[newPos[0]][newPos[1]].getOccupant().getObjectName() + ".");
			return false;
		}
		cells[newPos[0]][newPos[1]].setOccupant(cells[oldPos[0]][oldPos[1]].getOccupant());
		cells[oldPos[0]][oldPos[1]].setOccupant(null);
		return true;
	}

	public void spawn(occupants.Occupant occupant, int[] pos) {
		cells[pos[1]][pos[0]].setOccupant(occupant);
		occupant.setPosition(pos);
	}

	public void endCycle() {
		String str = "";
		for(int i = 0 ; i < 50 ; i++) str += "\n";
		System.out.println(str);
		System.out.println(misc.NotificationMessage.getMessage());
		printWorld();
	}

	public occupants.Occupant getFacingNeighbor(int[] currPos, String facingDirection) {
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
		occupants.PlayerCharacter pc = new occupants.PlayerCharacter(mainland);
		mainland.spawn(pc, new int[] {0, 0});
		items.Item[] items = new items.Item[] {new items.Item("knife"), new items.Item("box"), new items.Item("picture")};
		mainland.spawn(new occupants.Chest(mainland, items), new int[] {3, 4});
		mainland.printWorld();
		Scanner reader = new Scanner(System.in);
		while (true) {
			pc.task(reader.next());
		}
	}
}
