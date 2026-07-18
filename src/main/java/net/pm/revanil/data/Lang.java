package net.pm.revanil.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.pm.revanil.Revanil;
import net.pm.revanil.world.item.RItems;
import net.pm.revanil.world.level.block.RBlocks;

import java.util.concurrent.CompletableFuture;

public class Lang extends FabricLanguageProvider {
    public static final String IRON_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = "item."+ Revanil.MOD_ID+".smithing_template.iron_upgrade.additions_slot_description";
    public static final String IRON_UPGRADE_APPLIES_TO = "item."+ Revanil.MOD_ID+".smithing_template.iron_upgrade.applies_to";
    public static final String IRON_UPGRADE_BASE_SLOT_DESCRIPTION = "item."+ Revanil.MOD_ID+".smithing_template.iron_upgrade.base_slot_description";
    public static final String IRON_UPGRADE_INGREDIENTS = "item."+ Revanil.MOD_ID+".smithing_template.iron_upgrade.ingredients";
    public static final String GOLD_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = "item."+ Revanil.MOD_ID+".smithing_template.gold_upgrade.additions_slot_description";
    public static final String GOLD_UPGRADE_APPLIES_TO = "item."+ Revanil.MOD_ID+".smithing_template.gold_upgrade.applies_to";
    public static final String GOLD_UPGRADE_BASE_SLOT_DESCRIPTION = "item."+ Revanil.MOD_ID+".smithing_template.gold_upgrade.base_slot_description";
    public static final String GOLD_UPGRADE_INGREDIENTS = "item."+ Revanil.MOD_ID+".smithing_template.gold_upgrade.ingredients";
    public static final String DIAMOND_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = "item."+ Revanil.MOD_ID+".smithing_template.diamond_upgrade.additions_slot_description";
    public static final String DIAMOND_UPGRADE_APPLIES_TO = "item."+ Revanil.MOD_ID+".smithing_template.diamond_upgrade.applies_to";
    public static final String DIAMOND_UPGRADE_BASE_SLOT_DESCRIPTION = "item."+ Revanil.MOD_ID+".smithing_template.diamond_upgrade.base_slot_description";
    public static final String DIAMOND_UPGRADE_INGREDIENTS = "item."+ Revanil.MOD_ID+".smithing_template.diamond_upgrade.ingredients";

    public Lang(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(RItems.IRON_UPGRADE_SMITHING_TEMPLATE, "Iron Upgrade");
        translationBuilder.add(IRON_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, "Add Iron Ingot");
        translationBuilder.add(IRON_UPGRADE_APPLIES_TO, "Leather or Wooden Equipment");
        translationBuilder.add(IRON_UPGRADE_BASE_SLOT_DESCRIPTION, "Add leather armor, wooden weapon, or tool");
        translationBuilder.add(IRON_UPGRADE_INGREDIENTS, "Iron Ingot");

        translationBuilder.add(RItems.GOLD_UPGRADE_SMITHING_TEMPLATE, "Gold Upgrade");
        translationBuilder.add(GOLD_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, "Add Gold Ingot");
        translationBuilder.add(GOLD_UPGRADE_APPLIES_TO, "Leather or Wooden Equipment");
        translationBuilder.add(GOLD_UPGRADE_BASE_SLOT_DESCRIPTION, "Add leather armor, wooden weapon, or tool");
        translationBuilder.add(GOLD_UPGRADE_INGREDIENTS, "Gold Ingot");

        translationBuilder.add(RItems.DIAMOND_UPGRADE_SMITHING_TEMPLATE, "Diamond Upgrade");
        translationBuilder.add(DIAMOND_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, "Add Diamond");
        translationBuilder.add(DIAMOND_UPGRADE_APPLIES_TO, "Iron Equipment");
        translationBuilder.add(DIAMOND_UPGRADE_BASE_SLOT_DESCRIPTION, "Add diamond armor, weapon, or tool");
        translationBuilder.add(DIAMOND_UPGRADE_INGREDIENTS, "Diamond");

        translationBuilder.add("item.minecraft.smithing_template.netherite_upgrade.applies_to", "Gold Equipment");

        translationBuilder.add(RBlocks.MALACHITE_BLOCK, "Malachite Block");
        translationBuilder.add(RBlocks.MALACHITE_BLOCK.asItem(), "Malachite Block");
        translationBuilder.add(RItems.MALACHITE_CHUNK, "Malachite Chunk");
        translationBuilder.add(RBlocks.HEMATITE_BLOCK, "Hematite Block");
        translationBuilder.add(RBlocks.HEMATITE_BLOCK.asItem(), "Hematite Block");
        translationBuilder.add(RItems.HEMATITE_CHUNK, "Hematite Chunk");
    }

    public void addBlockWithItem(TranslationBuilder translationBuilder, Block block, String name) {
        translationBuilder.add(block, name);
        translationBuilder.add(block.asItem(), name);
    }
}
