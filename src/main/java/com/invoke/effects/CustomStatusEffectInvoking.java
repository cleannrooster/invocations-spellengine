package com.invoke.effects;

import com.invoke.InvokeMod;
import com.invoke.interfaces.InvokerEntity;
import com.invoke.mixin.PlayerEntityMixin;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.SpellInfo;
import net.spell_engine.internals.*;
import net.spell_engine.internals.casting.SpellCasterEntity;
import net.spell_engine.particle.ParticleHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellPower;

import java.util.List;
import java.util.function.Predicate;

import static com.invoke.InvokeMod.MODID;
import static net.spell_engine.internals.SpellContainerHelper.containerFromItemStack;
import static net.spell_engine.internals.SpellHelper.impactTargetingMode;

public class CustomStatusEffectInvoking extends StatusEffect {
    public CustomStatusEffectInvoking(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        if(entity instanceof InvokerEntity invokerEntity){
            invokerEntity.resetInvoke();


            if(containerFromItemStack(entity.getMainHandStack()) != null) {

                List<String> stringlist = containerFromItemStack(entity.getMainHandStack()).spell_ids;

                NbtList list = new NbtList();
                for(String string : stringlist){
                    if(!string.contains("invoke"))
                        list.add(NbtString.of(string));
                }
                list.add(NbtString.of(new Identifier(MODID,"nullinvoke").toString()));
                NbtCompound object = new NbtCompound();
                NbtCompound object1 = entity.getMainHandStack().getOrCreateNbt();

                object.putBoolean("is_proxy", true);
                object1.remove("spell_container");
                object.put("spell_ids", list);
                object1.put("spell_container", object);
            }

        }
        super.onRemoved(entity, attributes, amplifier);
    }
}
