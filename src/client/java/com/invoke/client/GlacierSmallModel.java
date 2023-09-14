package com.invoke.client;

import com.invoke.entities.GlacierSmall;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GlacierSmallModel<T extends GlacierSmall> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("invoke", "glacier_small"), "main");
    private final ModelPart bb_main;

    public GlacierSmallModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData bb_main = partdefinition.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -5.0F, -7.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.8727F, 0.6545F, -0.4363F));

        return TexturedModelData.of(meshdefinition, 64, 64);
    }



    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(T entity, float f, float g, float h, float i, float j) {

    }
}