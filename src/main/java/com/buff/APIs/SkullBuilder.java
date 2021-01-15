package com.buff.APIs;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder extends ItemBuilder {

    private SkullMeta skullMeta;

    public SkullBuilder(){
        super(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        skullMeta = (SkullMeta) itemMeta;
    }

    public SkullBuilder setOwner(String owner){
        skullMeta = (SkullMeta) itemMeta;
        skullMeta.setOwner(owner);
        return this;
    }

    @Override
    public ItemStack build() {
        skullMeta = (SkullMeta) itemMeta;
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
