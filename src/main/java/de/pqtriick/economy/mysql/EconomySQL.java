package de.pqtriick.economy.mysql;



import de.pqtriick.economy.Economy;
import de.pqtriick.economy.api.IEconomyAPI;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static de.pqtriick.economy.files.ConfigStorage.dbConfig;

/**
 * @author pqtriick_
 * @created 00:09, 05.09.2023
 */

public class EconomySQL implements IEconomyAPI {

    public static void loadDatabank() {
        Economy.mySQL = new MySQL(dbConfig.getString("mysql.host"), dbConfig.getString("mysql.databank"), dbConfig.getString("mysql.user"), dbConfig.getString("mysql.pass"), dbConfig.getString("mysql.autoreconnect"));
        Economy.mySQL.connect();
    }

    public static void createTable() {
        Economy.mySQL.update("CREATE TABLE IF NOT EXISTS simpleeconomy (uuid VARCHAR(36), bankmoney INT(16), localmoney INT(16))");
    }

    public boolean userExits(UUID uuid) {
        try {
            ResultSet rs = Economy.mySQL.getResult("SELECT uuid FROM simpleeconomy WHERE uuid='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("uuid") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createUser(UUID uuid) {
        Economy.mySQL.update("INSERT INTO simpleeconomy(uuid, bankmoney, localmoney) VALUES ('" + uuid + "', '0', '0')");
    }

    @Override
    public Integer getBankmoney(UUID uuid) {
        try {
            ResultSet rs = Economy.mySQL.getResult("SELECT bankmoney FROM simpleeconomy WHERE uuid='" + uuid + "'");
            if (rs.next()) {
                return rs.getInt("bankmoney");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer getLocalmoney(UUID uuid) {
        try {
            ResultSet rs = Economy.mySQL.getResult("SELECT localmoney FROM simpleeconomy WHERE uuid='" + uuid + "'");
            if (rs.next()) {
                return rs.getInt("localmoney");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void addBankmoney(UUID uuid, int amount) {
        int updated = getBankmoney(uuid) + amount;
        Economy.mySQL.update("UPDATE simpleeconomy SET bankmoney='" + updated + "' WHERE uuid='" + uuid + "'");



    }

    @Override
    public void addLocalmoney(UUID uuid, int amount) {
        int updated = getLocalmoney(uuid) + amount;
        Economy.mySQL.update("UPDATE simpleeconomy SET localmoney='" + updated + "' WHERE uuid='" + uuid + "'");

    }

    @Override
    public void removeBankmoney(UUID uuid, int amount) {
        int updated = getBankmoney(uuid) - amount;
        Economy.mySQL.update("UPDATE simpleeconomy SET bankmoney='" + updated + "' WHERE uuid='" + uuid + "'");

    }

    @Override
    public void removeLocalmoney(UUID uuid, int amount) {
        int updated = getLocalmoney(uuid) - amount;
        Economy.mySQL.update("UPDATE simpleeconomy SET localmoney='" + updated + "' WHERE uuid='" + uuid + "'");


    }
}
