package net.sog.core.common.data.helper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@SuppressWarnings("removal")
public class RecipeHelper {

    public static ItemStack item(String id) {
        return item(id, 1);
    }

    public static ItemStack item(String id, int count) {
        return new ItemStack(
                Objects.requireNonNull(
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation(id)),
                        "Item not found: " + id),
                count);
    }
}
