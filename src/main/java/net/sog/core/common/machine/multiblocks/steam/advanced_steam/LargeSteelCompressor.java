package net.sog.core.common.machine.multiblocks.steam.advanced_steam;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.*;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.sog.core.common.data.helper.AdvancedSteamParallelMultiblockMachine;
import net.sog.core.sogcore;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class LargeSteelCompressor {

    public static final MultiblockMachineDefinition LARGE_STEEL_COMPRESSOR = REGISTRATE
            .multiblock("large_steel_compressor", AdvancedSteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(GTRecipeTypes.COMPRESSOR_RECIPES)
            .recipeModifier(AdvancedSteamParallelMultiblockMachine.MODIFIER, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "AAAAAAA", "AAAAAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAAAAAB", "AAAAAAA", "AAAAAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAEEEAB", "AAAFAAA", "AAABAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAEEEAB", "ABFFFBA", "AABBBAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAEEEAB", "AAAFAAA", "AAABAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "AAAAAAA", "AAAAAAA", "AAAAAAA")
                    .aisle("ABBGBBA", "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA")
                    .where("G", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("D", blocks(STEEL_HULL.get()))
                    .where("E", blocks(CASING_STEEL_PIPE.get()))
                    .where("F", blocks(CASING_STEEL_GEARBOX.get()))
                    .where("B", blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("ABGHIJA", "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA", "AAAAAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "AAAAAAA", "AAAAAAA", "AAAAAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAEEEAB", "AAAFAAA", "AAABAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAEEEAB", "ABFFFBA", "AABBBAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAEEEAB", "AAAFAAA", "AAABAAA")
                    .aisle("BDDDDDB", "BAAAAAB", "BAAAAAB", "BAAAAAB", "AAAAAAA", "AAAAAAA")
                    .aisle("ABBBBBA", "ABBBBBA", "ABBBBBA", "ABBBBBA", "AAAAAAA", "AAAAAAA")
                    .where('H', LargeSteelCompressor.LARGE_STEEL_COMPRESSOR, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', CASING_STEEL_SOLID)
                    .where('D', STEEL_HULL)
                    .where('E', CASING_STEEL_PIPE)
                    .where('F', CASING_STEEL_GEARBOX)
                    .where('I', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('G', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('J', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    sogcore.id("block/machines/steam_compressor"))
            .register();

    public static void init() {}
}
