package com.buff.APIs;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ItemSpecification<T extends ItemBuilder> {

    public T setDisplayName(String name);

    public T setLore(List<String> lore);

    public T setLore(String... lore);

    public T unbreakable();

    public T addEnchantment(Enchantment enchantment, int strength);

    public T hideFlag(ItemFlag itemFlag);

    public T hideAllFlags();

    public ItemStack build();
}
