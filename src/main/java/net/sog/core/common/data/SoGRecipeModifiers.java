package net.sog.core.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import org.jetbrains.annotations.NotNull;

public class SoGRecipeModifiers {

    /*
     * Recipe Modifier that checks if Creosote is in a Hatch but won't consume!
     * 
     * public static @NotNull ModifierFunction creosoteSpeed(MetaMachine machine, GTRecipe recipe) {
     * var creosoteRecipe = GTRecipeBuilder.ofRaw()
     * .inputFluids(GTMaterials.Creosote.getFluid(100))
     * .buildRawRecipe();
     * 
     * if (machine instanceof IRecipeCapabilityHolder holder &&
     * RecipeHelper.matchRecipe(holder, creosoteRecipe).isSuccess()) {
     * return ModifierFunction.builder()
     * .durationMultiplier(0.333)
     * .build();
     * }
     * 
     * return ModifierFunction.builder().build();
     * }
     */

    /*
     * Recipe Modifier that checks if Creosote is in a Hatch and consumes it per Recipe!
     * public static @NotNull ModifierFunction creosoteSpeed(MetaMachine machine, GTRecipe recipe) {
     * var creosoteRecipe = GTRecipeBuilder.ofRaw()
     * .inputFluids(GTMaterials.Creosote.getFluid(100))
     * .buildRawRecipe();
     * if (machine instanceof IRecipeCapabilityHolder holder &&
     * RecipeHelper.matchRecipe(holder, creosoteRecipe).isSuccess()) {
     * 
     * RecipeHelper.handleRecipeIO(holder, creosoteRecipe, IO.IN, null);
     * 
     * return ModifierFunction.builder()
     * .durationMultiplier(0.333)
     * .build();
     * }
     * return ModifierFunction.builder().build();
     * }
     */

    //
    public static @NotNull ModifierFunction creosoteSpeed(MetaMachine machine, GTRecipe recipe) {
        var creosoteCheck = GTRecipeBuilder.ofRaw()
                .inputFluids(GTMaterials.Creosote.getFluid(1000))
                .buildRawRecipe();

        if (machine instanceof IRecipeCapabilityHolder holder &&
                RecipeHelper.matchRecipe(holder, creosoteCheck).isSuccess()) {

            return modifiedRecipe -> {
                GTRecipeBuilder builder = new GTRecipeBuilder(modifiedRecipe, modifiedRecipe.recipeType);

                builder.perTick = true;
                builder.inputFluids(GTMaterials.Creosote.getFluid(1));

                GTRecipe result = builder.buildRawRecipe();
                result.duration = Math.max(1, (int) (modifiedRecipe.duration * 0.333));

                return result;
            };
        }
        return ModifierFunction.IDENTITY;
    }

    public static @NotNull ModifierFunction speedboost2x(MetaMachine machine, GTRecipe recipe) {
        return ModifierFunction.builder()
                .durationMultiplier(0.5)
                .build();
    }

    public static @NotNull ModifierFunction speedboost3x(MetaMachine machine, GTRecipe recipe) {
        return ModifierFunction.builder()
                .durationMultiplier(0.333)
                .build();
    }

    public static @NotNull ModifierFunction speedboost4x(MetaMachine machine, GTRecipe recipe) {
        return ModifierFunction.builder()
                .durationMultiplier(0.25)
                .build();
    }

    public static @NotNull ModifierFunction speedboost5x(MetaMachine machine, GTRecipe recipe) {
        return ModifierFunction.builder()
                .durationMultiplier(0.2)
                .build();
    }

    public static @NotNull ModifierFunction speedboost10x(MetaMachine machine, GTRecipe recipe) {
        return ModifierFunction.builder()
                .durationMultiplier(0.1)
                .build();
    }
}
