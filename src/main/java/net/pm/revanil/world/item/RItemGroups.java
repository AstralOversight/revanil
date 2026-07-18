package net.pm.revanil.world.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.pm.revanil.world.level.block.RBlocks;

public class RItemGroups {

    public static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(content -> {
            content.insertAfter(Blocks.DEEPSLATE_COPPER_ORE, RBlocks.MALACHITE_BLOCK);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(content -> {
            content.insertAfter(Blocks.DEEPSLATE_IRON_ORE, RBlocks.HEMATITE_BLOCK);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(content -> {
            content.insertAfter(Items.AMETHYST_SHARD, RItems.MALACHITE_CHUNK, RItems.HEMATITE_CHUNK);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register((itemGroup) -> {
            itemGroup.insertBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, RItems.IRON_UPGRADE_SMITHING_TEMPLATE, RItems.GOLD_UPGRADE_SMITHING_TEMPLATE, RItems.DIAMOND_UPGRADE_SMITHING_TEMPLATE);
        });
    }
}
