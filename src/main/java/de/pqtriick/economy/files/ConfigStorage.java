package de.pqtriick.economy.files;

import de.pqtriick.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author pqtriick_
 * @created 23:04, 04.09.2023
 */

public class ConfigStorage {
    public static final File db = new File(Economy.getInstance().getDataFolder().getPath(), "databank.yml");
    public static final FileConfiguration dbConfig = YamlConfiguration.loadConfiguration(db);
    public static final File msg = new File(Economy.getInstance().getDataFolder().getPath(), "messages.yml");
    public static final FileConfiguration msgConfig = YamlConfiguration.loadConfiguration(msg);
    public static final File userData = new File(Economy.getInstance().getDataFolder().getPath(), "userdata.yml");
    public static final FileConfiguration userdataConfig = YamlConfiguration.loadConfiguration(userData);

}
