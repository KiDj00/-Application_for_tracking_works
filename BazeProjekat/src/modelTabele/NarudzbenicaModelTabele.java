/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTabele;

import domen.Narudzbenica;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class NarudzbenicaModelTabele extends AbstractTableModel {

    private List<Narudzbenica> lista;
    private final String[] kolona = {"Narudžbenica ID", "Cena bez PDV-a", "PDV", "Ukupna cena", "Rok izvršenja", "Garantni rok", "Način plaćanja", "Datum", "Poštanski broj mesta:", "Zapisnik ID", "Načelnik ID", "Kompanija ID"};

    public NarudzbenicaModelTabele() {
        lista = new ArrayList<>();
    }

    public NarudzbenicaModelTabele(List<Narudzbenica> lista) {
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
        Narudzbenica lista1 = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lista1.getId();
            case 1:
                return lista1.getCena().getVrednost();
            case 2:
                return lista1.getCena().getPdv();
            case 3:
                return lista1.getCena().getUkupna_cena();
            case 4:
                return lista1.getRokIzvrsenja();
            case 5:
                return lista1.getGarantniRok();
            case 6:
                return lista1.getNacinPlacanja();
            case 7:
                return lista1.getDatum();
            case 8:
                return lista1.getPostanskiBroj().getPostanskiBroj();
            case 9:
                return lista1.getZapisnikId().getId();
            case 10:
                return lista1.getNacelnikId().getId();
            case 11:
                return lista1.getKompanijaId().getId();

            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolona[column];
    }

    public Narudzbenica returnObject(int red) {
        return lista.get(red);
    }

    public void remove(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

}
