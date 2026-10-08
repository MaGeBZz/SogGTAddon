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

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.data.helper.MultiblockPredicateHelper.blocks;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class SteamAlloySmelter {

    public static final MultiblockMachineDefinition STEAM_ALLOY_SMELTER = REGISTRATE
            .multiblock("steam_alloy_smelter", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBB", "CCC", "CCC")
                    .aisle("BBB", "CAC", "CCC")
                    .aisle("BBB", "CFC", "CCC")
                    .where("F", Predicates.controller(Predicates.blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("B", blocks("gtceu:bronze_firebox_casing"))
                    .where("C", blocks("gtceu:steam_machine_casing")
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("BBB", "EFG", "CDC")
                    .aisle("BBB", "CAC", "CCC")
                    .aisle("BBB", "CCC", "CCC")
                    .where('F', SteamAlloySmelter.STEAM_ALLOY_SMELTER, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', FIREBOX_BRONZE)
                    .where('C', CASING_BRONZE_BRICKS)
                    .where('E', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('G', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('D', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    sogcore.id("block/machines/steam_alloy_smelter"))
            .register();

    public static void init() {}
}
