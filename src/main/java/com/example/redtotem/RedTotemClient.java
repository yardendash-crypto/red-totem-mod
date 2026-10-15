package com.example.redtotem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class RedTotemClient implements ClientModInitializer {
    // ARGB: 0x55 = about 33% opacity red. Raise it (e.g. 0x80) for a stronger tint.
    private static final int RED_TINT = 0x55FF0000;

    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementAfter(
            VanillaHudElements.MISC_OVERLAYS,
            Identifier.of("redtotem", "warning"),
            (context, tickCounter) -> {
                MinecraftClient mc = MinecraftClient.getInstance();
                PlayerEntity player = mc.player;
                if (player == null || player.isSpectator()) return;

                boolean holdingTotem = player.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING)
                        || player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING);
                if (holdingTotem) return;

                context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), RED_TINT);
            });
    }
}
