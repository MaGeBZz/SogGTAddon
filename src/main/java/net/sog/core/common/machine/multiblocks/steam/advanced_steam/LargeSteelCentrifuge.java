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
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.sog.core.common.data.helper.AdvancedSteamParallelMultiblockMachine;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class LargeSteelCentrifuge {

    public static final MultiblockMachineDefinition LARGE_STEEL_CENTRIFUGE = REGISTRATE
            .multiblock("large_steel_centrifuge", AdvancedSteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(GTRecipeTypes.CENTRIFUGE_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BAAAB", "CCCCC", "CCCCC", "ACCCA")
                    .aisle("AEEEA", "CAFAC", "CAFAC", "CCCCC")
                    .aisle("AEEEA", "CFFFC", "CFFFC", "CCCCC")
                    .aisle("AEEEA", "CAFAC", "CAFAC", "CCCCC")
                    .aisle("BEEEB", "CCGCC", "CCCCC", "ACCCA")
                    .where("G", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("B", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel)))
                    .where("F", blocks(CASING_STEEL_PIPE.get()))
                    .where("E", blocks(FIREBOX_STEEL.get()))
                    .where("C", blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("BEEEB", "CGHIC", "CCJCC", "ACCCA")
                    .aisle("AEEEA", "CAFAC", "CAFAC", "CCCCC")
                    .aisle("AEEEA", "CFFFC", "CFFFC", "CCCCC")
                    .aisle("AEEEA", "CAFAC", "CAFAC", "CCCCC")
                    .aisle("BAAAB", "CCCCC", "CCCCC", "ACCCA")
                    .where('H', LargeSteelCentrifuge.LARGE_STEEL_CENTRIFUGE, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel))
                    .where('C', CASING_STEEL_SOLID)
                    .where('E', FIREBOX_STEEL)
                    .where('F', CASING_STEEL_PIPE)
                    .where('I', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('G', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('J', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/centrifuge"))
            .register();

    public static void init() {}
}
