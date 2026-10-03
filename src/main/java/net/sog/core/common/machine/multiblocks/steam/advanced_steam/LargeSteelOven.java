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

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class LargeSteelOven {

    public static final MultiblockMachineDefinition LARGE_STEEL_OVEN = REGISTRATE
            .multiblock("large_steel_oven", AdvancedSteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(GTRecipeTypes.FURNACE_RECIPES)
            .recipeModifier(AdvancedSteamParallelMultiblockMachine.MODIFIER, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBBBB", "CCCCC", "CCCCC", "ACCCA")
                    .aisle("BBBBB", "CAAAC", "CAAAC", "ACCCA")
                    .aisle("BBBBB", "CAAAC", "CAAAC", "ACCCA")
                    .aisle("BBBBB", "CAAAC", "CAAAC", "ACCCA")
                    .aisle("BBBBB", "CCDCC", "CCCCC", "ACCCA")
                    .where("D", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("B", blocks(FIREBOX_STEEL.get()))
                    .where("C", blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("BBBBB", "CEFGC", "CCHCC", "ACCCA")
                    .aisle("BBBBB", "CAAAC", "CAAAC", "ACCCA")
                    .aisle("BBBBB", "CAAAC", "CAAAC", "ACCCA")
                    .aisle("BBBBB", "CAAAC", "CAAAC", "ACCCA")
                    .aisle("BBBBB", "CCCCC", "CCCCC", "ACCCA")
                    .where('F', LargeSteelOven.LARGE_STEEL_OVEN, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', FIREBOX_STEEL)
                    .where('C', CASING_STEEL_SOLID)
                    .where('G', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('E', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('H', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/electric_furnace"))
            .register();

    public static void init() {}
}
