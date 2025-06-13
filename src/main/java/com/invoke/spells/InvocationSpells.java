package com.invoke.spells;

import com.invoke.InvokeMod;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.ExternalSpellSchools;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.event.SpellEvents;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.api.util.TriState;
import net.spell_engine.client.gui.SpellTooltip;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.fx.SpellEngineSounds;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.target.SpellTarget;
import net.spell_power.api.SpellPower;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.List;

import static net.spell_power.api.SpellSchools.*;


public class InvocationSpells {
   public static ParticleBatch PARTICLES_BLUE_FEET = new ParticleBatch(SpellEngineParticles.area_effect_658.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.FEET,null,0,0,1,0,0,0,0,0,false, FROST.color, 2,true,1F);
   public static ParticleBatch PARTICLES_RED_FEET = new ParticleBatch(SpellEngineParticles.area_effect_480.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.FEET,null,0,0,1,0,0,0,0,0,false, 		4284889343L,2,true,1F);
   public static ParticleBatch PARTICLES_PURPLE_FEET = new ParticleBatch(SpellEngineParticles.area_effect_293.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.FEET,null,0,0,1,0,0,0,0,0,false, 4284940287L, 2,true,1F);

    public static ParticleBatch PARTICLES_BLUEROTA = new ParticleBatch(SpellEngineParticles.area_effect_658.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.FEET, ParticleBatch.Rotation.LOOK,0,0,1,0,0,90,0,0,false, FROST.color, 2,true,1F);
    public static ParticleBatch PARTICLES_REDROTA = new ParticleBatch(SpellEngineParticles.area_effect_480.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.FEET,ParticleBatch.Rotation.LOOK,0,0,1,0,0,90,0,0,false, 		4284889343L,2,true,1F);
    public static ParticleBatch PARTICLES_PURPLEROTA = new ParticleBatch(SpellEngineParticles.area_effect_293.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.FEET,ParticleBatch.Rotation.LOOK,0,0,1,0,0,90,0,0,false, 4284940287L, 2,true,1F);

