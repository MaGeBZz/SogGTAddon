package net.sog.core.client.emi;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sog.core.sogcore;

import com.mojang.brigadier.arguments.StringArgumentType;

@Mod.EventBusSubscriber(modid = sogcore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EMIFavoritesCommand {

    @SubscribeEvent
    public static void onRegister(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("emifavorites")
                        .then(Commands.literal("list").executes(ctx -> list(ctx.getSource())))
                        .then(Commands.literal("new")
                                .then(Commands.argument("name", StringArgumentType.word())
                                        .executes(ctx -> create(ctx.getSource(),
                                                StringArgumentType.getString(ctx, "name")))))
                        .then(Commands.literal("switch")
                                .then(Commands.argument("name", StringArgumentType.word())
                                        .executes(ctx -> switchTo(ctx.getSource(),
                                                StringArgumentType.getString(ctx, "name"))))));
    }

    private static int list(CommandSourceStack source) {
        source.sendSuccess(() -> Component.literal("EMI favorite sets:"), false);
        for (String name : EMIFavoriteSets.getSetNames()) {
            boolean active = name.equals(EMIFavoriteSets.getActiveSet());
            source.sendSuccess(() -> Component.literal((active ? " * " : "   ") + name), false);
        }
        return 1;
    }

    private static int create(CommandSourceStack source, String name) {
        if (EMIFavoriteSets.createSet(name)) {
            source.sendSuccess(() -> Component.literal("Created and switched to favorites set '" + name + "'"),
                    false);
        } else {
            source.sendFailure(Component.literal("A favorites set named '" + name + "' already exists"));
        }
        return 1;
    }

    private static int switchTo(CommandSourceStack source, String name) {
        if (!EMIFavoriteSets.getSetNames().contains(name)) {
            source.sendFailure(Component.literal("No favorites set named '" + name + "'"));
            return 0;
        }
        EMIFavoriteSets.switchTo(name);
        source.sendSuccess(() -> Component.literal("Switched to favorites set '" + name + "'"), false);
        return 1;
    }
}
