package net.sog.core.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static net.sog.core.common.data.SoGNewMaterialFlags.nanites;
import static net.sog.core.common.data.SoGNewMaterialFlags.ultraDense;

public class SoGRecipeGen {

    public static void init(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasFlag(MaterialFlags.NO_UNIFICATION)) {
                continue;
            }
            processUltraDensePlate(provider, material);
            processNanites(provider, material);
        }
    }

    private static void processUltraDensePlate(Consumer<FinishedRecipe> provider, Material material) {
        if (!material.shouldGenerateRecipesFor(ultraDense)) {
            return;
        }
        if (!material.shouldGenerateRecipesFor(plate) || !material.hasProperty(PropertyKey.DUST)) {
            return;
        }

        if (material.hasFlag(SoGNewMaterialFlags.GENERATE_ULTRADENSE_PLATE)) {
            // Genereal Recipe 64 Plates -> 1 Ultra-Dense in Bender
            GTRecipeTypes.BENDER_RECIPES.recipeBuilder("ultradense_plate_" + material.getName())
                    .inputItems(plate, material, 512)
                    .outputItems(ultraDense, material, 1)
                    .circuitMeta(16)
                    .duration((int) (material.getMass() * 20 * 4))
                    .EUt(VA[IV])
                    .save(provider);
        }
    }

    private static void processNanites(Consumer<FinishedRecipe> provider, Material material) {
        if (!material.shouldGenerateRecipesFor(nanites)) {
            return;
        }

        if (!material.shouldGenerateRecipesFor(plate) || !material.hasProperty(PropertyKey.FLUID)) {
            return;
        }

        if (material.hasFlag(SoGNewMaterialFlags.GENERATE_NANITES)) {
            // General Recipe 1 Dust -> 1 Nanite in Assembler
            GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder("nanites_" + material.getName())
                    .inputItems(ingot, material, 2048)
                    .inputItems(dust, material, 2048)
                    .inputItems(plate, material, 2048)
                    .inputFluids(material.getFluid(40960))
                    .outputItems(nanites, material, 1)
                    .duration((int) (material.getMass() * 20 * 64))
                    .EUt(VA[IV])
                    .save(provider);
        }
    }
}
