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
import net.minecraft.world.level.block.DirectionalBlock;
import net.sog.core.sogcore;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class SteamForgeHammer {

    public static final MultiblockMachineDefinition STEAM_FORGE_HAMMER = REGISTRATE
            .multiblock("steam_forge_hammer", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.FORGE_HAMMER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBB", "ABA", "AAA", "AAA", "AAA")
                    .aisle("BEB", "BAB", "BEB", "BFB", "BBB")
                    .aisle("BBB", "ACA", "AAA", "AAA", "AAA")
                    .where("C", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("E", blocks(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Steel)))
                    .where("F", Predicates.states(Blocks.STICKY_PISTON.defaultBlockState()
                            .setValue(DirectionalBlock.FACING, Direction.DOWN)))
                    .where("B", blocks(CASING_BRONZE_BRICKS.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("CFG", "AHA", "AAA", "AAA", "AAA")
                    .aisle("BDB", "BAB", "BDB", "BEB", "BBB")
                    .aisle("BBB", "ABA", "AAA", "AAA", "AAA")
                    .where('H', SteamForgeHammer.STEAM_FORGE_HAMMER, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', CASING_BRONZE_BRICKS.get())
                    .where('D', ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Steel))
                    .where('E', Blocks.STICKY_PISTON.defaultBlockState()
                            .setValue(DirectionalBlock.FACING, Direction.DOWN))
                    .where('G', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('C', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('F', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    sogcore.id("block/machines/steam_forge_hammer"))
            .register();

    public static void init() {}
}
