package com.invoke.mixin;

import com.invoke.InvokeMod;
import com.invoke.interfaces.InvokerEntity;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.internals.SpellContainerHelper;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import net.spell_engine.internals.casting.SpellCast;
import net.spell_engine.internals.casting.SpellCasterClient;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_power.api.SpellSchools;
import org.apache.logging.log4j.core.jmx.Server;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SpellHelper.class)
public class SpellCastMixin {
    @Inject(at = @At("HEAD"), method = "performSpell", cancellable = true)
    private static void invocationSpell(World world, PlayerEntity player, Identifier spellId, List<Entity> targets, SpellCast.Action action, float progress, CallbackInfo callbackInfo) {
        if (player != null  && SpellContainerHelper.getEquipped(player.getMainHandStack(), player) != null && SpellContainerHelper.getEquipped(player.getMainHandStack(), player).spell_ids != null && SpellContainerHelper.getEquipped(player.getMainHandStack(), player).spell_ids.contains("invoke:runicinvocation")) {

            if (targets.isEmpty() && (SpellRegistry.getSpell(spellId).release.target.type.equals(Spell.Release.Target.Type.CURSOR) || SpellRegistry.getSpell(spellId).release.target.type.equals(Spell.Release.Target.Type.METEOR))) {
                return;
            }
            if (player instanceof InvokerEntity invokerEntity && action.equals(SpellCast.Action.RELEASE) &&
                    !spellId.toString().contains("invoke")) {

                if (SpellRegistry.getSpell(spellId).school == SpellSchools.FIRE) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new Identifier(InvokeMod.MODID, "fire"), PacketByteBufs.empty());
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);
                    invokerEntity.InvokeSet(1, 2);
                }
                if (SpellRegistry.getSpell(spellId).school == SpellSchools.FROST) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new Identifier(InvokeMod.MODID, "frost"), PacketByteBufs.empty());
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(2, 2);

                }
                if (SpellRegistry.getSpell(spellId).school == SpellSchools.ARCANE) {

                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new Identifier(InvokeMod.MODID, "arcane"), PacketByteBufs.empty());
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(3, 2);


                }

            }
            if (player instanceof InvokerEntity invokerEntity && (spellId.getPath().equals("rah") || spellId.getPath().equals("gon") || spellId.getPath().equals("heo"))) {

                if (SpellRegistry.getSpell(spellId).school == SpellSchools.FIRE) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new Identifier(InvokeMod.MODID, "fire"), PacketByteBufs.empty());
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);
                    invokerEntity.InvokeSet(1, 2);
                }
                if (SpellRegistry.getSpell(spellId).school == SpellSchools.FROST) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new Identifier(InvokeMod.MODID, "frost"), PacketByteBufs.empty());
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(2, 2);

                }
                if (SpellRegistry.getSpell(spellId).school == SpellSchools.ARCANE) {

                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new Identifier(InvokeMod.MODID, "arcane"), PacketByteBufs.empty());
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[1], 0);
                    invokerEntity.InvokeSet(invokerEntity.getInvokeValue()[2], 1);

                    invokerEntity.InvokeSet(3, 2);


                }

            }
            if (player instanceof InvokerEntity invokerEntity && spellId.toString().contains("invoke") && action.equals(SpellCast.Action.RELEASE)) {
                if (!spellId.getPath().equals("rah") && !spellId.getPath().equals("gon") && !spellId.getPath().equals("heo")) {
                    if (player instanceof ServerPlayerEntity entity)
                        ServerPlayNetworking.send((ServerPlayerEntity) entity, new Identifier(InvokeMod.MODID, "reset"), PacketByteBufs.empty());

                    invokerEntity.InvokeSet(0, 0);
                    invokerEntity.InvokeSet(0, 1);
                    invokerEntity.InvokeSet(0, 2);
                }

            }
        }
    }
}
