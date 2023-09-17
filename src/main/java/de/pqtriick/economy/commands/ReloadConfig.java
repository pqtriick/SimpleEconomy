package de.pqtriick.economy.commands;

import de.pqtriick.economy.files.Config;
import de.pqtriick.economy.files.ConfigStorage;
import de.pqtriick.economy.mysql.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author pqtriick_
 * @created 18:20, 10.09.2023
 */

public class ReloadConfig implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("seco.reloadconfig")) {
            try {
                Config.saveFile(ConfigStorage.dbConfig, ConfigStorage.db);
                Config.saveFile(ConfigStorage.msgConfig, ConfigStorage.msg);
                Config.saveFile(ConfigStorage.userdataConfig, ConfigStorage.userData);
                p.sendMessage("§aSucessfully reloaded §bdatabank.yml & messages.yml");
            } catch (Exception e) {
                p.sendMessage("§cAn error occured, view the console for more information.");
                System.out.println(e);
            }
        }
        return false;
    }
}
