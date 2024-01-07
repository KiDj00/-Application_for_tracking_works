/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import static model.Pib.PIB_TIP;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

/**
 *
 * @author Korisnik
 */
public class UkupnaCena implements ORAData, ORADataFactory {

    public static final String CENA_TIP = "C##ADMIN.UKUPNA_CENA_TIP";
    private BigDecimal vrednost;
    private BigDecimal pdv;
    private BigDecimal ukupna_cena;

    @Override
    public Datum toDatum(Connection connection) throws SQLException {
        StructDescriptor sd
                = StructDescriptor.createDescriptor(CENA_TIP, connection);
        Object[] attributes = new Object[]{vrednost, pdv, ukupna_cena};
        return new STRUCT(sd, connection, attributes);
    }

    @Override
    public ORAData create(Datum d, int i) throws SQLException {
        UkupnaCena cena = new UkupnaCena();
        Struct struct = (Struct) d;
        Object[] attributes = struct.getAttributes();
        cena.vrednost = (BigDecimal) attributes[0];
        cena.pdv = (BigDecimal) attributes[1];
        cena.ukupna_cena = (BigDecimal) attributes[2];
        return cena;
    }

    public UkupnaCena(BigDecimal vrednost, BigDecimal pdv, BigDecimal ukupna_cena) {
        this.vrednost = vrednost;
        this.pdv = pdv;
        this.ukupna_cena = ukupna_cena;
    }

    public UkupnaCena() {
    }

    public BigDecimal getVrednost() {
        return vrednost;
    }

    public void setVrednost(BigDecimal vrednost) {
        this.vrednost = vrednost;
    }

    public BigDecimal getPdv() {
        return pdv;
    }

    public void setPdv(BigDecimal pdv) {
        this.pdv = pdv;
    }

    public BigDecimal getUkupna_cena() {
        return ukupna_cena;
    }

    public void setUkupna_cena(BigDecimal ukupna_cena) {
        this.ukupna_cena = ukupna_cena;
    }

    @Override
    public String toString() {
return "Cena bez pdv-a:"+vrednost+" Pdv: "+ pdv+" Ukupna cena: "+ukupna_cena; 
        }

}
