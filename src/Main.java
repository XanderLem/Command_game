import java.io.IOException;
public class Main
{
    private Coordinates pos;

    Room[][] world;
    public static void main(String[] args)
            throws IOException // when config file can't be read
    {

        Room r = new Room(args[0], new Coordinates(0,0), null);
        r.PrintRoom();
    }



}
