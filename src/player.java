import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for the player, in charger of keeping info, reading and wrting info, changing rooms etc
 */
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
        br.close();
    }


    /**
     * changes the coordinates
     * @param coor the new coordinates to set the position to
     */
    public void Change_Coords(Coordinates coor){
        this.pos = coor;
    }

    /**
     * @return the current position
     */
    public Coordinates getPos() {
        return pos;
    }

    /**
     * @return the current room
     */
    public Room GetRoom(){
        return currentRoom;
    }

    /**
     * @return the current room name
     */
    public String GetRoomName(){
        return currentRoomName;
    }

    /**
     * sets current room equal to room and current room name equal to room.tostring
     * @param room
     */
    public void ChangeRoom(Room room){
        this.currentRoom = room;
        this.currentRoomName = room.toString();
    }


    /**
     * adds change to the health
     * @param change the amount to add to the health
     */
    public void ChangeHealth(int change){
        health+=change;
    }

    /**
     * adds value to score
     * @param value the value to add to the score
     */
    public void addToScore(int value){
        score+=value;
    }

    /**
     * @return the name
     */
    public String GetName(){
        return name;
    }

    /**
     * @return the player token
     */
    public String toString(){
        return Player_token;
    }

    /**
     * saves all the info to the player file and then tells the current room to save
     * @throws IOException an exception needed because of buffered writer I think
     */
    public void save() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(currentRoomName);
        bw.newLine();
        bw.write(name);
        bw.newLine();
        bw.write(String.valueOf(health));
        bw.newLine();
        bw.write(String.valueOf(score));
        bw.newLine();
        bw.close();
        currentRoom.save();
    }

    /**
     * prints the players stats
     */
    public void printStats(){
        System.out.println();
        System.out.println("Room: "+ currentRoomName);
        System.out.println("Name: "+ name);
        System.out.println("Health: "+ health);
        System.out.println("Score: "+ score);
    }


}
