package net.sog.core;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class SoGGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return sogcore.SOG_REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return sogcore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        // SoGMaterialIconType.init();
        // SoGNewMaterialFlags.initTagPrefixes();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        // SoGRecipeGen.init(provider);
        // TESTRECIPES.init(provider);
    }
}
