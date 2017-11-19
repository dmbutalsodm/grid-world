public class Occupant implements OccupantInterface {
    private String displayCharacter;
    private World world;
    private int[] pos;
    private String objectName = "mystery occupant";

    public Occupant(String display, World world) {
        this.displayCharacter = display;
        this.world = world;
    }

    public void setPosition(int[] pos) {
        this.pos = pos;
    }

    public void setDisplayCharacter(String character) {
        this.displayCharacter = character;
    }
    
	public String getObjectName() {
		return objectName;
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