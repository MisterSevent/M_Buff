package com.buff.events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static com.buff.Buff.*;

public class InventoryClickEvent implements Listener {

    List<PotionEffect> effects1 = new ArrayList<PotionEffect>();


    @EventHandler
    public void aoClicar(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getInventory().getName().equals(getInstance().getConfig().getString("Inventory.name").replace('&', '§'))) e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack i = e.getCurrentItem();
        for (String key : getInstance().getConfig().getConfigurationSection("Inventory.buffs").getKeys(false)) {
            if (i.hasItemMeta() && i.getItemMeta().hasDisplayName() && i.getItemMeta().getDisplayName().equals(getInstance().getConfig().getString("Inventory.buffs."+key+".Name").replace('&', '§'))) {
                if (p.hasPermission(getInstance().getConfig().getString("Inventory.buffs." + key + ".permission"))) {
                    if (getInstance().getEconomy().hasAccount(p.getName())) {
                        if (getInstance().getEconomy().has(p.getName(), getInstance().getConfig().getInt("Inventory.buffs." + key + ".Price"))) {
                            getInstance().getEconomy().withdrawPlayer(p.getName(), getInstance().getConfig().getInt("Inventory.buffs." + key + ".Price"));
                            for (String effects : getInstance().getConfig().getConfigurationSection("Inventory.buffs." + key + ".Effects").getKeys(false)) {
                                //Crei uma lista de Poções e adicionei todas as poções da config.yml nessa lista e setei no jogador!
                                effects1.add(new PotionEffect(PotionEffectType.getByName(getInstance().getConfig().getString("Inventory.buffs." + key + ".Effects." + effects + ".Efeito"))
                                        , 20 * getInstance().getConfig().getInt("Inventory.buffs." + key + ".Effects." + effects + ".Duracao")
                                        , getInstance().getConfig().getInt("Inventory.buffs." + key + ".Effects." + effects + ".Nivel") - 1));
                            }
                            p.addPotionEffects(effects1);
                            p.sendMessage(getInstance().getConfig().getString("Inventory.buffs." + key + ".sucess").replace('&', '§'));
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 1F, 1F);
                            p.closeInventory();
                            return;
                        } else {
                            p.closeInventory();
                            p.sendMessage(getInstance().getConfig().getString("not-enough-money").replace('&', '§'));
                            p.playSound(p.getLocation(), Sound.BAT_DEATH, 1F, 1F);
                            return;
                        }

                    } else {
                        p.closeInventory();
                        p.sendMessage(getInstance().getConfig().getString("account-absent").replace('&', '§'));
                        return;
                    }

                } else {
                    p.closeInventory();
                    p.sendMessage(getInstance().getConfig().getString("no-permission").replace('&', '§'));
                    return;
                }
            }
        }

    }
}
