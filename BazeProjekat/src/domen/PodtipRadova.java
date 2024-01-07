/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class PodtipRadova {
    private TipRadova tipradovaId;
    private int podtipRadovaId;
    private String naziv;
    private int ukupnaCena;

    public PodtipRadova() {
    }

    public PodtipRadova(TipRadova tipradovaId, int podtipRadovaId, String naziv, int ukupnaCena) {
        this.tipradovaId = tipradovaId;
        this.podtipRadovaId = podtipRadovaId;
        this.naziv = naziv;
        this.ukupnaCena = ukupnaCena;
    }

    public TipRadova getTipradovaId() {
        return tipradovaId;
    }

    public void setTipradovaId(TipRadova tipradovaId) {
        this.tipradovaId = tipradovaId;
    }

    public int getPodtipRadovaId() {
        return podtipRadovaId;
    }

    public void setPodtipRadovaId(int podtipRadovaId) {
        this.podtipRadovaId = podtipRadovaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
    
    
}
