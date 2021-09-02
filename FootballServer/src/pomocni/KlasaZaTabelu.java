/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomocni;

/**
 *
 * @author ANA
 */
public class KlasaZaTabelu {
    private String nazivReprezentacije;
    private int datihGolova;
    private int primljenihGolova;
    private int golRazlika;

    public KlasaZaTabelu() {
    }

    public KlasaZaTabelu(String nazivReprezentacije, int datihGolova, int primljenihGolova, int golRazlika) {
        this.nazivReprezentacije = nazivReprezentacije;
        this.datihGolova = datihGolova;
        this.primljenihGolova = primljenihGolova;
        this.golRazlika = golRazlika;
    }

    public int getGolRazlika() {
        return golRazlika;
    }

    public void setGolRazlika(int golRazlika) {
        this.golRazlika = golRazlika;
    }

    public String getNazivReprezentacije() {
        return nazivReprezentacije;
    }

    public void setNazivReprezentacije(String nazivReprezentacije) {
        this.nazivReprezentacije = nazivReprezentacije;
    }

    public int getDatihGolova() {
        return datihGolova;
    }

    public void setDatihGolova(int datihGolova) {
        this.datihGolova = datihGolova;
    }

    public int getPrimljenihGolova() {
        return primljenihGolova;
    }

    public void setPrimljenihGolova(int primljenihGolova) {
        this.primljenihGolova = primljenihGolova;
    }
    
}
