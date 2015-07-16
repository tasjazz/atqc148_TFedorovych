package com.softserve.edu.oms.data;

import com.softserve.edu.atqc.tools.DataSource;

public final class DataSourceRepository {

    private DataSourceRepository() {
    }

    public static DataSource getJtdsMsSqlCredentials() {
        return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
                "jdbc:jtds:sqlserver://HP/_oms;instance=SQLEXPRESS;",
                "java", "java");
        		
//        		"jdbc:jtds:sqlserver://Admin-PC/136oms;instance=SQLEXPRESS;",
//              "db", "db");
    }

    public static DataSource getJtdsMsSqlLocalHostOms() {
        return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
                "jdbc:jtds:sqlserver://HP/_oms;instance=SQLEXPRESS;",
                "java", "java");
    }
    // TODO Read from properties

}
