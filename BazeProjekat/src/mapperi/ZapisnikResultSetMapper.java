/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.Mesto;
import domen.PozivZaDostavljanjePonude;
import domen.StrucniTim;
import domen.Zapisnik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.UkupnaCena;

/**
 *
 * @author Korisnik
 */
public class ZapisnikResultSetMapper {

    public static Zapisnik mapResultSetToZapisnik(ResultSet rs) throws SQLException {
        int zapisnikId = rs.getInt("zapisnikId");
        UkupnaCena vrednost = (UkupnaCena) rs.getObject("VREDNOST");
        LocalDate datum = rs.getDate("datum").toLocalDate();
        PozivZaDostavljanjePonude poziv = new PozivZaDostavljanjePonude();
        int pozivId = rs.getInt("pozivId");
        poziv.setId(pozivId);        
        
        Mesto mesto = new Mesto();
//        int mestoPostanskiBroj = rs.getInt("postanskiBroj");
        String mestoNaziv = rs.getString("nazivMesta");
   //     mesto.setPostanskiBroj(mestoPostanskiBroj);
        mesto.setNaziv(mestoNaziv);

        StrucniTim tim = new StrucniTim();
        int timId = rs.getInt("timId");
        tim.setId(timId);

        Zapisnik zapisnik = new Zapisnik();
        zapisnik.setId(zapisnikId);
        zapisnik.setVrednost(vrednost);
        zapisnik.setDatum(datum);
        zapisnik.setPozivId(poziv);
        zapisnik.setPostanskiBroj(mesto);
        zapisnik.setTimId(tim);
        return zapisnik;
    }
    
    public static Zapisnik mapResultSetToZapisnik1(ResultSet rs) throws SQLException {
        int zapisnikId = rs.getInt("zapisnikId");
        UkupnaCena vrednost = (UkupnaCena) rs.getObject("VREDNOST");
        LocalDate datum = rs.getDate("datum").toLocalDate();
        PozivZaDostavljanjePonude poziv = new PozivZaDostavljanjePonude();
        int pozivId = rs.getInt("pozivId");
        poziv.setId(pozivId);        
        
        Mesto mesto = new Mesto();
      int mestoPostanskiBroj = rs.getInt("postanskiBroj");
       // String mestoNaziv = rs.getString("nazivMesta");
        mesto.setPostanskiBroj(mestoPostanskiBroj);
       // mesto.setNaziv(mestoNaziv);

        StrucniTim tim = new StrucniTim();
        int timId = rs.getInt("timId");
        tim.setId(timId);

        Zapisnik zapisnik = new Zapisnik();
        zapisnik.setId(zapisnikId);
        zapisnik.setVrednost(vrednost);
        zapisnik.setDatum(datum);
        zapisnik.setPozivId(poziv);
        zapisnik.setPostanskiBroj(mesto);
        zapisnik.setTimId(tim);
        return zapisnik;
    }
}
