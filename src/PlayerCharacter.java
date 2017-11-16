public class PlayerCharacter extends Occupant {
    private World world;
    private String objectName = "player";
    private String facingDirection = "down";
    private String displayCharacter = "v";
    private static Boolean playerExists = false;

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
            move(task, getPosition());
            break;
        case "interact":
        case "i":
            
        }
    }
    
    public void move(String dir, int[] currPos) {
        switch(dir) {
            case "down":
            case "d":
                if(move(new int[] {currPos[0]+1, currPos[1]})) {
                    NotificationMessage.setMessage("You moved down.");
                }
                super.setDisplayCharacter("v");
                this.facingDirection = "down";
                break;
            case "up":
            case "u":
                if(move(new int[] {currPos[0]-1, currPos[1]})) {
                    NotificationMessage.setMessage("You moved up.");
                }
                super.setDisplayCharacter("^");
                this.facingDirection = "up";
                break;
            case "left":
            case "l":
                if(move(new int[] {currPos[0], currPos[1]-1})) {
                    NotificationMessage.setMessage("You moved left.");
                }
                super.setDisplayCharacter("<");
                this.facingDirection = "left";
                break;
            case "right":
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
}