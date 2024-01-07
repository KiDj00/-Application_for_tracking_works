/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public class PredmetNaloga {
    private RadniNalog radniNalogId;
    private int predmetId;
    private String opisPredmeta;
    private String nazivPredmeta;

    public PredmetNaloga() {
    }

    public PredmetNaloga(RadniNalog radniNalogId, int predmetId, String opisPredmeta, String nazivPredmeta) {
        this.radniNalogId = radniNalogId;
        this.predmetId = predmetId;
        this.opisPredmeta = opisPredmeta;
        this.nazivPredmeta = nazivPredmeta;
    }

    public RadniNalog getRadniNalogId() {
        return radniNalogId;
    }

    public void setRadniNalogId(RadniNalog radniNalogId) {
        this.radniNalogId = radniNalogId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public String getOpisPredmeta() {
        return opisPredmeta;
    }

    public void setOpisPredmeta(String opisPredmeta) {
        this.opisPredmeta = opisPredmeta;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }
    
    
}
