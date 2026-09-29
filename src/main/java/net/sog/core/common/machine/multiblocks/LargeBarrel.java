package net.sog.core.common.machine.multiblocks;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;

import net.sog.core.api.machine.multiblock.LargeBarrelMachine;
import net.sog.core.common.data.SoGRecipeTypes;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.*;
import static net.sog.core.common.data.SoGBlocks.ULTRA_DENSE_COLLIDER_CASING;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class LargeBarrel {

    public static final MultiblockMachineDefinition LARGE_BARREL = REGISTRATE
            .multiblock("large_barrel", LargeBarrelMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(SoGRecipeTypes.LARGE_BARREL_RECIPES)
            .recipeModifiers(OC_NON_PERFECT_SUBTICK, BATCH_MODE)
            .appearanceBlock(ULTRA_DENSE_COLLIDER_CASING)
            // .appearanceBlock(CASING_STEEL_SOLID)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("DDD", "PPP", "PPP", "PPP")
                    .aisle("DDD", "P P", "P P", "P P")
                    .aisle("DDD", "PCP", "PPP", "PPP")
                    .where('C', controller(blocks(definition.getBlock())))
                    .where('D', blocks(CASING_PUMP_DECK.get()))
                    .where(" ", Predicates.air())
                    .where('P', blocks(TREATED_WOOD_PLANK.get())
                            .or(abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(2))
                            .or(abilities(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(2)))
                    .build())
            .workableCasingModel(GTCEu.id("block/treated_wood_planks"),
                    GTCEu.id("block/machines/brewery"))
            .register();

    public static void init() {}
}
