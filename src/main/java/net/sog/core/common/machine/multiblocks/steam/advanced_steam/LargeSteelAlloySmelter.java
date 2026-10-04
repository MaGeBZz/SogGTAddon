package net.sog.core.common.machine.multiblocks.steam.advanced_steam;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.*;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.sog.core.common.data.helper.AdvancedSteamParallelMultiblockMachine;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.data.helper.MultiblockPredicateHelper.blocks;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class LargeSteelAlloySmelter {

    public static final MultiblockMachineDefinition LARGE_STEEL_ALLOY_SMELTER = REGISTRATE
            .multiblock("large_steel_alloy_smelter", AdvancedSteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .recipeModifier(AdvancedSteamParallelMultiblockMachine.MODIFIER, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BCCCB", "BDDDB", "ADDDA", "AAAAA")
                    .aisle("CCCCC", "DAAAD", "DAAAD", "ADDDA")
                    .aisle("CCCCC", "DAAAD", "DAAAD", "ADDDA")
                    .aisle("CCCCC", "DAAAD", "DAAAD", "ADDDA")
                    .aisle("BCCCB", "BDGDB", "ADDDA", "AAAAA")
                    .where("G", Predicates.controller(Predicates.blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("B", blocks("gtceu:steel_frame"))
                    .where("C", blocks("gtceu:steel_firebox_casing"))
                    .where("D", blocks("gtceu:solid_machine_casing")
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("BCCCB", "BFGHB", "ADIDA", "AAAAA")
                    .aisle("CCCCC", "DAAAD", "DAAAD", "ADDDA")
                    .aisle("CCCCC", "DAAAD", "DAAAD", "ADDDA")
                    .aisle("CCCCC", "DAAAD", "DAAAD", "ADDDA")
                    .aisle("BCCCB", "BDDDB", "ADDDA", "AAAAA")
                        .where('G', LargeSteelAlloySmelter.LARGE_STEEL_ALLOY_SMELTER, Direction.NORTH)
                        .where('A', Blocks.AIR)
                        .where('B', ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel))
                        .where('C', FIREBOX_STEEL)
                        .where('D', CASING_STEEL_SOLID)
                        .where('F', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                        .where('H', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                        .where('I', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/alloy_smelter"))
            .register();

    public static void init() {}
}
