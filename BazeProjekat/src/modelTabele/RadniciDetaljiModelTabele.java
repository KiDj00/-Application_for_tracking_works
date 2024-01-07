/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.Radnik;
import domen.RadnikDetalji;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class RadniciDetaljiModelTabele extends AbstractTableModel {

    private List<RadnikDetalji> lista;
    private final String[] kolona = {"Radnik ID", "JMBG", "Zvanje","Telefon"};

    public RadniciDetaljiModelTabele() {
        lista = new ArrayList<>();
    }

    public RadniciDetaljiModelTabele(List<RadnikDetalji> lista) {
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
        RadnikDetalji lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getId();
            case 1:
                return lista1.getJmbg();
            case 2:
                return lista1.getZvanje();
            case 3:
                return lista1.getTelefon();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public RadnikDetalji returnObject(int red) {
        return lista.get(red);
    }
}
