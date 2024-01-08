/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.Kompanija;
import domen.Mesto;
import domen.Nacelnik;
import domen.Narudzbenica;
import domen.Zapisnik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import tipovi.UkupnaCena;

/**
 *
 * @author Korisnik
 */
public class NarudzbenicaResultSetMapper {
    
    public static Narudzbenica mapResultSetToNarudzbenica(ResultSet rs) throws SQLException {
        int id = rs.getInt("narudzbenicaID");
        UkupnaCena cena = (UkupnaCena) rs.getObject("cena");
        LocalDate rokizvrsenja = rs.getDate("rokizvrsenja").toLocalDate();
        LocalDate garantni = rs.getDate("garantnirok").toLocalDate();
        
        String nacinPlacanja = rs.getString("nacinplacanja");
        LocalDate Datum = rs.getDate("Datum").toLocalDate();
        
        Mesto mesto = new Mesto();
        int mestoPostanskiBroj = rs.getInt("postanskiBroj");
        //  String mestoNaziv = rs.getString("nazivMesta");
        mesto.setPostanskiBroj(mestoPostanskiBroj);
        //  mesto.setNaziv(mestoNaziv);

        Zapisnik z = new Zapisnik();
        int zapisniId = rs.getInt("zapisnikid");
        z.setId(zapisniId);
        
        Nacelnik n = new Nacelnik();
        int nid = rs.getInt("nacelnikid");
        n.setId(nid);
        
        Kompanija k = new Kompanija();
        int kid = rs.getInt("kompanijaid");
        k.setId(kid);
//   String kid1 = rs.getString("nazivkompanije");
        //   k.setNaziv(kid1);
        
        Narudzbenica narudzbenica = new Narudzbenica(id, cena, rokizvrsenja, garantni, nacinPlacanja, Datum, mesto, z, n, k);
        
        return narudzbenica;
    }
}
