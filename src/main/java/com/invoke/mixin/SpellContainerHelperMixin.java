package com.invoke.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.container.SpellContainer;
import net.spell_engine.api.spell.container.SpellContainerHelper;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.internals.container.SpellContainerSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpellContainerSource.class)

public class SpellContainerHelperMixin {

    @Inject(at = @At("HEAD"), method = "getFirstSourceOfSpell", cancellable = true)
    private static void getFirstSourceOfSpellInvoke(Identifier spellId, PlayerEntity player, CallbackInfoReturnable<SpellContainerSource.SourcedContainer> callbackInfoReturnable) {
        if(spellId.getNamespace().equals("invoke")){
            callbackInfoReturnable.setReturnValue(new SpellContainerSource.SourcedContainer("main_hand",player.getMainHandStack(), SpellContainerSource.activeContainerOf(player)));
        }

    }
}
