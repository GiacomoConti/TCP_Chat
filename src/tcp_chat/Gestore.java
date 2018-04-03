package tcp_chat;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Giacomo
 */
public class Gestore {

    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    String autore, s1;
    InputStream is;
    OutputStream os;
    BufferedWriter bw;
    BufferedReader br;
    Boolean linea;
    Socket connection;
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
            System.out.println(PURPLE + "PURPLE = Console." + RESET + "\nBLACK = Client.\n");
        } catch (IOException ex) {
            Logger.getLogger(Gestore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void menu() {
        boolean fine = true, f =true;
        while (f) {
            fine = true;
            while (fine) {
                try {
                    if (linea) {
                        System.out.println(PURPLE + "Scrivi /text per inviare un messaggio o scrivi /help per visualizzare il menu. " + RESET);
                        s1 = tastiera.readLine();

                        if (s1.equals("/text")) {
                            scrivi();
                            fine = false;
                        }

                        if (s1.equals("/help")) {
                            System.out.println(PURPLE + "Set the username: /aut \nSet the line states: /stato \nSend a smile: /smile \nEcho a messagge: /echo \nEnd the chat: /end" + RESET);
                            s1 = tastiera.readLine();
                        }

                        if (s1.equals("/aut")) {
                            setAutore();
                        }

                        if (s1.equals("/stato")) {
                            setLinea();
                            fine = false;
                        }
                        if (s1.equals("/smile")) {
                            smile();
                            fine = false;
                        }

                        if (s1.equals("/end")) {
                            f = false;
                            end();                          
                        }
                    } else {
                        setLinea();
                    }
                } catch (IOException er) {
                    System.err.println("Errore inserimento tastiera");
                }
            }
            leggi();
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
            System.out.println(PURPLE + "Scrivere il messaggio da inviare:" + RESET);
            s1 = tastiera.readLine();
            bw.write(autore + ": " + s1 + "\n");
            bw.flush();
        } catch (IOException r) {
            System.err.println("Metodo scrivi = Errore di: " + r);
        }
    }

    void setAutore() {
        try {
            System.out.println(PURPLE + "Inserire nome utente: " + RESET);
            autore = tastiera.readLine();
            System.out.println(PURPLE + "Il tuo nome utente è: " + autore + RESET);
        } catch (IOException er) {
            System.err.println("Errore inserimento da tastiera");
        }
    }

    void setLinea() {
        try {
            System.out.println(PURPLE + "Selezionare lo stato dell'utente: /offline or /online" + RESET);
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
        try {
            bw.write(autore + ": " + "=)" + "\n");
            bw.flush();
        } catch (IOException r) {
            System.err.println("Metodo smile = Errore di " + r);
        }
    }

    void echo() {

    }

    void end() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connessione chiusa!");
            }
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
            System.err.println("Errore nella chiusura della connessione!");
        }
        System.out.println(PURPLE + "Disconnessione eseguita." + RESET);
    }
}
