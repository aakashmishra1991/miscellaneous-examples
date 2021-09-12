package com.sample;

import bitronix.tm.resource.jdbc.PoolingDataSource;

public class ConnectToMsSqlDataSource {

  public static void main(String[] args) {
    PoolingDataSource ds = new PoolingDataSource();
    ds.setUniqueName("jdbc/jbpm-ds");
    ds.setClassName("com.microsoft.sqlserver.jdbc.SQLServerXADataSource");
    ds.setMaxPoolSize(3);
    ds.setAllowLocalTransactions(true);
    ds.getDriverProperties().put("user", "sa");
    ds.getDriverProperties().put("password", "msSQL123");
    ds.getDriverProperties().put("URL", "jdbc:jtds:sqlserver://master:1433/master");
   // ds.getDriverProperties().put("driverClassName", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
    ds.init();

  }
//
//  public static DataSource getDataSource() {
//    SQLServerDataSource ds = new SQLServerDataSource();
//    ds.setUser("sa");
//    ds.setPassword("msSQL123");
//    ds.setServerName("localhost");
//    ds.setPortNumber(1433);
//    ds.setDatabaseName("master");
//    return ds;
//  }

}
