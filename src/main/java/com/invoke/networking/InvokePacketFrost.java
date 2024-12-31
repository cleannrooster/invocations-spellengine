package com.invoke.networking;

import com.invoke.InvokeMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record InvokePacketFrost(UUID uuid) implements CustomPayload {
    public static final Id<InvokePacketFrost> FROST = new Id<>(Identifier.of(InvokeMod.MODID,"frost"));

    public static final PacketCodec<RegistryByteBuf, InvokePacketFrost> PACKET_CODEC = Uuids.PACKET_CODEC.xmap(InvokePacketFrost::new, InvokePacketFrost::uuid ).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return FROST;
    }
}

