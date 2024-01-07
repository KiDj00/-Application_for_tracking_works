/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.PodtipRadova;
import domen.Zapisnik;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ZapisnikModelTabele extends AbstractTableModel {

    private List<Zapisnik> lista;
    private final String[] kolona = {"Zapisnik ID", "Cena bez PDV-a", "PDV", "Ukupna cena", "Datum", "Poziv ID", "Grad", "Tim ID"};

    public ZapisnikModelTabele() {
        lista = new ArrayList<>();
    }

    public ZapisnikModelTabele(List<Zapisnik> lista) {
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
        Zapisnik lista1 = lista.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                lista1.getId();
            case 1 ->
                lista1.getVrednost().getVrednost();
            case 2 ->
                lista1.getVrednost().getPdv();
            case 3 ->
                lista1.getVrednost().getUkupna_cena();
            case 4 ->
                lista1.getDatum();
            case 5 ->
                lista1.getPozivId().getId();
            case 6 ->
                lista1.getPostanskiBroj().getNaziv();
            case 7 ->
                lista1.getTimId().getId();
            default ->
                "N/A";
        };
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public Zapisnik returnObject(int red) {
        return lista.get(red);
    }
}
