package com.invoke.mixin;

import com.invoke.InvokeMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpellProjectile.class)
public class SpellProjectileMixin {
    @Inject(at = @At("HEAD"), method = "onBlockHit", cancellable = true)
    protected void onBlockHitFireball(BlockHitResult blockHitResult, CallbackInfo info) {
        SpellProjectile projectile = (SpellProjectile) (Object) this;
        if(projectile.getSpell().equals(SpellRegistry.getSpell(new Identifier(InvokeMod.MODID,"greaterfireball"))) && projectile.getOwner() instanceof LivingEntity living){
            boolean performed = SpellHelper.projectileImpact(living, projectile, null, projectile.getSpellInfo(), projectile.getImpactContext().position(projectile.getPos()));

        }
    }
}
