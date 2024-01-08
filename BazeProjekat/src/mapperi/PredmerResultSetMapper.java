/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;


import domen.Predmer;
import domen.RadniNalog;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class PredmerResultSetMapper {

    public static Predmer mapResultSetToPredmer(ResultSet rs) throws SQLException {
        int id = rs.getInt("predmerId");
        String naziv = rs.getString("nazivPredmera");

        RadniNalog radniNalog = new RadniNalog();
        int radniNalogID = rs.getInt("radniNalogID");
        radniNalog.setId(radniNalogID);

        Predmer predmer = new Predmer();
        predmer.setId(id);
        predmer.setNaziv(naziv);
        predmer.setRadniNalogId(radniNalog);

        return predmer;
    }
}
