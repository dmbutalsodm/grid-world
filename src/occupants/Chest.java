package occupants;

import java.util.Arrays;

public class Chest extends Occupant implements occupants.OccupantInterface {
    items.Item[] contains = new items.Item[10];
    private String objectName = "chest";

    public Chest(world.World world, items.Item[] items) {
        super(".", world);
        this.contains = items;
    }

    public Chest(world.World world) {
        super(".", world);
    }

    public String getObjectName() {
		return objectName;
	}

	public void use(PlayerCharacter pc) {
        pc.addItemsToInventory(contains);
        Arrays.fill(contains, null);
        misc.NotificationMessage.setMessage("You've emptied the chest of all its items.");
        return;
	}
}
