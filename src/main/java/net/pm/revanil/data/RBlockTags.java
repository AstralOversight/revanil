package net.pm.revanil.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.pm.revanil.Revanil;
import net.pm.revanil.world.level.block.RBlocks;

import java.util.concurrent.CompletableFuture;

public class RBlockTags extends FabricTagsProvider.BlockTagsProvider {
    public RBlockTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public TagKey<Block> CUT_COPPER = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "cut_copper"));
    public TagKey<Block> CUT_COPPER_STAIRS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "cut_copper_stairs"));
    public TagKey<Block> CUT_COPPER_SLAB = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "cut_copper_slab"));
    public TagKey<Block> CHISELED_COPPER = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "chiseled_copper"));
    public TagKey<Block> COPPER_GRATE = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "copper_grate"));
    public TagKey<Block> COPPER_BULB = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "copper_bulb"));
    public TagKey<Block> COPPER_DOOR = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "copper_door"));
    public TagKey<Block> COPPER_TRAPDOOR = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "copper_trapdoor"));

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .forceAddTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .setReplace(true);

        valueLookupBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .forceAddTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .forceAddTag(BlockTags.NEEDS_IRON_TOOL)
                .forceAddTag(BlockTags.NEEDS_STONE_TOOL)
                .setReplace(true);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(RBlocks.MALACHITE_BLOCK);

        valueLookupBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                .add(Blocks.PRISMARINE)
                .add(RBlocks.MALACHITE_BLOCK, RBlocks.HEMATITE_BLOCK);

        addMissingTags(wrapperLookup);
        recreateNeedsTags(wrapperLookup);
    }

    void addMissingTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(CUT_COPPER)
                .add(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER)
                .add(Blocks.WAXED_CUT_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER);
        valueLookupBuilder(CUT_COPPER_STAIRS)
                .add(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_CUT_COPPER_STAIRS, Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS, Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS, Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        valueLookupBuilder(CUT_COPPER_SLAB)
                .add(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_CUT_COPPER_SLAB, Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        valueLookupBuilder(CHISELED_COPPER)
                .add(Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER)
                .add(Blocks.WAXED_CHISELED_COPPER, Blocks.WAXED_EXPOSED_CHISELED_COPPER, Blocks.WAXED_WEATHERED_CHISELED_COPPER, Blocks.WAXED_OXIDIZED_CHISELED_COPPER);
        valueLookupBuilder(COPPER_GRATE)
                .add(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE)
                .add(Blocks.WAXED_COPPER_GRATE, Blocks.WAXED_EXPOSED_COPPER_GRATE, Blocks.WAXED_WEATHERED_COPPER_GRATE, Blocks.WAXED_OXIDIZED_COPPER_GRATE);
        valueLookupBuilder(COPPER_BULB)
                .add(Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB)
                .add(Blocks.WAXED_COPPER_BULB, Blocks.WAXED_EXPOSED_COPPER_BULB, Blocks.WAXED_WEATHERED_COPPER_BULB, Blocks.WAXED_OXIDIZED_COPPER_BULB);
        valueLookupBuilder(COPPER_DOOR)
                .add(Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR)
                .add(Blocks.WAXED_COPPER_DOOR, Blocks.WAXED_EXPOSED_COPPER_DOOR, Blocks.WAXED_WEATHERED_COPPER_DOOR, Blocks.WAXED_OXIDIZED_COPPER_DOOR);
        valueLookupBuilder(COPPER_TRAPDOOR)
                .add(Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_COPPER_TRAPDOOR, Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR, Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR, Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR);
    }

    void recreateNeedsTags(HolderLookup.Provider wrapperLookup) {
        //Basalts?
        //Blue Ice
        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(Blocks.IRON_ORE, Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK) //shallow iron
                .add(Blocks.GOLD_ORE, Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK) //shallow gold
                .add(Blocks.DEEPSLATE) //deep TODO: am I really doing this?
                .add(Blocks.DEEPSLATE_LAPIS_ORE) //deep lapis
                .add(Blocks.EMERALD_ORE, Blocks.EMERALD_BLOCK) //shallow emerald
                .add(RBlocks.HEMATITE_BLOCK)
                .setReplace(true);
        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(Blocks.DEEPSLATE_IRON_ORE) //deep iron
                .add(Blocks.DEEPSLATE_GOLD_ORE) //deep gold
                .add(Blocks.DEEPSLATE_EMERALD_ORE) //deep emerald
                .add(Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK) //shallow diamond
                .add(Blocks.REDSTONE_ORE, Blocks.REDSTONE_BLOCK) //shallow redstone
                .setReplace(true);
        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Blocks.DEEPSLATE_DIAMOND_ORE) //deep diamond
                .add(Blocks.DEEPSLATE_REDSTONE_ORE) //deep redstone
                .add(Blocks.ANCIENT_DEBRIS, Blocks.NETHERITE_BLOCK) //netherite TODO: maybe not? it's a sidegrade to diamond now...
                .add(Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN, Blocks.RESPAWN_ANCHOR)
                .setReplace(true);
    }

    public Identifier id(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }
}
