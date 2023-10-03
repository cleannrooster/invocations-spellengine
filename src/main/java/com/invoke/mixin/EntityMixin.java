package com.invoke.mixin;

import com.invoke.InvokeMod;
import dev.kosmx.playerAnim.mixin.PlayerRendererMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.internals.SpellContainerHelper;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_engine.particle.ParticleHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;
import net.spell_power.api.SpellPower;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static net.spell_engine.internals.SpellHelper.ammoForSpell;
import static net.spell_engine.internals.SpellHelper.impactTargetingMode;

@Mixin(LivingEntity.class)
public class EntityMixin {

    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)
    private void actual(final DamageSource player, float g, final CallbackInfo info) {
        LivingEntity player2 = ((LivingEntity) (Object) this);


        if (player.getAttacker() instanceof PlayerEntity player1 ) {
            ItemStack stack = player1.getMainHandStack();
            if (SpellContainerHelper.containerWithProxy(stack, player1) != null && SpellContainerHelper.containerWithProxy(stack, player1).spell_ids.contains("invoke:arcaneoverdrive")) {
                Predicate<Entity> selectionPredicate = (target2) -> {
                    return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.AREA, TargetHelper.Intent.HARMFUL, player1, target2)
                    );
                };
                Spell spell = SpellRegistry.getSpell(new Identifier(InvokeMod.MODID, "arcaneoverdrive"));

                if(player1 instanceof SpellCasterEntity entity && ammoForSpell(player1,spell,stack).satisfied()&& !entity.getCooldownManager().isCoolingDown(new Identifier(InvokeMod.MODID, "arcaneoverdrive"))) {
                    entity.getCooldownManager().set(new Identifier(InvokeMod.MODID, "arcaneoverdrive"), (int) (20*SpellHelper.getCooldownDuration(player1,spell)));

                    int i = 0;
                    List<Entity> targets = player1.getWorld().getOtherEntities(player1, player1.getBoundingBox().expand(spell.range), selectionPredicate);

                    if(entity.getCurrentSpellId() != null) {
                        LivingEntity living = (LivingEntity) entity;
                        i = (int) (entity.getCurrentCastProgress() * SpellHelper.getCastDuration(living, SpellRegistry.getSpell(entity.getCurrentSpellId())));
                    }
                    SpellHelper.ImpactContext context = new SpellHelper.ImpactContext(1.0F, 1.0F, (Vec3d) null, SpellPower.getSpellPower(spell.school, player1), impactTargetingMode(spell));

                    for (Entity target1 : targets) {
                        SpellHelper.performImpacts(player1.getWorld(), player1, target1, SpellRegistry.getSpell(new Identifier(InvokeMod.MODID, "arcaneoverdrive")), new SpellHelper.ImpactContext());
                    }
                    ParticleHelper.sendBatches(player1, spell.release.particles);
                    SpellHelper.AmmoResult ammoResult = ammoForSpell(player1, spell, stack);
                    if (ammoResult.ammo() != null) {
                        for(int ii = 0; ii < player1.getInventory().size(); ++ii) {
                            ItemStack stack1 = player1.getInventory().getStack(ii);
                            if (stack1.isOf(ammoResult.ammo().getItem())) {
                                stack1.decrement(1);
                                if (stack1.isEmpty()) {
                                    player1.getInventory().removeOne(stack1);
                                }
                                break;
                            }
                        }
                    }

                }
            }
        }
    }
}
