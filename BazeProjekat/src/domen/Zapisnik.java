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
public class Zapisnik {
    private int id;
    private UkupnaCena vrednost;
    private LocalDate datum;
    private PozivZaDostavljanjePonude pozivId;
    private Mesto postanskiBroj;
    private StrucniTim timId;

    public Zapisnik() {
    }

    public Zapisnik(int id, UkupnaCena vrednost, LocalDate datum, PozivZaDostavljanjePonude pozivId, Mesto postanskiBroj, StrucniTim timId) {
        this.id = id;
        this.vrednost = vrednost;
        this.datum = datum;
        this.pozivId = pozivId;
        this.postanskiBroj = postanskiBroj;
        this.timId = timId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UkupnaCena getVrednost() {
        return vrednost;
    }

    public void setVrednost(UkupnaCena vrednost) {
        this.vrednost = vrednost;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public PozivZaDostavljanjePonude getPozivId() {
        return pozivId;
    }

    public void setPozivId(PozivZaDostavljanjePonude pozivId) {
        this.pozivId = pozivId;
    }

    public Mesto getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Mesto postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public StrucniTim getTimId() {
        return timId;
    }

    public void setTimId(StrucniTim timId) {
        this.timId = timId;
    }

    
    
}
