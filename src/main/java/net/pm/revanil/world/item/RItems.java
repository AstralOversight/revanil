package net.pm.revanil.world.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.pm.revanil.references.RBlockItemIds;
import net.pm.revanil.references.RItemIds;
import net.pm.revanil.world.level.block.RBlocks;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RItems {

    public static final Item IRON_UPGRADE_SMITHING_TEMPLATE = registerItem(RItemIds.IRON_UPGRADE_SMITHING_TEMPLATE, SmithingTemplateItems::createIronUpgrade, new Item.Properties());
    public static final Item GOLD_UPGRADE_SMITHING_TEMPLATE = registerItem(RItemIds.GOLD_UPGRADE_SMITHING_TEMPLATE, SmithingTemplateItems::createGoldUpgrade, new Item.Properties());
    public static final Item DIAMOND_UPGRADE_SMITHING_TEMPLATE = registerItem(RItemIds.DIAMOND_UPGRADE_SMITHING_TEMPLATE, SmithingTemplateItems::createDiamondUpgrade, new Item.Properties());
    public static final Item MALACHITE_BLOCK = registerBlock(RBlockItemIds.MALACHITE_BLOCK, RBlocks.MALACHITE_BLOCK);
    public static final Item MALACHITE_CHUNK = registerItem(RItemIds.MALACHITE_CHUNK, Item::new, new Item.Properties());
    public static final Item HEMATITE_BLOCK = registerBlock(RBlockItemIds.HEMATITE_BLOCK, RBlocks.HEMATITE_BLOCK);
    public static final Item HEMATITE_CHUNK = registerItem(RItemIds.HEMATITE_CHUNK, Item::new, new Item.Properties());

    private static Item registerItem(final ResourceKey<Item> id, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(id));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    private static Item registerBlock(final BlockItemId id, final Block block) {
        return registerBlock(id, block, new Item.Properties());
    }

    private static Item registerBlock(final BlockItemId id, final Block block, final Item.Properties properties) {
        return registerBlock(id, block, BlockItem::new, properties);
    }

    private static Item registerBlock(final BlockItemId id, final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory, final Item.Properties properties) {
        return registerItem(id.item(), (p) -> itemFactory.apply(block, p), properties.useBlockDescriptionPrefix().requiredFeatures(block.requiredFeatures()));
    }

    public static void init() {
    }
}
