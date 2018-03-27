package tcp_chat;
/*
 * @author Giacomo
 */
public class AvvioServer{

    public static void main(String[] args) {
       
        Server s = new Server();
        s.attendi();
        s.comunica();
    }
}