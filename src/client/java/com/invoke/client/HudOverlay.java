package com.invoke.client;

import com.invoke.InvokeMod;
import com.invoke.interfaces.InvokerEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import net.spell_engine.internals.SpellContainerHelper;

public class HudOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }
        if (client.player != null && client.player instanceof InvokerEntity entity && SpellContainerHelper.containerWithProxy(client.player.getMainHandStack(), client.player) != null && SpellContainerHelper.containerWithProxy(client.player.getMainHandStack(), client.player).spell_ids != null && SpellContainerHelper.containerWithProxy(client.player.getMainHandStack(), client.player).spell_ids.contains("invoke:runicinvocation")) {


            if (entity.getInvokeValue()[2] == 1) {

                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/rah2.png"), x - 8 - 8 - 8 - 16, y / 2 - 8, 0, 0, 16, 16,
                        16, 16);
            }
            if (entity.getInvokeValue()[2] == 2) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/heo2.png"), x - 8 - 8 - 8 - 16, y / 2 - 8, 0, 0, 16, 16,
                        16, 16);
            }
            if (entity.getInvokeValue()[2] == 3) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/gon2.png"), x - 8 - 8 - 8 - 16, y / 2 - 8, 0, 0, 16, 16,
                        16, 16);
            }
            if (entity.getInvokeValue()[0] == 1) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/rah2.png"), x + 8 + 16, y / 2 - 8, 0, 0, 16, 16,
                        16, 16);
            }
            if (entity.getInvokeValue()[0] == 2) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/heo2.png"), x + 8 + 16, y / 2 - 8, 0, 0, 16, 16,
                        16, 16);
            }
            if (entity.getInvokeValue()[0] == 3) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/gon2.png"), x + 8 + 16, y / 2 - 8, 0, 0, 16, 16,
                        16, 16);
            }

            if (entity.getInvokeValue()[1] == 1) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/rah2.png"), x - 8, y / 2 - 8 - 8 - 16 - 8, 0, 0, 16, 16,
                        16, 16);
            }
            if (entity.getInvokeValue()[1] == 2) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/heo2.png"), x - 8, y / 2 - 8 - 8 - 16 - 8, 0, 0, 16, 16,
                        16, 16);
            }
            if (entity.getInvokeValue()[1] == 3) {
                drawContext.drawTexture(new Identifier(InvokeMod.MODID, "textures/spell/gon2.png"), x - 8, y / 2 - 8 - 8 - 16 - 8, 0, 0, 16, 16,
                        16, 16);
            }
        }
    }
}
