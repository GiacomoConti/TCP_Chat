package tcp_chat;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Giacomo
 */
public class Client {

    Socket connection;
    String serverAddress;
    int port;
    Gestore g;

    Client() {
        connection = null;
        serverAddress = "localhost";
        port = 2000;

    }

    void richiedi() {

        try {
            connection = new Socket(serverAddress, port);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void comunica() {
        g = new Gestore(connection);
        g.menu();
    }

}
