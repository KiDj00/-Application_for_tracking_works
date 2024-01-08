/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.Kompanija;
import java.sql.ResultSet;
import java.sql.SQLException;
import tipovi.Pib;

/**
 *
 * @author Korisnik
 */
public class KompanijaResultSetMapper {

    public static Kompanija mapResultSetToKompanija(ResultSet rs) throws SQLException {
        int id = rs.getInt("kompanijaID");
        Pib pib = (Pib) rs.getObject("pib");

        String naziv = rs.getString("nazivKompanije");
        String grad = rs.getString("Grad");

        Kompanija k = new Kompanija(id, pib, naziv, grad);
        return k;
    }
}
