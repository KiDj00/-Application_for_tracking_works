/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class PredmetZahteva {
    private Zahtev brojZahteva;
    private int predmetId;
    private String naziv;
    private String opis;

    public PredmetZahteva() {
    }

    public PredmetZahteva(Zahtev brojZahteva, int predmetId, String naziv, String opis) {
        this.brojZahteva = brojZahteva;
        this.predmetId = predmetId;
        this.naziv = naziv;
        this.opis = opis;
    }

    public Zahtev getBrojZahteva() {
        return brojZahteva;
    }

    public void setBrojZahteva(Zahtev brojZahteva) {
        this.brojZahteva = brojZahteva;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}
