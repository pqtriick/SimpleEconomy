package de.pqtriick.economy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author pqtriick_
 * @created 00:35, 05.09.2023
 */

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("seconomy.help")) {
            player.sendMessage("§7§m----------§7[§6§lSimple Economy§7]§7§m----------");
            player.sendMessage("");
            player.sendMessage("§7» §e/seco");
            player.sendMessage("§7» §6§o(See your local money. For bank money, find an ATM)");
            player.sendMessage("§7» §e/seco add [Player] [Bank/Local] [Amount]");
            player.sendMessage("§7» §6§o(Add local or bank money to a player)");
            player.sendMessage("§7» §e/seco remove [Player] [Bank/Local] [Amount]");
            player.sendMessage("§7» §6§o(Remove local or bank money from a player)");
            player.sendMessage("§7» §e/seco info [Player]");
            player.sendMessage("§7» §6§o(See how much money the player has)");
            player.sendMessage("§7» §e/seco pay [Player] [Amount]");
            player.sendMessage("§7» §6§o(Give your local money away to another player)");
            player.sendMessage("§7» §e/secohelp");
            player.sendMessage("§7» §6§o(Open this help page)");
        }
        return false;
    }
}
