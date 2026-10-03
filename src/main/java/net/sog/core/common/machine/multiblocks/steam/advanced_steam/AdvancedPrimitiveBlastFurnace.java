package net.sog.core.common.machine.multiblocks.steam.advanced_steam;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveBlastFurnaceMachine;
import com.gregtechceu.gtceu.utils.GTUtil;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.sog.core.common.data.SoGRecipeModifiers;

import static com.gregtechceu.gtceu.api.pattern.Predicates.abilities;
import static com.gregtechceu.gtceu.api.pattern.Predicates.controller;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static net.sog.core.common.data.helper.MultiblockPredicateHelper.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class AdvancedPrimitiveBlastFurnace {

    public static final MultiblockMachineDefinition ADVANCED_PRIMITIVE_BLAST_FURNACE = REGISTRATE
            .multiblock("advanced_primitive_blast_furnace", PrimitiveBlastFurnaceMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.PRIMITIVE_BLAST_FURNACE_RECIPES)
            .recipeModifiers(
                    SoGRecipeModifiers::speedboost3x,
                    SoGRecipeModifiers::creosoteSpeed)
            .model(createWorkableCasingMachineModel(
                    GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/primitive_blast_furnace"))
                    .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createPBFLavaRender)))
            .hasBER(true)
            .appearanceBlock(() -> block("gtceu:solid_machine_casing"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBB", "CCC", "CCC", "CCC")
                    .aisle("BBB", "CAC", "CAC", "CAC")
                    .aisle("BBB", "CDC", "CCC", "CCC")
                    .where("D", controller(Predicates.blocks(definition.get())))
                    .where("B", blocks("gtceu:steel_firebox_casing"))
                    .where("C", blocks("gtceu:solid_machine_casing")
                            .or(abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(1))
                            .or(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1)))
                    .where("A", Predicates.air()
                            .or(Predicates.custom(
                                    bws -> GTUtil.isBlockSnow(bws.getBlockState()), null)))
                    .build())

            .shapeInfo(definition -> MultiblockShapeInfo.builder()
                    .aisle("BBB", "DEF", "CGC", "CCC")
                    .aisle("BBB", "CAC", "CAC", "CAC")
                    .aisle("BBB", "CCC", "CCC", "CCC")
                    .where('E', AdvancedPrimitiveBlastFurnace.ADVANCED_PRIMITIVE_BLAST_FURNACE, Direction.NORTH)
                    .where('A', Blocks.AIR)
                    .where('B', FIREBOX_STEEL)
                    .where('C', CASING_STEEL_SOLID)
                    .where('F', PartAbility.IMPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('D', PartAbility.EXPORT_ITEMS.getAllBlocks().iterator().next())
                    .where('G', PartAbility.IMPORT_FLUIDS.getAllBlocks().iterator().next())
                    .build())

            .register();

    public static void init() {}
}
