/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.RadnikOsnovno;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class RadnikOsnovnoResultSetMapper {

    public static RadnikOsnovno mapResultSetToRadnikOsnovno(ResultSet rs) throws SQLException {
        int radniid = rs.getInt("radnikid");
        String ime = rs.getString("ime");
        String prezime = rs.getString("prezime");


        RadnikOsnovno radnik = new RadnikOsnovno(radniid, ime,prezime );
        return radnik;
    }
}
