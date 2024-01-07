/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.time.LocalDate;

/**
 *
 * @author Korisnik
 */
public class RadniNalog {
    private int id;
    private LocalDate datum;
    private Nacelnik nacelnikID;
    private Mesto postanskiBroj;
    private Zahtev brojZahteva; 

    public RadniNalog() {
    }

    public RadniNalog(int id, LocalDate datum, Nacelnik nacelnikID, Mesto postanskiBroj, Zahtev brojZahteva) {
        this.id = id;
        this.datum = datum;
        this.nacelnikID = nacelnikID;
        this.postanskiBroj = postanskiBroj;
        this.brojZahteva = brojZahteva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Nacelnik getNacelnikID() {
        return nacelnikID;
    }

    public void setNacelnikID(Nacelnik nacelnikID) {
        this.nacelnikID = nacelnikID;
    }

    public Mesto getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Mesto postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public Zahtev getBrojZahteva() {
        return brojZahteva;
    }

    public void setBrojZahteva(Zahtev brojZahteva) {
        this.brojZahteva = brojZahteva;
    }

    
}
