import java.io.*;
import java.util.*;

/**
 * the room object is basically where everything happens. its the world
 */
public class Room {

    private Object[][] map;
    private final String fileName;
    private final int numRows, numCols;
    public static final char COMMENT = '#';
    private static final String EMPTY= ".";
    private static final String Wall = "=";
    private static final String PLAYER = "P";
    private static final String TOKEN = "$";
    public Object parent;
    private player P;
    private HashMap<String,Object> hm;

    /**
     * reads from the room file and makes a 2d array filled with the characters/ player/ doors
     * @param filename the filename to read from
     * @param Parent the parent of the room (the manager)
     * @param p the player object
     * @throws IOException thrown if something goes wrong
     */
    public Room(String filename, Object Parent, player p) throws IOException {
        this.fileName = filename;
        this.parent = Parent;
        this.P = p;
        this.hm = new HashMap<>();
        File f = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String firstLine;
            firstLine = br.readLine();
            String[] dims = firstLine.split("\\s+");
            do {
                firstLine = br.readLine();
            } while ( firstLine.charAt( 0 ) == '#' );
            if (dims.length != 2) {
                System.out.println("Improper first line of config file: " + firstLine);

                // The following lines exist only to silence compiler warnings.
                this.numRows = -1;
                this.numCols = -1;
            } else {
                // First line (board dimensions) seems good.
                // One of the next two lines could have a parse-int error,
                // but we don't explicitly handle that. Program will crash.
                this.numRows = Integer.parseInt(dims[0]);
                this.numCols = Integer.parseInt(dims[1]);
                this.map = new Object[this.numRows][this.numCols];
                /*
                String line;
                int temp = 0;
                while( (line = br.readLine()) != null) {
                    if ( line.length() == 0 ||
                            line.charAt( 0 ) == COMMENT ) continue;
                    String[] pieces = line.split( "\\s+" );
                    for(int i = 0; i<numCols;i++){
                        map[temp][i] = pieces[i];
                    }
                    temp++;


                    System.out.print(line);

                }

                 */
                String line = firstLine;
                String token;
                for (int i = 0; i<numRows;i++ ){
                    if(line==null){break;}
                    if ( line.length() == 0 || line.charAt( 0 ) == COMMENT ) continue;
                    String[] pieces = line.split( "\\s+" );
                    for(int j = 0; j< pieces.length;j++){
                        Object obj;
                        token = pieces[j];
                        if(token.contains(".txt")){
                            obj = new Room(token, this,p);
                        }
                        else if(token.contains(".door")){
                            obj = new Door(this,token,new Coordinates(i,j));
                            hm.put(((Door)obj).getDest(),obj);
                        }
                        else if(token.equals(PLAYER)){
                            P.Change_Coords(new Coordinates(i,j));
                            P.ChangeRoom(this);
                            obj = P;
                        }
                        else{obj = token;}
                        if(obj==null){break;}
                        map[i][j] = obj;
                    }
                    line = br.readLine();
                }


            }

        br.close();
    }


    /**
     * @return the filename
     */
    @Override
    public String toString(){
        return fileName;
    }

    /**
     * @param pos the position to get from
     * @return whatever is at the position in the map
     */
    public Object GetAtPos(Coordinates pos){return map[pos.row()][pos.column()];}

    /**
     * @return the parent (not used, but might be useful in the future)
     */
    public Object GetParent(){return parent;}

    /**
     * clears the cell at the postion
     * @param pos the position to clear the cell
     */
    public void clearCell(Coordinates pos){map[pos.row()][pos.column()] = EMPTY;}

    /**
     * @param pos the position to check
     * @return true if the position is empty, false otherwise
     */
    public boolean isEmpty(Coordinates pos){
        return (map[pos.row()][pos.column()].equals(EMPTY));

    }

    /**
     * if the position is not empty and the player wants to go there, this method is called
     * to find out what to do
     * @param pos the position of the cell where the player wants to move to
     * @return true if the player can move there, false if they cant
     * @throws IOException if something goes wrong
     */
    public boolean collided(Coordinates pos) throws IOException {
        Object o = map[pos.row()][pos.column()];
        if(o.toString().equals(TOKEN)) {
            P.addToScore(1);
            return true;
        }
        else if(o instanceof Door){
            ((Manager)parent).switchRoom(((Door)o));
            return false;
        }
        return false;
    }

    /**
     * places an object at the position
     * @param obj the object to be placed
     * @param pos the position to place the object
     */
    public void PlaceOBJ(Object obj,Coordinates pos){
        map[pos.row()][pos.column()] = obj;
    }

    /**
     * places the player at the position
     * @param pos the position to place the player
     */
    public void PlaceP(Coordinates pos){
        map[pos.row()][pos.column()] = P;
    }

    /**
     * checks if the player can move to a position, also checks if the position is in the map
     * @param pos the distance/direction the player wants to move
     * @return true if the player can move the desired amount/ direction
     * @throws IOException thrown if something goes wrong
     */
    public boolean PCanMove(Coordinates pos) throws IOException {
        Coordinates c = P.getPos().plus(pos);
        if((c.row()>=0 && c.row()<=numRows)&&(c.column()>=0 && c.column()<= numCols)){
            if(!isEmpty(c)){
                return collided(c);
            }
            return true;
        }
        return false;

    }

    /**
     * method to change the room/ handle stuff when the player goes through a door to this new room
     * @param origin where the player came from
     * @param change the change in position the player should move
     * @throws IOException thrown if something goes wrong
     */
    public void changeRoom(String origin, Coordinates change) throws IOException {
        Object d = hm.get(origin);
        P.ChangeRoom(this);
        Coordinates c = ((Door)d).getPos().plus(change);
        P.Change_Coords(c);
        PlaceP(c);
        save();
    }

    /**
     * moves the player an amount of spaces in a direction
     * @param pos the position to add to the players position to get the destination
     */
    public void MoveP(Coordinates pos){
        Coordinates c = P.getPos().plus(pos);
        clearCell(P.getPos());
        PlaceP(c);
        P.Change_Coords(c);
    }

    /**
     * @return the player
     */
    public player getPlayer(){
        return P;
    }

    /**
     * goes through the map and writes it down in the room file to save the room
     * @throws IOException thrown if something goes wrong
     */
    public void save() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(numCols + " " + numCols);
        bw.newLine();
        for (Object[] objects : map) {
            for (Object s : objects) {
                if(s instanceof Door){
                    bw.write(((Door) s).saveName() + " ");
                }
                else{
                    bw.write(s.toString() + " ");
                }

            }
            bw.newLine();
        }
        bw.close();
    }

    /**
     * prints a string representation of the room
     */
    public void PrintRoom(){
            System.out.print("    ");
            for (int c = 0; c < map[0].length; ++c) {
                System.out.printf("%2d ", c);
            }
            System.out.println();
            for (int i = 0; i < map.length; i++) {
                System.out.printf("%2d  ", i);
                for (Object s : map[i]) {System.out.print(" " + s.toString() + " ");}
                System.out.println();
            }
        }
    }

