/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import model.Pib;

/**
 *
 * @author Korisnik
 */
public class Kompanija {

    private int id;
    private Pib pib;
    private String naziv;
    private String grad;

    public Kompanija() {
    }

    public Kompanija(int id, Pib pib, String naziv, String grad) {
        this.id = id;
        this.pib = pib;
        this.naziv = naziv;
        this.grad = grad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pib getPib() {
        return pib;
    }

    public void setPib(Pib pib) {
        this.pib = pib;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
