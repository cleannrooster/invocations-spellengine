package com.invoke;

import com.invoke.entities.GlacierSmall;
import com.invoke.interfaces.InvokerEntity;
import com.invoke.networking.InvokePacket;
import com.invoke.networking.InvokePacketFire;
import com.invoke.networking.InvokePacketFrost;
import com.invoke.networking.ResetPacket;
import com.invoke.spells.SpellCustomImpact;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.spell_engine.api.event.CombatEvents;
import net.spell_engine.api.item.SpellBooks;
import net.spell_engine.api.item.trinket.ISpellBookItem;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.casting.SpellCast;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_engine.utils.SoundHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellPower;
import net.spell_power.api.SpellSchools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static net.minecraft.registry.Registries.ENTITY_TYPE;
import static net.spell_engine.internals.SpellHelper.launchPoint;

public class InvokeMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

    public static final Logger LOGGER = LoggerFactory.getLogger("invoke");
	public static String MODID = "invoke";
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
	private static final String[] PUREARCANE = {
			"sonicboom",
			"magic_missile",
			"enders_gaze",
			"agonizingblast",

	};
	private static final String[] ARCANE3FIRE2 = {

			"meteorrush",
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

			"greaterfireball",
			"combustion",
			"risingflame",
			"supernova",
	};
	private static final String[] FIRE3ARCANE1 = {

			"flameray",
			"greaterfireball",
			"meteorrush",
			"armageddon",

	};
	private static final String[] FIRE3ARCANE2 = {

			"hijack",
			"bouncing",
			"buckshot",
			"magic_missile"
	};
	private static final String[] FIRE3ARCANE3 = {
			"sonicboom",
			"agonizingblast",
			"amethystburst",
			"supernova"
	};
	private static final String[][][] TOTAL_LIST = {{PUREARCANE,FROST3ARCANE2,FROST3ARCANE1 , PUREFROST},
			{ARCANE3FIRE1,FROST3ARCANE1,FROST3ARCANE3,PUREARCANE},
			{ARCANE3FIRE2,FIRE3ARCANE3, FROST3ARCANE2, ARCANE3FIRE2},
			{PUREFIRE, FIRE3ARCANE2, FIRE3ARCANE2, FIRE3ARCANE1}};
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

		TOTAL_LIST[0][0][1] = "blink";
		TOTAL_LIST[0][0][2] = "icebarrage";
		TOTAL_LIST[0][0][3] = "scorchingwind";

		TOTAL_LIST[0][1][1] = "arcane_launch";
		TOTAL_LIST[0][1][2] = "chill";
		TOTAL_LIST[0][1][3] = "combust";


		TOTAL_LIST[0][2][1] = "magic_missile";
		TOTAL_LIST[0][2][2] = "upheaval";
		TOTAL_LIST[0][2][3] = "greater_fireball";


		TOTAL_LIST[0][3][1] = "arcane_nova";
		TOTAL_LIST[0][3][2] = "glacier";
		TOTAL_LIST[0][3][3] = "supernova";

		TOTAL_LIST[1][3][3] = "greater_combust";
		TOTAL_LIST[1][3][2] = "shatter";
		TOTAL_LIST[1][3][1] = "power_word_kill";
		TOTAL_LIST[1][2][3] = "scorching_agony";
		TOTAL_LIST[1][2][2] = "deep_chill";
		TOTAL_LIST[1][2][1] = "snare";
		TOTAL_LIST[1][1][3] = "scorching_ray";
		TOTAL_LIST[1][1][2] = "sharedsuffering";
		TOTAL_LIST[1][1][1] = "sonicboom";

		TOTAL_LIST[3][3][3] = "inferno";
		TOTAL_LIST[3][3][2] = "frozen_resonance";
		TOTAL_LIST[3][3][1] = "time_dilate";
		TOTAL_LIST[3][2][3] = "homing";
		TOTAL_LIST[3][2][2] = "ice_nova";
		TOTAL_LIST[3][2][1] = "agonizingblast";
		TOTAL_LIST[3][1][3] = "flame_geyser";
		TOTAL_LIST[3][1][2] = "mass_hypothermia";
		TOTAL_LIST[3][1][1] = "overload";

		TOTAL_LIST[2][3][3] = "armageddon";
		TOTAL_LIST[2][3][2] = "icestorm";
		TOTAL_LIST[2][3][1] = "eldritch_blast";
		TOTAL_LIST[2][2][3] = "buckshot";
		TOTAL_LIST[2][2][2] = "essence_drain";
		TOTAL_LIST[2][2][1] = "amethystburst";
		TOTAL_LIST[2][1][3] = "flameray";
		TOTAL_LIST[2][1][2] = "glacialhammer";

		TOTAL_LIST[2][1][1] = "enders_gaze";

	}
