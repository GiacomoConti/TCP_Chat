package tcp_chat;
/*
 * @author Giacomo
 */
public class AvvioClient {

    public static void main(String[] args) {
        Client c = new Client();
        c.richiedi();
        c.comunica();
    }    
}