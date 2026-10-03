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
import net.minecraft.world.level.block.DirectionalBlock;
import net.sog.core.common.data.helper.AdvancedSteamParallelMultiblockMachine;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class LargeSteelForgeHammer {

    public static final MultiblockMachineDefinition LARGE_STEEL_FORGE_HAMMER = REGISTRATE
            .multiblock("large_steel_forge_hammer", AdvancedSteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(GTRecipeTypes.FORGE_HAMMER_RECIPES)
            .recipeModifier(AdvancedSteamParallelMultiblockMachine.MODIFIER, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBBBB", "BBBBB", "AAAAA", "AAAAA", "AAAAA")
                    .aisle("BDDDB", "BAAAB", "ADDDA", "AEEEA", "ABBBA")
                    .aisle("BDDDB", "BAAAB", "BDDDB", "BEEEB", "BBBBB")
                    .aisle("BDDDB", "BAAAB", "ADDDA", "AEEEA", "ABBBA")
                    .aisle("BBBBB", "BBBBB", "AAAAA", "AAAAA", "AAAAA")
                    .aisle("ABBBA", "ABFBA", "AAAAA", "AAAAA", "AAAAA")
                    .where("F", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("D", blocks(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Steel)))
                    .where("E", Predicates.states(Blocks.STICKY_PISTON.defaultBlockState()
                            .setValue(DirectionalBlock.FACING, Direction.DOWN)))
                    .where("B", blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("AFGHA", "ABIBA", "AAAAA", "AAAAA", "AAAAA")
                    .aisle("BBBBB", "BBBBB", "AAAAA", "AAAAA", "AAAAA")
                    .aisle("BDDDB", "BAAAB", "ADDDA", "AEEEA", "ABBBA")
                    .aisle("BDDDB", "BAAAB", "BDDDB", "BEEEB", "BBBBB")
                    .aisle("BDDDB", "BAAAB", "ADDDA", "AEEEA", "ABBBA")
                    .aisle("BBBBB", "BBBBB", "AAAAA", "AAAAA", "AAAAA")
                    .where('I', LargeSteelForgeHammer.LARGE_STEEL_FORGE_HAMMER, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', CASING_STEEL_SOLID)
                    .where('D', ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Steel))
                    .where('E',
                            Blocks.STICKY_PISTON.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.DOWN))
                    .where('H', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('F', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('G', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/compressor"))
            .register();

    public static void init() {}
}
