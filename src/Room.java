import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Room {

    private Object[][] map;
    private final String fileName;
    private final int numRows, numCols;
    public static final char COMMENT = '#';
    private static final String EMPTY= ".";
    private static final String Wall = "=";
    private static final String PLAYER = "P";
    public Object parent;
    private player P;

    public Room(String filename, Object Parent, player p) throws IOException {
        this.fileName = filename;
        //this.pos = Pos;
        this.parent = Parent;
        this.P = p;
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
                        else if(token.equals(PLAYER)){
                            P.Change_Coords(new Coordinates(i,j));
                            P.ChangeRoom(this);
                            obj = P;
                            System.out.println("FOUND PLAYER");
                        }
                        else{
                           obj = token;
                        }
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

    public boolean isEmpty(Coordinates pos){return map[pos.row()][pos.column()].equals(EMPTY);}

    public void PlaceOBJ(Object obj,Coordinates pos){
        map[pos.row()][pos.column()] = obj;
    }

    public boolean CanMove(Coordinates pos){
        if((pos.row()>=0 && pos.row()<=numRows)&&(pos.column()>=0 && pos.column()<= numCols)){
            return isEmpty(pos);
        }
        return false;

    }

    public void MoveP(Coordinates pos){
        Coordinates old = P.getPos();
        clearCell(old);
        PlaceOBJ(P,pos);
        P.Change_Coords(pos);
    }



    public void PrintRoom(){
        System.out.print( "    " );
        for ( int c = 0; c < map[0].length; ++c ) {
            System.out.printf( "%2d ", c );
        }
        System.out.println();
        for(int i=0; i<map.length;i++){
            System.out.printf( "%2d  ", i );
            for(Object s:map[i]){
                System.out.print(" "+ s.toString() + " ");
            }
            System.out.println();
        }
    }

}
