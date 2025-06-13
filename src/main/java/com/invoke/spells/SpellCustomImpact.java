package com.invoke.spells;

import com.google.common.base.Suppliers;
import com.invoke.InvokeMod;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.event.SpellHandlers;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.fx.ParticleHelper;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.casting.SpellCast;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_engine.internals.container.SpellContainerSource;
import net.spell_engine.internals.target.SpellTarget;
import net.spell_engine.utils.AnimationHelper;
import net.spell_engine.utils.SoundHelper;
import net.spell_engine.utils.WorldScheduler;
import net.spell_power.api.SpellPower;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static net.spell_engine.api.spell.event.SpellHandlers.registerCustomDelivery;
import static net.spell_engine.api.spell.event.SpellHandlers.registerCustomImpact;
import static net.spell_engine.internals.SpellHelper.imposeCooldown;

public class SpellCustomImpact {
    public static class  DelayImpact implements SpellHandlers.CustomImpact {
        @Override
        public SpellHandlers.ImpactResult onSpellImpact(RegistryEntry<Spell> registryEntry, SpellPower.Result result, LivingEntity livingEntity, @Nullable Entity entity, SpellHelper.ImpactContext impactContext) {
            Spell.AreaImpact areaImpact = registryEntry.value().area_impact;
            ParticleHelper.sendBatches(entity,areaImpact.particles);
            Vec3d vec3d = entity.getPos();

            ((WorldScheduler)livingEntity.getWorld()).schedule(20,()->{
                ParticleBatch[] batch;
                Spell.Impact[] impacts = new Spell.Impact[1];

                if(registryEntry.value().school.equals(SpellSchools.ARCANE)){
                        impacts[0] = InvocationSpells.createArcaneImpact(registryEntry.value().impacts.get(0).action.damage.spell_power_coefficient, registryEntry.value().impacts.get(0).action.damage.knockback);

                }
                else  if(registryEntry.value().school.equals(SpellSchools.FROST)){
                    impacts[0] = InvocationSpells.createFrostImpact(registryEntry.value().impacts.get(0).action.damage.spell_power_coefficient,registryEntry.value().impacts.get(0).action.damage.knockback);

                }
                else  if(registryEntry.value().school.equals(SpellSchools.FIRE)){
                    impacts[0] = InvocationSpells.createFireImpact(registryEntry.value().impacts.get(0).action.damage.spell_power_coefficient,registryEntry.value().impacts.get(0).action.damage.knockback);

                }

                SpellHelper.performImpacts(livingEntity.getWorld(),livingEntity,entity,entity,registryEntry, List.of(impacts),new SpellHelper.ImpactContext(1.0F,1.0F,vec3d,result, SpellTarget.FocusMode.AREA,0),true);
            });
            return new SpellHandlers.ImpactResult(true,false);
        }
    }
    public static void registerImpacts(){
        registerCustomImpact(Identifier.of(InvokeMod.MODID,"delay_area_impact"),new DelayImpact());


    }
}
