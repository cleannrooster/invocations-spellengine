package com.invoke.mixin;

import com.invoke.interfaces.InvokerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.spell_engine.client.gui.SpellTooltip;
import net.spell_engine.internals.SpellContainerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.invoke.InvokeClient.TOTAL_LIST;

@Mixin(SpellTooltip.class)
public class SpellTooltipMixin {
    @Inject(at = @At("HEAD"), method = "spellKeyPrefix", cancellable = true)
    private static void spellKeyPrefix(Identifier spellId, CallbackInfoReturnable<String> callbackInfoReturnable) {
        String var10000 = spellId.getNamespace();
        if (MinecraftClient.getInstance() != null) {

            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null && player instanceof InvokerEntity entity && SpellContainerHelper.getEquipped(player.getMainHandStack(), player) != null && SpellContainerHelper.getEquipped(player.getMainHandStack(), player).spell_ids != null && SpellContainerHelper.getEquipped(player.getMainHandStack(), player).spell_ids.contains("invoke:runicinvocation")) {
                int x = 0;
                int y = 0;
                int z = 0;
                int[] combination = {z, y, x};
                for (int i = 0; i < combination.length; i++) {
                    if (entity.getInvokeValue()[i] == 1) {
                        combination[0]++;
                    }
                    if (entity.getInvokeValue()[i] == 2) {
                        combination[1]++;
                    }
                    if (entity.getInvokeValue()[i] == 3) {
                        combination[2]++;
                    }
                }
                if (combination[0] > 3) {
                    combination[0] = 3;
                }
                if (combination[1] > 3) {
                    combination[1] = 3;
                }
                if (combination[2] > 3) {
                    combination[2] = 3;
                }

                if (spellId.getPath().equals("runicinvocation")) {

                    callbackInfoReturnable.setReturnValue("spell." + "invoke" + "." + TOTAL_LIST[combination[0]][combination[1]][combination[2]]);
                }
            }
        }
    }
}
