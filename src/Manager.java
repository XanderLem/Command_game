import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The Manager class acts as a bridge between the Main class and the other classes
 *
 */
public class Manager {
    private Coordinates pos;
    private Room r;
    private player p;

        Room[][] world;
        private String cWorld;

    /**
     * makes the player and makes the room
     * @throws IOException the exception that can be thrown if somthing goes wrong
     */
    public Manager() throws IOException // when config file can't be read
        {
            this.p = new player(this);
            this.r = new Room(p.GetRoomName(), this, p);
        }

    /**
     * prints the room and then the stats
     */
    public void showRoom(){
            r.PrintRoom();
            p.printStats();
    }

    /**
     * the real bridge between user input and output. calls the correct method depending on what was typed
     * @param l the list of strings that is used to determine what the user wants
     * @return ActionResult.OK if everything is ok.
     * @throws IOException thrown if something goes wrong
     */
    public ActionResult ExecuteAction(String[] l) throws IOException {
            switch (l[0]) {
                case "w" -> {
                    Coordinates c = new Coordinates( - 1,0) ;
                    if (r.PCanMove(c)) {r.MoveP(c);}
                    return ActionResult.OK;
                }
                case "s" -> {
                    Coordinates c = new Coordinates(1,0);
                    if (r.PCanMove(c)) {r.MoveP(c);}
                    return ActionResult.OK;
                }
                case "a" -> {
                    Coordinates c = new Coordinates(0, -1);
                    if (r.PCanMove(c)) {r.MoveP(c);}
                    return ActionResult.OK;
                }
                case "d" -> {
                    Coordinates c = new Coordinates(0, 1);
                    if (r.PCanMove(c)) {r.MoveP(c);}
                    return ActionResult.OK;
                }
                case "save"->{p.save();}
                case "score"->{p.addToScore(Integer.parseInt(l[1]));}
            }
            return ActionResult.OK;
        }

    /**
     * method to change what room the player is in, when the player moves into a space occupied by a door
     * @param d what door  the player wanted to go through
     * @throws IOException thrown if something goes wrong
     */
    public void switchRoom(Door d) throws IOException {
            Room newRoom = new Room(d.getDest(),this,p);
            d.save();
            newRoom.changeRoom(d.getOrigin(),d.pChange());
            r = newRoom;
        }
}
