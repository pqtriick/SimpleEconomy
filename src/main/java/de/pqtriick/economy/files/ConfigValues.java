package de.pqtriick.economy.files;

import static de.pqtriick.economy.files.ConfigStorage.*;

/**
 * @author pqtriick_
 * @created 23:10, 04.09.2023
 */

public class ConfigValues {

    public static void initdb() {
        if (dbConfig.getString("mysql.enabled").equals("new")) {
            dbConfig.set("mysql.host", "HOST");
            dbConfig.set("mysql.user", "USER");
            dbConfig.set("mysql.pass", "PASS");
            dbConfig.set("mysql.databank", "DATABASE");
            dbConfig.set("mysql.autoreconnect", "FALSE");
            Config.saveFile(dbConfig, db);
        }

    }

}
