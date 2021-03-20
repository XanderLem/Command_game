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

        public ActionResult ExecuteAction(String[] l){
            if(l[0].equals("w")){
                Coordinates c = new Coordinates(p.getPos().row()-1 ,p.getPos().column() );
                if( r.CanMove(c)){
                    r.MoveP(c);
                }
                return ActionResult.OK;
            }
            else if(l[0].equals("s")){
                Coordinates c = new Coordinates(p.getPos().row()+1 ,p.getPos().column() );
                if( r.CanMove(c)){
                    r.MoveP(c);
                }
                return ActionResult.OK;
            }
            else if(l[0].equals("a")){
                Coordinates c = new Coordinates(p.getPos().row() ,p.getPos().column()-1 );
                if( r.CanMove(c)){
                    r.MoveP(c);
                }
                return ActionResult.OK;
            }
            else if(l[0].equals("d")){
                Coordinates c = new Coordinates(p.getPos().row() ,p.getPos().column()+1 );
                if( r.CanMove(c)){
                    r.MoveP(c);
                }
                return ActionResult.OK;
            }
            return ActionResult.OK;
        }


}
