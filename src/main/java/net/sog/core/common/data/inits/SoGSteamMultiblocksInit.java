package net.sog.core.common.data.inits;

import net.sog.core.common.machine.multiblocks.steam.*;
import net.sog.core.common.machine.multiblocks.steam.advanced_steam.*;

public class SoGSteamMultiblocksInit {

    public static void init() {
        AdvancedPrimitiveBlastFurnace.init();
        LargeSteelOven.init();
        LargeSteelGrinder.init();
        LargeSteelForgeHammer.init();
        LargeSteelCompressor.init();
        LargeSteelCentrifuge.init();
        SteamForgeHammer.init();
        SteamCompressor.init();
        LargeBarrel.init();
        SteamCentrifuge.init();
    }
}
