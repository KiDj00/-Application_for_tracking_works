/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class PredmetPoziva {
    private PozivZaDostavljanjePonude pozivId;
    private int predmetId;
    private String napomena;
    private String opis;
    private String kriterijum;
    private LocalDate rokZaDostavu;

    public PredmetPoziva() {
    }

    public PredmetPoziva(PozivZaDostavljanjePonude pozivId, int predmetId, String napomena, String opis, String kriterijum, LocalDate rokZaDostavu) {
        this.pozivId = pozivId;
        this.predmetId = predmetId;
        this.napomena = napomena;
        this.opis = opis;
        this.kriterijum = kriterijum;
        this.rokZaDostavu = rokZaDostavu;
    }

    public PozivZaDostavljanjePonude getPozivId() {
        return pozivId;
    }

    public void setPozivId(PozivZaDostavljanjePonude pozivId) {
        this.pozivId = pozivId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getKriterijum() {
        return kriterijum;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    public LocalDate getRokZaDostavu() {
        return rokZaDostavu;
    }

    public void setRokZaDostavu(LocalDate rokZaDostavu) {
        this.rokZaDostavu = rokZaDostavu;
    }

    
    
            
}
