package net.sog.core;

import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.sog.core.common.data.SoGRecipeTypes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.sog.core.common.data.helper.RecipeHelper.*;

public class TESTRECIPES {

    public static void init(Consumer<FinishedRecipe> provider) {
        SoGRecipeTypes.HIGH_ENERGY_COLLIDER_RECIPES.recipeBuilder("test_barrel1")
                .inputItems(item("gtceu:steel_ingot", 32))
                .outputItems(item("minecraft:iron_ingot", 34))
                .duration(20 * 5)
                .EUt(VA[LuV])
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("test_wire_conversion")
                .inputItems(wireFine, Copper, 1500)
                .outputItems(cableGtHex, AnnealedCopper, 15375)
                .duration(20 * 10)
                .EUt(-VA[LuV])
                .save(provider);

        GTRecipeTypes.BLAST_RECIPES.recipeBuilder("ebf_coil_testus")
                .inputItems(wireFine, Copper, 1500)
                .outputItems(cableGtHex, AnnealedCopper, 15375)
                .blastFurnaceTemp(12600)
                .circuitMeta(3)
                .duration(20 * 10)
                .EUt(VA[LuV])
                .save(provider);

        SoGRecipeTypes.LARGE_BARREL_RECIPES.recipeBuilder("test_barrel2")
                .inputItems(item("gtceu:steel_ingot", 1))
                .outputItems(item("minecraft:iron_ingot", 2))
                .duration(20 * 5)
                .save(provider);

        SoGRecipeTypes.LARGE_BARREL_RECIPES.recipeBuilder("test_barrel2fluid")
                .inputFluids(Creosote.getFluid(1000))
                .inputItems(item("minecraft:copper_ingot", 1))
                .outputFluids(Creosote.getFluid(2000))
                .outputItems(item("minecraft:iron_ingot", 2))
                .duration(20 * 5)
                .save(provider);

        SoGRecipeTypes.LARGE_BARREL_RECIPES.recipeBuilder("test_barrel3fluid")
                .inputFluids(Creosote.getFluid(1000))
                .inputItems(item("minecraft:gold_ingot", 1))
                .outputFluids(Creosote.getFluid(1000))
                .outputItems(item("minecraft:iron_ingot", 4))
                .circuitMeta(2)
                .duration(20 * 5)
                .save(provider);
    }
}
