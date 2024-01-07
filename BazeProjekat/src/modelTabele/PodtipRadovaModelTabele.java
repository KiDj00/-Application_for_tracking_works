/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.PodtipRadova;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class PodtipRadovaModelTabele extends AbstractTableModel {

    private List<PodtipRadova> lista;
    private final String[] kolona = {"Tip radova ID", "Podtip radova id", "Naziv podtipa radova", "Ukupna cena"};

    public PodtipRadovaModelTabele() {
        lista = new ArrayList<>();
    }

    public PodtipRadovaModelTabele(List<PodtipRadova> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PodtipRadova lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getTipradovaId().getId();
            case 1:
                return lista1.getPodtipRadovaId();
            case 2:
                return lista1.getNaziv();
            case 3:
                return lista1.getUkupnaCena();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public PodtipRadova returnObject(int red) {
        return lista.get(red);
    }
        public void remove(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}
