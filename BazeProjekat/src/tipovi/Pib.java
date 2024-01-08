/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tipovi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

/**
 *
 * @author Korisnik
 */
public class Pib implements ORAData, ORADataFactory {

    private String vrednost;
    public static final String PIB_TIP = "C##ADMIN.PIB_TIP";

    @Override
    public Datum toDatum(Connection connection) throws SQLException {
        StructDescriptor sd
                = StructDescriptor.createDescriptor(PIB_TIP, connection);
        Object[] attributes = new Object[]{vrednost};
        return new STRUCT(sd, connection, attributes);
    }

    @Override
    public ORAData create(Datum d, int i) throws SQLException {
        Pib pib = new Pib();
        Struct struct = (Struct) d;
        Object[] attributes = struct.getAttributes();
        pib.vrednost = (String) attributes[0];
        return pib;
    }

    public Pib() {
    }

    public Pib(String vrednost) {
        this.vrednost = vrednost;
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

}
