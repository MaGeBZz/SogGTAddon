package net.sog.core.client.keybind;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import com.mojang.blaze3d.platform.InputConstants;

public class SoGsKeybinds {

    public static final KeyMapping OPEN_EMI_FAVORITE_PAGES = new KeyMapping(
            "key.sogcore.open_emi_favorite_pages",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            "key.categories.sogcore");

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(OPEN_EMI_FAVORITE_PAGES);
    }
}
