package net.sog.core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sog.core.client.keybind.SoGsKeybinds;
import net.sog.core.sogcore;

@Mod.EventBusSubscriber(modid = sogcore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientTickHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        while (SoGsKeybinds.OPEN_EMI_FAVORITE_PAGES.consumeClick()) {
            mc.setScreen(new net.sog.core.client.emi.EMIFavoritePagesScreen(mc.screen));
        }
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;

        if (mc.options.keyShift.isDown()) {
            ItemStack stack = mc.player.getMainHandItem();
        }
    }

    @SubscribeEvent
    public static void onRightClick(InputEvent.InteractionKeyMappingTriggered event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (!event.isUseItem()) return;
        ItemStack stack = mc.player.getMainHandItem();

        if (!mc.player.isShiftKeyDown()) return;
        if (mc.hitResult == null || mc.hitResult.getType() != HitResult.Type.MISS) return;

        event.setCanceled(true);
    }
}
