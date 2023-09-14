package com.invoke.mixin;

import com.invoke.InvokeMod;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.client.render.SpellProjectileRenderer;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.internals.SpellCasterEntity;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity> {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    public void render(T entity, float f, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo info) {
        if(entity instanceof SpellCasterEntity caster){
            if(caster.getCurrentSpellId() != null && caster.getCurrentSpellId().equals(new Identifier(InvokeMod.MODID,"meteorrush"))){
                /*Spell spell = SpellRegistry.getSpell(new Identifier(InvokeMod.MODID,"greaterfireball"));
                SpellProjectile projectile = new SpellProjectile(entity.getWorld(),entity,entity.getX(),entity.getY(),entity.getZ(), SpellProjectile.Behaviour.FLY,
                        spell,null,new SpellHelper.ImpactContext(), new Spell.ProjectileData().perks);
                EntityRenderer<? super SpellProjectile> renderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(projectile);
                renderer.render(projectile,entity.getHeadYaw(),tickDelta,matrices,vertexConsumerProvider,i);
                SpellProjectileRenderer*/
                info.cancel();
                /*

                if (entity.age >= 2 || (MinecraftClient.getInstance().cameraEntity != null && !(MinecraftClient.getInstance().cameraEntity.squaredDistanceTo(entity) < 12.25D))) {
                    matrices.push();
*/
                    /*if (renderData != null) {
                            switch(renderData.render) {
                                case FLAT:
                                    matrices.multiply(entity.getMovementDirection().getRotationQuaternion());
                                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
                                    break;
                                case DEEP:
                                    Vec3d velocity = entity.getVelocity();
                                    *//*if (projectile.previousVelocity != null) {
                                        velocity = projectile.previousVelocity.lerp(velocity, (double)tickDelta);
                                    }
*//*
                                    velocity = velocity.normalize();
                                    double directionBasedYaw = Math.toDegrees(Math.atan2(velocity.x, velocity.z)) + 180.0D;
                                    double directionBasedPitch = Math.toDegrees(Math.asin(velocity.y));
                                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)directionBasedYaw));
                                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float)directionBasedPitch));
                            }

                            long time = entity.getWorld().getTime();
                            float absoluteTime = (float)time + tickDelta;
                            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(absoluteTime * renderData.rotate_degrees_per_tick));
                            matrices.scale(renderData.scale, renderData.scale, renderData.scale);
                            if (renderData.model_id != null && !renderData.model_id.isEmpty()) {
                                Identifier modelId = new Identifier(renderData.model_id);
                                CustomModels.render(LightEmission.GLOW,  , modelId, matrices, vertexConsumers, light, entity.getId());
                            }
                        }
*/
   }
        }

    }

}
