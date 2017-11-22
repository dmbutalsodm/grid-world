package world;

import occupants.Occupant;

public class Cell {
	private occupants.Occupant occupant;

	public Cell() {
	}

	public void setDisplayAs(String figure) {
		if(figure.length() != 1) throw new UnsupportedOperationException("The display chraracter must be a single character!");
	}

	public String getDisplayAs() {
		if(this.occupant == null) return "-";
		return this.occupant.getDisplayCharacter();
	}

	public void setOccupant(Occupant o) {
		this.occupant = o;
	}
	public Occupant getOccupant() {
		return this.occupant;
	}
	
}