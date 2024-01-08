/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.TipRadova;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TipRadovaModelTabele extends AbstractTableModel {

    private List<TipRadova> lista;
    private final String[] kolona = {"Tip radova ID", "Naziv tipa radova", "Predmer ID"};

    public TipRadovaModelTabele() {
        lista = new ArrayList<>();
    }

    public TipRadovaModelTabele(List<TipRadova> lista) {
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
        TipRadova lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getId();
            case 1:
                return lista1.getNaziv();
            case 2:
                return lista1.getPredmerID().getId();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public TipRadova returnObject(int red) {
        return lista.get(red);
    }
}
