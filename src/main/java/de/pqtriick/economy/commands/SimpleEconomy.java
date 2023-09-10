package de.pqtriick.economy.commands;

import de.pqtriick.economy.mysql.EconomySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.pqtriick.economy.files.ConfigStorage.*;

/**
 * @author pqtriick_
 * @created 00:48, 05.09.2023
 */

public class SimpleEconomy implements CommandExecutor {

    private EconomySQL sql;
    private static String LOCALBALANCE = msgConfig.getString("messages.localbalance");
    private static String USAGE = msgConfig.getString("messages.usage");
    private static String INFOTITLE = msgConfig.getString("messages.playerinfotitle");
    private static String BANKINFOBALANCE = msgConfig.getString("messages.playerinfobank");
    private static String LOCALINFOBALANCE = msgConfig.getString("messages.playerinfolocal");
    private static String NOUSER = msgConfig.getString("messages.nouser");
    private static String NOPERM = msgConfig.getString("messages.noperm");
    private static String WRONGINPUT = msgConfig.getString("messages.wronginput");
    private static String PAYTOUSER = msgConfig.getString("messages.paytouser");
    private static String MONEYRECIEVE = msgConfig.getString("messages.moneyrecieved");
    private static String NOMONEY = msgConfig.getString("messages.nomoney");
    private static String NOPAYTOSELF = msgConfig.getString("messages.paidtoself");
    private static String ADDBANKBAL = msgConfig.getString("messages.adminaddbank");
    private static String ADDLOCALBAL = msgConfig.getString("messages.adminaddlocal");
    private static String REMOVEBANKBAL = msgConfig.getString("messages.adminremovebank");
    private static String REMOVELOCALBAL = msgConfig.getString("messages.adminremovelocal");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        NOPERM = NOPERM.replace("&", "§");
        NOUSER = NOUSER.replace("&", "§");
        WRONGINPUT = WRONGINPUT.replace("&", "§");
        sql = new EconomySQL();
        Player p = (Player) sender;
        if (args.length==0) {
            LOCALBALANCE = LOCALBALANCE.replace("&", "§");
            LOCALBALANCE = LOCALBALANCE.replace("%local_money%", Integer.toString(sql.getLocalmoney(p.getUniqueId())));
            p.sendMessage(LOCALBALANCE);
        }
        else if (args.length==1) {
            USAGE = USAGE.replace("&", "§");
            p.sendMessage(USAGE);
        } else if (args.length==2) {
            if (args[0].equalsIgnoreCase("info")) {
                if (p.hasPermission("seconomy.info")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (!sql.userExits(target.getUniqueId())) {
                        INFOTITLE = INFOTITLE.replace("&", "§");
                        INFOTITLE = INFOTITLE.replace("%target%", target.getName());
                        p.sendMessage(INFOTITLE);
                        p.sendMessage("");
                        BANKINFOBALANCE = BANKINFOBALANCE.replace("&", "§");
                        BANKINFOBALANCE = BANKINFOBALANCE.replace("%bank_money%", Integer.toString(sql.getBankmoney(target.getUniqueId())));
                        p.sendMessage(BANKINFOBALANCE);
                        LOCALINFOBALANCE = LOCALINFOBALANCE.replace("&", "§");
                        LOCALINFOBALANCE = LOCALINFOBALANCE.replace("%local_money%", Integer.toString(sql.getLocalmoney(target.getUniqueId())));
                        p.sendMessage(LOCALINFOBALANCE);
                    } else {
                        p.sendMessage(NOUSER);
                    }
                } else {
                    p.sendMessage(NOPERM);
                }
            }
        } else if (args.length==3) {
            if (args[0].equalsIgnoreCase("pay")) {
                Player target = Bukkit.getPlayer(args[1]);
                int amount = 0;
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (Exception x) {
                    p.sendMessage(WRONGINPUT);
                }
                if (sql.userExits(target.getUniqueId())) {
                    if (p != target) {
                        if (sql.getLocalmoney(p.getUniqueId()) >= amount) {
                            sql.removeLocalmoney(p.getUniqueId(), amount);
                            sql.addLocalmoney(target.getUniqueId(), amount);
                            PAYTOUSER = PAYTOUSER.replace("&", "§");
                            PAYTOUSER = PAYTOUSER.replace("%paid_money%", Integer.toString(amount));
                            PAYTOUSER = PAYTOUSER.replace("%target", target.getName());
                            p.sendMessage(PAYTOUSER);
                            MONEYRECIEVE = MONEYRECIEVE.replace("&", "§");
                            MONEYRECIEVE = MONEYRECIEVE.replace("%paid_money%", Integer.toString(amount));
                            MONEYRECIEVE = MONEYRECIEVE.replace("%player%", p.getName());
                            target.sendMessage(MONEYRECIEVE);
                        } else {
                            NOMONEY = NOMONEY.replace("&", "§");
                            p.sendMessage(NOMONEY);
                        }

                    } else {
                        NOPAYTOSELF = NOPAYTOSELF.replace("&", "§");
                        p.sendMessage(NOPAYTOSELF);
                    }
                } else {
                    p.sendMessage(NOUSER);
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
                        p.sendMessage(WRONGINPUT);
                    }
                    if (sql.userExits(target.getUniqueId())) {
                        if (args[2].equalsIgnoreCase("Bank")) {
                            sql.addBankmoney(target.getUniqueId(), amount);
                            ADDBANKBAL = ADDBANKBAL.replace("&", "§");
                            ADDBANKBAL = ADDBANKBAL.replace("%target%", target.getName());
                            ADDBANKBAL = ADDBANKBAL.replace("%amount%", Integer.toString(amount));
                            p.sendMessage(ADDBANKBAL);
                        }
                        if (args[2].equalsIgnoreCase("Local")) {
                            sql.addLocalmoney(target.getUniqueId(), amount);
                            ADDLOCALBAL = ADDBANKBAL.replace("&", "§");
                            ADDLOCALBAL = ADDBANKBAL.replace("%target%", target.getName());
                            ADDLOCALBAL = ADDBANKBAL.replace("%amount%", Integer.toString(amount));
                            p.sendMessage(ADDLOCALBAL);
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
                        p.sendMessage(WRONGINPUT);
                    }
                    if (sql.userExits(target.getUniqueId())) {
                        if (args[2].equalsIgnoreCase("Bank")) {
                            sql.removeBankmoney(target.getUniqueId(), amount);
                            REMOVEBANKBAL = REMOVEBANKBAL.replace("&", "§");
                            REMOVEBANKBAL = REMOVEBANKBAL.replace("%target%", target.getName());
                            REMOVEBANKBAL = REMOVEBANKBAL.replace("%amount%", Integer.toString(amount));
                            p.sendMessage(REMOVEBANKBAL);
                        }
                        if (args[2].equalsIgnoreCase("Local")) {
                            sql.removeLocalmoney(target.getUniqueId(), amount);
                            REMOVELOCALBAL = REMOVELOCALBAL.replace("&", "§");
                            REMOVELOCALBAL = REMOVELOCALBAL.replace("%target%", target.getName());
                            REMOVELOCALBAL = REMOVELOCALBAL.replace("%amount%", Integer.toString(amount));
                            p.sendMessage(REMOVELOCALBAL);
                        }
                    }
                }
            }

        }
        return false;
    }
}
