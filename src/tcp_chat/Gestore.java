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
            linea = true;
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
            try {
                System.out.println("Scrivi /text per inviare un messaggio o scrivi /help per visualizzare il menu ");
                s1 = tastiera.readLine();

                if (s1.equals("/text")) {
                    scrivi();
                }

                if (s1.equals("/help")) {
                    System.out.println("1)/aut \n2)/stato \n3)/end");
                    s1 = tastiera.readLine();
                }

                if (s1.equals("/aut")) {
                    setAutore();
                }

                if (s1.equals("/stato")) {
                    setLinea();
                }
                if (s1.equals("/end")) {
                    System.out.println("Eseguendo la disconnessione...");
                    f=false;
                    
                }
            } catch (IOException er) {
                System.err.println("Errore inserimento tastiera");
            }
            if(bw!=null){
                leggi();
            }
        }
        
    }

    void leggi() {
        try {
            System.out.println(br.readLine());
        } catch (IOException err) {
            System.err.println("Errore di: " + err);
        }
    }

    void scrivi() {

        try {
            System.out.println("Scrivere il messaggio da inviare:");
            s1 = tastiera.readLine();
            bw.write(autore + ": " + s1 + "\n");
            bw.flush();
        } catch (IOException r) {
            System.err.println("Metodo scrivi = Errore di: " + r);
        }
    }

    void setAutore() {
        try {
            System.out.println("Inserire nome utente: ");
            autore = tastiera.readLine();
            System.out.println("Il tuo nome utente è: " + autore);
        } catch (IOException er) {
            System.err.println("Errore inserimento da tastiera");
        }
    }

    void setLinea() {
        try {
            System.out.println("Selezionare lo stato dell'utente: \n1)/offline \n2)/online");
            s1 = tastiera.readLine();
            if ("/offline".equals(s1)) {
                linea = false;
            }
            if ("/online".equals(s1)) {
                linea = true;
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
