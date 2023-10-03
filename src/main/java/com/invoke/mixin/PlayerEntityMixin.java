package com.invoke.mixin;

import com.invoke.InvokeMod;
import com.invoke.interfaces.InvokerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.internals.SpellContainerHelper;
import net.spell_power.api.MagicSchool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements InvokerEntity {
	int casttime = 0;
	public int invokeValue = -1;
	public List<SpellProjectile> magicmissiles = new ArrayList<>();
	public List<Entity> glaciertargets = new ArrayList<>();

	public int getcasttime() {
		return casttime;
	}
	public int addcasttime(int value) {
		casttime += value;
		return casttime;
	}

	public void missilesAdd(SpellProjectile entity) {
		magicmissiles.add(entity);
	}

	@Override
	public void missilesRefresh() {
		magicmissiles = new ArrayList<>();
	}

	@Override
	public void targetsRefresh() {
		glaciertargets = new ArrayList<>();
	}

		@Override
	public List<SpellProjectile> getMissiles() {
		return magicmissiles;
	}

	@Override
	public void glaciersAdd(Entity entity) {
		glaciertargets.add(entity);
	}

	public List<Entity> getTargets() {
		return glaciertargets;
	}
/*
	@Inject(at = @At("HEAD"), method = "interact")
*/
	/*private void init(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> info) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if(player instanceof SpellCasterEntity caster && player.getStackInHand(hand).getItem() instanceof InvokerOrb orb){
			if(caster.getCurrentSpell() != null && caster.getCurrentSpell().learn.tier != 4){
				InvokeAdd(caster.getCurrentSpell().learn.tier);
				SpellContainerHelper.containerFromItemStack(player.getStackInHand(hand)).spell_ids = List.of();
				if(orb.getSchool().equals(MagicSchool.ARCANE)) {
				}
				if(orb.getSchool().equals(MagicSchool.FIRE)) {
					SpellContainerHelper.addSpell(new Identifier(InvokeMod.MODID,FIRE_INVOKER_LIST[getInvokeValue()]),player.getStackInHand(hand));

				}
				if(orb.getSchool().equals(MagicSchool.FROST)) {
					SpellContainerHelper.addSpell(new Identifier(InvokeMod.MODID,FROST_INVOKER_LIST[getInvokeValue()]),player.getStackInHand(hand));

				}
			}
		}
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}*/

	@Override
	public void InvokeAdd(int value) {
		invokeValue += value;
	}

	@Override
	public int getInvokeValue() {
		return invokeValue % 9;
	}
	@Override
	public void resetInvoke() {
		invokeValue = -1;
	}

}