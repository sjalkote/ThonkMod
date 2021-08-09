package com.technoship123.thonkmod.blocks;

import com.technoship123.thonkmod.ThonkMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

/** Class that contains all simple-blocks belonging to the mod */
@SuppressWarnings("unused")
public class ModBlocks {
    // Currently, the .harvestTool() does not take effect when specifying a Pickaxe ToolType, so a Minecraft tags located in
    // JSON files are being used until the bug is fixed.
    public static final RegistryObject<Block> THONK_ORE_BLOCK = registerWithItem("thonk_ore", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE)
                    .strength(15F, 9.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_THONK_ORE_BLOCK = registerWithItem("deepslate_thonk_ore", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(25F, 15.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));

    // Registry stuff below ------------------------------------------
    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return ThonkMod.Registrar.BLOCKS.register(name, block);
    }

    public static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block) {
        RegistryObject<T> regObj = registerNoItem(name, block);
        ThonkMod.Registrar.ITEMS.register(name, () -> new BlockItem(regObj.get(), new Item.Properties()
                .tab(ThonkMod.CREATIVE_TAB)));
        return regObj;
    }

    public static void registerWithItem() {}
}
