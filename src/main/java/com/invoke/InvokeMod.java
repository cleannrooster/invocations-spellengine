package com.invoke;

import com.invoke.entities.EndersGaze;
import com.invoke.entities.GlacierSmall;
import com.invoke.interfaces.InvokerEntity;
import com.invoke.networking.InvokePacket;
import com.invoke.networking.InvokePacketFire;
import com.invoke.networking.InvokePacketFrost;
import com.invoke.networking.ResetPacket;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSources;
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
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.spell_engine.api.event.CombatEvents;
import net.spell_engine.api.item.trinket.ISpellBookItem;
import net.spell_engine.api.item.trinket.SpellBookItem;
import net.spell_engine.api.item.trinket.SpellBooks;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.event.CustomSpellHandler;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.client.input.SpellHotbar;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.casting.SpellCast;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_engine.particle.ParticleHelper;
import net.spell_engine.utils.SoundHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellDamageSource;
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

	public static EntityType<EndersGaze> GAZEHITTER;

	public static EntityType<GlacierSmall> ICECRASH;
	public static EntityType<GlacierSmall> ICECRASH2;
	public static EntityType<GlacierSmall> ICECRASH3;
	public static EntityType<GlacierSmall> ICECRASH4;

	public void onInitialize() {
		int rawId = 1646123;
		ISpellBookItem book = SpellBooks.create(Identifier.of(MODID,"invoker"));
		ItemGroupEvents.modifyEntriesEvent(KEY).register((content) -> {
		});
		PayloadTypeRegistry.playS2C().register(InvokePacket.ARCANE, InvokePacket.PACKET_CODEC);
		PayloadTypeRegistry.playS2C().register(InvokePacketFire.FIRE, InvokePacketFire.PACKET_CODEC);
		PayloadTypeRegistry.playS2C().register(InvokePacketFrost.FROST, InvokePacketFrost.PACKET_CODEC);
		PayloadTypeRegistry.playS2C().register(ResetPacket.RESET, ResetPacket.PACKET_CODEC);

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
		Registry.register(Registries.ITEM,Identifier.of(MODID,"invoker_spell_book"),book.asItem());
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

		CustomSpellHandler.register(Identifier.of(MODID,"runic_invocation"), (data) ->{

			return false;

		});
		GAZEHITTER = Registry.register(
				ENTITY_TYPE,
				Identifier.of(MODID, "gazehitter"),
				FabricEntityTypeBuilder.<EndersGaze>create(SpawnGroup.MISC, EndersGaze::new)
						.dimensions(EntityDimensions.fixed(0.5F, 0.5F)) // dimensions in Minecraft units of the render
						.trackRangeBlocks(128)
						.trackedUpdateRate(1)
						.build()
		);

		ServerTickEvents.START_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

				if (player instanceof InvokerEntity playerDamageInterface && player instanceof SpellCasterEntity caster ) {

						if (caster.getCurrentSpell() == null) {

							float duration = SpellHelper.getCooldownDuration(player,SpellRegistry.from(player.getWorld()).get(Identifier.of(MODID, "magic_missile")))*20;

							if( !playerDamageInterface.getMissiles().isEmpty()) {
								SpellProjectile missile = playerDamageInterface.getMissiles().get(0);
								SpellHelper.shootProjectile(player.getWorld(),player,missile.getFollowedTarget(),missile.getSpellEntry(),new SpellHelper.ImpactContext().position(player.getPos()));
								playerDamageInterface.getMissiles().remove(playerDamageInterface.getMissiles().get(0));
							}
						}

						if (caster.getCurrentSpell() == null) {
							if (!player.getWorld().isClient) {
									if (!playerDamageInterface.getTargets().isEmpty()) {

										Entity missile = playerDamageInterface.getTargets().get(0);

										Optional<BlockPos> air = BlockPos.findClosest(BlockPos.ofFloored(missile.getPos()), 2, 2, pos ->
												player.getWorld().getBlockState(pos).isSolidBlock(player.getWorld(), pos)
														&& !player.getWorld().getBlockState(pos.add(0, 1, 0)).isSolidBlock(player.getWorld(), pos));
										if (air.isPresent()) {
											GlacierSmall newGlacier =
													new GlacierSmall(ICECRASH3, player.getWorld(), -1, player.getRotationVector(), player,
															new SpellHelper.ImpactContext(1.0F, 1.0F, null, SpellPower.getSpellPower(SpellSchools.FROST, player), TargetHelper.TargetingMode.AREA,0));
											newGlacier.setPosition(air.get().getX() + 0.5, air.get().getY() + 1, air.get().getZ() + 0.5);
											player.getWorld().spawnEntity(newGlacier);
										}
										playerDamageInterface.getTargets().remove(missile);
									}

								}

						}
				}
			}
		}
		);
		CustomSpellHandler.register(Identifier.of(MODID,"magic_missile"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
			SpellHelper.performSpell(data1.caster().getWorld(),data1.caster(),Identifier.of(MODID,"magic_missile2"), TargetHelper.SpellTargetResult.of(List.of(data1.targets().get(0))), SpellCast.Action.RELEASE,1.0F);
			return false;
		});
		CustomSpellHandler.register(Identifier.of(MODID,"deathchill"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

			if(data1.caster() instanceof InvokerEntity playerDamageInterface){
				if (!data1.caster().getWorld().isClient) {
					for(Entity entity : data1.targets()) {
						entity.setFrozenTicks(999);
					}
				}
			}
			return false;
		});
		CustomSpellHandler.register(Identifier.of(MODID,"upheaval"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

			if(data1.caster() instanceof InvokerEntity playerDamageInterface){
				if (!data1.caster().getWorld().isClient) {
					for(Entity entity : data1.targets()) {

						if(!playerDamageInterface.getTargets().contains(entity) && entity instanceof LivingEntity) {

								data1.caster().getWorld().playSound(null, data1.caster().getX(), data1.caster().getY(), data1.caster().getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.NEUTRAL, 1.5f, 0.4f / (data1.caster().getWorld().getRandom().nextFloat() * 0.4f + 0.8f));
							playerDamageInterface.glaciersAdd(entity);
						}
					}
				}

			}
			return false;
		});

		CustomSpellHandler.register(Identifier.of(MODID,"heo"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
			Spell spell = SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"heo"));
			if (!data1.caster().getWorld().isClient) {
				if(!data1.targets().isEmpty()) {
					List<GlacierSmall> list2 = new ArrayList<>();
					for (Entity target : data1.targets()) {
						if(target != data1.caster()) {
							GlacierSmall newGlacier = new GlacierSmall(ICECRASH2, data1.caster().getWorld(), -1, data1.caster().getRotationVector(), data1.caster(), data1.impactContext());
							newGlacier.setPosition(target.getX(), target.getY(), target.getZ());
							newGlacier.spell = SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID, "heo"));
							newGlacier.nodamage = true;
							list2.add(newGlacier);
							data1.caster().getWorld().spawnEntity(newGlacier);
							target.timeUntilRegen = 0;
						}
						SpellHelper.performImpacts(data1.caster().getWorld(), data1.caster(), target, data1.caster(), RegistryEntry.of(spell),spell.impact,data1.impactContext());

					}
					return true;
				}

			}
			return false;
		});
		CustomSpellHandler.register(Identifier.of(MODID,"eldritch_blast"),(data) -> {
					CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
					if (!data1.caster().getWorld().isClient) {
						if (!data1.targets().isEmpty()) {
							for (Entity target : data1.targets()) {
								SpellHelper.performSpell(data1.caster().getWorld(), data1.caster(), Identifier.of(MODID,"eldritch_blasts"), TargetHelper.SpellTargetResult.of(List.of(target)), SpellCast.Action.RELEASE,1.0F);
							}
						}
					}
					return false;

				}
		);
		CustomSpellHandler.register(Identifier.of(MODID,"power_word_kill"),(data) -> {
					CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
					if (!data1.caster().getWorld().isClient) {
						if (!data1.targets().isEmpty()) {
							for (Entity target : data1.targets()) {
								if(target instanceof LivingEntity living){
									if(living.getHealth() <= data1.impactContext().power().randomValue()){
										living.damage(living.getDamageSources().genericKill(),9999999);
									}
								}
							}
						}
					}
					return false;

				}
				);
					CustomSpellHandler.register(Identifier.of(MODID,"glacialhammer"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
						Spell spell = SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"glacialhammer"));

						if (!data1.caster().getWorld().isClient) {
				if(!data1.targets().isEmpty()) {
					List<GlacierSmall> list2 = new ArrayList<>();
					for (Entity target : data1.targets()) {
						if (target instanceof GlacierSmall small) {
							ParticleHelper.sendBatches(small,SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"glacialhammer")).impact[0].particles);
							List<LivingEntity> list = small.getWorld().getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class),small.getBoundingBox().stretch(1.5,1.5,1.5).expand(4),Objects::nonNull);
							for(LivingEntity living : list){

								SpellHelper.performImpacts(living.getWorld(),data1.caster(),living,data1.caster(),RegistryEntry.of(spell),spell.impact, data1.impactContext());
							}
							small.playSound(SoundEvents.BLOCK_GLASS_BREAK,1,1);
							small.discard();
						}
						else{
							if(list2.isEmpty()) {
								GlacierSmall newGlacier = new GlacierSmall(ICECRASH2, data1.caster().getWorld(), -1, data1.caster().getRotationVector(), data1.caster(), data1.impactContext());
								newGlacier.setPosition(target.getX(), target.getY(), target.getZ());
								list2.add(newGlacier);
								data1.caster().getWorld().spawnEntity(newGlacier);
							}

							target.timeUntilRegen = 0;

							SpellHelper.performImpacts(target.getWorld(),data1.caster(),target,data1.caster(),RegistryEntry.of(spell),spell.impact, data1.impactContext());

						}
					}
				}

			}
			return true;
		});
		CustomSpellHandler.register(Identifier.of(MODID,"sharedsuffering"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
			Spell spell = SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"sharedsuffering"));

			if (!data1.caster().getWorld().isClient) {
				if(!data1.targets().isEmpty()) {
					data1.caster().damage(data1.caster().getDamageSources().freeze(), 1.0f);

					for (Entity target : data1.targets()) {
						if (target instanceof LivingEntity living) {
							living.timeUntilRegen = 0;

							living.damage(living.getDamageSources().freeze(), 1.0f);

						}

						SpellHelper.performImpacts(data1.caster().getWorld(), (LivingEntity) data1.caster(), target,data1.caster(), RegistryEntry.of(spell),spell.impact, data1.impactContext());
					}
				}

			}
			return false;
		});
		CustomSpellHandler.register(Identifier.of(MODID,"glacier"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

			if (!data1.caster().getWorld().isClient) {
				Vec3d direction = data1.caster().getPos().add(data1.caster().getRotationVector().getX(),0,data1.caster().getRotationVector().getZ());
				Optional<BlockPos> air = BlockPos.findClosest(BlockPos.ofFloored(direction),2,2, pos ->
						data1.caster().getWorld().getBlockState(pos).isSolidBlock(data1.caster().getWorld(),pos)
				&& !data1.caster().getWorld().getBlockState(pos.add(0,1,0)).isSolidBlock(data1.caster().getWorld(),pos));
				if(air.isPresent()) {
					GlacierSmall newGlacier = new GlacierSmall(ICECRASH, data1.caster().getWorld(), 2, data1.caster().getRotationVector(), data1.caster(), data1.impactContext());
					newGlacier.setPosition(air.get().getX()+0.5,air.get().getY()+1,air.get().getZ()+0.5);
					data1.caster().getWorld().spawnEntity(newGlacier);
				}

			}
			return true;
		});
		CustomSpellHandler.register(Identifier.of(MODID,"frozen_resonance"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

			if (!data1.caster().getWorld().isClient) {
				List<Entity> list = data1.caster().getWorld().getOtherEntities(data1.caster(),data1.caster().getBoundingBox().stretch(1.5,1.5,1.5).expand(4,0,4));
				ParticleHelper.sendBatches( data1.caster(), SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"frozen_resonance")).release.particles);
				SpellHelper.ImpactContext impactContext = new SpellHelper.ImpactContext(1.0F, 1.0F, null, data1.impactContext().power(), data1.impactContext().targetingMode(),0);
				Spell spell = SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"frozen_resonance"));

				for(Entity entity : list){
					SpellHelper.performImpacts(entity.getWorld(), data1.caster(), entity,data1.caster(),RegistryEntry.of(spell),spell.impact, impactContext);
				}
				List<GlacierSmall> small = data1.caster().getWorld().getEntitiesByType(TypeFilter.instanceOf(GlacierSmall.class),data1.caster().getBoundingBox().expand(SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"frozen_resonance")).range), glacier -> glacier.lastresonance == 0);
				if(!small.isEmpty()) {

					GlacierSmall small1 = small.get(data1.caster().getRandom().nextInt(small.size()));
					List<Entity> list2 = small1.getWorld().getOtherEntities(small1, small1.getBoundingBox().stretch(1.5, 1.5, 1.5).expand(4, 0, 4));
					ParticleHelper.sendBatches(small1, SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID, "frozen_resonance")).release.particles);
					SoundHelper.playSound(data1.caster().getWorld(), small1, SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID, "frozen_resonance")).release.sound);
					for (Entity entity : list2) {
						SpellHelper.performImpacts(entity.getWorld(), (LivingEntity) data1.caster(), entity, data1.caster(), RegistryEntry.of(spell),spell.impact, impactContext);
					}
					small1.lastresonance = 12;
				}
				if(small.isEmpty()){
					for(int ii = 0; ii < 4; ii++) {
						int[] sign1 = {1,-1,1,-1};
						int[] sign2 = {1,-1,-1,1};
						Vec3d direction = data1.caster().getPos().add(sign1[ii]*2+ data1.caster().getRandom().nextDouble() * 4*sign1[ii], 0, sign2[ii]*2+data1.caster().getRandom().nextDouble() * 4*sign2[ii]);
						Optional<BlockPos> air = BlockPos.findClosest(BlockPos.ofFloored(direction), 4, 4, pos ->
								data1.caster().getWorld().getBlockState(pos).isSolidBlock(data1.caster().getWorld(), pos)
										&& !data1.caster().getWorld().getBlockState(pos.add(0, 1, 0)).isSolidBlock(data1.caster().getWorld(), pos));
						if (air.isPresent()) {
							GlacierSmall newGlacier = new GlacierSmall(ICECRASH2, data1.caster().getWorld(), -1, data1.caster().getRotationVector(), data1.caster(), data1.impactContext());
							newGlacier.setPosition(air.get().getX() + 0.5, air.get().getY() + 1, air.get().getZ() + 0.5);
							newGlacier.age = 160;
							data1.caster().getWorld().spawnEntity(newGlacier);
						}
					}
				}
			}
			return false;
		});
		CustomSpellHandler.register(Identifier.of(MODID,"enders_gaze"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

				if (!data1.caster().getWorld().isClient) {
					for(Entity entity : data1.targets()) {
						if(entity instanceof LivingEntity living) {

							for (int i = 1; i < 6; i++) {
								EndersGaze endersGaze = new EndersGaze(GAZEHITTER, data1.caster().getWorld(), data1.caster(), living, i);
								endersGaze.setPosition(living.getEyePos());
								endersGaze.power = data1.impactContext().power();
								endersGaze.spell = SpellRegistry.from(data1.caster().getWorld()).get(Identifier.of(MODID,"enders_gaze"));
								endersGaze.context = data1.impactContext();
								if (!data1.caster().getWorld().isClient()) {
									data1.caster().getWorld().spawnEntity(endersGaze);
								}
							}
							return true;

						}
					}
				}
			return true;
		});
		CombatEvents.SPELL_CAST.register(args -> {

		});
		CustomSpellHandler.register(Identifier.of(MODID,"blink"),(data) -> {
					CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

					if (!data1.caster().getWorld().isClient) {

						data1.caster().getWorld().playSound(null, data1.caster().getX(), data1.caster().getY(), data1.caster().getZ(), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.NEUTRAL, 0.5f, 0.4f / (data1.caster().getWorld().getRandom().nextFloat() * 0.4f + 0.8f));

						EnderPearlEntity pearl = new EnderPearlEntity(data1.caster().getWorld(), data1.caster());
						pearl.setPos(data1.caster().getX(), data1.caster().getEyeY() - 0.1f, data1.caster().getZ());
						pearl.setVelocity(data1.caster(), data1.caster().getPitch(), data1.caster().getYaw(), 0.0f, 1.5f, 1.0f);
						data1.caster().getWorld().spawnEntity(pearl);

						}
					return true;

				}
		);

		CustomSpellHandler.register(Identifier.of(MODID,"hijack"),(data) -> {
			CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

			if (!data1.caster().getWorld().isClient) {
				for(Entity entity2 : data1.targets()) {
					if (entity2 instanceof LivingEntity living) {
						data1.caster().getWorld().playSound(null, data1.caster().getX(), data1.caster().getY(), data1.caster().getZ(), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.NEUTRAL, 0.5f, 0.4f / (data1.caster().getWorld().getRandom().nextFloat() * 0.4f + 0.8f));

						EnderPearlEntity pearl = new EnderPearlEntity(data1.caster().getWorld(), living);
						pearl.setPos(data1.caster().getX(),data1.caster().getEyeY() - 0.1f, data1.caster().getZ());
						pearl.setVelocity(data1.caster(), data1.caster().getPitch(), data1.caster().getYaw(), 0.0f, 1.5f, 1.0f);
						living.getWorld().spawnEntity(pearl);
					}

				}
			}
			return true;
		});

	}
}