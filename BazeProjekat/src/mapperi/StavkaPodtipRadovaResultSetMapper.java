/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.PodtipRadova;
import domen.Predmer;
import domen.StavkaPodtipaRadova;
import domen.TipRadova;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UkupnaCena;

/**
 *
 * @author Korisnik
 */
public class StavkaPodtipRadovaResultSetMapper {

    public static StavkaPodtipaRadova mapResultSetToStavkapodtipRadova(ResultSet rs) throws SQLException {
        int stavkaid = rs.getInt("stavkaID");
        String nazivTipa = rs.getString("nazivtiparada");
        String opis = rs.getString("opisRadova");

        int kolicina = rs.getInt("kolicina");
        int cena = rs.getInt("jedinicnacena");
        String mera = rs.getString("NAZIVJEDINICEMERE");

        PodtipRadova tip = new PodtipRadova();
        int podtipid = rs.getInt("podtipradovaid");
        TipRadova tip1 = new TipRadova();
        int tipid = rs.getInt("tipradovaid");
        tip1.setId(tipid);
        tip.setTipradovaId(tip1);
        tip.setPodtipRadovaId(podtipid);

        StavkaPodtipaRadova stavkapodtip = new StavkaPodtipaRadova(tip, stavkaid, nazivTipa, opis, kolicina, cena, mera);
        return stavkapodtip;
    }
}
