package org.bank.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class DBManager {
    private Connection connection;

    public Connection getConnection() {
        if(Objects.isNull(connection)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(AppConstants.JDBC_URL,AppConstants.JDBC_USER_NAME,AppConstants.JDBC_PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  connection;
    }


}
