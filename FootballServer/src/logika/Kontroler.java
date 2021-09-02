/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Reprezentacija;
import domen.Utakmica;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author ANA
 */
public class Kontroler {

    private static Kontroler instanca;
    DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ArrayList<Utakmica> vratiListuUtakmica() {
        db.ucitajDriver();
        db.otvoriKonekciju();
        ArrayList<Utakmica> lista = db.vratiListuUtakmica();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Reprezentacija> vratiListuReprezentacija() {
        db.ucitajDriver();
        db.otvoriKonekciju();
        ArrayList<Reprezentacija> lista = db.vratiListuReprezentacija();
        db.zatvoriKonekciju();
        return lista;
    }

    public ServerskiOdgovor sacuvajPromene(ArrayList<Utakmica> listaUt) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.otvoriKonekciju();
        try {
            for (Utakmica utakmica : listaUt) {
                if (utakmica.getStatus().equals("Izmena")) {
                    db.izmeni(utakmica);
                }
                if (utakmica.getStatus().equals("Brisanje")) {
                    db.obrisi(utakmica);
                }
            }
            db.commit();
            so.setPoruka("Uspesno sacuvano");
            so.setOdgovor(true);
        } catch (SQLException ex) {
            db.rollback();
            so.setOdgovor(false);
            so.setPoruka("Neuspesno sacuvano");
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.zatvoriKonekciju();
        return so;
    }

}
