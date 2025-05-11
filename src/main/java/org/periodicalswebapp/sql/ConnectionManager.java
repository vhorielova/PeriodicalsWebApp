package org.periodicalswebapp.sql;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class ConnectionManager {
    private static DataSource dataSource;

    public ConnectionManager() {
    }

    static {
        final String url = "jdbc:postgresql://localhost:5432/periodicalswebapp";
        final PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(url);
        ds.setUser("postgres");
        ds.setPassword("");
        dataSource = ds;
    }


    public static DataSource getDataSource() {
        return dataSource;
    }
}
