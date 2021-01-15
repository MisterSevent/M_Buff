package com.buff;

import com.buff.commands.BuffCommand;
import com.buff.events.InventoryClickEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Buff extends JavaPlugin {

    private static Buff instance;

    private Economy economy;

    @Override
    public void onEnable() {
        instance = this;
        loadConfigurantion();
        loadCommands();
        loadEvents();
        if (!setupEconomy()) {
            Bukkit.getConsoleSender().sendMessage("§cNão foi possivel achar a dependencia VAULT!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {

    }

    public void loadEvents() {
        Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);

    }

    public void loadCommands() {
        getCommand("buff").setExecutor(new BuffCommand());

    }

    public void loadConfigurantion() {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    public static Buff getInstance() {
        return instance;
    }

    public Economy getEconomy() {
        return economy;
    }

    //Verifica se o servidor tem Vault
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

}
