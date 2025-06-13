package com.invoke;

import com.invoke.client.GlacierRenderer;
import com.invoke.client.GlacierSmallModel;
import com.invoke.client.HudOverlay;
import com.invoke.entities.GlacierSmall;
import com.invoke.interfaces.InvokerEntity;
import com.invoke.networking.InvokePacket;
import com.invoke.networking.InvokePacketFire;
import com.invoke.networking.InvokePacketFrost;
import com.invoke.networking.ResetPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.internals.casting.SpellCasterClient;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_power.api.SpellPowerMechanics;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;
import org.jetbrains.annotations.Nullable;

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
			"runic_invocation",
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
	 public static String getString(InvokerEntity entity, int x, int y, int z){
		 int first = 0;
		 int second = 0;
		 int third = 0;
		 if (entity.getInvokeValue()[0] == 3) {
			 first = 1;
		 }
		 if (entity.getInvokeValue()[1] == 3) {
			 second = 1;
		 }
		 if (entity.getInvokeValue()[2] == 3) {
			 third = 1;
		 }
		 if(entity.getInvokeValue()[0] == 2){
			 first = 2;
		 }
		 if(entity.getInvokeValue()[1] == 2){
			 second = 2;
		 }
		 if(entity.getInvokeValue()[2] == 2){
			 third = 2;
		 }
		 if(entity.getInvokeValue()[0] == 1){
			 first =3;
		 }
		 if(entity.getInvokeValue()[1] == 1){
			 second = 3;
		 }
		 if(entity.getInvokeValue()[2] == 1){
			 third = 3;
		 }

		 return TOTAL_LIST[first][second][third];
	 }
	static {
		TOTAL_LIST[0][0][0] = "runic_invocation";

		TOTAL_LIST[0][0][1] = "";
		TOTAL_LIST[0][0][2] = "";
		TOTAL_LIST[0][0][3] = "";

		TOTAL_LIST[0][1][1] = "";
		TOTAL_LIST[0][1][2] = "";
		TOTAL_LIST[0][1][3] = "";


		TOTAL_LIST[0][2][1] = "";
		TOTAL_LIST[0][2][2] = "";
		TOTAL_LIST[0][2][3] = "";


		TOTAL_LIST[0][3][1] = "";
		TOTAL_LIST[0][3][2] = "";
		TOTAL_LIST[0][3][3] = "";

		TOTAL_LIST[1][3][3] = "greater_geyser";
		TOTAL_LIST[1][3][2] = "frost_fangs";
		TOTAL_LIST[1][3][1] = "replicating_missile";
		TOTAL_LIST[1][2][3] = "self_immolate";
		TOTAL_LIST[1][2][2] = "comet";
		TOTAL_LIST[1][2][1] = "snare";
		TOTAL_LIST[1][1][3] = "trailblaze";
		TOTAL_LIST[1][1][2] = "frozen_miasma";
		TOTAL_LIST[1][1][1] = "magic_missile";

		TOTAL_LIST[3][3][3] = "greater_fireball";
		TOTAL_LIST[3][3][2] = "rimeblaze";
		TOTAL_LIST[3][3][1] = "plasma_blast";
		TOTAL_LIST[3][2][3] = "rain_of_fire";
		TOTAL_LIST[3][2][2] = "frostferno";
		TOTAL_LIST[3][2][1] = "sea_of_lavos";
		TOTAL_LIST[3][1][3] = "combustion";
		TOTAL_LIST[3][1][2] = "frost_glyph";
		TOTAL_LIST[3][1][1] = "supernova";

		TOTAL_LIST[2][3][3] = "dancing_ember";
		TOTAL_LIST[2][3][2] = "frozen_expanse";
		TOTAL_LIST[2][3][1] = "starfall";
		TOTAL_LIST[2][2][3] = "flame_geyser";
		TOTAL_LIST[2][2][2] = "ice_storm";
		TOTAL_LIST[2][2][1] = "lance_of_eos";
		TOTAL_LIST[2][1][3] = "mass_combustion";
		TOTAL_LIST[2][1][2] = "lance_of_heorot";

		TOTAL_LIST[2][1][1] = "wildshards";

	}
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(InvokeMod.ICECRASH, context -> new GlacierRenderer<GlacierSmall>(context,2,true));
		EntityRendererRegistry.register(InvokeMod.ICECRASH2, context -> new GlacierRenderer<GlacierSmall>(context,4,true));
		EntityRendererRegistry.register(InvokeMod.ICECRASH3, context -> new GlacierRenderer<GlacierSmall>(context,6,true));
		EntityRendererRegistry.register(InvokeMod.ICECRASH4, context -> new GlacierRenderer<GlacierSmall>(context,8,true));

		EntityModelLayerRegistry.registerModelLayer(GlacierSmallModel.LAYER_LOCATION, GlacierSmallModel::createBodyLayer);
		ClientPlayNetworking.registerGlobalReceiver(InvokePacketFire.FIRE, (payload,context) -> {
					if(MinecraftClient.getInstance().player instanceof InvokerEntity entity){

						entity.InvokeSet(entity.getInvokeValue()[1],0);
						entity.InvokeSet(entity.getInvokeValue()[2],1);
						entity.InvokeSet(1,2);

		}

				}
		);
		ClientPlayNetworking.registerGlobalReceiver(ResetPacket.RESET, (packetFire,handler) -> {
					if (handler.player() instanceof InvokerEntity entity) {

						entity.InvokeSet(0, 0);
						entity.InvokeSet(0, 1);
						entity.InvokeSet(0, 2);
					}
				}
		);
		HudRenderCallback.EVENT.register(new HudOverlay());
		ClientPlayNetworking.registerGlobalReceiver(InvokePacketFrost.FROST, (packet,handler) -> {
					if(handler.player() instanceof InvokerEntity entity){

						entity.InvokeSet(entity.getInvokeValue()[1],0);
						entity.InvokeSet(entity.getInvokeValue()[2],1);
						entity.InvokeSet(2,2);
					}

				}
		);
		ClientPlayNetworking.registerGlobalReceiver(InvokePacket.ARCANE, (packet,handler) -> {
					if(handler.player() instanceof InvokerEntity entity){

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

				if(player instanceof SpellCasterClient client && player instanceof InvokerEntity entity){
					if(client.getCurrentSpell() != null && client.getCurrentSpell().equals(SpellRegistry.from(player.getWorld()).get(Identifier.of(MODID,"runic_invocation")))){

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
						if( SpellRegistry.from(player.getWorld()).get(Identifier.of(MODID,InvokeClient.getString(entity,combination[0],combination[1],combination[2]))) != null) {
							Spell spell = SpellRegistry.from(player.getWorld()).get(Identifier.of(MODID,"runic_invocation"));
							RegistryEntry<Spell> toCast = SpellRegistry.from(player.getWorld()).getEntry(Identifier.of(MODID, InvokeClient.getString(entity,combination[0],combination[1],combination[2]))).get();

							client.startSpellCast(player.getStackInHand(Hand.MAIN_HAND), toCast);
							client.getCooldownManager().set(Identifier.of(MODID,"runic_invocation"),(int) (4*20));
						}
						else{
							client.getCooldownManager().set(Identifier.of(MODID,"runic_invocation"),(int) (1*20));

						}
					}
				}
			}
		});
	}
}