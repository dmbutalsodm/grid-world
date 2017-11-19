class Chest extends Occupant implements OccupantInterface {
    Item[] contains = new Item[10];
    private String objectName = "chest";

    public Chest(World world, Item[] items) {
        super(".", world);
        this.contains = items;
    }

    public Chest(World world) {
        super(".", world);
    }

    public String getObjectName() {
		return objectName;
	}

	public void use(PlayerCharacter pc) {
        NotificationMessage.setMessage("You've successfully interacted with the chest.");
        return;
	}
}
