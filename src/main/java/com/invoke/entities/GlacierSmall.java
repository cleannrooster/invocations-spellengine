package com.invoke.entities;

import com.invoke.InvokeMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.SpellInfo;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import net.spell_engine.particle.ParticleHelper;
import net.spell_engine.utils.SoundHelper;

import java.util.List;
import java.util.Optional;

import static com.invoke.InvokeMod.MODID;

public class GlacierSmall extends ExplosiveProjectileEntity {

    private  Vec3d direction = new Vec3d(0,1,0);
    private int chain = -1;
    public Spell spell = SpellRegistry.getSpell(new Identifier(MODID,"glacier"));
    public SpellHelper.ImpactContext context;
    public boolean nodamage = false;
    public int lastresonance = 0;
    public GlacierSmall(EntityType<GlacierSmall> entityType, World level) {
        super(entityType, level);
        this.noClip = true;
        this.setPosition(this.getX(),(int)((this.getY()*2)/2),this.getZ());
    }
    public GlacierSmall(EntityType<GlacierSmall> entityType, World level, int chain, Vec3d direction, Entity owner, SpellHelper.ImpactContext context) {
        super(entityType, level);
        this.noClip = true;
        this.chain = chain;
        this.direction = direction;
        this.setOwner(owner);
        this.context = context;
        this.setPosition(this.getX(),(int)((this.getY()*2)/2),this.getZ());
    }

    @Override
    protected ParticleEffect getParticleType() {
        return ParticleTypes.SNOWFLAKE;
    }

    @Override
    protected boolean isBurning() {
        return false;
    }

    @Override
    public void setNoGravity(boolean bl) {
        super.setNoGravity(bl);
    }
    @Override
    public boolean isCollidable() {
        return false;
    }


    @Override
    public boolean handleAttack(Entity entity) {
        return true;
    }

    @Override
    public boolean damage(DamageSource damageSource, float f) {
        return false;
    }

    @Override
    public void tick() {
        if(firstUpdate ){
            SoundHelper.playSound(this.getWorld(),this,this.spell.release.sound);
            if(!this.getWorld().isClient())
            ParticleHelper.sendBatches( this, this.spell.release.particles);

        }
        if(firstUpdate && this.getOwner()!= null){
            this.setRotation(this.getOwner().getYaw(),this.getOwner().getPitch());
        }
        if( !nodamage && firstUpdate && this.getOwner() != null && this.spell != null && this.context != null){
            List<Entity> list = this.getWorld().getOtherEntities(this,this.getBoundingBox().stretch(1.5,1.5,1.5), entity -> entity != this.getOwner());
            spell.impact[0].action.damage.spell_power_coefficient *= this.getBoundingBox().getXLength()/3.0F;
            for(Entity target : list) {
                SpellInfo info = new SpellInfo(SpellRegistry.getSpell(new Identifier(MODID,"glacier")),new Identifier(MODID,"glacier"));

                SpellHelper.performImpacts(this.getWorld(), (LivingEntity) this.getOwner(), target,this.getOwner(),info,this.context);
            }
            spell.impact[0].action.damage.spell_power_coefficient /= this.getBoundingBox().getXLength()/3.0F;

        }
        if(age == 5 && this.chain == 2){
            Vec3d direction = this.getPos().add(this.direction.getX(),0,this.direction.getZ());
            Optional<BlockPos> air = BlockPos.findClosest(BlockPos.ofFloored(direction),2,2, pos ->
                    this.getWorld().getBlockState(pos).isSolidBlock(this.getWorld(),pos)
                            && !this.getWorld().getBlockState(pos.add(0,1,0)).isSolidBlock(this.getWorld(),pos));
            if(air.isPresent()) {
                GlacierSmall newGlacier = new GlacierSmall(InvokeMod.ICECRASH2, this.getWorld(), 1, this.direction, this.getOwner(), this.context);
                newGlacier.setPosition(air.get().getX()+0.5,(double)air.get().getY()+1,air.get().getZ()+0.5);
                this.getWorld().spawnEntity(newGlacier);
            }
        }
        if(age == 5 && this.chain == 1){
            Vec3d direction = this.getPos().add(this.direction.getX()*2,0,this.direction.getZ()*2);
            Optional<BlockPos> air = BlockPos.findClosest(BlockPos.ofFloored(direction),2,2, pos ->
                    this.getWorld().getBlockState(pos).isSolidBlock(this.getWorld(),pos)
                            && !this.getWorld().getBlockState(pos.add(0,1,0)).isSolidBlock(this.getWorld(),pos));
            if(air.isPresent()) {
                GlacierSmall newGlacier = new GlacierSmall(InvokeMod.ICECRASH3, this.getWorld(), 0, this.direction, this.getOwner(), this.context);
                newGlacier.setPosition(air.get().getX()+0.5,(double)air.get().getY()+1,air.get().getZ()+0.5);
                this.getWorld().spawnEntity(newGlacier);
            }
        }
        if(age == 5 && this.chain == 0){
            Vec3d direction = this.getPos().add(this.direction.getX()*3,0,this.direction.getZ()*3);
            Optional<BlockPos> air = BlockPos.findClosest(BlockPos.ofFloored(direction),2,2, pos ->
                    this.getWorld().getBlockState(pos).isSolidBlock(this.getWorld(),pos)
                            && !this.getWorld().getBlockState(pos.add(0,1,0)).isSolidBlock(this.getWorld(),pos));
            if(air.isPresent()) {
                GlacierSmall newGlacier = new GlacierSmall(InvokeMod.ICECRASH4, this.getWorld(), -1, this.direction, this.getOwner(), this.context);
                newGlacier.setPosition(air.get().getX()+0.5,(double)air.get().getY()+1,air.get().getZ()+0.5);
                this.getWorld().spawnEntity(newGlacier);
            }
        }
        if(age > 240 && !this.getWorld().isClient()){
            this.setPosition(this.getPos().subtract(0,(this.getHeight()/(10*2)),0));
        }
        if(this.age > 260 && !this.getWorld().isClient()){
            this.discard();
        }
        this.firstUpdate = false;
        if(this.lastresonance > 0){
            lastresonance--;
        }
    }

    @Override
    public boolean collidesWith(Entity entity) {
        return false;
    }

    @Override
    public void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compoundTag) {
        super.readCustomDataFromNbt(compoundTag);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound compoundTag) {
        super.writeCustomDataToNbt(compoundTag);

    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

    }

    @Override
    protected void onCollision(HitResult hitResult) {

    }
}