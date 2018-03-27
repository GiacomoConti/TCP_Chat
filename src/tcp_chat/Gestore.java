package tcp_chat;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Giacomo
 */
public class Gestore {

    String autore, s1, s2;
    InputStream is;
    OutputStream os;
    BufferedWriter bw;
    BufferedReader br;
    Boolean linea;
    Socket connection;
    int scelta, n;
    BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

    Gestore(Socket c) {
        try {
            connection = c;
            is = connection.getInputStream();
            os = connection.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));
            br = new BufferedReader(new InputStreamReader(is));
            autore = "default";
        } catch (IOException ex) {
            Logger.getLogger(Gestore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void menu() {
        boolean f = true;
        while (f) {
            boolean fine = true;
            while (fine) {
                try {
                    fine=true;
                    System.out.println("Scrivi /text per inviare un messaggio o Scrivi /help per visualizzare il menu ");
                    s1 = tastiera.readLine();
                    if (s1.equals("/text")) {
                        scrivi();
                        fine = false;
                    }

                    if (s1.equals("/help")) {
                        System.out.println("1)/aut \n2)/stato");
                        fine = false;
                    }

                    if (s1.equals("/aut")) {
                        setAutore();
                        fine = false;
                    }
                    if (s1.equals("/stato")) {
                        setLinea();
                        fine = false;
                    }

                } catch (IOException er) {
                    System.err.println("Errore inserimento tastiera");
                }
            }
            f=false;
            leggi();
        }
    }

    void leggi() {
        try {
            System.out.println("Sto leggendo " + br.readLine());
        } catch (IOException err) {
            System.err.println("Errore di: " + err);
        }
    }

    void scrivi() {

        try {
            s1 = tastiera.readLine();
            bw.write(s1+"\n");
            bw.flush();
        } catch (IOException r) {
            System.err.println("Metodo scrivi = Errore di: " + r);
        }
    }

    void setAutore() {
        try {
            autore = tastiera.readLine();
        } catch (IOException er) {
            System.err.println("Errore inserimento da tastiera");
        }
    }

    void setLinea() {
        try {
            s1 = tastiera.readLine();
            if ("Offline".equals(s1) || "offline".equals(s1)) {
                linea = false;
            }
        } catch (IOException er) {
            System.err.println("Errore inserimento da tastiera");
        }
    }
    
    void smile() {

    }

    void echo() {
        try {
            s1 = tastiera.readLine();
            bw.write(s1);
            bw.flush();
        } catch (IOException r) {
            System.err.println("Errore di: " + r);
        }
    }

    void end() {

    }
}
