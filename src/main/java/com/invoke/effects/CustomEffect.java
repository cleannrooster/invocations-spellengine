package com.invoke.effects;

import com.invoke.InvokeMod;
import com.invoke.interfaces.InvokerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.spell_engine.internals.SpellContainerHelper.containerFromItemStack;

public class CustomEffect extends StatusEffect {
    public CustomEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

}
