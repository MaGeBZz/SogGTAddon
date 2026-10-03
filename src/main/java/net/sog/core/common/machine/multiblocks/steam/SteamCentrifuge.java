package net.sog.core.common.machine.multiblocks.steam;

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

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class SteamCentrifuge {

    public static final MultiblockMachineDefinition STEAM_CENTRIFUGE = REGISTRATE
            .multiblock("steam_centrifuge", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.CENTRIFUGE_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BCCCB", "BDDDB", "BDDDB", "ADDDA")
                    .aisle("CCCCC", "DAAAD", "DABAD", "DABAD")
                    .aisle("CCCCC", "DAFAD", "DBFBD", "DBFBD")
                    .aisle("CCCCC", "DAAAD", "DABAD", "DABAD")
                    .aisle("BCCCB", "BDEDB", "BDDDB", "ADDDA")
                    .where("E", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("F", blocks(CASING_BRONZE_PIPE.get()))
                    .where("B", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Bronze)))
                    .where("C", blocks(FIREBOX_BRONZE.get()))
                    .where("D", blocks(CASING_BRONZE_BRICKS.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("BCCCB", "BGHIB", "BDJDB", "ADDDA")
                    .aisle("CCCCC", "DAAAD", "DABAD", "DABAD")
                    .aisle("CCCCC", "DAFAD", "DBFBD", "DBFBD")
                    .aisle("CCCCC", "DAAAD", "DABAD", "DABAD")
                    .aisle("BCCCB", "BDDDB", "BDDDB", "ADDDA")
                    .where('H', SteamCentrifuge.STEAM_CENTRIFUGE, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Bronze))
                    .where('C', FIREBOX_BRONZE)
                    .where('D', CASING_BRONZE_BRICKS)
                    .where('F', CASING_BRONZE_PIPE)
                    .where('I', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('G', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('J', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/machines/centrifuge"))
            .register();

    public static void init() {}
}
