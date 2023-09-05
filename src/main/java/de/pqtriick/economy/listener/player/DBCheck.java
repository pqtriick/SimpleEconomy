package de.pqtriick.economy.listener.player;

import de.pqtriick.economy.listener.atm.ATMInteraction;
import de.pqtriick.economy.mysql.EconomySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author pqtriick_
 * @created 01:14, 05.09.2023
 */

public class DBCheck implements Listener {

    private EconomySQL sql;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        sql = new EconomySQL();
        Player p = event.getPlayer();
        if (!sql.userExits(p.getUniqueId())) {
            sql.createUser(p.getUniqueId());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (ATMInteraction.chatinput.get(event.getPlayer()) != null) {
            ATMInteraction.chatinput.remove(event.getPlayer());
        }
    }
}
