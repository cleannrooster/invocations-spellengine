package com.invoke.client;

import com.invoke.entities.GlacierSmall;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.spell_engine.client.render.SpellProjectileRenderer;

public class GlacierRenderer<T extends GlacierSmall> extends EntityRenderer<T> {
    public static final Identifier TEXTURE  = new Identifier("spellbladenext", "textures/entity/sword1.png");
    public static final Identifier TEXTURE2  = new Identifier("spellbladenext", "textures/entity/sword2.png");
    private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean lit;
    private final GlacierSmallModel model;
    public GlacierRenderer(EntityRendererFactory.Context p_174420_, float p_174417_, boolean p_174418_) {
        super(p_174420_);
        this.model = new GlacierSmallModel<>(p_174420_.getPart(GlacierSmallModel.LAYER_LOCATION));
        this.itemRenderer = p_174420_.getItemRenderer();
        this.scale = p_174417_;
        this.lit = p_174418_;
    }


    @Override
    public void render(T entity, float f, float g, MatrixStack poseStack, VertexConsumerProvider multiBufferSource, int i) {
        poseStack.push();
        poseStack.translate(0,-0.25,0);
        poseStack.multiply(entity.getHorizontalFacing().getRotationQuaternion());
        if(entity.age < 4){
            poseStack.translate(0,-(entity.getHeight()/(4*2))*(4-entity.age),0);
        }
        poseStack.scale(this.scale,this.scale,this.scale);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.model.getLayer(getTexture(entity)));

        this.model.render(poseStack,vertexConsumer,i, OverlayTexture.DEFAULT_UV,1.0F,1.0F,1.0F,1.0F);
        poseStack.pop();
        super.render(entity, f, g, poseStack, multiBufferSource, i);
    }
    @Override
    public Identifier getTexture(T entity) {
        return new Identifier("invoke", "textures/entity/ice.png");
    }
}
