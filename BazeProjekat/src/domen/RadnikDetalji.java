/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class RadnikDetalji {

    private int id;
    private String jmbg;
    private String zvanje;
    private String telefon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public RadnikDetalji(int id, String jmbg, String zvanje, String telefon) {
        this.id = id;
        this.jmbg = jmbg;
        this.zvanje = zvanje;
        this.telefon = telefon;
    }

    public RadnikDetalji() {
    }

}
