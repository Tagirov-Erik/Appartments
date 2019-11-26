package com.gmail.eriktagirov;

import java.sql.Connection;

/**
 * Created by Bios on 29.11.2017.
 */
public class AppartmentDAOEx extends AbstractDAO<Integer, Appartments> {
    public AppartmentDAOEx(Connection conn, String table) {
        super(conn, table);
    }
}
