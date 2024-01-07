/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class TipRadova {
    private int id;
    private String naziv;
    private Predmer predmerID;

    public TipRadova() {
    }

    public TipRadova(int id, String naziv, Predmer predmerID) {
        this.id = id;
        this.naziv = naziv;
        this.predmerID = predmerID;
    }

    public Predmer getPredmerID() {
        return predmerID;
    }

    public void setPredmerID(Predmer predmerID) {
        this.predmerID = predmerID;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
