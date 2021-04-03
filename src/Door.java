import java.io.IOException;

/**
 * Door class is mainly for changing rooms
 */
public class Door {
    private final String origin;
    private final Room parent;
    private final String destination;
    private final String token = "D";
    private final Coordinates pos;


    /**
     * assigns the parent, oragin, destination and position.
     * the destination is derived from the string dest which looks like "dest.door",
     * it takes"dest" and adds ".txt" to the end of it to get the file name of the destination "dest.txt"
     * @param parent the room where this door is
     * @param dest the string that was read in the room file
     * @param pos the coordinates of the door in the room
     */
    public Door(Room parent, String dest, Coordinates pos) {
        this.parent = parent;
        this.origin = parent.toString();
        this.destination = dest.substring(0,dest.indexOf("."))+".txt";
        this.pos = pos;

    }

    /**
     * @return the doors token
     */
    @Override
    public String toString(){
        return token;
    }

    /**
     * @return the position of the door
     */
    public Coordinates getPos(){
        return pos;
    }

    /**
     * @return the origin of the door (where it is)
     */
    public String getOrigin(){
        return origin;
    }

    /**
     * @return the destination of the door
     */
    public String getDest(){
        return destination;
    }

    /**
     * @return the parent of the door
     */
    public Room getParent(){
        return parent;
    }

    /**
     * used when the player is changing rooms, the manager calls door.save()
     * saves the room without the player in it
     * @throws IOException thrown if somthing goes wrong
     */
    public void save() throws IOException {
        parent.clearCell(parent.getPlayer().getPos());
        parent.save();
    }

    /**
     * calculates the direction the player was in relation to the door
     * used to ensure that when the player goes into the new room, they go 1 space in the direction they were going
     *
     * @return those calculated coordinates
     */
    public Coordinates pChange(){
        Coordinates p = parent.getPlayer().getPos();
        return new Coordinates(pos.row()-p.row(),pos.column()-p.column());
    }

    /**
     * @return the string representation of the door for saving ( dest.door)
     */
    public String saveName(){
        return destination.substring(0,destination.indexOf("."))+".door";
    }







}
