/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Reprezentacija;
import domen.Utakmica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author ANA
 */
public class ObradaZahteva extends Thread {

    Socket s;

    public ObradaZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.VRATI_SVE_UTAKMICE:
                    ArrayList<Utakmica> listaUtakmica = Kontroler.getInstanca().vratiListuUtakmica();
                    so.setOdgovor(listaUtakmica);
                    break;
                case Operacije.VRATI_REPREZENTACIJE:
                    ArrayList<Reprezentacija> listaRep = Kontroler.getInstanca().vratiListuReprezentacija();
                    so.setOdgovor(listaRep);
                    break;
                case Operacije.SACUVAJ_PROMENE:
                    ArrayList<Utakmica> listaUt = (ArrayList<Utakmica>) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajPromene(listaUt);
                    break;
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
