package com.invoke;

import com.invoke.client.GlacierRenderer;
import com.invoke.client.GlacierSmallModel;
import com.invoke.client.HudOverlay;
import com.invoke.entities.GlacierSmall;
import com.invoke.interfaces.InvokerEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.internals.SpellContainerHelper;
import net.spell_engine.internals.SpellRegistry;
import net.spell_engine.internals.casting.SpellCasterClient;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_power.api.SpellPowerMechanics;

import java.sql.Array;
import java.util.Arrays;
import java.util.Objects;

import static com.invoke.InvokeMod.MODID;

public class InvokeClient implements ClientModInitializer {
	private static final String[] FIRE_INVOKER_LIST = {
			"risingflame",
			"flameray",
			"scorchingwind",
			"meteorrush",
			"greaterfireball",
			"supernova",
			"buckshot",
			"combustion",
			"armageddon"
	};
	private static final String[] ARCANE3FIRE1 = {
			"scorchingwind",
			"magic_missile",
			"blink",
			"sonicboom"
	};
	private static final String[] PUREARCANE1 = {
			"runicinvocation",
			"magic_missile",
			"enders_gaze",
			"agonizingblast",

	};
	private static final String[] PUREARCANE = {
			"sonicboom",
			"magic_missile",
			"enders_gaze",
			"agonizingblast",

	};
	private static final String[] ARCANE3FIRE2 = {

			"greaterfireball",
			"amethystburst",
			"sharedsuffering",
			"upheaval",
	};
	private static final String[] ARCANE3FIRE3 = {
			"supernova",
			"meteorrush",
			"buckshot",
			"amethystburst",
	};
	private static final String[] ARCANE_INVOKER_LIST = {
			"arcaneoverdrive",
			"blink",
			"amethystburst",
			"enders_gaze",
			"magic_missile",
			"sonicboom",
			"hijack",
			"bouncing",
			"agonizingblast"
	};

