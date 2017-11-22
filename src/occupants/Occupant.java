package occupants;

public abstract class Occupant {
    private String displayCharacter;
    private world.World world;
    private int[] pos;
    public abstract String getObjectName();

    public Occupant(String display, world.World world) {
        this.displayCharacter = display;
        this.world = world;
    }

    public void setPosition(int[] pos) {
        this.pos = pos;
    }

    public void setDisplayCharacter(String character) {
        this.displayCharacter = character;
    }
    
    public int[] getPosition() {
        return this.pos;
    }

    public boolean move(int[] newpos) {
        if(this.pos == null) throw new UnsupportedOperationException("I cannot move if I do not have a position!");
        if(world.move(this.pos, newpos)) {
            this.pos = newpos;
            return true;
        }
        return false;
    }

    public String getDisplayCharacter() {
        return this.displayCharacter;
    }

	public void use(PlayerCharacter pc) {
		throw new UnsupportedOperationException("Something went terribly wrong.");
	}
}