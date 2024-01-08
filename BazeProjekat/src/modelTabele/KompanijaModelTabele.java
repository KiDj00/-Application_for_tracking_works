/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.Kompanija;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class KompanijaModelTabele extends AbstractTableModel{
        private List<Kompanija> lista;
    private final String[] kolona = {"Kompanija ID", "PIB", "Naziv kompanije", "Grad"};

    public KompanijaModelTabele() {
        lista = new ArrayList<>();
    }

    public KompanijaModelTabele(List<Kompanija> lista) {
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
        Kompanija lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getId();
            case 1:
                return lista1.getPib().getVrednost();
            case 2:
                return lista1.getNaziv();
            case 3:
                return lista1.getGrad();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public Kompanija returnObject(int red) {
        return lista.get(red);
    }

    public void remove(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}
