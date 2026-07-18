package net.pm.revanil.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.pm.revanil.world.item.RItems;
import net.pm.revanil.world.level.block.RBlocks;

import java.util.concurrent.CompletableFuture;

public class RBlockLoot extends FabricBlockLootSubProvider {
    public RBlockLoot(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(RBlocks.MALACHITE_BLOCK, createSilkTouchDispatchTable(RBlocks.MALACHITE_BLOCK, this.applyExplosionDecay(RBlocks.MALACHITE_BLOCK, LootItem.lootTableItem(RItems.MALACHITE_CHUNK).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))).apply(LimitCount.limitCount(IntRange.range(1, 4))))));
        add(RBlocks.HEMATITE_BLOCK, createSilkTouchDispatchTable(RBlocks.HEMATITE_BLOCK, this.applyExplosionDecay(RBlocks.HEMATITE_BLOCK, LootItem.lootTableItem(RItems.HEMATITE_CHUNK).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))).apply(LimitCount.limitCount(IntRange.range(1, 4))))));
    }
}
