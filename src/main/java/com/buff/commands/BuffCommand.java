package com.buff.commands;

import com.buff.Inventorys.InventoryBuff;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.buff.Buff.*;

public class BuffCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(getInstance().getConfig().getString("console-execute").replace('&', '§'));
            return true;
        }

        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("buff")) {
            if (args.length == 0) {
                p.openInventory(new InventoryBuff().getInventory());
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission(getInstance().getConfig().getString("permission-admin"))) {
                        getInstance().reloadConfig();
                        p.sendMessage(getInstance().getConfig().getString("sucess-reload").replace('&', '§'));
                        return true;
                    }else {
                        p.sendMessage(getInstance().getConfig().getString("no-permission").replace('&', '§'));
                    }
                }
            }

        }
        return false;
    }
}
