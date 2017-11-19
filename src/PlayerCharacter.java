import java.util.ArrayList;

public class PlayerCharacter extends Occupant implements OccupantInterface {
    private World world;
    private String objectName = "player";
    private String facingDirection = "down";
    private String displayCharacter = "v";
    private static Boolean playerExists = false;
    private ArrayList<Item> inventory = new ArrayList<Item>();

    public PlayerCharacter(World world) {
        super("v", world);
        if(PlayerCharacter.playerExists) throw new UnsupportedOperationException("You cannot have more than one player at a time.");
        this.world = world;
        PlayerCharacter.playerExists = true;
    }

    public void task(String task) {
        switch (task.toLowerCase()) {
        case "down":
        case "d":
        case "up":
        case "u":
        case "left":
        case "l":
        case "right":
        case "r":
            move(task.substring(0, 1), getPosition());
            break;
        case "interact":
        case "int":
            world.getTargetNeighbor(getPosition(), facingDirection).interact(this);
            world.endCycle();
            break;
        default:
            NotificationMessage.setMessage("I don't understand your command.");
            world.endCycle();
        }
    }
    
    public void move(String dir, int[] currPos) {
        switch(dir) {
            case "d":
                if(move(new int[] {currPos[0]+1, currPos[1]})) {
                    NotificationMessage.setMessage("You moved down.");
                }
                super.setDisplayCharacter("v");
                this.facingDirection = "down";
                break;
            case "u":
                if(move(new int[] {currPos[0]-1, currPos[1]})) {
                    NotificationMessage.setMessage("You moved up.");
                }
                super.setDisplayCharacter("^");
                this.facingDirection = "up";
                break;
            case "l":
                if(move(new int[] {currPos[0], currPos[1]-1})) {
                    NotificationMessage.setMessage("You moved left.");
                }
                super.setDisplayCharacter("<");
                this.facingDirection = "left";
                break;
            case "r":
                if(move(new int[] {currPos[0], currPos[1]+1})) {
                    NotificationMessage.setMessage("You moved right.");
                }
                super.setDisplayCharacter(">");
                this.facingDirection = "right";
                break;
        }
        world.endCycle();
        return;
    }

	public void interact(PlayerCharacter pc) {
        throw new UnsupportedOperationException();
	}
}
