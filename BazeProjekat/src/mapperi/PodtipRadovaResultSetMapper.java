/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.PodtipRadova;
import domen.TipRadova;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class PodtipRadovaResultSetMapper {

    public static PodtipRadova mapResultSetTopodtipRadova(ResultSet rs) throws SQLException {
        int podtipradovaId = rs.getInt("podtipradovaid");
        String naziv = rs.getString("nazivpodtiparada");
        int cena = rs.getInt("ukupnacena");
        TipRadova tip = new TipRadova();
        int tipid = rs.getInt("tipradovaid");
        tip.setId(tipid);

        PodtipRadova podtip = new PodtipRadova(tip, podtipradovaId, naziv, cena);
        return podtip;
    }
}
