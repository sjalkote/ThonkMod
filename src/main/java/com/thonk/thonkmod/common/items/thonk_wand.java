package com.thonk.thonkmod.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class thonk_wand extends Item {

    public thonk_wand(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        // If the user is holding down the LEFT CONTROL key:
        if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL)) {
            tooltip.add(new StringTextComponent("\u00A77With a \u00A73right click\u00A77, you can summon" +
                    " \u00A7elightning \u00A77\nwherever you look. There is a \u00A74cooldown" +
                    "\u00A77 of\n\u00A7c5 seconds per use."));

        // If the user is holding down the LEFT SHIFT key:
        } else if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new StringTextComponent("\u00A77This \u00A76legendary \u00A77item is rumored to\n" +
                    "posses a power that allows the user\nto summon \u00A7elightning \u00A77wherever they wish."));
        }
        // The default text to show:
        else {
            tooltip.add(new TranslationTextComponent("tooltip.thonk_wand.hold_shift"));
            tooltip.add(new TranslationTextComponent("tooltip.thonk_wand.hold_control"));
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
        // The endpoint of the ray (where it travels to)
        double rayLength = 100.0;
        Vector3d playerRotation = player.getViewVector(0);
        Vector3d rayPath = playerRotation.scale(rayLength);
        // The start and end points of the ray
        Vector3d from = player.getEyePosition(0);
        Vector3d to = from.add(rayPath);
        // Create a ray, then cast it.
        RayTraceContext rayCtx = new RayTraceContext(from, to, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, null);
        BlockRayTraceResult rayHit = world.clip(rayCtx);

        // Now use the results
        if (rayHit.getType() != RayTraceResult.Type.MISS){
            // If we get a hit (If the ray does not miss)
            Vector3d hitLocation = rayHit.getLocation();

            LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(hitLocation.x, hitLocation.y, hitLocation.z);
            world.addFreshEntity(lightning);

            // The cooldown is a 1/10 of the specified integer, not ticks. (So 50 would set it to 5 seconds)
            player.getCooldowns().addCooldown(this, 50);

        }

        return super.use(world, player, handIn);
    }


    /*
            // NOTE: The following gives the player a potion effect, followed by the duration and multiplier integers.
             It is important to note that the duration and the effect are not measured as you may think. The duration is
             measured with in-game ticks. There are 20 in-game ticks for every 1 second, so a duration listed as 60
             would last for 3 seconds. The multiplier will be one more than whatever integer you supply a multiplier of
             5, it would be 6 in-game. Setting the multiplier as 0 or not specifying it at all will therefore set the
             multiplier as 1, which is the default of a potion effect. //
            playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 60)); // Duration then multiplier
     */

}
