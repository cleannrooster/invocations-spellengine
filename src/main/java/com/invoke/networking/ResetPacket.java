package com.invoke.networking;

import com.invoke.InvokeMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record ResetPacket(UUID uuid) implements CustomPayload {

    public static final Id<ResetPacket> RESET = new Id<>(Identifier.of(InvokeMod.MODID,"reset"));

    public static final PacketCodec<RegistryByteBuf, ResetPacket> PACKET_CODEC = Uuids.PACKET_CODEC.xmap(ResetPacket::new, ResetPacket::uuid ).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return RESET;
    }
}

