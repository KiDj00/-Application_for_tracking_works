/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.RadnikDetalji;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class RadnikDetaljiResultSetMapper {

    public static RadnikDetalji mapResultSetToRadnikDetalji(ResultSet rs) throws SQLException {
        int radniid = rs.getInt("radnikid");
        String jmbg = rs.getString("jmbg");
        String zvanje = rs.getString("zvanje");
        String telefon = rs.getString("telefon");

        RadnikDetalji radnik = new RadnikDetalji(radniid, jmbg, zvanje, telefon);
        return radnik;
    }
}
