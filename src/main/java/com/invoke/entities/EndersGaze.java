package com.invoke.entities;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.spell_engine.api.spell.ParticleBatch;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.particle.ParticleHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.SpellPower;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class EndersGaze extends SpellProjectile implements FlyingItemEntity {
    public int number;
    public Entity target;
    public SpellPower.Result power;
    public SpellHelper.ImpactContext context;
    public Spell spell;
    public int life = 80;

    public EndersGaze(EntityType<? extends EndersGaze> entityType, World level) {
        super(entityType, level);
    }
    public EndersGaze(EntityType<? extends EndersGaze> entityType, World level, PlayerEntity player, Entity target, int number) {
        super(entityType, level);
        this.setOwner(player);
        this.target = target;
        this.number = number;
    }

    @Override
    public Behaviour behaviour() {
        return Behaviour.FLY;
    }

    @Override
    public void tick() {
        this.setNoGravity(true);
        ParticleHelper.play(this.getWorld(),this,this.getPitch(),this.getYaw(), new ParticleBatch("spell_engine:arcane_spell", ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER, ParticleBatch.Rotation.LOOK,3,0,0,0));
        if(this.firstUpdate){
            this.playSoundIfNotSilent(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL);
        }
        if(this.target != null &&  this.target.isAlive()) {
            double tridentEntity = 0;
            double f = 0;
            float g = (float) (-sin(tridentEntity * ((float) Math.PI / 180)) * cos(f * ((float) Math.PI / 180)));
            float h = (float) -sin(f * ((float) Math.PI / 180));
            float r = (float) (cos(tridentEntity * ((float) Math.PI / 180)) * cos(f * ((float) Math.PI / 180)));
            this.setPosition(this.target.getX() + (1D + target.getBoundingBox().getXLength()) * 0.5 * (1-0.25*Math.pow((((double) (this.age % 8) / 8D)-1),2)) * cos((((double) (this.age % 40) / 40D)) * (2D * (double) Math.PI) + (2 * Math.PI * (double) (number % 5) / 5D)), (double) this.target.getBoundingBox().getCenter().getY(), this.target.getZ()  + (1D + target.getBoundingBox().getXLength()) * 0.5 * (1-0.25*Math.pow((((double) (this.age % 8) / 8D)-1),2)) * sin((((double) (this.age % 40) / 40D)) * (2 * Math.PI) + (2 * Math.PI * (double) (number % 5) / 5D)));

        }
        if(this.age % 8 == 0){
            for (int ii = 0; ii < 25; ii++) {
                int i = (ii*40) % 1000;
                if (i <= 1000) {

                    double[] indices = IntStream.rangeClosed(0, (int) ((1000)))
                            .mapToDouble(x -> x).toArray();
                    if (i < 0) {
                        return;
                    }
                    double phi = Math.acos(1 - 2 * indices[i] / 1000);
                    double theta = Math.PI * (1 + Math.pow(5, 0.5) * indices[i]);
                    if (phi == Math.toRadians(180) && theta == Math.toRadians(180)) {
                        this.setInvisible(true);
                    }
                    double x = cos(theta) * sin(phi);
                    double y = -cos(phi);
                    double z = Math.sin(theta) * sin(phi);
                    this.getWorld().addParticle(ParticleTypes.WITCH, this.getX() + 2 * x, this.getY() + 2 * y, this.getZ() + 2 * z, 0, 0, 0);
                }
            }
            if(!this.getWorld().isClient() && this.power != null && this.context != null && this.getOwner() instanceof LivingEntity living2 && this.spell != null) {
                if (this.power != null && this.target != null && this.spell != null && this.context != null) {
                    SpellPower.Vulnerability vulnerability = SpellPower.Vulnerability.none;
                    vulnerability = SpellPower.Vulnerability.none;
                    if(target instanceof LivingEntity living)
                    vulnerability = SpellPower.getVulnerability(living, MagicSchool.ARCANE);

                    //SpellPower.Result power = SpellPower.getSpellPower(MagicSchool.ARCANE, (LivingEntity) this.getOwner());
                    double amount = this.power.randomValue(vulnerability);
                    amount *= (double) 0.1;
                    EntityAttributeModifier modifier = new EntityAttributeModifier(UUID.randomUUID(),"knockbackresist",1, EntityAttributeModifier.Operation.ADDITION);
                    ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
                    builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, modifier);
                    if(target instanceof LivingEntity living)
                        living.getAttributes().addTemporaryModifiers(builder.build());

                    target.timeUntilRegen = 0;
                    SpellHelper.performImpacts(this.getWorld(),living2,this.target,living2,this.spell,this.context);
                    if(target instanceof LivingEntity living)
                        living.getAttributes().removeModifiers(builder.build());

                    //this.playSound(SoundEvents.ILLUSIONER_CAST_SPELL);

                }
            }
        }
            if(this.age > life && !this.getWorld().isClient()){
            this.discard();
        }
            if(this.getOwner() instanceof PlayerEntity living && !this.getWorld().isClient()) {
                if (this.target == null || !this.target.isAlive() || (this.target instanceof LivingEntity living2 && living2.isDead())) {
                    Predicate<Entity> selectionPredicate = (target) -> {
                        return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.AREA, TargetHelper.Intent.HARMFUL, living, target)
                                );
                    };
                    Spell.Release.Target.Area area = new Spell.Release.Target.Area();
                    area.angle_degrees = 360;
                    List<Entity> list = TargetHelper.targetsFromArea(this, this.getPos(),4F, area,  selectionPredicate);
                    list.removeIf(entity -> entity instanceof EndersGaze);
                    if(this.target != null) {
                        list.removeIf(entity -> entity == this.target);
                    }

                    this.target = getNearestEntity(list, TargetPredicate.createNonAttackable(), living, this.getX(), this.getY(), this.getZ());
                    if(this.target != null){
                        this.age = 0;
                    }
                    else{
                        this.discard();
                    }
                }
            }
        //super.tick();
        this.firstUpdate = false;
    }
    @Nullable
    public <T extends Entity> T getNearestEntity(List<? extends T> list, TargetPredicate targetingConditions, @Nullable Entity livingEntity, double d, double e, double f) {
        double g = -1.0D;
        T livingEntity2 = null;
        Iterator var13 = list.iterator();

        while(true) {
            T livingEntity3;
            double h;
            do {
                do {
                    if (!var13.hasNext()) {
                        return livingEntity2;
                    }

                    livingEntity3 = (T)var13.next();
                } while(livingEntity == livingEntity3);

                h = livingEntity3.squaredDistanceTo(d, e, f);
            } while(g != -1.0D && !(h < g));

            g = h;
            livingEntity2 = livingEntity3;
        }
    }


    @Override
    public ItemStack getStack() {
        return Items.ENDER_EYE.getDefaultStack();
    }


    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
    }

    @Override
    public boolean damage(DamageSource damageSource, float f) {
        if (damageSource.getAttacker() == this.getOwner()) {
            return false;
        }
        this.playSoundIfNotSilent(SoundEvents.BLOCK_GLASS_BREAK);
        this.discard();
        return super.damage(damageSource, f);
    }
}
