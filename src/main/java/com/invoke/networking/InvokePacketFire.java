package com.invoke.networking;

import com.invoke.InvokeMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

import java.util.UUID;

public record InvokePacketFire(UUID uuid) implements CustomPayload {
    public static final Id<InvokePacketFire> FIRE  = new CustomPayload.Id<>(Identifier.of(InvokeMod.MODID,"fire"));

    public static final PacketCodec<RegistryByteBuf, InvokePacketFire> PACKET_CODEC = Uuids.PACKET_CODEC.xmap(InvokePacketFire::new, InvokePacketFire::uuid ).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return FIRE;
    }
}

