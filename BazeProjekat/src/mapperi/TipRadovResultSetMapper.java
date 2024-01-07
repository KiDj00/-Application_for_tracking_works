/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.Mesto;
import domen.Predmer;
import domen.TipRadova;
import domen.Zahtev;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class TipRadovResultSetMapper {

    public static TipRadova mapResultSetToTipRadova(ResultSet rs) throws SQLException {
        int tipradovaID = rs.getInt("tipradovaid");
        String naziv = rs.getString("nazivtiparada");

        Predmer predmer = new Predmer();
        int predmerID = rs.getInt("predmerid");
        predmer.setId(predmerID);

        TipRadova tip = new TipRadova(tipradovaID, naziv, predmer);
        return tip;
    }
}
