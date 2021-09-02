/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Utakmica;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANA
 */
public class ModelTabeleUtakmica extends AbstractTableModel {

    ArrayList<Utakmica> listaUtakmica;
    ArrayList<Utakmica> listaObrisanih;

    public ModelTabeleUtakmica(ArrayList<Utakmica> listaUtakmica) {
        this.listaUtakmica = listaUtakmica;
        listaObrisanih = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listaUtakmica.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Utakmica u = listaUtakmica.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getGrupa();
            case 1:
                return u.getDomacin();
            case 2:
                return u.getGost();
            case 3:
                return u.getGolovaDomacin();
            case 4:
                return u.getGolovaGost();
            case 5:
                return u.getStatus();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Grupa";
            case 1:
                return "Domacin";
            case 2:
                return "Gost";
            case 3:
                return "Golova domacin";
            case 4:
                return "Golova gost";
            case 5:
                return "Status";
            default:
                return "n/a";
        }
    }

    public Utakmica dajIzabranuUtakmicu(int red) {
        return listaUtakmica.get(red);
    }

    public boolean izmeni(Utakmica u) {

        if (u.getGost().getReprezentacijaID() == u.getDomacin().getReprezentacijaID()) {
            System.out.println("Isti gost i domacin");
            return false;
        }
        boolean flag = false;
        String[] nizDozvoljenih = {"A", "B", "C", "D", "F", "E"};
        for (int i = 0; i < nizDozvoljenih.length; i++) {
            if (nizDozvoljenih[i].equals(u.getGrupa())) {
                flag = true;
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "Grupa moze imati vrednosti A,B,C,D,E,F");
            return false;
        }

        for (Utakmica ut : listaUtakmica) {
            if (ut.getDomacin().getReprezentacijaID() == u.getDomacin().getReprezentacijaID() && ut.getGost().getReprezentacijaID() == u.getGost().getReprezentacijaID()) {
                JOptionPane.showMessageDialog(null, "Ovaj par vec postoji");
                return false;
            }
        }

        for (Utakmica utakmica : listaUtakmica) {
            if (u.getUtakmicaID() == utakmica.getUtakmicaID()) {
                utakmica.setDomacin(u.getDomacin());
                utakmica.setGost(u.getGost());
                utakmica.setGolovaDomacin(u.getGolovaDomacin());
                utakmica.setGolovaGost(u.getGolovaGost());
                utakmica.setGrupa(u.getGrupa());
                utakmica.setStatus("Izmena");
            }
        }
        fireTableDataChanged();

        return true;
    }

    public void obrisi(int red) {
        Utakmica izbrisanaUtakmica = listaUtakmica.remove(red);
        izbrisanaUtakmica.setStatus("Brisanje");
        listaObrisanih.add(izbrisanaUtakmica);
        
        fireTableDataChanged();
    }

    public ArrayList<Utakmica> vratiListuSvihUtakmica() {
        ArrayList<Utakmica> listaU= listaUtakmica;
        listaU.addAll(listaObrisanih);
        return listaU;
    }

}
