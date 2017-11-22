package occupants;

import java.util.ArrayList;

public class PlayerCharacter extends Occupant implements OccupantInterface {
    private world.World world;
    private String facingDirection = "down";
    private String displayCharacter = "v";
    private static Boolean playerExists = false;
    private ArrayList<items.Item> inventory = new ArrayList<items.Item>();

    public PlayerCharacter(world.World world) {
        super("v", world);
        if(PlayerCharacter.playerExists) throw new UnsupportedOperationException("You cannot have more than one player at a time.");
        this.world = world;
        PlayerCharacter.playerExists = true;
    }

    public ArrayList<items.Item> getItems() {
        return inventory;
    }

    public void addItemsToInventory(items.Item[] items) {
        for (items.Item i : items) inventory.add(i);
    }

    public String getObjectName() {
        return "player";
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
        case "use":
            use(this);
            break;
        case "quit":
        case "exit":
        case "die":
        case "end":
            System.out.println("\n\n\n\n\n\n\n\n\nGoodbye!");
            System.exit(0);
        case "inv": 
            logInventory();
            break;
        default:
            misc.NotificationMessage.setMessage("I don't understand your command.");
            break;
        }
        world.endCycle();
    }
    
    private void logInventory() {
        if (inventory.size() == 0) {
            misc.NotificationMessage.setMessage("You have no items.");
            return;
        }
        String[] items = new String[inventory.size()];
        for (int i = 0 ; i < inventory.size() ; i++) {
            items[i] = inventory.get(i).getItemName();
        }
        misc.NotificationMessage.setMessage("The items you have are: " + String.join(", ", items));
	}

	public void move(String dir, int[] currPos) {
        switch(dir) {
            case "d":
                if(move(new int[] {currPos[0]+1, currPos[1]})) {
                    misc.NotificationMessage.setMessage("You moved down.");
                }
                super.setDisplayCharacter("v");
                this.facingDirection = "down";
                break;
            case "u":
                if(move(new int[] {currPos[0]-1, currPos[1]})) {
                    misc.NotificationMessage.setMessage("You moved up.");
                }
                super.setDisplayCharacter("^");
                this.facingDirection = "up";
                break;
            case "l":
                if(move(new int[] {currPos[0], currPos[1]-1})) {
                    misc.NotificationMessage.setMessage("You moved left.");
                }
                super.setDisplayCharacter("<");
                this.facingDirection = "left";
                break;
            case "r":
                if(move(new int[] {currPos[0], currPos[1]+1})) {
                    misc.NotificationMessage.setMessage("You moved right.");
                }
                super.setDisplayCharacter(">");
                this.facingDirection = "right";
                break;
        }
        world.endCycle();
        return;
    }

	public void use(PlayerCharacter pc) {
        try {
            world.getFacingNeighbor(getPosition(), facingDirection).use(this);
        } catch (Exception e) {
            misc.NotificationMessage.setMessage("There's nothing in front of you to use.");
        }
	}
}
