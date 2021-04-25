package com.thonk.thonkmod.core.init;

import com.thonk.thonkmod.thonkmod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, thonkmod.MOD_ID);

    // Thonk Ore
    public static final RegistryObject<Block> THONK_ORE_BLOCK = BLOCKS.register("thonk_ore_block",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(15f, 30f) // Hardness and Resistance
            .harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.NETHER_GOLD_ORE))); // Diamond pic or higher, nether gold ore sounds.
}
