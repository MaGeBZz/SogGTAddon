package net.sog.core.common.data.helper;

import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class MultiblockPredicateHelper {

    public static Block block(String id) {
        return Objects.requireNonNull(
                ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id)),
                "Block not found: " + id);
    }

    public static TraceabilityPredicate blocks(String... ids) {
        Block[] blockArray = new Block[ids.length];
        for (int i = 0; i < ids.length; i++) {
            blockArray[i] = block(ids[i]);
        }
        return Predicates.blocks(blockArray);
    }

    public static TraceabilityPredicate blocksOr(String first, String... rest) {
        TraceabilityPredicate predicate = blocks(first);
        for (String id : rest) {
            predicate = predicate.or(blocks(id));
        }
        return predicate;
    }
}
