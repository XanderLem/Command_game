import java.io.*;
import java.util.*;

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


    @Override
    public String toString(){
        return fileName;
    }

    public Object GetAtPos(Coordinates pos){return map[pos.row()][pos.column()];}

    public Object GetParent(){return parent;}

    public void clearCell(Coordinates pos){map[pos.row()][pos.column()] = EMPTY;}

    public boolean isEmpty(Coordinates pos){
        return (map[pos.row()][pos.column()].equals(EMPTY));

    }

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


    public void PlaceOBJ(Object obj,Coordinates pos){
        map[pos.row()][pos.column()] = obj;
    }

    public void PlaceP(Coordinates pos){
        map[pos.row()][pos.column()] = P;
    }

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

    public void changeRoom(String origin, Coordinates change) throws IOException {
        Object d = hm.get(origin);
        P.ChangeRoom(this);
        Coordinates c = ((Door)d).getPos().plus(change);
        P.Change_Coords(c);
        PlaceP(c);
        save();
    }

    public void MoveP(Coordinates pos){
        Coordinates c = P.getPos().plus(pos);
        clearCell(P.getPos());
        PlaceP(c);
        P.Change_Coords(c);
    }

    public player getPlayer(){
        return P;
    }

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

        public void PrintRoom () {
            System.out.print("    ");
            for (int c = 0; c < map[0].length; ++c) {
                System.out.printf("%2d ", c);
            }
            System.out.println();
            for (int i = 0; i < map.length; i++) {
                System.out.printf("%2d  ", i);
                for (Object s : map[i]) {
                    System.out.print(" " + s.toString() + " ");
                }
                System.out.println();
            }
        }
    }

