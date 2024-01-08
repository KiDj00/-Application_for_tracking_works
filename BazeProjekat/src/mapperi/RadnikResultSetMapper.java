/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.Radnik;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class RadnikResultSetMapper {

    public static Radnik mapResultSetToRadnik(ResultSet rs) throws SQLException {
        int radniid = rs.getInt("radnikid");
        String ime = rs.getString("ime");
        String prezime = rs.getString("prezime");
        String jmbg = rs.getString("jmbg");
        String zvanje = rs.getString("zvanje");
        String telefon = rs.getString("telefon");

        Radnik radnik = new Radnik(radniid, ime, prezime, jmbg, zvanje, telefon);
        return radnik;
    }
}
