package de.pqtriick.economy.listener.atm;

import de.pqtriick.economy.mysql.EconomySQL;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author pqtriick_
 * @created 01:53, 05.09.2023
 */

public class ATMChatInput implements Listener {

    private EconomySQL sql;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        sql = new EconomySQL();
        Player player = event.getPlayer();
        if (ATMInteraction.chatinput.get(player).equals("INPUT")) {
            event.setCancelled(true);
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                ATMInteraction.chatinput.remove(player);
                player.sendMessage("§7[§cATM§7] §cSucessfully cancelled input.");
            } else {
                try {
                    int amount = Integer.parseInt(message);
                    if (sql.getLocalmoney(player.getUniqueId()) >= amount) {
                        sql.removeLocalmoney(player.getUniqueId(), amount);
                        sql.addBankmoney(player.getUniqueId(), amount);
                        player.sendMessage("§7[§cATM§7] §a§lDEPOSIT §8| §2+ " + amount + "§2$");
                        ATMInteraction.chatinput.remove(player);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                    } else {
                        player.sendMessage("§7[§cATM§7] §cYou don't have so much money!");
                    }
                } catch (Exception e) {
                    player.sendMessage("§7[§cATM§7] §cWrite an actual number!");
                }
            }
        } else if (ATMInteraction.chatinput.get(player).equals("WITHDRAW")) {
            event.setCancelled(true);
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                ATMInteraction.chatinput.remove(player);
                player.sendMessage("§7[§cATM§7] §cSucessfully cancelled input.");
            } else {
                try {
                    int amount = Integer.parseInt(message);
                    if (sql.getBankmoney(player.getUniqueId()) >= amount) {
                        sql.removeBankmoney(player.getUniqueId(), amount);
                        sql.addLocalmoney(player.getUniqueId(), amount);
                        player.sendMessage("§7[§cATM§7] §c§lWITHDRAW §8| §2+ " + amount + "§2$");
                        ATMInteraction.chatinput.remove(player);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                    } else {
                        player.sendMessage("§7[§cATM§7] §cYou don't have so much money!");
                    }
                } catch (Exception e) {
                    player.sendMessage("§7[§cATM§7] §cWrite an actual number!");
                }
            }

        }
    }
}
