package de.pqtriick.economy.listener.atm;

import de.pqtriick.economy.files.UserData;
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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static de.pqtriick.economy.files.ConfigStorage.*;
import static de.pqtriick.economy.util.Skull.CustomSkull.getCustomSkull;

/**
 * @author pqtriick_
 * @created 01:22, 05.09.2023
 */

public class ATMInteraction implements Listener {

    Inventory ATMStart;
    public static HashMap<Player, String> chatinput = new HashMap<>();
    private EconomySQL sql;
    private static String UIDEPOSIT = msgConfig.getString("messages.atmuideposit");
    private static String UIDEPOSITDESC = msgConfig.getString("messages.atmuidepositdesc");
    private static String UIBALANCE = msgConfig.getString("messages.atmuibalance");
    private static String UIBALANCEDESC = msgConfig.getString("messages.atmuibalancedesc");
    private static String UIWITHDRAW = msgConfig.getString("messages.atmuiwithdraw");
    private static String UIWITHDRAWDESC = msgConfig.getString("messages.atmuiwithdrawdesc");

    private static String ATMPREFIX = msgConfig.getString("messages.atmprefix");
    private static String ATMINPUT = msgConfig.getString("messages.atminput");
    private static String ATMCANCEL = msgConfig.getString("messages.atmcancel");
    private static String CURRENTBALANCE = msgConfig.getString("messages.currentbalance");


    @EventHandler
    public void onATMInteract(PlayerInteractEvent event) {
        UIDEPOSIT = UIDEPOSIT.replace("&", "§");
        UIDEPOSITDESC = UIDEPOSITDESC.replace("&", "§");
        UIBALANCE = UIBALANCE.replace("&", "§");
        UIBALANCEDESC = UIBALANCEDESC.replace("&", "§");
        UIWITHDRAW = UIWITHDRAW.replace("&", "§");
        UIWITHDRAWDESC = UIWITHDRAWDESC.replace("&", "§");

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
                    ATMStart.setItem(12, getCustomSkull(Skulls.ATM_INPUT.getTexture(), UIDEPOSIT, UIDEPOSITDESC));
                    ATMStart.setItem(13, getCustomSkull(Skulls.ATM_INFO.getTexture(), UIBALANCE, UIBALANCEDESC));
                    ATMStart.setItem(14, getCustomSkull(Skulls.ATM_OUTPUT.getTexture(), UIWITHDRAW, UIWITHDRAWDESC));
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
        ATMPREFIX = ATMPREFIX.replace("&", "§");
        ATMINPUT = ATMINPUT.replace("&", "§");
        ATMCANCEL = ATMCANCEL.replace("&", "§");
        sql = new EconomySQL();
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase("§6§lATM §7» §eChoose an action")) {
            if (event.getSlot() != 12 || event.getSlot() != 13 || event.getSlot() != 14) {
                event.setCancelled(true);
                if (event.getSlot() == 12) {
                    chatinput.remove(player);
                    chatinput.put(player, "INPUT");
                    player.closeInventory();
                    player.sendMessage(ATMPREFIX + " " + ATMINPUT);
                    player.sendMessage(ATMPREFIX + " " + ATMCANCEL);
                }
                if (event.getSlot() == 13) {
                    player.closeInventory();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    CURRENTBALANCE = CURRENTBALANCE.replace("&", "§");
                    if (dbConfig.getString("mysql.enabled").equalsIgnoreCase("TRUE")) {
                        CURRENTBALANCE = CURRENTBALANCE.replace("%bank_money%", Integer.toString(sql.getBankmoney(player.getUniqueId())));
                    } else {
                        CURRENTBALANCE = CURRENTBALANCE.replace("%bank_money%", Integer.toString(UserData.getBankmoney(player)));
                    }
                    player.sendMessage(ATMPREFIX + " " + CURRENTBALANCE);
                    player.sendMessage("§7[§cATM§7] §7" + dtf.format(LocalDateTime.now()));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_CARTOGRAPHER, 1, 1);
                }
            }
            if (event.getSlot() == 14) {
                chatinput.remove(player);
                chatinput.put(player, "WITHDRAW");
                player.closeInventory();
                player.sendMessage(ATMPREFIX + " " + ATMINPUT);
                player.sendMessage(ATMPREFIX + " " + ATMCANCEL);
            }
        }
    }

}



