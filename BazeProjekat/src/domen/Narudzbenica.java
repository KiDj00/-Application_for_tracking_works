
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.time.LocalDate;
import tipovi.UkupnaCena;

/**
 *
 * @author Korisnik
 */
public class Narudzbenica {
    private int id;
    private UkupnaCena cena;
    private LocalDate rokIzvrsenja;
    private LocalDate garantniRok;
    private String nacinPlacanja;
    private LocalDate datum;
    private Mesto postanskiBroj;
    private Zapisnik zapisnikId;
    private Nacelnik nacelnikId;
    private Kompanija kompanijaId;

    public Narudzbenica() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UkupnaCena getCena() {
        return cena;
    }

    public void setCena(UkupnaCena cena) {
        this.cena = cena;
    }

    public LocalDate getRokIzvrsenja() {
        return rokIzvrsenja;
    }

    public void setRokIzvrsenja(LocalDate rokIzvrsenja) {
        this.rokIzvrsenja = rokIzvrsenja;
    }

    public LocalDate getGarantniRok() {
        return garantniRok;
    }

    public void setGarantniRok(LocalDate garantniRok) {
        this.garantniRok = garantniRok;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Mesto getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Mesto postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public Zapisnik getZapisnikId() {
        return zapisnikId;
    }

    public void setZapisnikId(Zapisnik zapisnikId) {
        this.zapisnikId = zapisnikId;
    }

    public Nacelnik getNacelnikId() {
        return nacelnikId;
    }

    public void setNacelnikId(Nacelnik nacelnikId) {
        this.nacelnikId = nacelnikId;
    }

    public Kompanija getKompanijaId() {
        return kompanijaId;
    }

    public void setKompanijaId(Kompanija kompanijaId) {
        this.kompanijaId = kompanijaId;
    }

    public Narudzbenica(int id, UkupnaCena cena, LocalDate rokIzvrsenja, LocalDate garantniRok, String nacinPlacanja, LocalDate datum, Mesto postanskiBroj, Zapisnik zapisnikId, Nacelnik nacelnikId, Kompanija kompanijaId) {
        this.id = id;
        this.cena = cena;
        this.rokIzvrsenja = rokIzvrsenja;
        this.garantniRok = garantniRok;
        this.nacinPlacanja = nacinPlacanja;
        this.datum = datum;
        this.postanskiBroj = postanskiBroj;
        this.zapisnikId = zapisnikId;
        this.nacelnikId = nacelnikId;
        this.kompanijaId = kompanijaId;
    }

    
}
