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

public record InvokePacket(UUID uuid) implements CustomPayload {
    public static final Id<InvokePacket> ARCANE = new Id<>(Identifier.of(InvokeMod.MODID,"arcane"));

    public static final PacketCodec<RegistryByteBuf, InvokePacket> PACKET_CODEC = Uuids.PACKET_CODEC.xmap(InvokePacket::new, InvokePacket::uuid ).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return ARCANE;
    }
}

