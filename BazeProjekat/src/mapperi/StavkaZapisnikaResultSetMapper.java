/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapperi;

import domen.Kompanija;
import domen.Mesto;
import domen.PozivZaDostavljanjePonude;
import domen.StavkaZapisnika;
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
public class StavkaZapisnikaResultSetMapper {

    public static StavkaZapisnika mapResultSetToStavkaZapisnika(ResultSet rs) throws SQLException {
        int stavkaid = rs.getInt("stavkaID");
        UkupnaCena vrednost = (UkupnaCena) rs.getObject("VREDNOST");
        LocalDate rokzaObavaljanje = rs.getDate("rokzaobavljanje").toLocalDate();
        int opcija = rs.getInt("opcijaPonude");
        LocalDate garantniRok = rs.getDate("garantniRok").toLocalDate();

        Kompanija kom = new Kompanija();
        int id = rs.getInt("kompanijaID");
        String nazivkom = rs.getString("nazivKompanije");
        kom.setId(id);
        kom.setNaziv(nazivkom);

        Zapisnik z = new Zapisnik();
        int zapid = rs.getInt("zapisnikid");
        z.setId(zapid);

        StavkaZapisnika stavka = new StavkaZapisnika();
        stavka.setZapisnikId(z);
        stavka.setStavkaId(stavkaid);
        stavka.setKompanija(kom);
        stavka.setVrednost(vrednost);
        stavka.setOpcijaPonude(opcija);
        stavka.setGarantniRok(garantniRok);
        stavka.setRokZaObavljanje(rokzaObavaljanje);

        return stavka;
    }
}
