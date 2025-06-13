package com.invoke.data;


import com.invoke.spells.InvocationSpells;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.spell_engine.api.datagen.SpellGenerator;
import net.spell_engine.rpg_series.datagen.RPGSeriesDataGen;

import java.util.concurrent.CompletableFuture;

public class InvokeDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(InvocationSpellGen::new);
    }



    public static class InvocationSpellGen extends SpellGenerator {
        public InvocationSpellGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generateSpells(Builder builder) {
            for (var entry: InvocationSpells.entries) {
                builder.add(entry.id(), entry.spell());
            }
        }
    }


}