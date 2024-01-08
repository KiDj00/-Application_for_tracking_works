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
public class PozivZaDostavljanjePonude {
    private int id;
    private LocalDate datum;
    private Nacelnik nacelnikId;
    private  Mesto postanskiBroj;
    private Predmer predmerId;

    public PozivZaDostavljanjePonude() {
    }

    public PozivZaDostavljanjePonude(int id, LocalDate datum, Nacelnik nacelnikId, Mesto postanskiBroj, Predmer predmerId) {
        this.id = id;
        this.datum = datum;
        this.nacelnikId = nacelnikId;
        this.postanskiBroj = postanskiBroj;
        this.predmerId = predmerId;
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

    public Predmer getPredmerId() {
        return predmerId;
    }

    public void setPredmerId(Predmer predmerId) {
        this.predmerId = predmerId;
    }

    
    
    
}
