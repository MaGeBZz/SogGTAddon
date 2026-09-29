package net.sog.core.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class SoGRecipeTypes {

    public static GTRecipeType HIGH_ENERGY_COLLIDER;

    public static void init() {
        HIGH_ENERGY_COLLIDER = register("high_energy_collider", MULTIBLOCK)
                .setMaxIOSize(4, 2, 8, 4)
                .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
                .setProgressBar(GuiTextures.PROGRESS_BAR_COMPRESS, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(SoGSounds.COLLIDER)
                .setEUIO(IO.IN);
    }
}
