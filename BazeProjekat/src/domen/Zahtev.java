/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class Zahtev {
    private int brojZahteva;
    private String naziv;
    private Nacelnik nacelnikId;
    private Mesto postanskiBroj;

    public Zahtev() {
    }

    public Zahtev(int brojZahteva, String naziv, Nacelnik nacelnikId, Mesto postanskiBroj) {
        this.brojZahteva = brojZahteva;
        this.naziv = naziv;
        this.nacelnikId = nacelnikId;
        this.postanskiBroj = postanskiBroj;
    }

    public int getBrojZahteva() {
        return brojZahteva;
    }

    public void setBrojZahteva(int brojZahteva) {
        this.brojZahteva = brojZahteva;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Nacelnik getNacelnikId() {
        return nacelnikId;
    }

    public void setNacelnikId(Nacelnik nacelnikId) {
        this.nacelnikId = nacelnikId;
    }

    public Mesto getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Mesto postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }
    
}
