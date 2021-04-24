package com.thonk.thonkmod.core.init;

import com.thonk.thonkmod.thonkmod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, thonkmod.MOD_ID);

    public static final RegistryObject<Item> THONK_ITEM = ITEMS.register("thonk_item", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

}
