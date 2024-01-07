/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.StavkaPodtipaRadova;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class StavkaPodtipaRadovaModelTabele extends AbstractTableModel {

    private List<StavkaPodtipaRadova> lista;
    private final String[] kolona = {"Tip radova ID", "Podtip radova ID", "Stavka ID", "Naziv tipa rada", "Opis radova", "Količina", "Jedinicna cena", "Jedinica mere"};

    public StavkaPodtipaRadovaModelTabele() {
        lista = new ArrayList<>();
    }

    public StavkaPodtipaRadovaModelTabele(List<StavkaPodtipaRadova> lista) {
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
        StavkaPodtipaRadova lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getPodtipRadova().getTipradovaId().getId();
            case 1:
                return lista1.getPodtipRadova().getPodtipRadovaId();
            case 2:
                return lista1.getStavkaId();
            case 3:
                return lista1.getNazivRada();
            case 4:
                return lista1.getOpisRadova();
            case 5:
                return lista1.getKolicina();
            case 6:
                return lista1.getJedinicnaCena();
            case 7:
                return lista1.getNazivJediniceMere();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public StavkaPodtipaRadova returnObject(int red) {
        return lista.get(red);
    }

    public void remove(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}
