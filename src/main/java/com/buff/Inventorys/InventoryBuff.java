package com.buff.Inventorys;

import com.buff.APIs.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import static com.buff.Buff.*;

public class InventoryBuff {

    private Inventory inventory;

    public InventoryBuff() {
        inventory = Bukkit.createInventory(null, 9 * getInstance().getConfig().getInt("Inventory.size"), getInstance().getConfig().getString("Inventory.name").replace('&', '§'));
        insertItems();
    }
        private void insertItems () {
            for (String key : getInstance().getConfig().getConfigurationSection("Inventory.buffs").getKeys(false)) {
                ItemStack i = new ItemBuilder(Material.getMaterial(getInstance().getConfig().getString("Inventory.buffs." + key + ".Material")),
                        1,
                        (short) getInstance().getConfig().getInt("Inventory.buffs." + key + ".Data-value")).
                        setDisplayName(getInstance().getConfig().getString("Inventory.buffs."+key+".Name").replace('&', '§'))
                        .setLore(getInstance().getConfig().getStringList("Inventory.buffs."+key+".Lore")).build();
                inventory.setItem(getInstance().getConfig().getInt("Inventory.buffs." + key + ".Slot"), i);
            }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
