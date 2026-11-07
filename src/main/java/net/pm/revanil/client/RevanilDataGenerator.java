package net.pm.revanil.client;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.pm.revanil.data.*;
import net.pm.revanil.data.RLithoMaterialRules;
import net.pm.revanil.data.worldgen.material.RMaterialRules;

public class RevanilDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(RBlockLoot::new);
        pack.addProvider(RBlockTags::new);
        pack.addProvider(DynamicRegistries::new);
        pack.addProvider(RItemTags::new);
        pack.addProvider(Lang::new);
        pack.addProvider(Models::new);
        pack.addProvider(Recipes::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.MATERIAL_RULE, RMaterialRules::bootstrap);
        registryBuilder.add(LithostitchedRegistries.WORLDGEN_MODIFIER, RLithoMaterialRules::bootstrap);
        registryBuilder.add(LithostitchedRegistries.WORLDGEN_MODIFIER, RLithoRemove::bootstrap);
    }
}
