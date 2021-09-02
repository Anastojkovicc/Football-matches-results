/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomocni.KlasaZaTabelu;

/**
 *
 * @author ANA
 */
public class ModelTabeleServera extends AbstractTableModel {

    ArrayList<KlasaZaTabelu> lista;

    public ModelTabeleServera(ArrayList<KlasaZaTabelu> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KlasaZaTabelu k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getNazivReprezentacije();
            case 1:
                return k.getDatihGolova();
            case 2:
                return k.getPrimljenihGolova();
            case 3:
                return k.getGolRazlika();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Reprezentacija";
            case 1:
                return "Datih golova";
            case 2:
                return "Primljenih golova";
            case 3:
                return "Gol razlike";

            default:
                return "n/a";
        }

    }

}
