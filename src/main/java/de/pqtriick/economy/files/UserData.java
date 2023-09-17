package de.pqtriick.economy.files;

import org.bukkit.entity.Player;

import static de.pqtriick.economy.files.ConfigStorage.userData;
import static de.pqtriick.economy.files.ConfigStorage.userdataConfig;

/**
 * @author pqtriick_
 * @created 12:07, 17.09.2023
 */

public class UserData {

    public static boolean userExists(Player player) {
        if (userdataConfig.getString("user." + player.getUniqueId()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static int getBankmoney(Player player) {
        return Integer.parseInt(userdataConfig.getString("user." + player.getUniqueId() + ".bankmoney"));
    }

    public static int getLocalmoney(Player player) {
        return Integer.parseInt(userdataConfig.getString("user." + player.getUniqueId() + ".localmoney"));
    }

    public static void addBankmoney(Player player, int amount) {
        int newamount = getBankmoney(player) + amount;
        userdataConfig.set("user." + player.getUniqueId() + ".bankmoney", newamount);
    }
    public static void addLocalmoney(Player player, int amount) {
        int newamount = getLocalmoney(player) + amount;
        userdataConfig.set("user." + player.getUniqueId() + ".localmoney", newamount);
    }

    public static void removeBankmoney(Player player, int amount) {
        int newamount = getBankmoney(player) - amount;
        userdataConfig.set("user." + player.getUniqueId() + ".bankmoney", newamount);
    }
    public static void removeLocalmoney(Player player, int amount) {
        int newamount = getLocalmoney(player) - amount;
        userdataConfig.set("user." + player.getUniqueId() + ".localmoney", newamount);
    }


}
