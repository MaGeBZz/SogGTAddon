package net.sog.core.common.data;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import net.sog.core.api.data.SoGMaterialIconType;

import java.util.function.Predicate;

public class SoGNewMaterialFlags {

    public static final MaterialFlag GENERATE_NANITES = new MaterialFlag.Builder("generate_nanites")
            .build();
    public static final MaterialFlag GENERATE_ULTRADENSE_PLATE = new MaterialFlag.Builder("generate_ultradense_plate")
            .build();

    public static TagPrefix ultraDense;
    public static TagPrefix nanites;

    public static final Predicate<Material> hasPlateProp = material -> material.hasFlag(MaterialFlags.GENERATE_PLATE);

    public static void initTagPrefixes() {
        ultraDense = new TagPrefix("ultradensePlate")
                .idPattern("ultradense_%s_plate")
                .defaultTagPath("ultra_dense_plates/%s")
                .defaultTagPath("ultra_dense_plates")
                .materialIconType(SoGMaterialIconType.ultraDense)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(mat -> mat.hasFlag(SoGNewMaterialFlags.GENERATE_ULTRADENSE_PLATE));

        nanites = new TagPrefix("nanites")
                .idPattern("%s_nanites")
                .defaultTagPath("nanites/%s")
                .defaultTagPath("nanites")
                .materialIconType(SoGMaterialIconType.nanites)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(mat -> mat.hasFlag(SoGNewMaterialFlags.GENERATE_NANITES));
    }
}
