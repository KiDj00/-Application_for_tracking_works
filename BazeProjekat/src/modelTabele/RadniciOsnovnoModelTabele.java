/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.RadnikOsnovno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class RadniciOsnovnoModelTabele extends AbstractTableModel {

    private List<RadnikOsnovno> lista;
    private final String[] kolona = {"Radnik ID", "Ime", "Prezime"};

    public RadniciOsnovnoModelTabele() {
        lista = new ArrayList<>();
    }

    public RadniciOsnovnoModelTabele(List<RadnikOsnovno> lista) {
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
        RadnikOsnovno lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getId();
            case 1:
                return lista1.getIme();
            case 2:
                return lista1.getPrezime();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public RadnikOsnovno returnObject(int red) {
        return lista.get(red);
    }

    public void remove(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}
