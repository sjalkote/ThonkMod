// ThonkMod is under the GNU General Public License v3. Please make sure to follow it if using my code, thanks!
package com.technoship123.thonkmod;

import com.technoship123.thonkmod.blocks.ModBlocks;
import com.technoship123.thonkmod.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;


/** The <b>main class</b> of Thonk Mod.
 * @author TechnoShip123
 */
@Mod("thonkmod")
public class ThonkMod
{
    /** The <b>Mod ID</b> for ThonkMod. Must match the Mod ID listed in the <code>mods.toml</code> file & the <code>@Mod</code> annotation */
    public static final String MID = "thonkmod";
    /** The <b>CreativeModeTab</b> for ThonkMod, which houses all things related to the mod. */
    public static final CreativeModeTab CREATIVE_TAB = new CreativeTab("thonkmod");
    private static final Logger LOGGER = LogManager.getLogger();  // Directly reference a log4j logger.

    public ThonkMod() {
        // Register blocks from the Registrar using the DeferredRegister
        Registrar.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup); // Register the setup method
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC); // Register the enqueueIMC method
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC); // Register the processIMC method
        // Register ourselves for the server and other game events we may be interested in:
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("thonkmod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    /** Houses methods & objects related to the registry of Blocks, Items, and BlockItems for ThonkMod. */
    public static class Registrar {
        /** DeferredRegister for registering <b>Blocks</b> */
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MID);
        /** DeferredRegister for registering <b>Items</b> & <b>BlockItems</b> */
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MID);
        /** Adds items & blocks to the registry task, uses the <code><b>DeferredRegister</b></code>. */
        public static void register() {
            IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
            BLOCKS.register(eventBus);  ITEMS.register(eventBus);  // Register Blocks & Items
            ModBlocks.registerWithItem(); ModItems.register();  // Invoke methods to add the Blocks & Items
        }
    }

    /** Class that creates the custom <code><b>CreativeModeTab</b></code> for ThonkMod-related Items & BlockItems.
     * @see net.minecraft.world.item.CreativeModeTab */
    public static class CreativeTab extends CreativeModeTab {
        // The name used in lang files will be something like "itemGroup.creativeTabName":
        public CreativeTab(String creativeTabName) { super(creativeTabName); }
        @Nonnull
        public ItemStack makeIcon() {
            return ModItems.THONK_ITEM.get().getDefaultInstance();  // Set the Thonk Item as our CreativeTab icon.
        }
    }

}
