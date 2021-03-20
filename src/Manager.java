import java.io.IOException;


public class Manager {

        private Coordinates pos;

        Room[][] world;
        private String cWorld;
        public Manager() throws IOException // when config file can't be read
        {
            player p = new player(this);
            Room r = new Room(p.GetRoomName(), this, p);
            r.PrintRoom();
        }

}
