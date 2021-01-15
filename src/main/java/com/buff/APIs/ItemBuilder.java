package com.buff.APIs;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder implements ItemSpecification<ItemBuilder>{


    protected ItemMeta itemMeta;
    protected ItemStack itemStack;

    protected Material material;
    protected int quantity, data;

    public ItemBuilder(Material material) {
        this.material = material;
        itemStack = new ItemStack(material, 1);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int quantity) {
        this.material = material;
        this.quantity = quantity;
        itemStack = new ItemStack(material, quantity);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int quantity, int data) {
        this.material = material;
        this.quantity = quantity;
        this.data = data;
        itemStack = new ItemStack(material, quantity, (short) data);
        itemMeta = itemStack.getItemMeta();
    }

    @Override
    public ItemBuilder setDisplayName(String name) {
        itemMeta.setDisplayName(name.replace("&", "§"));
        return this;
    }

    @Override
    public ItemBuilder setLore(List<String> lore) {
        itemMeta.setLore(lore.stream().map(x -> x.replace("&", "§")).collect(Collectors.toList()));
        return this;
    }

    @Override
    public ItemBuilder setLore(String... lore) {
        List<String> list = Arrays.asList(lore);
        itemMeta.setLore(list.stream().map(x -> x.replace("&", "§")).collect(Collectors.toList()));
        return this;
    }

    @Override
    public ItemBuilder unbreakable() {
        return this;
    }

    @Override
    public ItemBuilder addEnchantment(Enchantment enchantment, int strength) {
        itemMeta.addEnchant(enchantment, strength, true);
        return this;
    }


    @Override
    public ItemBuilder hideAllFlags() {
        itemMeta.addItemFlags(ItemFlag.values());
        return this;
    }


    @Override
    public ItemBuilder hideFlag(ItemFlag itemFlag) {
        itemMeta.addItemFlags(itemFlag);
        return this;
    }

    @Override
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
