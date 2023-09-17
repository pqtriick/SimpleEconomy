package de.pqtriick.economy.listener.atm;

import de.pqtriick.economy.files.Config;
import de.pqtriick.economy.files.UserData;
import de.pqtriick.economy.mysql.EconomySQL;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static de.pqtriick.economy.files.ConfigStorage.*;

/**
 * @author pqtriick_
 * @created 01:53, 05.09.2023
 */

public class ATMChatInput implements Listener {

    private EconomySQL sql;
    private static String ATMPREFIX = msgConfig.getString("messages.atmprefix");
    private static String ATMCANCELINPUT = msgConfig.getString("messages.atmcancelinput");
    private static String DEPOSITSUCESS = msgConfig.getString("messages.atmdeposit");
    private static String NOMONEY = msgConfig.getString("messages.nomoney");
    private static String WRONGINPUT = msgConfig.getString("messages.wronginput");
    private static String WITHDRAWSUCESS = msgConfig.getString("messages.atmwithdraw");

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        ATMPREFIX = ATMPREFIX.replace("&", "§");
        NOMONEY = NOMONEY.replace("&", "§");
        WRONGINPUT = WRONGINPUT.replace("&", "§");

        sql = new EconomySQL();
        Player player = event.getPlayer();
        if (ATMInteraction.chatinput.get(player).equals("INPUT")) {
            event.setCancelled(true);
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                ATMInteraction.chatinput.remove(player);
                ATMCANCELINPUT = ATMCANCELINPUT.replace("&", "§");
                player.sendMessage(ATMPREFIX + " " + ATMCANCELINPUT);
            } else {
                try {
                    int amount = Integer.parseInt(message);
                    if (dbConfig.getString("mysql.enabled").equalsIgnoreCase("TRUE")) {
                        if (sql.getLocalmoney(player.getUniqueId()) >= amount) {
                            sql.removeLocalmoney(player.getUniqueId(), amount);
                            sql.addBankmoney(player.getUniqueId(), amount);
                            DEPOSITSUCESS = DEPOSITSUCESS.replace("&", "§");
                            DEPOSITSUCESS = DEPOSITSUCESS.replace("%amount%", Integer.toString(amount));
                            player.sendMessage(ATMPREFIX + " " + DEPOSITSUCESS);
                            ATMInteraction.chatinput.remove(player);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                        } else {
                            player.sendMessage(ATMPREFIX + " " + NOMONEY);
                        }
                    } else {
                        if (UserData.getLocalmoney(player) >= amount) {
                            UserData.removeLocalmoney(player, amount);
                            UserData.addBankmoney(player, amount);
                            Config.saveFile(userdataConfig, userData);
                            DEPOSITSUCESS = DEPOSITSUCESS.replace("&", "§");
                            DEPOSITSUCESS = DEPOSITSUCESS.replace("%amount%", Integer.toString(amount));
                            player.sendMessage(ATMPREFIX + " " + DEPOSITSUCESS);
                            ATMInteraction.chatinput.remove(player);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                        } else {
                            player.sendMessage(ATMPREFIX + " " + NOMONEY);
                        }
                    }
                } catch (Exception e) {
                    player.sendMessage(ATMPREFIX + " " + WRONGINPUT);
                }
            }

        } else if (ATMInteraction.chatinput.get(player).equals("WITHDRAW")) {
            event.setCancelled(true);
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                ATMInteraction.chatinput.remove(player);
                player.sendMessage(ATMPREFIX + " " + ATMCANCELINPUT);
            } else {
                try {
                    int amount = Integer.parseInt(message);
                    if (dbConfig.getString("mysql.enabled").equalsIgnoreCase("TRUE")) {
                        if (sql.getBankmoney(player.getUniqueId()) >= amount) {
                            sql.removeBankmoney(player.getUniqueId(), amount);
                            sql.addLocalmoney(player.getUniqueId(), amount);
                            WITHDRAWSUCESS = WITHDRAWSUCESS.replace("&", "§");
                            WITHDRAWSUCESS = WITHDRAWSUCESS.replace("%amount%", Integer.toString(amount));
                            player.sendMessage(ATMPREFIX + " " + WITHDRAWSUCESS);
                            ATMInteraction.chatinput.remove(player);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                        } else {
                            player.sendMessage(ATMPREFIX + " " + NOMONEY);
                        }
                    } else {
                        if (UserData.getBankmoney(player) >= amount) {
                            UserData.removeBankmoney(player, amount);
                            UserData.addLocalmoney(player, amount);
                            Config.saveFile(userdataConfig, userData);
                            WITHDRAWSUCESS = WITHDRAWSUCESS.replace("&", "§");
                            WITHDRAWSUCESS = WITHDRAWSUCESS.replace("%amount%", Integer.toString(amount));
                            player.sendMessage(ATMPREFIX + " " + WITHDRAWSUCESS);
                            ATMInteraction.chatinput.remove(player);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                        } else {
                            player.sendMessage(ATMPREFIX + " " + NOMONEY);
                        }
                    }
                } catch (Exception e) {
                    player.sendMessage(ATMPREFIX + " " + WRONGINPUT);
                }
            }

        }
    }
}
