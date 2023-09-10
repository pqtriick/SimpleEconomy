package de.pqtriick.economy.files;

import static de.pqtriick.economy.files.ConfigStorage.*;

/**
 * @author pqtriick_
 * @created 23:10, 04.09.2023
 */

public class ConfigValues {

    public static void initdb() {
        if (dbConfig.getString("mysql.enabled").equals("new")) {
            dbConfig.set("mysql.host", "HOST");
            dbConfig.set("mysql.user", "USER");
            dbConfig.set("mysql.pass", "PASS");
            dbConfig.set("mysql.databank", "DATABASE");
            dbConfig.set("mysql.autoreconnect", "FALSE");
            dbConfig.set("mysql.enabled", null);
            Config.saveFile(dbConfig, db);
        }

    }
    public static void initMsg() {
        if (msgConfig.getString("messages.init").equals("new")) {
            msgConfig.set("messages.init", null);
            msgConfig.set("messages.atmprefix", "&7[&cATM&7]");
            msgConfig.set("messages.nouser", "&7» &cUser could not be found.");
            msgConfig.set("messages.noperm","&7» &cYou don't have permission to use this command!");
            msgConfig.set("messages.wronginput", "&7» &cYou need to insert a real number!");
            msgConfig.set("messages.paytouser", "&7» &aSucessfully paid &2%paid_money%&2$ &ato &e%target% !");
            msgConfig.set("messages.moneyrecieved", "&7» &aYou recieved &2%paid_money%&2$ &afrom &e%player% !");
            msgConfig.set("messages.nomoney", "&7» &c You don't have so much money!");
            msgConfig.set("messages.paidtoself", "&7» &cYou can't pay to yourself!");
            msgConfig.set("messages.local_balance", "&7» &eYour current local balance: &2%local_money% &2$");
            msgConfig.set("messages.usage", "&7» &eUse &6/secohelp &efor more information");
            msgConfig.set("messages.playerinfotitle", "&7&m-----&7[&6&l %target%&6&ls &6Balance&7]&7&m-----");
            msgConfig.set("messages.playerinfolocal", "&7» &Local balance: &2 %local_money% &2$");
            msgConfig.set("messages.playerinfobank", "&7» &Local balance: &2 %bank_money% &2$");
            msgConfig.set("messages.adminaddlocal", "&7» &aSucessfully added &2%amount%&2$ &ato &e%target%s &alocal balance!");
            msgConfig.set("messages.adminaddbank", "&7» &aSucessfully added &2%amount%&2$ &ato &e%target%s &abank balance!");
            msgConfig.set("messages.adminremovelocal", "&7» &aSucessfully removed &2%amount%&2$ &afrom &e%target%s &alocal balance!");
            msgConfig.set("messages.adminremovebank", "&7» &aSucessfully removed &2%amount%&2$ &afrom &e%target%s &abank balance!");

            msgConfig.set("messages.atmcancel", "&7» §cSucessfully cancalled input.");
            msgConfig.set("messages.atmdeposit", "&a&lDEPOSIT &8| &2%amount%$");
            msgConfig.set("messages.atmwithdraw", "&c&lWITHDRAW &8| &2%amount%$");
            msgConfig.set("messages.atmuideposit", "&a&lDEPOSIT");
            msgConfig.set("messages.atmuidepositdesc", "&eDeposit your local money");
            msgConfig.set("messages.atmuiwithdraw", "&c&lWITHDRAW");
            msgConfig.set("messages.atmuiwithdrawdesc", "&eWithdraw money from your bank account");
            msgConfig.set("messages.atmuibalance", "&3&lBALANCE");
            msgConfig.set("messages.atmuibalancedesc", "&eCheck you bank balance");
            msgConfig.set("messages.atmcancel", "&eTo cancel this process simple type &c'cancel' &eto cancel your action");
            msgConfig.set("messages.atminput", "&eType in the chat how much money you want to deposit/withdraw &7(Without $)");
            msgConfig.set("messages.currentbalance", "&eYour current balance: &2%bank_money%&2$");
            Config.saveFile(msgConfig, msg);
        }
    }

}
