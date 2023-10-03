package com.invoke.interfaces;

import net.minecraft.entity.Entity;
import net.spell_engine.entity.SpellProjectile;
import net.spell_engine.particle.ParticleHelper;

import java.util.List;

public interface InvokerEntity{
      void InvokeAdd(int value);
      int getInvokeValue();
      int getcasttime();
      int addcasttime(int value);

      void resetInvoke();
      void missilesAdd(SpellProjectile entity);
      void missilesRefresh();
      void targetsRefresh();

      List<SpellProjectile> getMissiles();
      void glaciersAdd(Entity entity);
      List<Entity> getTargets();


}
