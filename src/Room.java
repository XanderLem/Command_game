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
    protected Coordinates pos;
    public Object parent;

    public Room(String filename, Coordinates Pos, Object Parent) throws IOException {
        this.fileName = filename;
        this.pos = Pos;
        this.parent = Parent;
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
                for (int i = 0; i<numRows;i++ ){
                    if(line==null){break;}
                    if ( line.length() == 0 || line.charAt( 0 ) == COMMENT ) continue;
                    String[] pieces = line.split( "\\s+" );
                    for(int j = 0; j< pieces.length;j++){
                     Object obj;
                        if(pieces[j].contains(".txt")){
                            obj = new Room(pieces[j],new Coordinates(i,j), this);
                        }
                        else{
                           obj = pieces[j];
                        }
                        if(obj==null){break;}
                        map[i][j] = obj;
                    }
                    line = br.readLine();
                }


            }


    }


    @Override
    public String toString(){
        return fileName;
    }

    public Object GetAtPos(Coordinates pos){
        return map[pos.row()][pos.column()];
    }

    public Object GetParent(){
        return parent;
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
