package net.sog.core.common.data.helper;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;

import org.jetbrains.annotations.NotNull;

public class AdvancedSteamParallelMultiblockMachine extends SteamParallelMultiblockMachine {

    public AdvancedSteamParallelMultiblockMachine(IMachineBlockEntity holder) {
        super(holder, 32);
    }

    public static @NotNull ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        return SteamParallelMultiblockMachine.recipeModifier(machine, recipe);
    }

    public static final RecipeModifier MODIFIER = AdvancedSteamParallelMultiblockMachine::recipeModifier;
}
