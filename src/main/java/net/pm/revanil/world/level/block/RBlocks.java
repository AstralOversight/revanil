package net.pm.revanil.world.level.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.pm.revanil.references.RBlockItemIds;

import java.util.function.Function;

public class RBlocks {
    public static final Block MALACHITE_BLOCK = register(RBlockItemIds.MALACHITE_BLOCK, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).mapColor(MapColor.COLOR_GREEN));
    public static final Block HEMATITE_BLOCK = register(RBlockItemIds.HEMATITE_BLOCK, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).mapColor(MapColor.COLOR_GRAY));

    private static Block register(BlockItemId blockId, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        //block
        Block block = blockFactory.apply(properties.setId(blockId.block()));

        return Registry.register(BuiltInRegistries.BLOCK, blockId.block(), block);
    }

    public static void init() {
    }
}