/*	public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
			("items_v2", Default.itemConfig)
			.builder()
			.setDirectory(MODID)
			.sanitize(true)
			.build();
	public static ConfigManager<LootConfig> lootConfig = new ConfigManager<LootConfig>
			("loot_v2", Default.lootConfig)
			.builder()
			.setDirectory(MODID)
			.sanitize(true)
			.constrain(LootConfig::constrainValues)
			.build();*/
	public ItemGroup INVOCATIONS;
	public RegistryKey<ItemGroup> KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),Identifier.of(InvokeMod.MODID,"generic"));
	private void preInit() {

	}


	public static EntityType<GlacierSmall> ICECRASH;
	public static EntityType<GlacierSmall> ICECRASH2;
	public static EntityType<GlacierSmall> ICECRASH3;
	public static EntityType<GlacierSmall> ICECRASH4;

	public void onInitialize() {
		int rawId = 1646123;
		ISpellBookItem book = SpellBooks.create(Identifier.of(MODID, "invoker"));
		ItemGroupEvents.modifyEntriesEvent(KEY).register((content) -> {
		});
		PayloadTypeRegistry.playS2C().register(InvokePacket.ARCANE, InvokePacket.PACKET_CODEC);
		PayloadTypeRegistry.playS2C().register(InvokePacketFire.FIRE, InvokePacketFire.PACKET_CODEC);
		PayloadTypeRegistry.playS2C().register(InvokePacketFrost.FROST, InvokePacketFrost.PACKET_CODEC);
		PayloadTypeRegistry.playS2C().register(ResetPacket.RESET, ResetPacket.PACKET_CODEC);
		SpellCustomImpact.registerImpacts();
		INVOCATIONS = FabricItemGroup.builder()
				.icon(() -> new ItemStack(book))
				.displayName(Text.translatable("itemGroup.invoke.general"))
				.build();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//lootConfig.refresh();
		//itemConfig.refresh();
		Registry.register(Registries.ITEM_GROUP, KEY, INVOCATIONS);
		//Items.register(itemConfig.value.weapons);
		//itemConfig.save();

		ItemGroupEvents.modifyEntriesEvent(KEY).register((content) -> {
			content.add(book);
		});
		Registry.register(Registries.ITEM, Identifier.of(MODID, "invoker_spell_book"), book.asItem());
		//SpellBooks.createAndRegister(Identifier.of(MODID,"wildinvoker"),KEY);
		ICECRASH = Registry.register(
				ENTITY_TYPE,
				Identifier.of(MODID, "glaciersmall"),
				FabricEntityTypeBuilder.<GlacierSmall>create(SpawnGroup.MISC, GlacierSmall::new)
						.dimensions(EntityDimensions.changing(1.5F, 1.5F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		ICECRASH2 = Registry.register(
				ENTITY_TYPE,
				Identifier.of(MODID, "glaciermedium"),
				FabricEntityTypeBuilder.<GlacierSmall>create(SpawnGroup.MISC, GlacierSmall::new)
						.dimensions(EntityDimensions.changing(3.0F, 3.0F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		ICECRASH3 = Registry.register(
				ENTITY_TYPE,
				Identifier.of(MODID, "glacierlarge"),
				FabricEntityTypeBuilder.<GlacierSmall>create(SpawnGroup.MISC, GlacierSmall::new)
						.dimensions(EntityDimensions.changing(4.5F, 4.5F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		ICECRASH4 = Registry.register(
				ENTITY_TYPE,
				Identifier.of(MODID, "glacierhuge"),
				FabricEntityTypeBuilder.<GlacierSmall>create(SpawnGroup.MISC, GlacierSmall::new)
						.dimensions(EntityDimensions.changing(6.0F, 6.0F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);
		LOGGER.info("Hello Fabric world!");



	}
}