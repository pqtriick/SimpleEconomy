package de.pqtriick.economy.listener.atm;

import de.pqtriick.economy.mysql.EconomySQL;
import de.pqtriick.economy.util.ItemBuilder;
import de.pqtriick.economy.util.Skull.Skulls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static de.pqtriick.economy.util.Skull.CustomSkull.getCustomSkull;

/**
 * @author pqtriick_
 * @created 01:22, 05.09.2023
 */

public class ATMInteraction implements Listener {

    Inventory ATMStart;
    public static HashMap<Player, String> chatinput = new HashMap<>();
    private EconomySQL sql;

    @EventHandler
    public void onATMInteract(PlayerInteractEvent event) {
        Block clicked = event.getClickedBlock();
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (clicked.getType() == Material.OAK_SIGN || clicked.getType() == Material.OAK_WALL_SIGN) {
                Sign sign = (Sign) clicked.getState();
                if (ChatColor.stripColor(sign.getLine(1)).equalsIgnoreCase("[ATM]")) {
                    ATMStart = Bukkit.createInventory(null, 9 * 3, "§6§lATM §7» §eChoose an action");
                    for (int i = 0; i <= 11; i++) {
                        ATMStart.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("").build());
                    }
                    ATMStart.setItem(12, getCustomSkull(Skulls.ATM_INPUT.getTexture(), "§a§lDEPOSIT", "§eDeposit your local money"));
                    ATMStart.setItem(13, getCustomSkull(Skulls.ATM_INFO.getTexture(), "§3§lBALANCE", "§eCheck your bank balance"));
                    ATMStart.setItem(14, getCustomSkull(Skulls.ATM_OUTPUT.getTexture(), "§c§lWITHDRAW", "§eWithdraw money from your bank account"));
                    for (int i = 15; i <= 26; i++) {
                        ATMStart.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("").build());
                    }
                    p.openInventory(ATMStart);

                }

            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        sql = new EconomySQL();
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§6§lATM §7» §eChoose an action")) {
            if (event.getSlot() != 12 || event.getSlot() != 13 || event.getSlot() != 14) {
                event.setCancelled(true);
                if (event.getSlot() == 12) {
                    chatinput.remove(player);
                    chatinput.put(player, "INPUT");
                    player.closeInventory();
                    player.sendMessage("§7[§cATM§7] §eType in the chat how much money you want to deposit §7(Without $)");
                    player.sendMessage("§7[§cATM§7] §eTo cancel this process simply type §c'cancel' §eto cancel your action");
                }
                if (event.getSlot() == 13) {
                    player.closeInventory();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    player.sendMessage("§7[§cATM§7] §eYour current Balance: §2" + sql.getBankmoney(player.getUniqueId()) + "§2$");
                    player.sendMessage("§7[§cATM§7] §7" + dtf.format(LocalDateTime.now()));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_CARTOGRAPHER, 1, 1);
                }
            }
            if (event.getSlot() == 14) {
                chatinput.remove(player);
                chatinput.put(player, "WITHDRAW");
                player.closeInventory();
                player.sendMessage("§7[§cATM§7] §eType in the chat how much money you want to withdraw §7(Without $)");
                player.sendMessage("§7[§cATM§7] §eTo cancel this process simply type §c'cancel' §eto cancel your action");
            }
        }
    }
}



