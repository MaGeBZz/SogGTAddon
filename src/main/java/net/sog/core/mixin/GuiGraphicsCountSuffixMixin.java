package net.sog.core.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.FormattedCharSequence;
import net.sog.core.common.utils.CompactCount;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsCountSuffixMixin {
    // BIG Thanks to Phoenix for this!

    @Inject(method = "drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I",
            at = @At("HEAD"),
            cancellable = true)
    private void sog$compactString(Font font, String text, int x, int y, int color, boolean dropShadow,
                                   CallbackInfoReturnable<Integer> cir) {
        String compacted = CompactCount.compactIfNumeric(text);
        if (!compacted.equals(text)) {
            cir.setReturnValue(this.sog$renderScaled(font, compacted, x, y, color, dropShadow));
        }
    }

    @Inject(method = "drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I",
            at = @At("HEAD"),
            cancellable = true)
    private void sog$compactSequence(Font font, FormattedCharSequence text, int x, int y, int color,
                                     boolean dropShadow, CallbackInfoReturnable<Integer> cir) {
        StringBuilder sb = new StringBuilder();
        text.accept((index, style, codePoint) -> {
            sb.appendCodePoint(codePoint);
            return true;
        });

        String original = sb.toString();
        String compacted = CompactCount.compactIfNumeric(original);

        if (!compacted.equals(original)) {
            cir.setReturnValue(this.sog$renderScaled(font, compacted, x, y, color, dropShadow));
        }
    }

    @Unique
    private int sog$renderScaled(Font font, String text, int x, int y, int color, boolean shadow) {
        GuiGraphics graphics = (GuiGraphics) (Object) this;
        float scale = 0.70f;

        float originalWidth = font.width(text);
        float scaledWidth = originalWidth * scale;

        float xOffset = originalWidth - scaledWidth;

        graphics.pose().pushPose();

        graphics.pose().translate(x + xOffset, y + 2, 0);

        graphics.pose().scale(scale, scale, 1.0f);

        int result = graphics.drawString(font, text, 0, 0, color, shadow);

        graphics.pose().popPose();
        return result;
    }
}
