/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.StavkaZapisnika;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class StavkaZapisnikaModelTabele extends AbstractTableModel {

    private List<StavkaZapisnika> lista;
    private final String[] kolona = {"Zapisnik ID", "Stavka ID", "Naziv kompanije", "Cena bez PDV-a", "PDV", "Ukupna cena", "Rok za obavljanje", "Opicija ponude", "Garantni rok", "Kompanija ID"};

    public StavkaZapisnikaModelTabele() {
        lista = new ArrayList<>();
    }

    public StavkaZapisnikaModelTabele(List<StavkaZapisnika> lista) {
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
        StavkaZapisnika lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getZapisnikId().getId();
            case 1:
                return lista1.getStavkaId();
            case 2:
                return lista1.getKompanija().getNaziv();
            case 3:
                return lista1.getVrednost().getVrednost();
            case 4:
                return lista1.getVrednost().getPdv();
            case 5:
                return lista1.getVrednost().getUkupna_cena();
            case 6:
                return lista1.getRokZaObavljanje();
            case 7:
                return lista1.getOpcijaPonude();
            case 8:
                return lista1.getGarantniRok();
            case 9:
                return lista1.getKompanija().getId();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public StavkaZapisnika returnObject(int red) {
        return lista.get(red);
    }

    public void remove(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}
