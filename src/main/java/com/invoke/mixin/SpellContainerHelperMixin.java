package com.invoke.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.internals.SpellContainerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpellContainerHelper.class)

public class SpellContainerHelperMixin {

    @Inject(at = @At("HEAD"), method = "getFirstSourceOfSpell", cancellable = true)
    private static void getFirstSourceOfSpellInvoke(Identifier spellId, PlayerEntity player, CallbackInfoReturnable<SpellContainerHelper.Source> callbackInfoReturnable) {
        if(spellId.getNamespace().equals("invoke")){
            callbackInfoReturnable.setReturnValue(new SpellContainerHelper.Source(player.getMainHandStack(),SpellContainerHelper.getAvailable(player)));
        }

    }
}
