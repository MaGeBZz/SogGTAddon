package net.sog.core.common.blocks;

import com.gregtechceu.gtceu.api.block.ActiveBlock;

import net.sog.core.common.data.helper.SoGBlockBuilders;

import com.tterrag.registrate.util.entry.BlockEntry;

public class SoGActiveCasings {

    public static BlockEntry<ActiveBlock> STEEL_CRUSHING_WHEELS;

    public static void init() {
        // GTCeu (own recolored ones)
        STEEL_CRUSHING_WHEELS = SoGBlockBuilders.createActiveCasing(
                "steel_crushing_wheels",
                "block/active_casings/steel_crushing_wheels");
    }
}
