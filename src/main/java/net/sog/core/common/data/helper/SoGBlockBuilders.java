package net.sog.core.common.data.helper;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ActiveBlock;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

public class SoGBlockBuilders {

    public static BlockEntry<CoilBlock> createCoilBlock(ICoilType coilType) {
        BlockEntry<CoilBlock> coilBlock = REGISTRATE
                .block(coilType.getName() + "_coil_block", p -> new CoilBlock(p, coilType))
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate(GTModels.createCoilModel(coilType))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new).build().register();
        GTCEuAPI.HEATING_COILS.put(coilType, coilBlock);
        return coilBlock;
    }

    public static BlockEntry<ActiveBlock> createActiveCasing(String name, String texturePath) {
        return REGISTRATE.block(name, ActiveBlock::new)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((ctx, prov) -> prov.simpleBlock(
                        ctx.get(),
                        prov.models().cubeAll(
                                ctx.getName(),
                                prov.modLoc(texturePath))))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static BlockEntry<Block> createGlassBlock(String name, String texture) {
        NonNullFunction<BlockBehaviour.Properties, Block> supplier = GlassBlock::new;
        return REGISTRATE.block(name, supplier)
                .initialProperties(() -> Blocks.GLASS)
                .properties(p -> p.isValidSpawn((s, l, p2, e) -> false))
                .addLayer(() -> RenderType::translucent)
                .exBlockstate(GTModels.cubeAllModel(new ResourceLocation("gtultimatecore", texture)))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new).build().register();
    }

    public static BlockEntry<Block> createCasingBlock(String name, String texture) {
        return REGISTRATE.block(name, Block::new)
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .properties(p -> p.isValidSpawn((s, l, p2, e) -> false))
                .addLayer(() -> RenderType::cutoutMipped)
                .exBlockstate(GTModels.cubeAllModel(new ResourceLocation("gtultimatecore", texture)))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new).build().register();
    }
}
