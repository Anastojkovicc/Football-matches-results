/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author ANA
 */
public class Utakmica implements Serializable {

    private int utakmicaID;
    private String grupa;
    private Reprezentacija domacin;
    private Reprezentacija gost;
    private int golovaDomacin;
    private int golovaGost;
    private String status;

    public Utakmica() {
    }

    public Utakmica(int utakmicaID, String grupa, Reprezentacija domacin, Reprezentacija gost, int golovaDomacin, int golovaGost, String status) {
        this.utakmicaID = utakmicaID;
        this.grupa = grupa;
        this.domacin = domacin;
        this.gost = gost;
        this.golovaDomacin = golovaDomacin;
        this.golovaGost = golovaGost;
        this.status = status;
    }

    public int getGolovaGost() {
        return golovaGost;
    }

    public void setGolovaGost(int golovaGost) {
        this.golovaGost = golovaGost;
    }

    public int getUtakmicaID() {
        return utakmicaID;
    }

    public void setUtakmicaID(int utakmicaID) {
        this.utakmicaID = utakmicaID;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public Reprezentacija getDomacin() {
        return domacin;
    }

    public void setDomacin(Reprezentacija domacin) {
        this.domacin = domacin;
    }

    public Reprezentacija getGost() {
        return gost;
    }

    public void setGost(Reprezentacija gost) {
        this.gost = gost;
    }

    public int getGolovaDomacin() {
        return golovaDomacin;
    }

    public void setGolovaDomacin(int golovaDomacin) {
        this.golovaDomacin = golovaDomacin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
