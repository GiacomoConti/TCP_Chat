package tcp_chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author Giacomo
 */
public class Server {

    int port;
    String autore;
    ServerSocket sSocket;
    Socket connection;
    Gestore g;
    Server() {
        port = 2000;
        sSocket = null;
        connection = null;
    }

    public void attendi() {

        try {
            sSocket = new ServerSocket(port);
            System.out.println("In attesa di connessioni!");
            connection = sSocket.accept();
            System.out.println("Connessione stabilita!");
            System.out.println("Socket server: " + connection.getLocalSocketAddress());
            System.out.println("Socket client: " + connection.getRemoteSocketAddress());
        } catch (IOException e) {
            System.err.println("Errore di I/O!");
        }

    }

    void comunica() {
        g = new Gestore(connection);
        g.leggi();
        g.menu();
        //autore = g.getAutore();   
        //stato = g.getStato();
    }
}
