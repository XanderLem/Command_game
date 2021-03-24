import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Manager {
    private Coordinates pos;
    private Room r;
    private player p;

        Room[][] world;
        private String cWorld;
        public Manager() throws IOException // when config file can't be read
        {
            this.p = new player(this);
            this.r = new Room(p.GetRoomName(), this, p);


        }

        public void showRoom(){
            r.PrintRoom();
        }

        public ActionResult ExecuteAction(String[] l) throws IOException {
            switch (l[0]) {
                case "w" -> {
                    Coordinates c = p.getPos().plus(new Coordinates( - 1,0)) ;
                    if (r.CanMove(c)) {
                        r.MoveP(c);
                    }
                    return ActionResult.OK;
                }
                case "s" -> {
                    Coordinates c = p.getPos().plus(new Coordinates(1,0));
                    if (r.CanMove(c)) {
                        r.MoveP(c);
                    }
                    return ActionResult.OK;
                }
                case "a" -> {
                    Coordinates c = p.getPos().plus(new Coordinates(0, -1));
                    if (r.CanMove(c)) {
                        r.MoveP(c);
                    }
                    return ActionResult.OK;
                }
                case "d" -> {
                    Coordinates c = p.getPos().plus(new Coordinates(0, 1));
                    if (r.CanMove(c)) {
                        r.MoveP(c);
                    }
                    return ActionResult.OK;
                }
                case "save"->{
                    p.save();
                }
                case"score"->{
                    p.addToScore(Integer.parseInt(l[1]));
                }
            }
            return ActionResult.OK;
        }


}
