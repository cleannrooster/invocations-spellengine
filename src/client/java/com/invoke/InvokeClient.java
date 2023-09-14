package com.invoke;

import com.invoke.client.GlacierRenderer;
import com.invoke.client.GlacierSmallModel;
import com.invoke.entities.GlacierSmall;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.internals.SpellCasterEntity;
import net.spell_engine.internals.SpellRegistry;
import net.spell_power.api.attributes.SpellAttributes;

import java.util.Objects;

import static com.invoke.InvokeMod.MODID;

public class InvokeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(InvokeMod.GAZEHITTER, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(InvokeMod.ICECRASH, context -> new GlacierRenderer<GlacierSmall>(context,2,false));
		EntityRendererRegistry.register(InvokeMod.ICECRASH2, context -> new GlacierRenderer<GlacierSmall>(context,4,false));
		EntityRendererRegistry.register(InvokeMod.ICECRASH3, context -> new GlacierRenderer<GlacierSmall>(context,6,false));
		EntityRendererRegistry.register(InvokeMod.ICECRASH4, context -> new GlacierRenderer<GlacierSmall>(context,8,false));

		EntityModelLayerRegistry.registerModelLayer(GlacierSmallModel.LAYER_LOCATION, GlacierSmallModel::createBodyLayer);


		ClientTickEvents.START_CLIENT_TICK.register(server -> {
			PlayerEntity player = server.player;
			World level = server.world;

			if (player != null && level != null) {
				double speed = player.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * player.getAttributeValue(SpellAttributes.HASTE.attribute) * 0.01 * 4;
				BlockHitResult result = level.raycast(new RaycastContext(player.getPos(), player.getPos().add(0, -2, 0), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.ANY, player));
				if (player.isSneaking()) {
					speed *= 0;
				}
				double modifier = 0;
				if (result.getType() == HitResult.Type.BLOCK) {
					modifier = 1;
				}
				speed *= 1.5;

				if (SpellRegistry.getSpell(new Identifier(MODID, "meteorrush")) != null) {
					Spell spell = SpellRegistry.getSpell(new Identifier(MODID, "meteorrush"));

					if (player instanceof SpellCasterEntity caster) {

						if (Objects.equals(caster.getCurrentSpellId(), new Identifier(MODID, "meteorrush"))) {
							speed *= 0.1*(2/caster.getCurrentCastProgress()-2);
							player.setVelocity(player.getRotationVec(1).subtract(0, player.getRotationVec(1).y, 0).normalize().multiply(speed, speed * modifier, speed).add(0, player.getVelocity().y, 0));
						}
					}
				}
			}
		});
	}
}