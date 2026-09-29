package net.sog.core.mixin.emi;

import net.sog.core.client.emi.EMIFavoriteSets;

import dev.emi.emi.runtime.EmiFavorites;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Emi's favorite list is merely a list of a global favorites instance.
 * This mixin adds our own favorites from our added EMI tabs.
 */
@Mixin(value = EmiFavorites.class, remap = false)
public abstract class EMIFavoritesMixin {

    @Inject(method = "addFavorite(Ldev/emi/emi/api/stack/EmiIngredient;Ldev/emi/emi/api/recipe/EmiRecipe;)V",
            at = @At("TAIL"))
    private static void sogcore$onAddFavorite(CallbackInfo ci) {
        EMIFavoriteSets.onFavoritesMutated();
    }

    @Inject(method = "addFavoriteAt", at = @At("TAIL"))
    private static void sogcore$onAddFavoriteAt(CallbackInfo ci) {
        EMIFavoriteSets.onFavoritesMutated();
    }

    @Inject(method = "removeFavorite", at = @At("TAIL"))
    private static void sogcore$onRemoveFavorite(CallbackInfoReturnable<Boolean> cir) {
        EMIFavoriteSets.onFavoritesMutated();
    }
}
