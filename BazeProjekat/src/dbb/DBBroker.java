/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbb;

import domen.Kompanija;
import domen.Narudzbenica;
import domen.PodtipRadova;
import domen.Predmer;
import domen.Radnik;
import domen.RadnikDetalji;
import domen.RadnikOsnovno;
import domen.StavkaPodtipaRadova;
import domen.StavkaZapisnika;
import domen.TipRadova;
import domen.Zapisnik;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapperi.KompanijaResultSetMapper;
import mapperi.NarudzbenicaResultSetMapper;
import mapperi.PodtipRadovaResultSetMapper;
import mapperi.PredmerResultSetMapper;
import mapperi.RadnikDetaljiResultSetMapper;
import mapperi.RadnikOsnovnoResultSetMapper;
import mapperi.RadnikResultSetMapper;
import mapperi.StavkaPodtipRadovaResultSetMapper;
import mapperi.StavkaZapisnikaResultSetMapper;
import mapperi.TipRadovResultSetMapper;
import mapperi.ZapisnikResultSetMapper;
import tipovi.Pib;
import tipovi.UkupnaCena;

/**
 *
 * @author Korisnik
 */
public class DBBroker {

    private Connection konekcija;

    public DBBroker() {
    }

    public void otvoriKonekciju() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        konekcija = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##admin", "admin");
        konekcija.setAutoCommit(false);
        registerMapTypes(konekcija);

    }

    public void zatvoriKonekciju() throws SQLException {
        konekcija.close();
    }

    public void commit() throws SQLException {
        konekcija.commit();
    }

    public void rollback() throws SQLException {
        konekcija.rollback();
    }

    private void registerMapTypes(Connection con) throws SQLException, ClassNotFoundException {
        Map<String, Class<?>> mapTypes = con.getTypeMap();
        mapTypes.put(Pib.PIB_TIP, Pib.class);
        mapTypes.put(UkupnaCena.CENA_TIP, UkupnaCena.class);
    }

    public List<Zapisnik> getAllZapisnici() throws SQLException {
        List<Zapisnik> zapisnici = new ArrayList<>();
        String upit = "SELECT z.zapisnikID,z.vrednost as vrednost,z.datum,p.pozivID,"
                + "m.nazivMesta,t.timid FROM Zapisnik z "
                + "LEFT JOIN POZIVZADOSTAVLJANJEPOUNDE p ON p.pozivId = z.pozivId "
                + "LEFT JOIN Mesto m ON m.postanskibroj = z.postanskibroj "
                + "LEFT JOIN StrucniTim t ON t.timId = z.timId ";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Zapisnik zapisnik = ZapisnikResultSetMapper.mapResultSetToZapisnik(resultSet);
                zapisnici.add(zapisnik);
            }

            return zapisnici;
        }
    }

    public List<Predmer> getAllPredmer() throws SQLException {
        List<Predmer> predmeri = new ArrayList<>();
        String upit = "SELECT * FROM PREDMER";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Predmer predmer = PredmerResultSetMapper.mapResultSetToPredmer(resultSet);
                predmeri.add(predmer);
            }
            return predmeri;
        }
    }

    public boolean insertRadnik(Radnik radnik) {
        String sql = "insert into radnikview (radnikid,ime,prezime,jmbg,zvanje,telefon) "
                + "values (?,?,?,?,?,?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(sql)) {
            preparedStatement.setInt(1, radnik.getId());
            preparedStatement.setString(2, radnik.getIme());
            preparedStatement.setString(3, radnik.getPrezime());
            preparedStatement.setString(4, radnik.getJmbg());
            preparedStatement.setString(5, radnik.getZvanje());
            preparedStatement.setString(6, radnik.getTelefon());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public List<TipRadova> vratiTipoveRadovaPoPredmeru(Predmer p) throws SQLException {
        List<TipRadova> tipovi = new ArrayList<>();
        String upit = "SELECT * FROM Tipradova where predmerid = ?";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, p.getId());
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    TipRadova tip = TipRadovResultSetMapper.mapResultSetToTipRadova(resultSet);
                    tip.setPredmerID(p);
                    tipovi.add(tip);
                }
                return tipovi;
            }
        }

    }

    public ArrayList<PodtipRadova> vratiPodtipoveRadovaPoTipu(TipRadova p) throws SQLException {
        ArrayList<PodtipRadova> podtipovi = new ArrayList<>();
        String upit = "SELECT * FROM podtipradova where tipradovaid = ?";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, p.getId());
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PodtipRadova pdotip = PodtipRadovaResultSetMapper.mapResultSetTopodtipRadova(resultSet);
                    pdotip.setTipradovaId(p);
                    podtipovi.add(pdotip);
                }
                return podtipovi;
            }
        }
    }

    public ArrayList<StavkaPodtipaRadova> vratiStavkePodtipoveRadovaPoPodtipu(PodtipRadova p) throws SQLException {
        ArrayList<StavkaPodtipaRadova> stavke = new ArrayList<>();
        String upit = "SELECT * FROM stavkapodtiparadova where podtipradovaid = ?";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, p.getPodtipRadovaId());
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    StavkaPodtipaRadova stavka = StavkaPodtipRadovaResultSetMapper.mapResultSetToStavkapodtipRadova(resultSet);
                    stavka.setPodtipRadova(p);
                    stavke.add(stavka);
                }
                return stavke;
            }
        }

    }

    public ArrayList<RadnikOsnovno> getAllRadniciOsnovno() throws SQLException {
        ArrayList<RadnikOsnovno> radniciOsnovno = new ArrayList<>();
        String upit = "SELECT * FROM radnik";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RadnikOsnovno radnikO = RadnikOsnovnoResultSetMapper.mapResultSetToRadnikOsnovno(resultSet);
                radniciOsnovno.add(radnikO);
            }
            return radniciOsnovno;
        }
    }

    public ArrayList<RadnikDetalji> getAllRadniciDetalji() throws SQLException {
        ArrayList<RadnikDetalji> radnicidetalji = new ArrayList<>();
        String upit = "SELECT * FROM radnikdetalji";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RadnikDetalji radnikD = RadnikDetaljiResultSetMapper.mapResultSetToRadnikDetalji(resultSet);
                radnicidetalji.add(radnikD);
            }
            return radnicidetalji;
        }
    }

    public void deleteRadnik(int id) throws SQLException {
        String upit = "DELETE FROM RADNIKVIEW WHERE RADNIKID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public Radnik vratiRadnikPoID(int id) throws SQLException {
        Radnik r = new Radnik();
        String upit = "SELECT * FROM RADNIKVIEW WHERE RADNIKID = ?";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, id);
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Radnik ra = RadnikResultSetMapper.mapResultSetToRadnik(resultSet);
                    ra.setId(id);
                    r = ra;
                }
                return r;
            }
        }
    }

    public boolean updateRadnik(Radnik r) {
        String upit = "UPDATE radnikview SET ime = ?, prezime = ?, jmbg = ?, zvanje = ?, telefon = ? "
                + "WHERE radnikid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setString(1, r.getIme());
            preparedStatement.setString(2, r.getPrezime());
            preparedStatement.setString(3, r.getJmbg());
            preparedStatement.setString(4, r.getZvanje());
            preparedStatement.setString(5, r.getTelefon());
            preparedStatement.setInt(6, r.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<StavkaZapisnika> vratiStavkeZapisnikaPoZapisniku(Zapisnik z) throws SQLException {
        ArrayList<StavkaZapisnika> stavke = new ArrayList<>();
        String upit = "SELECT * FROM stavkazapisnika where zapisnikID = ?";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, z.getId());
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    StavkaZapisnika stavka = StavkaZapisnikaResultSetMapper.mapResultSetToStavkaZapisnika(resultSet);
                    stavka.setZapisnikId(z);
                    stavke.add(stavka);
                }
                return stavke;
            }
        }
    }

    public boolean updateStavkaZapsinika(StavkaZapisnika sz) {
        String upit = "UPDATE StavkaZAPISNIKA SET VREDNOST = ?, rokzaobavljanje = ?, opcijaponude = ?, garantnirok = ?, kompanijaId = ? "
                + "WHERE stavkaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setObject(1, sz.getVrednost());
            preparedStatement.setObject(2, sz.getRokZaObavljanje());
            preparedStatement.setInt(3, sz.getOpcijaPonude());
            preparedStatement.setObject(4, sz.getGarantniRok());
            preparedStatement.setInt(5, sz.getKompanija().getId());
            preparedStatement.setInt(6, sz.getStavkaId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateKompanija(Kompanija k) {
        String upit = "UPDATE Kompanija SET PIB = ?, NAZIVKOMPANIJE = ?, GRAD = ? WHERE KOMPANIJAID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setObject(1, k.getPib());
            preparedStatement.setString(2, k.getNaziv());
            preparedStatement.setString(3, k.getGrad());
            preparedStatement.setInt(4, k.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Kompanija> getAllKompanija() throws SQLException {
        ArrayList<Kompanija> kompanije = new ArrayList<>();
        String upit = "SELECT * FROM kompanija";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Kompanija kompanija = KompanijaResultSetMapper.mapResultSetToKompanija(resultSet);
                kompanije.add(kompanija);
            }
            return kompanije;
        }
    }

    public boolean insetKompanija(Kompanija k) {
        String upit = "insert into kompanija (kompanijaid,Pib,nazivkompanije,grad) "
                + "values (?,?,?,?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, k.getId());
            preparedStatement.setObject(2, k.getPib());
            preparedStatement.setString(3, k.getNaziv());
            preparedStatement.setString(4, k.getGrad());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public Zapisnik vratiZapisnikPoStavki(StavkaZapisnika sz) throws SQLException {
        Zapisnik z = new Zapisnik();
        String upit = "SELECT * FROM zapisnik WHERE zapisnikid = ?";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, sz.getZapisnikId().getId());
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Zapisnik za = ZapisnikResultSetMapper.mapResultSetToZapisnik1(resultSet);
                    za.setId(sz.getZapisnikId().getId());
                    z = za;
                }
                return z;
            }
        }
    }

    public void deleteKompanija(int id) throws SQLException {
        String upit = "DELETE FROM kompanija WHERE kompanijaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteStavkaZapisnika(int id) throws SQLException {
        String upit = "DELETE FROM stavkazapisnika WHERE stavkaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public boolean insertStavkaZapsinika(StavkaZapisnika sz) {
        String upit = "insert into stavkaZapisnika (zapisnikID,STAVKAID,VREDNOST,ROKZAOBAVLJANJE,OPCIJAPONUDE,GARANTNIROK,KOMPANIJAID) "
                + "values (?,?,?,?,?,?,?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, sz.getZapisnikId().getId());
            preparedStatement.setInt(2, sz.getStavkaId());
            preparedStatement.setObject(3, sz.getVrednost());
            preparedStatement.setObject(4, sz.getRokZaObavljanje());
            preparedStatement.setInt(5, sz.getOpcijaPonude());
            preparedStatement.setObject(6, sz.getGarantniRok());
            preparedStatement.setInt(7, sz.getKompanija().getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public boolean updateTipRadova(TipRadova tr) {
        String upit = "UPDATE TipRadova SET nazivtiparada = ?, predmerid = ? WHERE TIPRADOVAID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setString(1, tr.getNaziv());
            preparedStatement.setObject(2, tr.getPredmerID().getId());
            preparedStatement.setInt(3, tr.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updatePodtipRadova(PodtipRadova pod) {
        String upit = "UPDATE PodTipRadova SET nazivpodtiparada = ? WHERE podTIPRADOVAID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setString(1, pod.getNaziv());
            preparedStatement.setInt(2, pod.getPodtipRadovaId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updatePodtipRadovaCENA(PodtipRadova pod) {
        String upit = "UPDATE PodTipRadova SET nazivpodtiparada = ? , ukupnacena = ? WHERE podTIPRADOVAID = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setString(1, pod.getNaziv());
            preparedStatement.setInt(2, pod.getUkupnaCena());
            preparedStatement.setInt(3, pod.getPodtipRadovaId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertPodtipRadova(PodtipRadova pr) {
        String upit = "insert into podtipradova (tipradovaid,podtipradovaid,NAZIVPODTIPARADA) "
                + "values (?,?,?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, pr.getTipradovaId().getId());
            preparedStatement.setInt(2, pr.getPodtipRadovaId());
            preparedStatement.setString(3, pr.getNaziv());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public void deletePodtipRadova(int id) throws SQLException {
        String upit = "DELETE FROM podtipradova WHERE podtipradovaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteStavkaPodtipaRadova(int id) throws SQLException {
        String upit = "DELETE FROM Stavkapodtiparadova WHERE stavkaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public boolean insertStavkaPodtipaRadova(StavkaPodtipaRadova spr) {
        String upit = "insert into Stavkapodtiparadova (tipradovaid,podtipradovaid,stavkaid,opisradova,kolicina,jedinicnacena,nazivjedinicemere) "
                + "values (?,?,?,?,?,?,?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, spr.getPodtipRadova().getTipradovaId().getId());
            preparedStatement.setInt(2, spr.getPodtipRadova().getPodtipRadovaId());
            preparedStatement.setInt(3, spr.getStavkaId());
            preparedStatement.setString(4, spr.getOpisRadova());
            preparedStatement.setInt(5, spr.getKolicina());
            preparedStatement.setInt(6, spr.getJedinicnaCena());
            preparedStatement.setString(7, spr.getNazivJediniceMere());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public ArrayList<String> getAllJediniceMere() throws SQLException {
        ArrayList<String> jediniceMere = new ArrayList<>();
        String upit = "SELECT nazivjedinicemere FROM stavkapodtiparadova";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String jedinica = resultSet.getString("nazivjedinicemere");
                jediniceMere.add(jedinica);
            }
            return jediniceMere;
        }
    }

    public boolean updateStavkaPodtipaRadova(StavkaPodtipaRadova spr) {
        String upit = "UPDATE Stavkapodtiparadova SET opisradova = ? , kolicina = ? , jedinicnacena = ? , nazivjedinicemere = ? "
                + " WHERE stavkaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setString(1, spr.getOpisRadova());
            preparedStatement.setInt(2, spr.getKolicina());
            preparedStatement.setInt(3, spr.getJedinicnaCena());
            preparedStatement.setString(4, spr.getNazivJediniceMere());
            preparedStatement.setInt(5, spr.getStavkaId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateStavkaPodtipaRadovaNAZIV(StavkaPodtipaRadova spr) {
        String upit = "UPDATE Stavkapodtiparadova SET NAZIVTIPARADA = ? , opisradova = ? , kolicina = ? , jedinicnacena = ? , nazivjedinicemere = ? "
                + " WHERE stavkaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setString(1, spr.getNazivRada());
            preparedStatement.setString(2, spr.getOpisRadova());
            preparedStatement.setInt(3, spr.getKolicina());
            preparedStatement.setInt(4, spr.getJedinicnaCena());
            preparedStatement.setString(5, spr.getNazivJediniceMere());
            preparedStatement.setInt(6, spr.getStavkaId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean dodajJedinicuMere(String txt) {
        String upit = "{call DODAVANJE_NOVE_JEDINICE_MERE(?)}";

        try ( CallableStatement callableStatement = konekcija.prepareCall(upit)) {
            callableStatement.setString(1, txt);
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Narudzbenica> getAllNarudzbenica() throws SQLException {
        ArrayList<Narudzbenica> narudzbenice = new ArrayList<>();
        String upit = "SELECT * From Narudzbenica";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit);  ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Narudzbenica n = NarudzbenicaResultSetMapper.mapResultSetToNarudzbenica(resultSet);
                narudzbenice.add(n);
            }

            return narudzbenice;
        }
    }

    public void deleteNarudzbecnica(int id) throws SQLException {
        String upit = "DELETE FROM narudzbenica WHERE narudzbenicaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public boolean insertNarudzbenica(Narudzbenica n) {
        String upit = "insert into Narudzbenica (narudzbenicaid,cena,rokizvrsenja,garantnirok,nacinplacanja,datum,postanskibroj,zapisnikid,"
                + "nacelnikid,kompanijaid) "
                + "values (?,?,?,?,?,?,?,?,?,?)";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setInt(1, n.getId());
            preparedStatement.setObject(2, n.getCena());
            preparedStatement.setObject(3, n.getRokIzvrsenja());
            preparedStatement.setObject(4, n.getGarantniRok());
            preparedStatement.setString(5, n.getNacinPlacanja());
            preparedStatement.setObject(6, n.getDatum());
            preparedStatement.setInt(7, n.getPostanskiBroj().getPostanskiBroj());
            preparedStatement.setInt(8, n.getZapisnikId().getId());
            preparedStatement.setInt(9, n.getNacelnikId().getId());
            preparedStatement.setInt(10, n.getKompanijaId().getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public boolean updateNarudzbenica(Narudzbenica n) {
        String upit = "UPDATE Narudzbenica SET cena = ? , rokizvrsenja = ? , garantnirok = ? , nacinplacanja = ? , datum = ? , "
                + "postanskibroj = ? , zapisnikid = ? , nacelnikid = ? , kompanijaid = ? "
                + " WHERE narudzbenicaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setObject(1, n.getCena());
            preparedStatement.setObject(2, n.getRokIzvrsenja());
            preparedStatement.setObject(3, n.getGarantniRok());
            preparedStatement.setString(4, n.getNacinPlacanja());
            preparedStatement.setObject(5, n.getDatum());
            preparedStatement.setInt(6, n.getPostanskiBroj().getPostanskiBroj());
            preparedStatement.setInt(7, n.getZapisnikId().getId());
            preparedStatement.setInt(8, n.getNacelnikId().getId());
            preparedStatement.setInt(9, n.getKompanijaId().getId());
            preparedStatement.setInt(10, n.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<Narudzbenica> getNarudzbenicePoGodini(String uslov) throws SQLException {
        ArrayList<Narudzbenica> narudzbenice = new ArrayList<>();
        String upit = "SELECT * FROM narudzbenica PARTITION (" + uslov + ")";
        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Narudzbenica n = NarudzbenicaResultSetMapper.mapResultSetToNarudzbenica(resultSet);
                    narudzbenice.add(n);
                }
                return narudzbenice;
            }
        }
    }

    public boolean updateNazivStavkaZapsinika(StavkaZapisnika sz) {
        String upit = "UPDATE StavkaZAPISNIKA SET nazivKompanije = ? WHERE stavkaid = ?";

        try ( PreparedStatement preparedStatement = konekcija.prepareStatement(upit)) {
            preparedStatement.setString(1, sz.getKompanija().getNaziv());
            preparedStatement.setInt(2, sz.getStavkaId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }
}
