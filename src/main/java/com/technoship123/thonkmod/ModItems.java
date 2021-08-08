package com.technoship123.thonkmod;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> THONK_ITEM = ThonkMod.Registrar.ITEMS.register("thonk", () ->
            new Item(new Item.Properties().tab(ThonkMod.CREATIVE_TAB).rarity(Rarity.UNCOMMON).fireResistant()));

    static void register() {}
}
