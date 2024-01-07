/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class StavkaPodtipaRadova {
    private PodtipRadova podtipRadova;
    private int stavkaId;
    private String nazivRada;
    private String opisRadova;
    private int kolicina;
    private int jedinicnaCena;
    private String nazivJediniceMere;

    public StavkaPodtipaRadova() {
    }

    public StavkaPodtipaRadova(PodtipRadova podtipRadova, int stavkaId, String nazivRada, String opisRadova, int kolicina, int jedinicnaCena, String nazivJediniceMere) {
        this.podtipRadova = podtipRadova;
        this.stavkaId = stavkaId;
        this.nazivRada = nazivRada;
        this.opisRadova = opisRadova;
        this.kolicina = kolicina;
        this.jedinicnaCena = jedinicnaCena;
        this.nazivJediniceMere = nazivJediniceMere;
    }

    public PodtipRadova getPodtipRadova() {
        return podtipRadova;
    }

    public void setPodtipRadova(PodtipRadova podtipRadova) {
        this.podtipRadova = podtipRadova;
    }

    public int getStavkaId() {
        return stavkaId;
    }

    public void setStavkaId(int stavkaId) {
        this.stavkaId = stavkaId;
    }

    public String getNazivRada() {
        return nazivRada;
    }

    public void setNazivRada(String nazivRada) {
        this.nazivRada = nazivRada;
    }

    public String getOpisRadova() {
        return opisRadova;
    }

    public void setOpisRadova(String opisRadova) {
        this.opisRadova = opisRadova;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getJedinicnaCena() {
        return jedinicnaCena;
    }

    public void setJedinicnaCena(int jedinicnaCena) {
        this.jedinicnaCena = jedinicnaCena;
    }

    public String getNazivJediniceMere() {
        return nazivJediniceMere;
    }

    public void setNazivJediniceMere(String nazivJediniceMere) {
        this.nazivJediniceMere = nazivJediniceMere;
    }

   
    
    
}
