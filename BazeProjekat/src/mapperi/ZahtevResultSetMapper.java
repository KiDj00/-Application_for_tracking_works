/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.Mesto;
import domen.Nacelnik;
import domen.Zahtev;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public class ZahtevResultSetMapper {

    public static Zahtev mapResultSetToZahtev(ResultSet rs) throws SQLException {
        int brojZahteva = rs.getInt("brojZahteva");
        String naziv = rs.getString("nazivZahteva");

        Nacelnik nacelnik = new Nacelnik();
        int nacelnikId = rs.getInt("nacelnikId");
        String nacelnikIme = rs.getString("ime");
        nacelnik.setId(nacelnikId);
        nacelnik.setIme(nacelnikIme);

        Mesto mesto = new Mesto();
        int mestoPostanskiBroj = rs.getInt("postanskiBroj");
        String mestoNaziv = rs.getString("nazivMesta");
        mesto.setPostanskiBroj(mestoPostanskiBroj);
        mesto.setNaziv(mestoNaziv);
        
        Zahtev zahtev = new Zahtev();
        zahtev.setBrojZahteva(brojZahteva);
        zahtev.setNaziv(naziv);
        zahtev.setNacelnikId(nacelnik);
        zahtev.setPostanskiBroj(mesto);
        
        return zahtev;
    }
}
