import java.io.IOException;
import java.util.Scanner;

public class Main
{
    private Coordinates pos;
    public static final String END_GAME = "quit";
    private static final String NL = System.lineSeparator();

    Room[][] world;
    public static void main(String[] args)
            throws IOException // when config file can't be read
    {

        Manager m = new Manager();
        playGame(m);
    }


    private static void playGame( Manager manager ) throws IOException {
        Scanner console = new Scanner( System.in ); // User's move commands

        manager.showRoom();
        System.out.print( NL + "> " ); // prompt

        // Continue loop until end of file reached, or user quits.
        while ( console.hasNextLine() ) {

            // Read start row, start column, end row, end column.
            //
            String move = console.nextLine();
            String[] parts = move.split( "\\s+" );


                // Special case for quitting below:
                if ( parts.length == 1 &&
                        parts[ 0 ].equalsIgnoreCase( END_GAME ) ) {
                    break;
                }





            ActionResult result = manager.ExecuteAction(parts);
            if ( !result.ok ) {
                System.out.println( result.message() );
            }

            // Get next move.
            manager.showRoom();
            System.out.print( NL + "> " ); // prompt
        }
        console.close();
    }



}
