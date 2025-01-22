package com.invoke.mixin;

import com.invoke.InvokeMod;
import com.invoke.interfaces.InvokerEntity;
import com.invoke.networking.InvokePacket;
import com.invoke.networking.InvokePacketFire;
import com.invoke.networking.InvokePacketFrost;
import com.invoke.networking.ResetPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.registry.SpellRegistry;
import net.spell_engine.internals.SpellContainerHelper;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.casting.SpellCast;
import net.spell_engine.internals.casting.SpellCasterClient;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellSchools;
import org.apache.logging.log4j.core.jmx.Server;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

@Mixin(SpellHelper.class)
public class SpellCastMixin {
    @Inject(at = @At("HEAD"), method = "performSpell", cancellable = true)
    private static void invocationSpell(World world, PlayerEntity player, Identifier spellId, TargetHelper.SpellTargetResult targets, SpellCast.Action action, float progress, CallbackInfo callbackInfo) {
        if (player != null  && SpellContainerHelper.getAvailable( player) != null && SpellContainerHelper.getAvailable( player).spell_ids() != null && SpellContainerHelper.getAvailable( player).spell_ids().contains("invoke:runic_invocation")) {
            if (player instanceof InvokerEntity invokerEntity && action.equals(SpellCast.Action.RELEASE) &&
                    !spellId.toString().contains("invoke")) {
                if (SpellRegistry.from(player.getWorld()).get(spellId).school == SpellSchools.FIRE) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new InvokePacketFire(UUID.randomUUID()));
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);
                    invokerEntity.InvokeSet(1, 2);
                }
                if (SpellRegistry.from(player.getWorld()).get(spellId).school == SpellSchools.FROST) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new InvokePacketFrost(UUID.randomUUID()));
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(2, 2);

                }
                if (SpellRegistry.from(player.getWorld()).get(spellId).school == SpellSchools.ARCANE) {

                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new InvokePacket(UUID.randomUUID()));
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(3, 2);


                }

            }
            if (player instanceof InvokerEntity invokerEntity && (spellId.getPath().equals("rah") || spellId.getPath().equals("gon") || spellId.getPath().equals("heo"))) {

                if (SpellRegistry.from(player.getWorld()).get(spellId).school == SpellSchools.FIRE) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new InvokePacketFire(UUID.randomUUID()));
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);
                    invokerEntity.InvokeSet(1, 2);
                }
                if (SpellRegistry.from(player.getWorld()).get(spellId).school == SpellSchools.FROST) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new InvokePacketFrost(UUID.randomUUID()));
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(2, 2);

                }
                if (SpellRegistry.from(player.getWorld()).get(spellId).school == SpellSchools.ARCANE) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new InvokePacket(UUID.randomUUID()));

                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(3, 2);


                }

            }

        }
        if (player instanceof InvokerEntity invokerEntity && spellId.toString().contains("invoke") ) {
            if (!spellId.getPath().equals("rah") && !spellId.getPath().equals("gon") && !spellId.getPath().equals("heo")) {
                if (player instanceof ServerPlayerEntity entity)
                    ServerPlayNetworking.send((ServerPlayerEntity) entity, new ResetPacket(UUID.randomUUID()));

                invokerEntity.InvokeSet(0, 0);
                invokerEntity.InvokeSet(0, 1);
                invokerEntity.InvokeSet(0, 2);
            }

        }
    }
}
