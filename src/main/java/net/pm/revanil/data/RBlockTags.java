package net.pm.revanil.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.pm.revanil.Revanil;
import net.pm.revanil.references.RBlockItemIds;

import java.util.concurrent.CompletableFuture;

public class RBlockTags extends FabricTagsProvider.BlockTagsProvider {
    public RBlockTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .forceAddTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .setReplace(true);

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .forceAddTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .forceAddTag(BlockTags.NEEDS_IRON_TOOL)
                .forceAddTag(BlockTags.NEEDS_STONE_TOOL)
                .setReplace(true);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(RBlockItemIds.MALACHITE_BLOCK.block());

        tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                .add(BlockItemIds.PRISMARINE.block())
                .add(RBlockItemIds.MALACHITE_BLOCK.block())
                .add(RBlockItemIds.HEMATITE_BLOCK.block());

        recreateNeedsTags(wrapperLookup);
    }

    void recreateNeedsTags(HolderLookup.Provider wrapperLookup) {
        //Basalts?
        //Blue Ice
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockItemIds.IRON_ORE.block()).add(BlockItemIds.RAW_IRON_BLOCK.block()).add(BlockItemIds.IRON_BLOCK.block()) //shallow iron
                .add(BlockItemIds.GOLD_ORE.block()).add(BlockItemIds.RAW_GOLD_BLOCK.block()).add(BlockItemIds.GOLD_BLOCK.block()) //shallow gold
                .add(BlockItemIds.DEEPSLATE.block()) //deep TODO: am I really doing this?
                .add(BlockItemIds.DEEPSLATE_LAPIS_ORE.block()) //deep lapis
                .add(BlockItemIds.EMERALD_ORE.block()).add(BlockItemIds.EMERALD_BLOCK.block()) //shallow emerald
                .add(RBlockItemIds.HEMATITE_BLOCK.block())
                .setReplace(true);
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockItemIds.DEEPSLATE_IRON_ORE.block()) //deep iron
                .add(BlockItemIds.DEEPSLATE_GOLD_ORE.block()) //deep gold
                .add(BlockItemIds.DEEPSLATE_EMERALD_ORE.block()) //deep emerald
                .add(BlockItemIds.DIAMOND_ORE.block()).add(BlockItemIds.DIAMOND_BLOCK.block()) //shallow diamond
                .add(BlockItemIds.REDSTONE_ORE.block()).add(BlockItemIds.REDSTONE_BLOCK.block()) //shallow redstone
                .setReplace(true);
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockItemIds.DEEPSLATE_DIAMOND_ORE.block()) //deep diamond
                .add(BlockItemIds.DEEPSLATE_REDSTONE_ORE.block()) //deep redstone
                .add(BlockItemIds.ANCIENT_DEBRIS.block()).add(BlockItemIds.NETHERITE_BLOCK.block()) //netherite TODO: maybe not? it's a sidegrade to diamond now...
                .add(BlockItemIds.OBSIDIAN.block()).add(BlockItemIds.CRYING_OBSIDIAN.block()).add(BlockItemIds.RESPAWN_ANCHOR.block())
                .setReplace(true);
    }

    public Identifier id(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }
}
