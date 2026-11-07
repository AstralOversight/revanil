package net.pm.revanil.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntLimit;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.pm.revanil.world.item.RItems;
import net.pm.revanil.world.level.block.RBlocks;

import java.util.concurrent.CompletableFuture;

public class RBlockLoot extends FabricBlockLootSubProvider {
    public RBlockLoot(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(RBlocks.MALACHITE_BLOCK, createSilkTouchDispatchTable(RBlocks.MALACHITE_BLOCK, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(RBlocks.MALACHITE_BLOCK, (((UniformContainerBase.Builder)LootItem.lootTableItem(RItems.MALACHITE_CHUNK).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))).apply(ApplyBonusCount.addUniformBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))).apply(LimitCount.limitCount(IntLimit.range(1, 4))))));
        add(RBlocks.HEMATITE_BLOCK, createSilkTouchDispatchTable(RBlocks.HEMATITE_BLOCK, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(RBlocks.HEMATITE_BLOCK, (((UniformContainerBase.Builder)LootItem.lootTableItem(RItems.HEMATITE_CHUNK).apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))).apply(ApplyBonusCount.addUniformBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))).apply(LimitCount.limitCount(IntLimit.range(1, 4))))));
    }
}