	private static final String[] FROST_INVOKER_LIST = {
			"glacialhammer",
			"icebarrage",
			"sharedsuffering",
			"upheaval",
			"glacier",
			"resonance",
			"icestorm",
			"freezeaura",
			"deathchill"
	};
	private static final String[] PUREFROST = {
			"resonance",
			"glacialhammer",
			"icebarrage",
			"icestorm",
	};
	private static final String[] FROST3ARCANE1 = {

			"glacier",
			"icebarrage",
			"magic_missile",
			"bouncing",
	};
	private static final String[] FROST3ARCANE2 = {
			"icebarrage",
			"glacier",
			"icestorm",
			"resonance"

	};
	private static final String[] FROST3ARCANE3 = {
			"upheaval",
			"icestorm",
			"amethystburst",
			"supernova"
	};
	private static final String[] PUREFIRE = {

			"armageddon",
			"combustion",
			"risingflame",
			"supernova",
	};
	private static final String[] FIRE3ARCANE1 = {

			"flameray",
			"greaterfireball",
			"buckshot",
			"armageddon",

	};
	private static final String[] FIRE3ARCANE2 = {

			"hijack",
			"bouncing",
			"scorchingwind",
			"magic_missile"
	};
	private static final String[] FIRE3ARCANE3 = {
			"sonicboom",
			"agonizingblast",
			"amethystburst",
			"supernova"
	};
	 public static String[][][] TOTAL_LIST = new String[4][4][4];
	static {
		TOTAL_LIST[0][0][0] = "runicinvocation";
		TOTAL_LIST[0][0][1] = "magic_missile";
		TOTAL_LIST[0][0][2] = "enders_gaze";
		TOTAL_LIST[0][0][3] = "agonizingblast";
		TOTAL_LIST[0][1][1] = "sonicboom";
		TOTAL_LIST[0][2][1] = "amethystburst";
		TOTAL_LIST[1][1][1] = "buckshot";
		TOTAL_LIST[1][0][1] = "blink";
		TOTAL_LIST[2][0][1] = "supernova";
		TOTAL_LIST[0][1][2] = "bouncing";
		TOTAL_LIST[1][0][2] = "combustion";
		TOTAL_LIST[0][1][0] = "icebarrage";
		TOTAL_LIST[0][2][0] = "glacier";
		TOTAL_LIST[0][3][0] = "resonance";
		TOTAL_LIST[1][1][0] = "sharedsuffering";
		TOTAL_LIST[2][1][0] = "combustion";
		TOTAL_LIST[1][2][0] = "upheaval";
		TOTAL_LIST[3][0][0] = "armageddon";
		TOTAL_LIST[1][0][0] = "scorchingwind";
		TOTAL_LIST[2][0][0] = "greaterfireball";


	}
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(InvokeMod.GAZEHITTER, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(InvokeMod.ICECRASH, context -> new GlacierRenderer<GlacierSmall>(context,2,true));
		EntityRendererRegistry.register(InvokeMod.ICECRASH2, context -> new GlacierRenderer<GlacierSmall>(context,4,true));
		EntityRendererRegistry.register(InvokeMod.ICECRASH3, context -> new GlacierRenderer<GlacierSmall>(context,6,true));
		EntityRendererRegistry.register(InvokeMod.ICECRASH4, context -> new GlacierRenderer<GlacierSmall>(context,8,true));

		EntityModelLayerRegistry.registerModelLayer(GlacierSmallModel.LAYER_LOCATION, GlacierSmallModel::createBodyLayer);
		ClientPlayNetworking.registerGlobalReceiver(new Identifier(MODID,"fire"), (client, handler, buf, responseSender) -> {
					if(client.player instanceof InvokerEntity entity){

						entity.InvokeSet(entity.getInvokeValue()[1],0);
						entity.InvokeSet(entity.getInvokeValue()[2],1);
						entity.InvokeSet(1,2);

		}

				}
		);
		ClientPlayNetworking.registerGlobalReceiver(new Identifier(MODID,"reset"), (client, handler, buf, responseSender) -> {
					if (client.player instanceof InvokerEntity entity) {

						entity.InvokeSet(0, 0);
						entity.InvokeSet(0, 1);
						entity.InvokeSet(0, 2);
					}
				}
		);
		HudRenderCallback.EVENT.register(new HudOverlay());
		ClientPlayNetworking.registerGlobalReceiver(new Identifier(MODID,"frost"), (client, handler, buf, responseSender) -> {
					if(client.player instanceof InvokerEntity entity){

						entity.InvokeSet(entity.getInvokeValue()[1],0);
						entity.InvokeSet(entity.getInvokeValue()[2],1);
						entity.InvokeSet(2,2);
					}

				}
		);
		ClientPlayNetworking.registerGlobalReceiver(new Identifier(MODID,"arcane"), (client, handler, buf, responseSender) -> {
					if(client.player instanceof InvokerEntity entity){

						entity.InvokeSet(entity.getInvokeValue()[1],0);
						entity.InvokeSet(entity.getInvokeValue()[2],1);
						entity.InvokeSet(3,2);
					}

				}
		);
		ClientTickEvents.START_CLIENT_TICK.register(server -> {
			PlayerEntity player = server.player;
			World level = server.world;

			if (player != null && level != null) {

				double speed = player.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * player.getAttributeValue(SpellPowerMechanics.HASTE.attribute) * 0.01 * 4;
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

					if (player instanceof SpellCasterClient caster) {
						if (caster.getSpellCastProgress() != null && Objects.equals(caster.getCurrentSpell(), SpellRegistry.getSpell(new Identifier(MODID, "meteorrush")))) {
							speed *= 0.1*(2/caster.getSpellCastProgress().ratio()-2);
							player.setVelocity(player.getRotationVec(1).subtract(0, player.getRotationVec(1).y, 0).normalize().multiply(speed, speed * modifier, speed).add(0, player.getVelocity().y, 0));
						}
					}
				}
				if(player instanceof SpellCasterClient client && player instanceof InvokerEntity entity){
					if(client.getCurrentSpell() != null && client.getCurrentSpell().equals(SpellRegistry.getSpell(new Identifier(MODID,"runicinvocation")))){

						int[] combination = {0,0,0};
						for(int i = 0; i < combination.length; i++){
							if (entity.getInvokeValue()[i] == 1) {
								combination[0]++;
							}
							if (entity.getInvokeValue()[i] == 2) {
								combination[1]++;
							}
							if (entity.getInvokeValue()[i] == 3) {
								combination[2]++;
							}
						}
						if(combination[0] > 3){
							combination[0] = 3;
						}
						if(combination[1] > 3){
							combination[1] = 3;
						}
						if(combination[2] > 3){
							combination[2] = 3;
						}

						if( SpellRegistry.getSpell(new Identifier(MODID,TOTAL_LIST[combination[0]][combination[1]][combination[2]])) != null) {
							Spell spell = SpellRegistry.getSpell(new Identifier(MODID,"runicinvocation"));
							client.startSpellCast(player.getStackInHand(Hand.MAIN_HAND), new Identifier(MODID, TOTAL_LIST[combination[0]][combination[1]][combination[2]]));
							client.getCooldownManager().set(new Identifier(MODID,"runicinvocation"),(int) (spell.cost.cooldown_duration*20));
						}
					}
				}
			}
		});
	}
}