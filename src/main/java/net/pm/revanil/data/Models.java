package net.pm.revanil.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.pm.revanil.world.item.RItems;
import net.pm.revanil.world.level.block.RBlocks;

public class Models extends FabricModelProvider {

    public Models(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(RBlocks.MALACHITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(RBlocks.HEMATITE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(RItems.IRON_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RItems.GOLD_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RItems.DIAMOND_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RItems.MALACHITE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RItems.HEMATITE_CHUNK, ModelTemplates.FLAT_ITEM);
    }
}
