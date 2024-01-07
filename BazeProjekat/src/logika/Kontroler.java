/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import dbb.DBBroker;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Kontroler {

    public static Kontroler instanca;
    DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public List<Zapisnik> getAllZapisnici() {
        List<Zapisnik> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getAllZapisnici();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Predmer> getAllPredmer() {
        List<Predmer> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getAllPredmer();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean insertRadnik(Radnik radnik) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertRadnik(radnik);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public ArrayList<TipRadova> vratiTipoveRadovaPoPredmeru(Predmer p) {
        ArrayList<TipRadova> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = (ArrayList<TipRadova>) db.vratiTipoveRadovaPoPredmeru(p);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return lista;
    }

    public ArrayList<PodtipRadova> vratiPodtipoveRadovaPoTipu(TipRadova p) {
        ArrayList<PodtipRadova> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.vratiPodtipoveRadovaPoTipu(p);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return lista;
    }

    public ArrayList<StavkaPodtipaRadova> vratiStavkePodtipoveRadovaPoPodtipu(PodtipRadova p) {
        ArrayList<StavkaPodtipaRadova> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.vratiStavkePodtipoveRadovaPoPodtipu(p);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return lista;
    }

    public ArrayList<RadnikOsnovno> getAllRadniciOsnovno() {
        ArrayList<RadnikOsnovno> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getAllRadniciOsnovno();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<RadnikDetalji> getAllRadniciDetalji() {
        ArrayList<RadnikDetalji> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getAllRadniciDetalji();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void deleteRadnik(int id) {
        try {
            db.otvoriKonekciju();
            db.deleteRadnik(id);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Radnik vratiRadnikPoID(int id) {
        Radnik r = new Radnik();
        try {
            db.otvoriKonekciju();
            r = db.vratiRadnikPoID(id);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return r;
    }

    public boolean updateRadnik(Radnik radnik) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateRadnik(radnik);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;

    }

    public ArrayList<StavkaZapisnika> vratiStavkeZapisnikaPoZapisniku(Zapisnik z) {
        ArrayList<StavkaZapisnika> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.vratiStavkeZapisnikaPoZapisniku(z);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return lista;
    }

    public boolean updateStavkaZapsinika(StavkaZapisnika sz) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateStavkaZapsinika(sz);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public ArrayList<Kompanija> getAllKompanija() {
        ArrayList<Kompanija> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getAllKompanija();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean updateKompanija(Kompanija k) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateKompanija(k);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean insetKompanija(Kompanija k) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insetKompanija(k);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            // Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public Zapisnik vratiZapisnikPoStavki(StavkaZapisnika sz) {
        Zapisnik z = new Zapisnik();
        try {
            db.otvoriKonekciju();
            z = db.vratiZapisnikPoStavki(sz);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return z;
    }

    public void deleteKompanija(int id) {
        try {
            db.otvoriKonekciju();
            db.deleteKompanija(id);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStavkaZapisnika(int id) {
        try {
            db.otvoriKonekciju();
            db.deleteStavkaZapisnika(id);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertStavkaZapsinika(StavkaZapisnika sz) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertStavkaZapsinika(sz);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            // Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updateTipRadova(TipRadova tr) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateTipRadova(tr);
            db.commit();

            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean updatePodtipRadova(PodtipRadova pod) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updatePodtipRadova(pod);
            db.commit();

            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean updatePodtipRadovaCENA(PodtipRadova pod) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updatePodtipRadovaCENA(pod);
            db.commit();

            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean insertPodtipRadova(PodtipRadova pr) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertPodtipRadova(pr);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            // Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public void deletePodtipRadova(int id) {
        try {
            db.otvoriKonekciju();
            db.deletePodtipRadova(id);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStavkaPodtipaRadova(int id) {
        try {
            db.otvoriKonekciju();
            db.deleteStavkaPodtipaRadova(id);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertStavkaPodtipaRadova(StavkaPodtipaRadova spr) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertStavkaPodtipaRadova(spr);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            // Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public ArrayList<String> getAllJediniceMere() {
        ArrayList<String> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getAllJediniceMere();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean updateStavkaPodtipaRadova(StavkaPodtipaRadova spr) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateStavkaPodtipaRadova(spr);
            db.commit();

            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean updateStavkaPodtipaRadovaNAZIV(StavkaPodtipaRadova spr) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateStavkaPodtipaRadovaNAZIV(spr);
            db.commit();

            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public boolean dodajJedinicuMere(String txt) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.dodajJedinicuMere(txt);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            // Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public ArrayList<Narudzbenica> getAllNarudzbenica() {
        ArrayList<Narudzbenica> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getAllNarudzbenica();
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void deleteNarudzbecnica(int id) {
        try {
            db.otvoriKonekciju();
            db.deleteNarudzbecnica(id);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertNarudzbenica(Narudzbenica n) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.insertNarudzbenica(n);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            // Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return uspesno;
    }

    public boolean updateNarudzbenica(Narudzbenica n) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateNarudzbenica(n);
            db.commit();

            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

    public ArrayList<Narudzbenica> getNarudzbenicePoGodini(String uslov) {
        ArrayList<Narudzbenica> lista = new ArrayList<>();
        try {
            db.otvoriKonekciju();
            lista = db.getNarudzbenicePoGodini(uslov);
            db.zatvoriKonekciju();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean updateNazivStavkaZapsinika(StavkaZapisnika sz) {
        boolean uspesno = false;
        try {
            db.otvoriKonekciju();
            uspesno = db.updateNazivStavkaZapsinika(sz);
            db.commit();
            db.zatvoriKonekciju();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uspesno;
    }

}
