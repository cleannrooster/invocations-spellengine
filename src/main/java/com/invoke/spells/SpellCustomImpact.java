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
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.fx.ParticleHelper;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.fx.SpellEngineSounds;
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
import static net.spell_power.api.SpellSchools.FROST;

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
                        impacts[0] = createArcaneImpact(registryEntry.value().impacts.get(0).action.damage.spell_power_coefficient, registryEntry.value().impacts.get(0).action.damage.knockback);

                }
                else  if(registryEntry.value().school.equals(SpellSchools.FROST)){
                    impacts[0] = createFrostImpact(registryEntry.value().impacts.get(0).action.damage.spell_power_coefficient,registryEntry.value().impacts.get(0).action.damage.knockback);

                }
                else  if(registryEntry.value().school.equals(SpellSchools.FIRE)){
                    impacts[0] = createFireImpact(registryEntry.value().impacts.get(0).action.damage.spell_power_coefficient,registryEntry.value().impacts.get(0).action.damage.knockback);

                }

                SpellHelper.performImpacts(livingEntity.getWorld(),livingEntity,entity,entity,registryEntry, List.of(impacts),new SpellHelper.ImpactContext(1.0F,1.0F,vec3d,result, SpellTarget.FocusMode.AREA,0));
            });
            return new SpellHandlers.ImpactResult(true,false);
        }
    }
    private static Spell.Impact createImpact(Spell.Impact.Action.Type type, float coeff, float knockback) {
        var impact = new Spell.Impact();
        impact.action = new Spell.Impact.Action();
        impact.action.type = type;
        if(type == Spell.Impact.Action.Type.DAMAGE) {
            impact.action.damage = new Spell.Impact.Action.Damage();
            impact.action.damage.knockback = knockback;
            impact.action.damage.spell_power_coefficient = coeff;
        }
        return impact;
    }
    public static Spell.Impact createFrostImpact(float coeff, float knockback) {
        var impact = createImpact(Spell.Impact.Action.Type.DAMAGE,coeff, knockback);
        impact.school = FROST;
        ParticleBatch[] hitParticles = new ParticleBatch[]{
                new ParticleBatch("spell_engine:magic_frost_impact_burst", ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER, ParticleBatch.Rotation.LOOK, 20, 0.2f, 0.7F, 360)
        };
        impact.particles = hitParticles;
        Spell.TargetCondition targetCondition = new Spell.TargetCondition();
        targetCondition.entity_type = "#minecraft:freeze_immune_entity_types";
        Spell.Impact.TargetModifier targetModifier = new Spell.Impact.TargetModifier();

        targetModifier.conditions = List.of(targetCondition);
        targetModifier.modifier = new Spell.Impact.Modifier();
        targetModifier.modifier.power_multiplier = -0.3F;
        Spell.Impact.TargetModifier targetModifier2 = new Spell.Impact.TargetModifier();
        targetCondition.entity_type = "#minecraft:freeze_hurts_extra_types";
        targetModifier2.conditions = List.of(targetCondition);
        targetModifier2.modifier = new Spell.Impact.Modifier();
        targetModifier2.modifier.power_multiplier = 0.3F;
        impact.target_modifiers = List.of(targetModifier,targetModifier2);
        var sound = new Sound(SpellEngineSounds.GENERIC_FROST_IMPACT.id());
        impact.sound = sound;
        return impact;
    }
    public static Spell.Impact createArcaneImpact(float coeff, float knockback) {
        var impact = createImpact(Spell.Impact.Action.Type.DAMAGE,coeff, knockback);
        impact.school = SpellSchools.ARCANE;
        ParticleBatch[] hitParticles = new ParticleBatch[]{
                new ParticleBatch("spell_engine:magic_arcane_spark_float", ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,20,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.MagicParticles.get(SpellEngineParticles.MagicParticles.Shape.ARCANE,  SpellEngineParticles.MagicParticles.Motion.FLOAT).id().toString()
                        , ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,20,0.05f,0.1F,360)

        };
        impact.particles = hitParticles;
        var sound = new Sound(SpellEngineSounds.GENERIC_ARCANE_RELEASE.id());
        impact.sound = sound;
        return impact;
    }
    public static Spell.Impact createFireImpact(float coeff, float knockback) {
        var impact = createImpact(Spell.Impact.Action.Type.DAMAGE,coeff, knockback);
        impact.school = SpellSchools.FIRE;
        ParticleBatch[] hitParticles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.flame_spark.id().toString(), ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER, ParticleBatch.Rotation.LOOK, 20, 0.2f, 0.7F, 360),
                new ParticleBatch("minecraft:smoke", ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER, ParticleBatch.Rotation.LOOK, 20, 0.2f, 0.7F, 360)

        };
        Spell.TargetCondition targetCondition = new Spell.TargetCondition();
        targetCondition.entity_type = "#minecraft:freeze_immune_entity_types";
        Spell.Impact.TargetModifier targetModifier = new Spell.Impact.TargetModifier();

        targetModifier.conditions = List.of(targetCondition);
        targetModifier.modifier = new Spell.Impact.Modifier();
        targetModifier.modifier.critical_chance_bonus = 0.3F;

        impact.target_modifiers = List.of(targetModifier);
        impact.particles = hitParticles;

        var sound = new Sound("minecraft:entity.player.hurt_on_fire");
        impact.sound = sound;
        return impact;
    }
    public static void registerImpacts(){
        registerCustomImpact(Identifier.of(InvokeMod.MODID,"delay_area_impact"),new DelayImpact());


    }
}
