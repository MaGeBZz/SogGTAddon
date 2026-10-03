package net.sog.core.common.machine.multiblocks.steam.advanced_steam;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
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
import static net.sog.core.common.blocks.SoGActiveCasings.STEEL_CRUSHING_WHEELS;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class LargeSteelGrinder {

    public static final MultiblockMachineDefinition LARGE_STEEL_GRINDER = REGISTRATE
            .multiblock("large_steel_grinder", AdvancedSteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(GTRecipeTypes.MACERATOR_RECIPES)
            .recipeModifier(AdvancedSteamParallelMultiblockMachine.MODIFIER, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBBBB", "BBBBB", "BBBBB")
                    .aisle("BBBBB", "BCCCB", "BAAAB")
                    .aisle("BBBBB", "BCCCB", "BAAAB")
                    .aisle("BBBBB", "BCCCB", "BAAAB")
                    .aisle("BBBBB", "BBDBB", "BBBBB")
                    .where("D", Predicates.controller(blocks(definition.getBlock())))
                    .where("A", Predicates.any())
                    .where("C", blocks(STEEL_CRUSHING_WHEELS.get()))
                    .where("B", blocks(CASING_STEEL_SOLID.get())
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setMaxGlobalLimited(2))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setExactLimit(1)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("BBDBB", "BEFGB", "BBBBB")
                    .aisle("BBBBB", "BCCCB", "BAAAB")
                    .aisle("BBBBB", "BCCCB", "BAAAB")
                    .aisle("BBBBB", "BCCCB", "BAAAB")
                    .aisle("BBBBB", "BBBBB", "BBBBB")
                    .where('F', LargeSteelGrinder.LARGE_STEEL_GRINDER, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', CASING_STEEL_SOLID)
                    .where('C', STEEL_CRUSHING_WHEELS.get())
                    .where('G', PartAbility.STEAM_IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('E', PartAbility.STEAM_EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('D', PartAbility.STEAM.getAllBlocks().iterator().next())
                    .build())

            .workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/gcym/large_maceration_tower"))
            .register();

    public static void init() {}
}
