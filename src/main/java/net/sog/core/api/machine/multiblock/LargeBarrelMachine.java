package net.sog.core.api.machine.multiblock;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.gui.widget.GhostCircuitSlotWidget;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.gui.widget.TankWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveWorkableMachine;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;

import net.minecraft.world.entity.player.Player;

public class LargeBarrelMachine extends PrimitiveWorkableMachine implements IUIMachine {

    public LargeBarrelMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public ModularUI createUI(Player player) {
        GhostCircuitSlotWidget circuitSlot = new GhostCircuitSlotWidget();
        circuitSlot.setCircuitInventory(importItems.storage);

        circuitSlot.setSelfPosition(7, 62);
        circuitSlot.setBackgroundTexture(
                new GuiTextureGroup(
                        GuiTextures.SLOT,
                        new ResourceTexture("soggtaddon:textures/ui/steam_int_circuit_overlay.png")));

        return new ModularUI(176, 166, this, player)
                .background(GuiTextures.PRIMITIVE_BACKGROUND)

                // Text
                .widget(new LabelWidget(55, 18, "Large Barrel"))

                // Item Inputs
                .widget(new SlotWidget(importItems.storage, 1, 30, 42, true, true)
                        .setBackgroundTexture(new GuiTextureGroup(
                                GuiTextures.PRIMITIVE_SLOT)))

                .widget(new SlotWidget(importItems.storage, 2, 48, 42, true, true)
                        .setBackgroundTexture(new GuiTextureGroup(
                                GuiTextures.PRIMITIVE_SLOT)))

                // Item Outputs
                .widget(new SlotWidget(exportItems.storage, 0, 110, 42, true, false)
                        .setBackgroundTexture(new GuiTextureGroup(
                                GuiTextures.PRIMITIVE_SLOT)))

                .widget(new SlotWidget(exportItems.storage, 1, 128, 42, true, false)
                        .setBackgroundTexture(new GuiTextureGroup(
                                GuiTextures.PRIMITIVE_SLOT)))

                // Fluid Input
                .widget(new TankWidget(
                        importFluids.getStorages()[0],
                        7,
                        6,
                        18,
                        54,
                        true,
                        true)
                        .setBackground(GuiTextures.FLUID_SLOT))

                // Fluid Output
                .widget(new TankWidget(
                        exportFluids.getStorages()[0],
                        150,
                        6,
                        18,
                        54,
                        true,
                        false)
                        .setBackground(GuiTextures.FLUID_SLOT))

                // Progress Bar
                .widget(new ProgressWidget(
                        recipeLogic::getProgressPercent,
                        78,
                        41,
                        20,
                        20,
                        GuiTextures.PROGRESS_BAR_ARROW))

                // Ghost Circuit Slot
                .widget(circuitSlot)

                // Player Inventory
                .widget(UITemplate.bindPlayerInventory(
                        player.getInventory(),
                        GuiTextures.PRIMITIVE_SLOT,
                        7,
                        84,
                        true));
    }
}
