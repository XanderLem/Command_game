import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class player {
    protected Coordinates pos;
    public Object parent;
    private final String fileName = "Player.txt";
    private final String Player_token = "P";
    private Room currentRoom;
    private String currentRoomName;
    public int score;
    private int health;
    private String name;

    public player(Object parent) throws IOException {
        this.parent = parent;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        this.currentRoomName = br.readLine();
        this.name = br.readLine();
        this.health = Integer.parseInt(br.readLine());
        this.score = Integer.parseInt(br.readLine());
    }



    public void Change_Coords(Coordinates coor){
        this.pos = coor;
    }

    public void Update_coords(Coordinates coor){
        this.pos.plus(coor);
    }

    public Coordinates getPos() {
        return pos;
    }

    public Room GetRoom(){
        return currentRoom;
    }

    public String GetRoomName(){
        return currentRoomName;
    }

    public void ChangeRoom(Room room){
        this.currentRoom = room;
        this.currentRoomName = room.toString();
    }


    public void ChangeHealth(int change){
        health+=change;
    }

    public String GetName(){
        return name;
    }

    public String toString(){
        return Player_token;
    }


}