/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import domen.Nacelnik;

/**
 *
 * @author Korisnik
 */
public class Sektor {
    private int id;
    private String naziv;
    private Nacelnik nacelnikId;

    public Sektor() {
    }

    public Sektor(int id, String naziv, Nacelnik nacelnikId) {
        this.id = id;
        this.naziv = naziv;
        this.nacelnikId = nacelnikId;
    }
    
}
