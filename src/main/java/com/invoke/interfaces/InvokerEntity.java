package com.invoke.interfaces;

import net.minecraft.entity.Entity;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.particle.ParticleHelper;

import java.util.List;

public interface InvokerEntity{
      void InvokeAdd(int value);
      int getInvokeValue();
      void resetInvoke();
      void missilesAdd(SpellProjectile entity);
      void missilesRefresh();
      List<SpellProjectile> getMissiles();
      void glaciersAdd(Entity entity);
      List<Entity> getTargets();


}
