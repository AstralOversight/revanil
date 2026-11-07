package net.pm.revanil.client;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.pm.revanil.Revanil;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistries extends FabricDynamicRegistryProvider {
    public DynamicRegistries(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        entries.addAll(provider.lookupOrThrow(Registries.NOISE_SETTINGS));
        entries.addAll(provider.lookupOrThrow(Registries.MATERIAL_RULE));
        entries.addAll(provider.lookupOrThrow(LithostitchedRegistries.WORLDGEN_MODIFIER));
    }

    @Override
    public String getName() {
        return Revanil.MOD_ID;
    }
}
