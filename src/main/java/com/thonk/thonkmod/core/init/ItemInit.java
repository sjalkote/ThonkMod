package com.thonk.thonkmod.core.init;

import com.thonk.thonkmod.thonkmod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, thonkmod.MOD_ID);

    // ITEMS
    public static final RegistryObject<Item> THONK_ITEM = ITEMS.register("thonk_item", () -> new Item(new Item.Properties().tab(thonkmod.THONKMOD_TAB)));

    // BLOCK ITEMS
    public static final RegistryObject<BlockItem> THONK_ORE_BLOCK = ITEMS.register("thonk_ore_block", () -> new BlockItem(BlockInit.THONK_ORE_BLOCK.get(),
            new Item.Properties().tab(thonkmod.THONKMOD_TAB)));

}
