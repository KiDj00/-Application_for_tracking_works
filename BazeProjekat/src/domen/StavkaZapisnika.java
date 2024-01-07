/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.time.LocalDate;
import java.util.Date;
import model.UkupnaCena;

/**
 *
 * @author Korisnik
 */
public class StavkaZapisnika {
    private Zapisnik zapisnikId;
    private int stavkaId;
    private Kompanija kompanija;
    private UkupnaCena vrednost;
    private LocalDate rokZaObavljanje;
    private int opcijaPonude;
    private LocalDate garantniRok;

    public StavkaZapisnika() {
    }

    public StavkaZapisnika(Zapisnik zapisnikId, int stavkaId, Kompanija kompanija, UkupnaCena vrednost, LocalDate rokZaObavljanje, int opcijaPonude, LocalDate garantniRok) {
        this.zapisnikId = zapisnikId;
        this.stavkaId = stavkaId;
        this.kompanija = kompanija;
        this.vrednost = vrednost;
        this.rokZaObavljanje = rokZaObavljanje;
        this.opcijaPonude = opcijaPonude;
        this.garantniRok = garantniRok;
    }

    public Zapisnik getZapisnikId() {
        return zapisnikId;
    }

    public void setZapisnikId(Zapisnik zapisnikId) {
        this.zapisnikId = zapisnikId;
    }

    public int getStavkaId() {
        return stavkaId;
    }

    public void setStavkaId(int stavkaId) {
        this.stavkaId = stavkaId;
    }

    public Kompanija getKompanija() {
        return kompanija;
    }

    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    public UkupnaCena getVrednost() {
        return vrednost;
    }

    public void setVrednost(UkupnaCena vrednost) {
        this.vrednost = vrednost;
    }

    public LocalDate getRokZaObavljanje() {
        return rokZaObavljanje;
    }

    public void setRokZaObavljanje(LocalDate rokZaObavljanje) {
        this.rokZaObavljanje = rokZaObavljanje;
    }

    public int getOpcijaPonude() {
        return opcijaPonude;
    }

    public void setOpcijaPonude(int opcijaPonude) {
        this.opcijaPonude = opcijaPonude;
    }

    public LocalDate getGarantniRok() {
        return garantniRok;
    }

    public void setGarantniRok(LocalDate garantniRok) {
        this.garantniRok = garantniRok;
    }

    

}
