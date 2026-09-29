package net.sog.core.mixin.emi;

import net.minecraft.client.Minecraft;
import net.sog.core.client.emi.EMIFavoritePagesScreen;

import dev.emi.emi.config.SidebarType;
import dev.emi.emi.screen.EmiScreenBase;
import dev.emi.emi.screen.EmiScreenManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * EMI's favorite button only applies to the global (per instance) favorites.
 * So this mixin makes clicking that button open our own GUI.
 */
@Mixin(value = EmiScreenManager.class, remap = false)
public abstract class EMIScreenManagerMixin {

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private static void sogcore$onMouseClicked(double mouseX, double mouseY, int button,
                                               CallbackInfoReturnable<Boolean> cir) {
        if (EmiScreenBase.getCurrent().isEmpty()) return;

        for (EmiScreenManager.SidebarPanel panel : EMIScreenManagerAccessor.sogcore$getPanels()) {
            if (panel.getType() != SidebarType.FAVORITES || !panel.isVisible() || panel.space == null) continue;
            if (!panel.cycle.isMouseOver(mouseX, mouseY)) continue;

            Minecraft mc = Minecraft.getInstance();
            mc.setScreen(new EMIFavoritePagesScreen(mc.screen));
            cir.setReturnValue(true);
            cir.cancel();
            return;
        }
    }
}
