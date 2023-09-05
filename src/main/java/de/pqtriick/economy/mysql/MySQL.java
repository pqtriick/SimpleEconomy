package de.pqtriick.economy.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author pqtriick_
 * @created 23:35, 04.09.2023
 */

public class MySQL {

    private Connection con;

    private String HOST = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";
    private String AUTOCONNECT = "";

    public MySQL(String host, String database, String user, String password, String autoconnect) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        this.AUTOCONNECT = autoconnect;
        connect();

    }


    public void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=" + AUTOCONNECT, USER, PASSWORD);
                System.out.println("[MySQL] Verbindung hergestellt");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[MySQL] Bei der Verbindung ist ein Fehler aufgetreten!");
            }
        }

    }

    public void close() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println("[MySQL] Verbindung zu Datenbank geschlossen.");
            } catch (SQLException e) {
                System.out.println("[MySQL] Konnte Verbindung zur Datenbank nicht beenden.");
            }
        }

    }

    public void update(String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getResult(String qry) {
        if (isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public boolean isConnected() {
        return con != null;
    }
}
