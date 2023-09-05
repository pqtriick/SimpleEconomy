package de.pqtriick.economy.commands;

import de.pqtriick.economy.mysql.EconomySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author pqtriick_
 * @created 00:48, 05.09.2023
 */

public class SimpleEconomy implements CommandExecutor {

    private EconomySQL sql;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sql = new EconomySQL();
        Player p = (Player) sender;
        if (args.length==0) {
            p.sendMessage("§7» §eYour current local balance: §2" + sql.getLocalmoney(p.getUniqueId()) + "§2$");
        }
        else if (args.length==1) {
            p.sendMessage("§7» §eUse §6/secohelp §efor more Information.");
        } else if (args.length==2) {
            if (args[0].equalsIgnoreCase("info")) {
                if (p.hasPermission("seconomy.info")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (!sql.userExits(target.getUniqueId())) {
                        p.sendMessage("§7§m-----§7[§6§l" + target.getName() + "§6§ls §6Balance§7]§7§m-----");
                        p.sendMessage("");
                        p.sendMessage("§7» §6Bank balance: §2" + sql.getBankmoney(target.getUniqueId()) + "§2$");
                        p.sendMessage("§7» §6Local balance: §2" + sql.getLocalmoney(target.getUniqueId()) + "§2$");
                    } else {
                        p.sendMessage("§7» §cUser could not be found in databank.");
                    }
                } else {
                    p.sendMessage("§7» §cYou don't have permission to use this command!");
                }
            }
        } else if (args.length==3) {
            if (args[0].equalsIgnoreCase("pay")) {
                Player target = Bukkit.getPlayer(args[1]);
                int amount = 0;
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (Exception x) {
                    p.sendMessage("§7» §cYou need to insert a real number!");
                }
                if (sql.userExits(target.getUniqueId())) {
                    if (p != target) {
                        if (sql.getLocalmoney(p.getUniqueId()) >= amount) {
                            sql.removeLocalmoney(p.getUniqueId(), amount);
                            sql.addLocalmoney(target.getUniqueId(), amount);
                            p.sendMessage("§7» §aSucessfully paid §2" + amount + "§2$ §ato §e" + target.getName() + "!");
                            target.sendMessage("§7» §aYou recieved §2" + amount + "§2$ §afrom §e" + p.getName() + "!");
                        } else {
                            p.sendMessage("§7» §cYou don't have so much money!");
                        }

                    } else {
                        p.sendMessage("§7» §cYou can't pay to yourself!");
                    }
                } else {
                    p.sendMessage("§7» §cCould not find user!");
                }
            }

        } else if (args.length==4) {
            if (args[0].equalsIgnoreCase("add")) {
                if (p.hasPermission("seconomy.add")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(args[3]);
                    } catch (Exception x) {
                        p.sendMessage("§7» §cYou need to insert a real number!");
                    }
                    if (sql.userExits(target.getUniqueId())) {
                        if (args[2].equalsIgnoreCase("Bank")) {
                            sql.addBankmoney(target.getUniqueId(), amount);
                            p.sendMessage("§7» §aSucessfully added §2" + amount + "§2$ §ato §e" + target.getName() + " §abank balance!");
                        }
                        if (args[2].equalsIgnoreCase("Local")) {
                            sql.addLocalmoney(target.getUniqueId(), amount);
                            p.sendMessage("§7» §aSucessfully added §2" + amount + "§2$ §ato §e" + target.getName() + " §alocal balance!");
                        }
                    }
                }
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (p.hasPermission("seconomy.remove")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(args[3]);
                    } catch (Exception x) {
                        p.sendMessage("§7» §cYou need to insert a real number!");
                    }
                    if (sql.userExits(target.getUniqueId())) {
                        if (args[2].equalsIgnoreCase("Bank")) {
                            sql.removeBankmoney(target.getUniqueId(), amount);
                            p.sendMessage("§7» §aSucessfully removed §2" + amount + "§2$ §afrom §e" + target.getName() + "§es §abank balance!");
                        }
                        if (args[2].equalsIgnoreCase("Local")) {
                            sql.removeLocalmoney(target.getUniqueId(), amount);
                            p.sendMessage("§7» §aSucessfully removed §2" + amount + "§2$ §afrom §e" + target.getName() + "§es §alocal balance!");
                        }
                    }
                }
            }

        }
        return false;
    }
}
