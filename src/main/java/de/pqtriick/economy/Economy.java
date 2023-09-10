package de.pqtriick.economy;

import de.pqtriick.economy.api.EconomyAPI;
import de.pqtriick.economy.api.IEconomyAPI;
import de.pqtriick.economy.commands.HelpCommand;
import de.pqtriick.economy.commands.SimpleEconomy;
import de.pqtriick.economy.files.Config;
import de.pqtriick.economy.files.ConfigStorage;
import de.pqtriick.economy.files.ConfigValues;
import de.pqtriick.economy.listener.atm.ATMChatInput;
import de.pqtriick.economy.listener.atm.ATMInteraction;
import de.pqtriick.economy.listener.player.DBCheck;
import de.pqtriick.economy.mysql.EconomySQL;
import de.pqtriick.economy.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static de.pqtriick.economy.files.ConfigStorage.dbConfig;
import static de.pqtriick.economy.files.ConfigStorage.msgConfig;
import static de.pqtriick.economy.mysql.EconomySQL.createTable;
import static de.pqtriick.economy.mysql.EconomySQL.loadDatabank;

public final class Economy extends JavaPlugin {

    public static Economy instance;
    public static MySQL mySQL;
    private IEconomyAPI api;

    @Override

    public void onEnable() {

        instance = this;
        fileinit();
        initmaindb();
        api = new EconomySQL();
        EconomyAPI.setApi(api);

        this.getCommand("secohelp").setExecutor(new HelpCommand());
        this.getCommand("seco").setExecutor(new SimpleEconomy());
        Bukkit.getPluginManager().registerEvents(new DBCheck(), this);
        Bukkit.getPluginManager().registerEvents(new ATMInteraction(), this);
        Bukkit.getPluginManager().registerEvents(new ATMChatInput(), this);


    }

    @Override
    public void onDisable() {
        mySQL.close();
    }

    public static Economy getInstance() {
        return instance;
    }

    private void fileinit() {
        Config.createDir();
        Config.createFile(ConfigStorage.db);
        Config.setDefaults(dbConfig, ConfigStorage.db, "mysql.enabled", "new");
        ConfigValues.initdb();
        Config.createFile(ConfigStorage.msg);
        Config.setDefaults(msgConfig, ConfigStorage.msg, "messages.init", "new");
        ConfigValues.initMsg();
    }

    private void initmaindb() {
        loadDatabank();
        createTable();

    }
}
