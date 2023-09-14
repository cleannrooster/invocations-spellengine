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
import net.spell_engine.internals.*;
import net.spell_engine.particle.ParticleHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.SpellPower;

import java.util.List;
import java.util.function.Predicate;

import static com.invoke.InvokeMod.FLAMERUSH;
import static com.invoke.InvokeMod.MODID;
import static net.spell_engine.internals.SpellContainerHelper.containerFromItemStack;
import static net.spell_engine.internals.SpellHelper.impactTargetingMode;

public class CustomStatusEffectInvoking extends StatusEffect {
    public CustomStatusEffectInvoking(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(entity instanceof PlayerEntity player1 && player1.hasStatusEffect(FLAMERUSH)){
            player1.removeStatusEffect(FLAMERUSH);
            ItemStack stack = player1.getMainHandStack();
            Predicate<Entity> selectionPredicate = (target2) -> {
                return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.AREA, TargetHelper.Intent.HARMFUL, player1, target2)
                );
            };
            Spell spell = SpellRegistry.getSpell(new Identifier(InvokeMod.MODID,"greaterfireball"));
            Spell spell1 = SpellRegistry.getSpell(new Identifier(InvokeMod.MODID,"meteorrush"));

            if(player1 instanceof SpellCasterEntity entity1) {

                int i = 0;
                List<Entity> targets = player1.getWorld().getOtherEntities(player1, player1.getBoundingBox().expand(spell1.range), selectionPredicate);

                if(entity1.getCurrentSpellId() != null) {
                    LivingEntity living = (LivingEntity) entity;
                    i = (int) (entity1.getCurrentCastProgress() * SpellHelper.getCastDuration(living, SpellRegistry.getSpell(entity1.getCurrentSpellId())));
                }
                SpellHelper.ImpactContext context = new SpellHelper.ImpactContext(1.0F, 1.0F, (Vec3d) null, SpellPower.getSpellPower(spell1.school, player1), impactTargetingMode(spell1));

                for (Entity target1 : targets) {
                    SpellHelper.performImpacts(player1.getWorld(), player1, target1, SpellRegistry.getSpell(new Identifier(InvokeMod.MODID, "greaterfireball")), new SpellHelper.ImpactContext());
                }
                ParticleHelper.sendBatches(player1, spell1.release.particles);


            }
        }
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
