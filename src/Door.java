import java.io.IOException;

public class Door {
    private final String origin;
    private final Room parent;
    private final String destination;
    private final String token = "D";
    private final Coordinates pos;


    public Door(Room parent, String dest, Coordinates pos) {
        this.parent = parent;
        this.origin = parent.toString();
        this.destination = dest.substring(0,dest.indexOf("."))+".txt";
        this.pos = pos;

    }

    @Override
    public String toString(){
        return token;
    }

    public Coordinates getPos(){
        return pos;
    }

    public String getOrigin(){
        return origin;
    }

    public String getDest(){
        return destination;
    }

    public Room getParent(){
        return parent;
    }

    public void save() throws IOException {
        parent.clearCell(parent.getPlayer().getPos());
        parent.save();
    }

    public Coordinates pChange(){
        Coordinates p = parent.getPlayer().getPos();
        return new Coordinates(pos.row()-p.row(),pos.column()-p.column());
    }

    public String saveName(){
        return destination.substring(0,destination.indexOf("."))+".door";
    }







}
