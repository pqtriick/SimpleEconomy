package de.pqtriick.economy.files;

import de.pqtriick.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author pqtriick_
 * @created 23:04, 04.09.2023
 */

public class Config {

    private static File directory = new File(Economy.getInstance().getDataFolder().getPath());

    public static void createDir() {
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile(FileConfiguration configuration, File file) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDefaults(FileConfiguration config, File file, String path, String value) {
        config.options().copyDefaults(true);
        config.addDefault(path, value);
        Config.saveFile(config, file);
    }




}