    public static ParticleBatch glyph(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.2F,0.2F,angle,0,10,false, FROST.color, scale,follow,1F);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.2F,0.2F,angle,0,10,false, 4284889343L, scale,follow,1F);
        }
            return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.2F,0.2F,angle,0,10,false, 4284940287L, scale,follow,1F);

    }
    public static ParticleBatch glyph_outer(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.02F,0.02F,angle,0,50,false, FROST.color, scale,follow,1F);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.02F,0.02F,angle,0,50,false, 4284889343L, scale,follow,1F);
        }
        return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.02F,0.02F,angle,0,50,false, 4284940287L, scale,follow,1F);

    }
    public static ParticleBatch glyph_center(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.02F,0.02F,angle,0,75,false, FROST.color, scale,follow,1F);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(SpellEngineParticles.flame_spark.id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.02F,0.02F,angle,0,75,false, 4284889343L, scale,follow,1F);
        }
        return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,10,0.02F,0.02F,angle,0,75,false, 4284940287L, scale,follow,1F);

    }
    public static ParticleBatch glyph_area(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(SpellEngineParticles.area_effect_658.id().toString(), ParticleBatch.Shape.WIDE_PIPE, origin, rotate,0,0,1,0,0,angle,0,1,false, FROST.color, scale,follow,1F);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(SpellEngineParticles.area_effect_480.id().toString(), ParticleBatch.Shape.WIDE_PIPE, origin, rotate,0,0,1,0,0,angle,0,1,false, 4284889343L, scale,follow,1F);
        }
        return  new ParticleBatch(SpellEngineParticles.area_effect_293.id().toString(), ParticleBatch.Shape.WIDE_PIPE, origin, rotate,0,0,1,0,0,angle,0,1,false, 4284940287L, scale,follow,1F);

    }
    public static ParticleBatch glyph_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.2F,0.2F,angle,0,10,false, FROST.color, scale,follow,1F);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.2F,0.2F,angle,0,10,false, 4284889343L, scale,follow,1F);
        }
        return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.2F,0.2F,angle,0,10,false, 4284940287L, scale,follow,1F);

    }
    public static ParticleBatch glyph_outer_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.02F,0.02F,angle,0,50,false, FROST.color, scale,follow,1F);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.02F,0.02F,angle,0,50,false, 4284889343L, scale,follow,1F);
        }
        return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.02F,0.02F,angle,0,50,false, 4284940287L, scale,follow,1F);

    }
    public static ParticleBatch glyph_center_release(float scale, float angle, ParticleBatch.Origin origin, ParticleBatch.Rotation rotate, SpellSchool school, boolean follow){
        if(school.equals(FROST)){
            return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.02F,0.02F,angle,0,75,false, FROST.color, scale,follow,1F);
        }
        if(school.equals(FIRE)){
            return  new ParticleBatch(SpellEngineParticles.flame_spark.id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.02F,0.02F,angle,0,75,false, 4284889343L, scale,follow,1F);
        }
        return  new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CIRCLE, origin, rotate,45,45,100,0.02F,0.02F,angle,0,75,false, 4284940287L, scale,follow,1F);

    }

    public record Entry(Identifier id, Spell spell, String title, String description,
                        @Nullable SpellTooltip.DescriptionMutator mutator) {
    }

    public static final List<Entry> entries = new ArrayList<>();

    private static Entry add(Entry entry) {
        entries.add(entry);

        return entry;
    }
    private static Entry addIfInstalled(Entry entry, String modid) {
        if(FabricLoader.getInstance().isModLoaded(modid)) {
            entries.add(entry);
        }

        return entry;
    }
    private static ParticleBatch arcaneCastingParticles() {
        return new ParticleBatch(
                SpellEngineParticles.getMagicParticleVariant(
                        SpellEngineParticles.ARCANE,
                        SpellEngineParticles.MagicParticleFamily.Shape.SPELL,
                        SpellEngineParticles.MagicParticleFamily.Motion.ASCEND
                ).id().toString(),
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                1, 0.05F, 0.1F);
    }
    private static ParticleBatch fireCastingParticles() {
        return new ParticleBatch(
                SpellEngineParticles.flame.id().toString(),
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                1, 0.05F, 0.1F);
    }
    private static ParticleBatch frostCastingParticles() {
        return new ParticleBatch(
                SpellEngineParticles.getMagicParticleVariant(
                        SpellEngineParticles.FROST,
                        SpellEngineParticles.MagicParticleFamily.Shape.SPELL,
                        SpellEngineParticles.MagicParticleFamily.Motion.ASCEND
                ).id().toString(),
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                1, 0.05F, 0.1F);
    }
    public static Spell activeSpellBase() {
        var spell = new Spell();
        spell.range = 0;
        spell.tier = 7;
        spell.learn = new Spell.Learn();
        spell.type = Spell.Type.ACTIVE;
        spell.active = new Spell.Active();
        spell.active.scroll = new Spell.Active.Scroll();


        return spell;
    }

    private static Spell passiveSpellBase() {
        var spell = new Spell();
        spell.range = 0;
        spell.tier = 7;

        spell.type = Spell.Type.PASSIVE;
        spell.passive = new Spell.Passive();
        spell.tooltip = new Spell.Tooltip();
        spell.tooltip.name = new Spell.Tooltip.LineOptions(true, true);
        spell.tooltip.description.color = Formatting.DARK_GREEN.asString();
        spell.tooltip.description.show_in_compact = false;

        return spell;
    }
    private static Spell.Delivery createDelivery(Spell.Delivery.Type type) {
        var delivery = new Spell.Delivery();
        delivery.type = type;
        return delivery;

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
    public static Spell.Impact createArcaneImpact(float coeff, float knockback) {
        var impact = createImpact(Spell.Impact.Action.Type.DAMAGE,coeff, knockback);
        impact.school = SpellSchools.ARCANE;
        ParticleBatch[] hitParticles = new ParticleBatch[]{
                new ParticleBatch("spell_engine:magic_arcane_spark_float", ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,20,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString()
                        , ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,20,0.05f,0.1F,360)

        };
        impact.particles = hitParticles;
        var sound = new Sound(SpellEngineSounds.GENERIC_ARCANE_RELEASE.id());
        impact.sound = sound;
        return impact;
    }

    public static Spell.Impact createLightningImpact(float coeff, float knockback) {
        var impact = createImpact(Spell.Impact.Action.Type.DAMAGE,coeff, knockback);
        impact.school = SpellSchools.LIGHTNING;
        ParticleBatch[] hitParticles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.WHITE, SpellEngineParticles.MagicParticleFamily.Shape.IMPACT, SpellEngineParticles.MagicParticleFamily.Motion.BURST).id().toString(), ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER, ParticleBatch.Rotation.LOOK, 20, 0.2f, 0.7F, 360)
        };
        impact.particles = hitParticles;
        var sound = new Sound(SpellEngineSounds.GENERIC_LIGHTNING_RELEASE.id());
        impact.sound = sound;
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
    private static Spell.Impact createHealingImpact(float coeff, float knockback) {
        var impact = createImpact(Spell.Impact.Action.Type.HEAL,coeff, knockback);
        impact.school = SpellSchools.HEALING;

        impact.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.HOLY, SpellEngineParticles.MagicParticleFamily.Shape.IMPACT, SpellEngineParticles.MagicParticleFamily.Motion.ASCEND).id().toString(), ParticleBatch.Shape.PIPE, ParticleBatch.Origin.FEET, null, 20, 0.2f, 0.7F, 360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.HOLY, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.PIPE, ParticleBatch.Origin.FEET, null, 20, 0.1f, 0.35F, 360),

        };
        impact.sound = new Sound(SpellEngineSounds.GENERIC_HEALING_IMPACT_1.id());
        return impact;
    }
    private static Spell.Impact createPhysicalimpact(float coeff, float knockback) {
        var impact = createImpact(Spell.Impact.Action.Type.DAMAGE,coeff, knockback);
        impact.school = ExternalSpellSchools.PHYSICAL_MELEE;

        return impact;
    }
    private static Spell.Active.Cast createCast(int channelticks, float duration, String sound, String animation, @Nullable SpellSchool school) {
        var cast = new Spell.Active.Cast();
        cast = new Spell.Active.Cast();
        cast.animation = animation;
        cast.sound = new Sound(sound);
        cast.channel_ticks = channelticks;
        cast.duration = duration;
        if(school != null) {
            if (school.equals(SpellSchools.FIRE)) {
                cast.particles = new ParticleBatch[]{fireCastingParticles()};
            }
            if (school.equals(FROST)) {
                cast.particles = new ParticleBatch[]{frostCastingParticles()};
            }
            if (school.equals(SpellSchools.ARCANE)) {
                cast.particles = new ParticleBatch[]{arcaneCastingParticles()};
            }
        }
        return cast;
    }
    private static void configureCooldown(Spell spell, float duration, boolean proportional,@Nullable String id) {
        if (spell.cost == null) {
            spell.cost = new Spell.Cost();
        }
        if (spell.cost.cooldown == null) {
            spell.cost.cooldown = new Spell.Cost.Cooldown();
        }
        if(id != null){
            spell.cost.item = new Spell.Cost.Item();
            spell.cost.item.id = id;
            spell.cost.item.amount = 1;

        }
        if(proportional){
            spell.cost.cooldown.proportional = true;
        }
        spell.cost.cooldown.duration = duration;
    }



    private static Spell projectileBase(SpellSchool school, Identifier projIdentifier, float velocity,float knockback) {

        var spell = activeSpellBase();
        spell.school = school;


        var delivery = createDelivery(Spell.Delivery.Type.PROJECTILE);
        delivery.projectile = new Spell.Delivery.ShootProjectile();

        delivery.projectile.launch_properties.velocity = velocity;

        delivery.projectile.projectile = new Spell.ProjectileData();

        delivery.projectile.projectile.client_data = new Spell.ProjectileData.Client();

        var model = new Spell.ProjectileModel();
        model.model_id = String.valueOf(projIdentifier);
        delivery.projectile.projectile.client_data.model = model;
        spell.deliver = delivery;
        Spell.Impact[] impact = new Spell.Impact[1];
        impact[0] = createImpact(Spell.Impact.Action.Type.DAMAGE,1.8F,knockback);
        spell.impacts = List.of(impact[0]);

        return spell;
    }
    private static Spell meteor_base(SpellSchool school, Identifier projIdentifier, float velocity,float knockback) {

        var spell = activeSpellBase();
        spell.school = school;


        var delivery = createDelivery(Spell.Delivery.Type.METEOR);
        delivery.meteor = new Spell.Delivery.Meteor();
        delivery.meteor.launch_properties.velocity = velocity;

        delivery.meteor.projectile = new Spell.ProjectileData();

        delivery.meteor.projectile.client_data = new Spell.ProjectileData.Client();

        var model = new Spell.ProjectileModel();
        model.model_id = String.valueOf(projIdentifier);
        delivery.meteor.projectile.client_data.model = model;
        spell.deliver = delivery;
        Spell.Impact[] impact = new Spell.Impact[1];
        impact[0] = createImpact(Spell.Impact.Action.Type.DAMAGE,1.8F,knockback);
        spell.impacts = List.of(impact[0]);

        return spell;
    }
    private static Entry lance_of_heorot = add(lance_of_heorot());

    private static Entry lance_of_heorot() {
        var spell = activeSpellBase();
        spell.school = FROST;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.target.area.angle_degrees = 10;

        var id = Identifier.of(InvokeMod.MODID, "lance_of_heorot");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Lance of Eos";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 32;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_arcane_casting","invoke:one_handed_projectile_charge",SpellSchools.ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createArcaneImpact(1.0F,1F);
        impacts[1] = createFrostImpact(1.0F,1);
        spell.release = new Spell.Release();

        spell.release.sound = new Sound("wizards:arcane_missile_release");
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 400, 0.1f, 20F, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 400, 0.1f, 20F, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 2, 4, 20),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 1, 2, 40),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 400, 0.1f, 20F, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 400, 0.1f, 20F, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 2, 4, 20),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 1, 2, 40)

        };
        spell.impacts = List.of(impacts);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry heo = add(heo());

    private static Entry heo() {
        var spell = activeSpellBase();
        spell.school = FROST;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.target.area.angle_degrees = 10;

        var id = Identifier.of(InvokeMod.MODID, "heo");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Lance of Eos";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;
        spell.learn = new Spell.Learn();
        spell.tier =2;
        spell.range = 16;
        spell.active.cast = createCast(0,0.6F,"spell_engine:generic_frost_casting","invoke:one_handed_projectile_charge", FROST);
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFrostImpact(0.6F,1);
        impacts[1] = new Spell.Impact();
        impacts[1].action =new  Spell.Impact.Action();
        impacts[1].action.apply_to_caster = true;

        impacts[1].action.type = Spell.Impact.Action.Type.STATUS_EFFECT;
        impacts[1].action.status_effect = new Spell.Impact.Action.StatusEffect();
        impacts[1].action.status_effect.effect_id = "spell_power:frost";
        impacts[1].action.status_effect.duration = 6;
        impacts[1].action.status_effect.amplifier = 2;
        impacts[1].action.status_effect.apply_mode = Spell.Impact.Action.StatusEffect.ApplyMode.ADD;
        spell.release = new Spell.Release();

        spell.release.sound = new Sound("spell_engine:generic_frost_release");
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 0.1f, 5F, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 0.1f, 5, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 50, 1, 2, 20),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 50, 0.5F, 1, 40)

        };
        spell.impacts = List.of(impacts);
        configureCooldown(spell, 0F, false, "runes:frost_stone");

        return new Entry(id, spell, title, description, null);

    }
    private static Entry lance_of_eos = add(lance_of_eos());

    private static Entry lance_of_eos() {
        var spell = activeSpellBase();
        spell.school = SpellSchools.ARCANE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.target.area.angle_degrees = 10;

        var id = Identifier.of(InvokeMod.MODID, "lance_of_eos");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Lance of Eos";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 32;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_arcane_casting","invoke:one_handed_projectile_charge",SpellSchools.ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createArcaneImpact(1.6F,0F);

        spell.release = new Spell.Release();

        spell.release.sound = new Sound("wizards:arcane_missile_release");
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 400, 0.1f, 20F, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 400, 0.1f, 20F, 5),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 2, 4, 20),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 1, 2, 40)

        };
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry supernova = add(supernova());

    private static Entry supernova() {
        var spell = meteor_base(ARCANE,Identifier.of("wizards:projectile/fire_blast"),1,1);
        spell.school = ARCANE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.required = true;
        spell.target.aim.sticky = true;
        spell.deliver.meteor.launch_height = 0;
        spell.deliver.meteor.projectile.client_data.model.scale = 0;
        var id = Identifier.of(InvokeMod.MODID, "supernova");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 32;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_projectile_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(1.0F,2F);
        impacts[1] = createArcaneImpact(1.0F,2F);

        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 8;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),
                glyph_area(8,0, ParticleBatch.Origin.FEET,null,ARCANE,false),
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, .8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0)};
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        spell.impacts = List.of(impacts[0],impacts[1]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry mass_combustion = add(mass_combustion());

    private static Entry mass_combustion() {
        var spell = meteor_base(FIRE,Identifier.of("wizards:projectile/fire_blast"),1,1);
        spell.school = FIRE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.deliver.meteor.launch_height = 0;
        spell.deliver.meteor.projectile.client_data.model.scale = 0;
        var id = Identifier.of(InvokeMod.MODID, "mass_combustion");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 16;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_projectile_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(1F,2F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),

                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.2F, .3F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.2F, 0.3F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.2F, 0.3F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.1F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.1F, 0.1F, 0)};
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry combustion = add(combustion());

    private static Entry combustion() {
        var spell = meteor_base(FIRE,Identifier.of("wizards:projectile/fire_blast"),1,1);
        spell.school = FIRE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.required = true;
        spell.target.aim.sticky = true;
        spell.deliver.meteor.launch_height = 0;
        spell.deliver.meteor.projectile.client_data.model.scale = 0;
        var id = Identifier.of(InvokeMod.MODID, "combustion");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 32;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_projectile_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(1.8F,2F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 8;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),

                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, .8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0)};
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry greater_fireball = add(greater_fireball());

    private static Entry greater_fireball() {
        var spell = activeSpellBase();
        spell.school = FIRE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.required = false;
        spell.target.aim.sticky = false;

        var id = Identifier.of(InvokeMod.MODID, "greater_fireball");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.PROJECTILE;

        var projectile = projectileBase(FIRE, Identifier.of("wizards:projectile/fire_blast"),3,2).deliver.projectile;
        projectile.projectile.homing_angle = 30;
        projectile.projectile.divergence = 0;
        projectile.direct_towards_target = true;
        projectile.projectile.homing_after_relative_distance = 0.4F;
        projectile.projectile.homing_after_absolute_distance = 4;
        projectile.projectile.client_data.model.scale = 3;
        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch("minecraft:smoke", ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,2,0.05f,0.1F,360)

        };
        spell.deliver.projectile = projectile;
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 64;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_projectile_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(1.8F,2F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 8;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),

                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, .8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                        new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0)};
                        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 2, 4, 20),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 1, 2, 40),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 0.5F, 1, 75)


        };
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry self_immolate = add(self_immolate());

    private static Entry self_immolate() {
        var spell = activeSpellBase();
        spell.school = FIRE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();

        var id = Identifier.of(InvokeMod.MODID, "self_immolate");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 8;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_area_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.FEET, null,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(2.0F,2F);

        spell.release = new Spell.Release();

        spell.release.sound = new Sound("entity.generic.explode");
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),

                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, .8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0)};

        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }

    private static Entry frost_fangs = add(frost_fangs());

    private static Entry frost_fangs() {
        var spell = projectileBase(FROST, Identifier.of("wizards:projectile/arcane_missile"),1,1);
        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.sticky = true;
        spell.target.aim.required = true;

        spell.school = FROST;


        var id = Identifier.of(InvokeMod.MODID, "frost_fangs");
        var description = "Unleash many arcane shards in every direction for the next 4 seconds.";
        var title = "Panetti's Wildshards";

        spell.deliver.projectile.launch_properties.extra_launch_count = 8+7;
        spell.deliver.projectile.launch_properties.extra_launch_delay = 2;
        spell.deliver.projectile.projectile.perks = new Spell.ProjectileData.Perks();
        spell.deliver.projectile.projectile.perks.bounce = 4;
        spell.deliver.projectile.projectile.homing_angle = 30;
        spell.deliver.projectile.projectile.homing_after_absolute_distance = 4;
        spell.deliver.projectile.projectile.homing_after_relative_distance = 0.2F;
        spell.deliver.projectile.projectile.client_data.model.scale = 0;
        spell.deliver.projectile.direction_offsets = new Spell.Delivery.ShootProjectile.DirectionOffset[]{
                new Spell.Delivery.ShootProjectile.DirectionOffset(-120,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(-120,-60),
                new Spell.Delivery.ShootProjectile.DirectionOffset(120,-60),
                new Spell.Delivery.ShootProjectile.DirectionOffset(120,0)};

        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.snowflake.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360)

        };
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;
        spell.deliver.projectile.projectile.divergence = 0;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 128;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_arcane_casting","invoke:one_handed_area_charge", ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createArcaneImpact(0.2F,0F);
        impacts[1] = createFrostImpact(0.2F,0F);

        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};

        spell.impacts = List.of(impacts);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry magic_missile = add(magic_missile());

    private static Entry magic_missile() {
        var spell = projectileBase(ARCANE, Identifier.of("wizards:projectile/arcane_missile"),1,1);
        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.sticky = true;
        spell.target.aim.required = true;

        spell.school = ARCANE;


        var id = Identifier.of(InvokeMod.MODID, "magic_missile");
        var description = "Unleash many arcane shards in every direction for the next 4 seconds.";
        var title = "Panetti's Wildshards";

        spell.deliver.projectile.launch_properties.extra_launch_count = 7;
        spell.deliver.projectile.launch_properties.extra_launch_delay = 2;
        spell.deliver.projectile.projectile.perks = new Spell.ProjectileData.Perks();
        spell.deliver.projectile.projectile.perks.bounce = 4;
        spell.deliver.projectile.projectile.homing_angle = 30;
        spell.deliver.projectile.projectile.homing_after_absolute_distance = 4;
        spell.deliver.projectile.projectile.homing_after_relative_distance = 0.2F;

        spell.deliver.projectile.direction_offsets = new Spell.Delivery.ShootProjectile.DirectionOffset[]{
                new Spell.Delivery.ShootProjectile.DirectionOffset(-120,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(-120,-60),
                new Spell.Delivery.ShootProjectile.DirectionOffset(120,-60),
                new Spell.Delivery.ShootProjectile.DirectionOffset(120,0)};

        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360)

        };
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;
        spell.deliver.projectile.projectile.divergence = 0;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 128;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_arcane_casting","invoke:one_handed_area_charge", ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createArcaneImpact(0.6F,0F);

        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};

        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry wildshards = add(wildshards());

    private static Entry wildshards() {
        var spell = projectileBase(ARCANE, Identifier.of("wizards:projectile/arcane_missile"),1,1);
        spell.target = new Spell.Target();
        spell.target.aim = new Spell.Target.Aim();
        spell.target.type = Spell.Target.Type.AIM;

        spell.school = ARCANE;


        var id = Identifier.of(InvokeMod.MODID, "wildshards");
        var description = "Unleash many arcane shards in every direction for the next 4 seconds.";
        var title = "Panetti's Wildshards";

        spell.deliver.projectile.launch_properties.extra_launch_count = 8*4-1;
        spell.deliver.projectile.launch_properties.extra_launch_delay = 2;
        spell.deliver.projectile.projectile.perks = new Spell.ProjectileData.Perks();
        spell.deliver.projectile.projectile.perks.ricochet = 4;
        spell.deliver.projectile.projectile.perks.ricochet_range = 8;
        spell.deliver.projectile.projectile.perks.bounce = 4;

        spell.deliver.projectile.direction_offsets = new Spell.Delivery.ShootProjectile.DirectionOffset[]{
                new Spell.Delivery.ShootProjectile.DirectionOffset(0,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(45,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(90,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(135,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(180,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(225,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(270,0),
                new Spell.Delivery.ShootProjectile.DirectionOffset(315,0)};

        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360)

        };
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;
        spell.deliver.projectile.projectile.divergence = 0;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_arcane_casting","invoke:one_handed_area_charge", ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createArcaneImpact(0.6F,0F);

        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 2, 4, 20),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 1, 2, 40),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 0.5F, 1, 75)


        };
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry replicating = add(replicating());

    private static Entry replicating() {
        var spell = projectileBase(ARCANE, Identifier.of("wizards:projectile/arcane_missile"),1,1);
        spell.target = new Spell.Target();
        spell.target.aim = new Spell.Target.Aim();
        spell.target.type = Spell.Target.Type.AIM;

        spell.school = ARCANE;


        var id = Identifier.of(InvokeMod.MODID, "replicating_missile");
        var description = "Shoot a chaining volatile missile that replicates wildly on hit, dealing {damage} arcane damage.";
        var title = "Panetti's Replicating Missile";


        spell.deliver.projectile.projectile.perks = new Spell.ProjectileData.Perks();
        spell.deliver.projectile.projectile.perks.ricochet = 4;
        spell.deliver.projectile.projectile.perks.ricochet_range = 8;
        spell.deliver.projectile.projectile.perks.bounce = 4;
        spell.deliver.projectile.projectile.perks.chain_reaction_size = 4;

        spell.deliver.projectile.projectile.client_data.model.scale = 2;



        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 64;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_arcane_casting","invoke:one_handed_area_charge", ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createArcaneImpact(0.6F,0F);

        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_ARCANE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 2, 4, 20),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 1, 2, 40),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 100, 0.5F, 1, 75)


        };
        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360)

        };
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;

        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry comet = add(comet());

    private static Entry comet() {
        var spell = meteor_base(FROST, Identifier.of("wizards:projectile/frost_shard"),2,1);
        spell.target = new Spell.Target();
        spell.target.aim = new Spell.Target.Aim();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim.sticky = true;
        spell.school = FROST;

        spell.deliver.meteor.projectile.client_data.model.scale = 3;
        spell.deliver.meteor.projectile.hitbox = new Spell.ProjectileData.HitBox(3,3);
        var id = Identifier.of(InvokeMod.MODID, "comet");
        var description = "Barrage the target location with ice shards for 4 seconds, dealing {damage} frost damage per shard.";
        var title = "Janus' Ice Storm";



        spell.deliver.meteor.projectile.divergence = 15;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_frost_casting","invoke:one_handed_area_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFrostImpact(0.4F,0F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 8;
        spell.area_impact.sound  = new Sound("wizards:frost_shard_impact");
        spell.area_impact.particles  = new ParticleBatch[]{

                new ParticleBatch(SpellEngineParticles.frost_shard.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, .8F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.7F, 0.8F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0)};


        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry trailblaze = add(trailblaze());

    private static Entry trailblaze() {
        var spell = meteor_base(FIRE, Identifier.of("wizards:projectile/fire_blast"),2,1);
        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.CASTER;


        var id = Identifier.of(InvokeMod.MODID, "trailblaze");
        var description = "Barrage the target location with ice shards for 4 seconds, dealing {damage} frost damage per shard.";
        var title = "Janus' Ice Storm";

        spell.deliver.meteor.launch_properties.extra_launch_count = 120;
        spell.deliver.meteor.launch_properties.extra_launch_delay = 2;
        spell.deliver.meteor.projectile.divergence = 15;

        spell.deliver.meteor.launch_radius = 3;

        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_area_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.FEET, null,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(0.4F,0F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.sound  = new Sound("wizards:fireball_impact");
        spell.area_impact.particles  = new ParticleBatch[]{

                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.1F, .2F, 0),
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0)
        };
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.FEET, null,FIRE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.FEET, null,FIRE,false)};
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry ice_storm = add(ice_storm());

    private static Entry ice_storm() {
        var spell = meteor_base(FROST, Identifier.of("wizards:projectile/frost_shard"),2,1);
        spell.target = new Spell.Target();
        spell.target.aim = new Spell.Target.Aim();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim.sticky = true;
        spell.school = FROST;


        var id = Identifier.of(InvokeMod.MODID, "ice_storm");
        var description = "Barrage the target location with ice shards for 4 seconds, dealing {damage} frost damage per shard.";
        var title = "Janus' Ice Storm";

        spell.deliver.meteor.launch_properties.extra_launch_count = 40;
        spell.deliver.meteor.launch_properties.extra_launch_delay = 2;
        spell.deliver.meteor.projectile.divergence = 15;

        spell.deliver.meteor.launch_radius = 3;

        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_frost_casting","invoke:one_handed_area_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFrostImpact(0.4F,0F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.sound  = new Sound("wizards:frost_shard_impact");
        spell.area_impact.particles  = new ParticleBatch[]{

                new ParticleBatch(SpellEngineParticles.frost_shard.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, .2F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0)
        };
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry dancing_ember = add(dancing_ember());

    private static Entry dancing_ember() {
        var spell = activeSpellBase();
        spell.school = FIRE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.CASTER;


        var id = Identifier.of(InvokeMod.MODID, "dancing_ember");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.PROJECTILE;

        var projectile = projectileBase(FIRE, Identifier.of("wizards:projectile/fire_blast"),1F,1).deliver.projectile;
        projectile.projectile.homing_angle = 10;

        projectile.projectile.divergence = 0;
        projectile.launch_properties.extra_launch_delay = 2;

        projectile.launch_properties.extra_launch_count = 7;
        projectile.direction_offsets = new Spell.Delivery.ShootProjectile.DirectionOffset[]{
                new Spell.Delivery.ShootProjectile.DirectionOffset(45,-45),
                new Spell.Delivery.ShootProjectile.DirectionOffset(-45,-45),
                new Spell.Delivery.ShootProjectile.DirectionOffset(45+90,-45),
                new Spell.Delivery.ShootProjectile.DirectionOffset(-45-90,-45)
        };
        projectile.projectile.homing_after_relative_distance = 0.2F;
        projectile.projectile.homing_after_absolute_distance = 4;
        projectile.projectile.perks.pierce = 99;
        projectile.projectile.perks.bounce = 99;

        projectile.projectile.client_data.model.scale = 0.8F;
        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch("minecraft:smoke", ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,2,0.05f,0.1F,360)

        };
        spell.deliver.projectile = projectile;
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 128;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_area_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(0.8F,2F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),

                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, .2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 15, 0.05F, 0.05F, 0)};
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};

        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry starfall = add(starfall());

    private static Entry starfall() {
        var spell = activeSpellBase();


        var id = Identifier.of(InvokeMod.MODID, "starfall");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";

         spell = meteor_base(ARCANE, Identifier.of("wizards:projectile/arcane_missile"),2,1);
        spell.school = ARCANE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.required = false;
        spell.target.aim.sticky = true;


        spell.deliver.meteor.launch_properties.extra_launch_count = 3;
        spell.deliver.meteor.launch_properties.extra_launch_delay = 20;
        spell.deliver.meteor.launch_height = 20;
        spell.deliver.meteor.projectile.divergence = 30;

        spell.deliver.meteor.projectile.hitbox = new Spell.ProjectileData.HitBox(3,3);
        spell.deliver.meteor.projectile.client_data.model.scale = 3F;

        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                glyph_area(8,0, ParticleBatch.Origin.FEET,null,FIRE,true),

                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch("minecraft:smoke", ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,2,0.05f,0.1F,360)

        };
        spell.deliver.meteor.projectile.client_data.travel_particles = particlebatch;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 64;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_area_charge", ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};
        Spell.Impact[] impacts = new Spell.Impact[3];

        impacts[0] = createFireImpact(0.4F,0.5F);
        impacts[1] = createArcaneImpact(0.4F,0.5F);
        impacts[2] = createFrostImpact(0.4F,0.5F);

        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 6;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),
                glyph_area(8,0, ParticleBatch.Origin.FEET,null,ARCANE,false),

                new ParticleBatch(SpellEngineParticles.snowflake.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.5F, .7F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.5F, 0.7F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.5F, 0.7F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0)};
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_ARCANE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};

        spell.impacts = List.of(impacts);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry plasma_blast = add(plasma_blast());

    private static Entry plasma_blast() {
        var spell = activeSpellBase();
        spell.school = ARCANE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.required = false;
        spell.target.aim.sticky = true;


        var id = Identifier.of(InvokeMod.MODID, "plasma_blast");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.PROJECTILE;

        var projectile = projectileBase(FIRE, Identifier.of("wizards:projectile/arcane_missile"),2,1).deliver.projectile;
        projectile.projectile.homing_angle = 20;

        projectile.projectile.divergence = 0;
        projectile.direct_towards_target = true;
        Spell.Delivery.ShootProjectile.DirectionOffset offset = new Spell.Delivery.ShootProjectile.DirectionOffset(0,-45);
        projectile.direction_offsets = new Spell.Delivery.ShootProjectile.DirectionOffset[]{offset};
        projectile.projectile.homing_after_relative_distance = 0.2F;
        projectile.projectile.homing_after_absolute_distance = 4;
        projectile.projectile.hitbox = new Spell.ProjectileData.HitBox(3,3);
        projectile.projectile.client_data.model.scale = 3F;
        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                glyph_area(8,0, ParticleBatch.Origin.FEET,null,FIRE,true),

        new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch("minecraft:smoke", ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,2,0.05f,0.1F,360)

        };
        spell.deliver.projectile = projectile;
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 64;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_projectile_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(1.2F,0.5F);
        impacts[1] = createArcaneImpact(1.2F,0.5F);

        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),
                glyph_area(8,0, ParticleBatch.Origin.FEET,null,ARCANE,false),

                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.5F, .7F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.5F, 0.7F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.5F, 0.7F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.ARCANE, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.4F, 0.4F, 0)};
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_projectile_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};

        spell.impacts = List.of(impacts[0],impacts[1]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry rain_of_fire = add(rain_of_fire());

    private static Entry rain_of_fire() {
        var spell = activeSpellBase();
        spell.school = FIRE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();


        var id = Identifier.of(InvokeMod.MODID, "rain_of_fire");
        var description = "Blast all enemies in a line with arcane energies, dealing {damage} arcane damage.";
        var title = "Greater Fireball";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.PROJECTILE;

        var projectile = projectileBase(FIRE, Identifier.of("wizards:projectile/fire_blast"),2,1).deliver.projectile;
        projectile.projectile.homing_angle = 40;

        projectile.projectile.divergence = 0;
        projectile.direct_towards_target = true;
        Spell.Delivery.ShootProjectile.DirectionOffset offset = new Spell.Delivery.ShootProjectile.DirectionOffset(0,-45);
        projectile.direction_offsets = new Spell.Delivery.ShootProjectile.DirectionOffset[]{offset};
        projectile.projectile.homing_after_relative_distance = 0.4F;
        projectile.projectile.homing_after_absolute_distance = 8;
        projectile.projectile.client_data.model.scale = 0.8F;
        ParticleBatch[] particlebatch = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,3,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,1,0.05f,0.1F,360),
                new ParticleBatch("minecraft:smoke", ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null,2,0.05f,0.1F,360)

        };
        spell.deliver.projectile = projectile;
        spell.deliver.projectile.projectile.client_data.travel_particles = particlebatch;


        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.active.cast = createCast(0,1F,"spell_engine:generic_fire_casting","invoke:one_handed_area_charge", FIRE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFireImpact(0.8F,2F);
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.sound  = new Sound("entity.generic.explode");
        spell.area_impact.particles  = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.fire_explosion.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 2, 0.3F, 0.5F, 0),

                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 450, 0.1F, .2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.1F, 0.2F, 0),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER, null, 45, 0.05F, 0.05F, 0)};
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};

        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry arcane_sea = add(arcane_sea());

    private static Entry arcane_sea() {
        var spell = activeSpellBase();
        spell.school = ARCANE;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.target.area.angle_degrees = 90;

        var id = Identifier.of(InvokeMod.MODID, "sea_of_lavos");
        var description = "Create a glyph under all enemies in a cone in front of you, detonating after a second, dealing {damage} Arcane Damage to all enemies caught in the blast.";
        var title = "Sea of Lavos";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.particles =  new ParticleBatch[]{glyph_area(4,0, ParticleBatch.Origin.FEET,null, ARCANE,false)};

        spell.active.cast = createCast(0,1F,"spell_engine:generic_arcane_casting","invoke:one_handed_area_charge",SpellSchools.ARCANE);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};

        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createArcaneImpact(0.8F,0F);
        impacts[0].action.type = Spell.Impact.Action.Type.CUSTOM;

        impacts[0].action.custom = new Spell.Impact.Action.Custom();
        impacts[0].action.custom.intent = SpellTarget.Intent.HARMFUL;

        impacts[0].action.custom.handler = "invoke:delay_area_impact";
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_ARCANE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,ARCANE,false)};

        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);
    }
    private static Entry frost_glyph = add(frost_glyph());

    private static Entry frost_glyph() {
        var spell = activeSpellBase();
        spell.school = FROST;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.CASTER;

        var id = Identifier.of(InvokeMod.MODID, "frost_glyph");
        var description = "Create a glyph underneath you that brands nearby enemies, dealing {damage} to these enemies after a delay.";
        var title = "Frozen Expanse";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.METEOR;
        spell.deliver.meteor = meteor_base(FROST, Identifier.of("wizards:projectile/frost_shard"),0.5F,1).deliver.meteor;
        spell.deliver.meteor.projectile.client_data.model.scale = 0;
        spell.deliver.meteor.projectile.homing_angle = 0;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 8;
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 8;
        spell.area_impact.particles =  new ParticleBatch[]{glyph_area(8,0, ParticleBatch.Origin.FEET,null, FROST,false),
                glyph_release(2,0, ParticleBatch.Origin.FEET  , null,FROST,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.FEET, null,FROST,false),
                glyph_center_release(2,0, ParticleBatch.Origin.FEET, null,FROST,false)};
        spell.area_impact.sound = new Sound(SpellEngineSounds.GENERIC_FROST_IMPACT.id());

        spell.active.cast = createCast(0,1F,"spell_engine:generic_frost_casting","invoke:one_handed_area_charge", FROST);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.FEET, null,FROST,false),
                glyph_outer(2,0, ParticleBatch.Origin.FEET, null,FROST,false),
                glyph_center(2,0, ParticleBatch.Origin.FEET, null,FROST,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFrostImpact(1.6F,1F);

        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.FEET  , null,FROST,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.FEET, null,FROST,false),
                glyph_center_release(2,0, ParticleBatch.Origin.FEET, null,FROST,false)};
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry frozen_expanse = add(frozen_expanse());

    private static Entry frozen_expanse() {
        var spell = activeSpellBase();
        spell.school = FROST;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.target.area.angle_degrees = 90;

        var id = Identifier.of(InvokeMod.MODID, "frozen_expanse");
        var description = "Create a glyph under all enemies in a cone in front of you, detonating after a second, dealing {damage} to all enemies caught in the blast and giving them Slowness II";
        var title = "Frozen Expanse";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 8;
        spell.area_impact.particles =  new ParticleBatch[]{glyph_area(8,0, ParticleBatch.Origin.FEET,null, FROST,false)};

        spell.active.cast = createCast(0,1F,"spell_engine:generic_frost_casting","invoke:one_handed_area_charge", FROST);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFrostImpact(0.8F,0F);
        impacts[0].action.type = Spell.Impact.Action.Type.CUSTOM;
        impacts[0].action.custom = new Spell.Impact.Action.Custom();
        impacts[0].action.custom.intent = SpellTarget.Intent.HARMFUL;

        impacts[0].action.custom.handler = "invoke:delay_area_impact";
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false)};
        spell.impacts = List.of(impacts[0]);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry frostferno = add(frostferno());

    private static Entry frostferno() {
        var spell = activeSpellBase();
        spell.school = FROST;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.target.area.angle_degrees = 90;

        var id = Identifier.of(InvokeMod.MODID, "frostferno");
        var description = "Create a glyph under all enemies in a cone in front of you, detonating after a second, dealing {damage} to all enemies caught in the blast and giving them Slowness II";
        var title = "Frozen Expanse";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 12;
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 115;
        spell.area_impact.radius = 4;
        spell.area_impact.particles =  new ParticleBatch[]{glyph_area(4,0, ParticleBatch.Origin.FEET,null, FROST,false)};

        spell.active.cast = createCast(0,1F,"spell_engine:generic_frost_casting","invoke:one_handed_area_charge", FROST);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[3];

        impacts[0] = createFrostImpact(0.8F,0F);
        impacts[1] = createFireImpact(0.4F,0F);
        impacts[2] = new Spell.Impact();
        impacts[2].action = new Spell.Impact.Action();
        impacts[2].action.type = Spell.Impact.Action.Type.FIRE;
        impacts[2].action.fire = new Spell.Impact.Action.Fire();
        impacts[2].action.fire.duration = 80;
        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPELL, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 600, 0.1f, 20F, 90),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.SPARK, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 600, 0.1f, 20F, 90),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 300, 2, 4, 115),
                new ParticleBatch(SpellEngineParticles.getMagicParticleVariant(SpellEngineParticles.FROST, SpellEngineParticles.MagicParticleFamily.Shape.STRIPE, SpellEngineParticles.MagicParticleFamily.Motion.FLOAT).id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 1, 2, 135),
                new ParticleBatch(SpellEngineParticles.flame.id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 600, 0.1f, 20F, 90),
                new ParticleBatch(SpellEngineParticles.flame_spark.id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 600, 0.1f, 20F, 90),
                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 300, 2, 4, 115),
                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(), ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK, 200, 1, 2, 135)

        };        spell.impacts = List.of(impacts);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
    private static Entry wildfire = add(wildfire());

    private static Entry wildfire() {
        var spell = activeSpellBase();
        spell.school = FROST;

        spell.target = new Spell.Target();
        spell.target.type = Spell.Target.Type.AREA;
        spell.target.area = new Spell.Target.Area();
        spell.target.area.angle_degrees = 90;

        var id = Identifier.of(InvokeMod.MODID, "rimeblaze");
        var description = "Create a glyph under all enemies in a cone in front of you, detonating after a second, dealing {damage} to all enemies caught in the blast and giving them Slowness II";
        var title = "Frozen Expanse";
        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.DIRECT;
        spell.learn = new Spell.Learn();
        spell.tier = 3;
        spell.range = 24;
        spell.area_impact = new Spell.AreaImpact();
        spell.area_impact.area = new Spell.Target.Area();
        spell.area_impact.area.angle_degrees = 360F;
        spell.area_impact.radius = 4;
        spell.area_impact.particles =  new ParticleBatch[]{glyph_area(4,0, ParticleBatch.Origin.FEET,null, FIRE,false)};

        spell.active.cast = createCast(0,1F,"spell_engine:generic_frost_casting","invoke:one_handed_area_charge", FROST);
        spell.active.cast.particles = new ParticleBatch[]{glyph(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        Spell.Impact[] impacts = new Spell.Impact[2];

        impacts[0] = createFrostImpact(0.8F,0F);
        impacts[0].action.type = Spell.Impact.Action.Type.CUSTOM;
        impacts[0].action.custom = new Spell.Impact.Action.Custom();
        impacts[0].action.custom.intent = SpellTarget.Intent.HARMFUL;

        impacts[0].action.custom.handler = "invoke:delay_area_impact";
        impacts[1] = new Spell.Impact();
        impacts[1].action = new Spell.Impact.Action();
        impacts[1].action.type = Spell.Impact.Action.Type.FIRE;

        impacts[1].action.fire = new Spell.Impact.Action.Fire();
        impacts[1].action.fire.duration = 80;


        spell.release = new Spell.Release();

        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());
        spell.release.animation = "invoke:one_handed_area_release";
        spell.release.particles = new ParticleBatch[]{glyph_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FROST,false),
                glyph_outer_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false),
                glyph_center_release(2,0, ParticleBatch.Origin.LAUNCH_POINT, ParticleBatch.Rotation.LOOK,FIRE,false)};
        spell.impacts = List.of(impacts);
        configureCooldown(spell, 1F, false, null);

        return new Entry(id, spell, title, description, null);

    }
}
