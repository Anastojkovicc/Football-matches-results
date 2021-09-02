/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Reprezentacija;
import domen.Utakmica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANA
 */
public class DBBroker {

    Connection konekcija;

    public void ucitajDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjul16g1", "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Utakmica> vratiListuUtakmica() {
        ArrayList<Utakmica> listaUtakmica = new ArrayList<>();
        String sql = "SELECT * FROM utakmica u INNER JOIN reprezentacija d ON u.DomacinID=d.ReprezentacijaID JOIN reprezentacija g ON u.GostID=g.ReprezentacijaID";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int domacinID = rs.getInt("d.ReprezentacijaID");
                int gostID = rs.getInt("g.ReprezentacijaID");
                String dom = rs.getString("d.Naziv");
                String gos = rs.getString("g.Naziv");
                Reprezentacija domacin = new Reprezentacija(domacinID, dom);
                Reprezentacija gost = new Reprezentacija(gostID, gos);
                int utakmicaID = rs.getInt("UtakmicaID");
                String grupa = rs.getString("Grupa");
                int golovaDomacin = rs.getInt("GolovaDomacin");
                int golovaGost = rs.getInt("GolovaGost");

                Utakmica u = new Utakmica(utakmicaID, grupa, domacin, gost, golovaDomacin, golovaGost, "");
                listaUtakmica.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUtakmica;
    }

    public ArrayList<Reprezentacija> vratiListuReprezentacija() {
        ArrayList<Reprezentacija> listaReprezentacija = new ArrayList<>();
        String sql = "SELECT * FROM reprezentacija";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int repID = rs.getInt("ReprezentacijaID");
                String rep = rs.getString("Naziv");
                Reprezentacija r = new Reprezentacija(repID, rep);

                listaReprezentacija.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaReprezentacija;
    }

    public void izmeni(Utakmica u) throws SQLException {
        String sql = "UPDATE utakmica SET Grupa=?,DomacinID=?,GostID=?,GolovaDomacin=?,GolovaGost=? WHERE UtakmicaID=?";
        PreparedStatement ps = konekcija.prepareStatement(sql);
        ps.setString(1, u.getGrupa());
        ps.setInt(2, u.getDomacin().getReprezentacijaID());
        ps.setInt(3, u.getGost().getReprezentacijaID());
        ps.setInt(4, u.getGolovaDomacin());
        ps.setInt(5, u.getGolovaGost());
        ps.setInt(6, u.getUtakmicaID());
        ps.executeUpdate();
    }

    public void obrisi(Utakmica utakmica) throws SQLException {
        String sql = "DELETE FROM utakmica where UtakmicaID=" + utakmica.getUtakmicaID();
        Statement s = konekcija.createStatement();
        s.executeUpdate(sql);
    }
}
