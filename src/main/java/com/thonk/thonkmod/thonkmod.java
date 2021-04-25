package com.thonk.thonkmod;

import com.thonk.thonkmod.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("thonkmod")
public class thonkmod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "thonkmod";
    public static final ItemGroup THONKMOD_TAB = new ThonkmodTab("thonkmodtab");

    public thonkmod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        ItemInit.ITEMS.register(bus);
        // BlockInit.BLOCKS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        // Does something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
        LOGGER.info("Mod Loaded! Mod ID is - {}", MOD_ID);
    }


    // Creative Tab for the mod
    public static class ThonkmodTab extends ItemGroup {

        public ThonkmodTab (String label) {
            super(label);
        }

        @Nonnull
        public ItemStack makeIcon() {
            return ItemInit.THONK_ITEM.get().getDefaultInstance(); // Set the creative tab icon to the default instance of this item.
        }

    }

}
