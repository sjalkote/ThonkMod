package com.thonk.thonkmod.core.init;

import com.thonk.thonkmod.common.items.thonk_wand;
import com.thonk.thonkmod.thonkmod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


@SuppressWarnings("unused")
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, thonkmod.MOD_ID);

    // ITEMS
    public static final RegistryObject<Item> THONK = ITEMS.register("thonk",
            () -> new Item(new Item.Properties().tab(thonkmod.THONKMOD_TAB)));

    // SPECIAL ITEMS
    public static final RegistryObject<thonk_wand> THONK_WAND = ITEMS.register("thonk_wand",
            () -> new thonk_wand(new Item.Properties().tab(thonkmod.THONKMOD_TAB).fireResistant().rarity(Rarity.EPIC).stacksTo(1)));

    // BLOCK ITEMS
    public static final RegistryObject<BlockItem> THONK_ORE = ITEMS.register("thonk_ore",
            () -> new BlockItem(BlockInit.THONK_ORE.get(), new Item.Properties().tab(thonkmod.THONKMOD_TAB)));

}
