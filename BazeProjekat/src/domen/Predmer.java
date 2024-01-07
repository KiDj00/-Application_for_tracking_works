/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class Predmer {

    private int id;
    private String naziv;
    private RadniNalog radniNalogId;

    public Predmer() {
    }

    public Predmer(int id, String naziv, RadniNalog radniNalogId) {
        this.id = id;
        this.naziv = naziv;
        this.radniNalogId = radniNalogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public RadniNalog getRadniNalogId() {
        return radniNalogId;
    }

    public void setRadniNalogId(RadniNalog radniNalogId) {
        this.radniNalogId = radniNalogId;
    }

    @Override
    public String toString() {
        return id + naziv + radniNalogId;
    }

}
