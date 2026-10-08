package net.sog.core.common.machine.multiblocks.steam;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.sog.core.sogcore;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class SteamCompressor {

    public static final MultiblockMachineDefinition STEAM_COMPRESSOR = REGISTRATE
            .multiblock("steam_compressor", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.COMPRESSOR_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABBBA", "ABBBA", "ABBBA", "AABAA", "AAAAA", "AAAAA")
                    .aisle("BDDDB", "BAAAB", "BAAAB", "ABABA", "AABAA", "AAAAA")
                    .aisle("BDDDB", "BAAAB", "BAAAB", "AAEAA", "AAFAA", "AABAA")
                    .aisle("BDDDB", "BAAAB", "BAAAB", "AAAAA", "AAAAA", "AAAAA")
                    .aisle("ABGBA", "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA")
                    .where("G", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("D", blocks(BRONZE_HULL.get()))
                    .where("E", blocks(CASING_BRONZE_PIPE.get()))
                    .where("F", blocks(CASING_BRONZE_GEARBOX.get()))
                    .where("B", blocks(CASING_BRONZE_BRICKS.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("AGIJA", "AAAAA", "AAAAA", "AAAAA", "AAAAA", "AAAAA")
                    .aisle("BDDDH", "BAAAB", "BAAAB", "AAAAA", "AAAAA", "AAAAA")
                    .aisle("BDDDB", "BAAAB", "BAAAB", "AAEAA", "AAFAA", "AABAA")
                    .aisle("BDDDB", "BAAAB", "BAAAB", "ABABA", "AABAA", "AAAAA")
                    .aisle("ABBBA", "ABBBA", "ABBBA", "AABAA", "AAAAA", "AAAAA")
                    .where('I', SteamCompressor.STEAM_COMPRESSOR, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', CASING_BRONZE_BRICKS)
                    .where('D', BRONZE_HULL)
                    .where('E', CASING_BRONZE_PIPE)
                    .where('F', CASING_BRONZE_GEARBOX)
                    .where('J', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('G', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('H', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    sogcore.id("block/machines/steam_compressor"))
            .register();

    public static void init() {}
}
