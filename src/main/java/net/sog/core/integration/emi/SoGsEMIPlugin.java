package net.sog.core.integration.emi;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;

@EmiEntrypoint
public class SoGsEMIPlugin implements EmiPlugin {

    public static final EmiRecipeCategory FISSION_FUEL = new EmiRecipeCategory(
            new ResourceLocation("sogcore", "fission_fuel"),
            EmiStack.of(ChemicalHelper.get(TagPrefix.ingot, GTMaterials.Uranium235)));

    public static final EmiRecipeCategory FISSION_COOLANT = new EmiRecipeCategory(
            new ResourceLocation("sogcore", "fission_coolant"),
            EmiStack.of(Items.WATER_BUCKET));

    public static final EmiRecipeCategory FISSION_BREEDING = new EmiRecipeCategory(
            new ResourceLocation("sogcore", "fission_breeding"),
            EmiStack.of(Items.CAULDRON));

    @Override
    public void register(EmiRegistry registry) {
        // Phoenix Fission is being split out of PhoenixCore into its own mod. Fission-specific formula
        // search aliases only make sense while that content is actually present.

        registry.addCategory(FISSION_FUEL);
        registry.addCategory(FISSION_COOLANT);
        registry.addCategory(FISSION_BREEDING);

        registerMaterialFluidSearchAliases(registry);
    }

    /**
     * GTCEU material fluids are indexed by EMI as their own EmiStack, but their baked search name often
     * doesn't resolve to the material's real display name (missing/mismatched fluid translation keys), so
     * searching by material name only ever matches the bucket item. Aliasing the fluid stack to the material's
     * known localized name fixes lookup regardless of the fluid's own translation state.
     */
    private static void registerMaterialFluidSearchAliases(EmiRegistry registry) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (!material.hasProperty(PropertyKey.FLUID)) continue;

            Fluid fluid = material.getFluid();
            if (fluid == null || fluid == Fluids.EMPTY) continue;

            EmiStack fluidStack = EmiStack.of(fluid);
            if (fluidStack.isEmpty()) continue;

            registry.addAlias(fluidStack, material.getLocalizedName());
        }
    }

    private static void addFormulaAliases(EmiRegistry registry, EmiStack stack, String... terms) {
        for (String term : terms) {
            registry.addAlias(stack, Component.literal(term));
        }
    }
}
