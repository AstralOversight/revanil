package net.pm.revanil.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.pm.revanil.Revanil;
import net.pm.revanil.world.item.RItems;
import net.pm.revanil.world.level.block.RBlocks;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Recipes extends FabricRecipeProvider {
    public Recipes(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    static final Map<Item, Item> IRON_UPGRADE = new LinkedHashMap<>();
    static final Map<Item, Item> GOLD_UPGRADE = new LinkedHashMap<>();
    static final Map<Item, Item> DIAMOND_UPGRADE = new LinkedHashMap<>();
    static final Map<Item, Item> NETHERITE_UPGRADE = new LinkedHashMap<>();
    public static void mapUpgrades() {
        IRON_UPGRADE.put(Items.WOODEN_AXE, Items.IRON_AXE);
        IRON_UPGRADE.put(Items.WOODEN_HOE, Items.IRON_HOE);
        IRON_UPGRADE.put(Items.WOODEN_PICKAXE, Items.IRON_PICKAXE);
        IRON_UPGRADE.put(Items.WOODEN_SHOVEL, Items.IRON_SHOVEL);
        IRON_UPGRADE.put(Items.WOODEN_SWORD, Items.IRON_SWORD);
        IRON_UPGRADE.put(Items.WOODEN_SPEAR, Items.IRON_SPEAR);
        IRON_UPGRADE.put(Items.LEATHER_HELMET, Items.IRON_HELMET);
        IRON_UPGRADE.put(Items.LEATHER_CHESTPLATE, Items.IRON_CHESTPLATE);
        IRON_UPGRADE.put(Items.LEATHER_LEGGINGS, Items.IRON_LEGGINGS);
        IRON_UPGRADE.put(Items.LEATHER_BOOTS, Items.IRON_BOOTS);
        IRON_UPGRADE.put(Items.LEATHER_HORSE_ARMOR, Items.IRON_HORSE_ARMOR);
        IRON_UPGRADE.put(Items.NAUTILUS_SHELL, Items.IRON_NAUTILUS_ARMOR);
        GOLD_UPGRADE.put(Items.WOODEN_AXE, Items.GOLDEN_AXE);
        GOLD_UPGRADE.put(Items.WOODEN_HOE, Items.GOLDEN_HOE);
        GOLD_UPGRADE.put(Items.WOODEN_PICKAXE, Items.GOLDEN_PICKAXE);
        GOLD_UPGRADE.put(Items.WOODEN_SHOVEL, Items.GOLDEN_SHOVEL);
        GOLD_UPGRADE.put(Items.WOODEN_SWORD, Items.GOLDEN_SWORD);
        GOLD_UPGRADE.put(Items.WOODEN_SPEAR, Items.GOLDEN_SPEAR);
        GOLD_UPGRADE.put(Items.LEATHER_HELMET, Items.GOLDEN_HELMET);
        GOLD_UPGRADE.put(Items.LEATHER_CHESTPLATE, Items.GOLDEN_CHESTPLATE);
        GOLD_UPGRADE.put(Items.LEATHER_LEGGINGS, Items.GOLDEN_LEGGINGS);
        GOLD_UPGRADE.put(Items.LEATHER_BOOTS, Items.GOLDEN_BOOTS);
        GOLD_UPGRADE.put(Items.LEATHER_HORSE_ARMOR, Items.GOLDEN_HORSE_ARMOR);
        GOLD_UPGRADE.put(Items.NAUTILUS_SHELL, Items.GOLDEN_NAUTILUS_ARMOR);
        DIAMOND_UPGRADE.put(Items.IRON_AXE, Items.DIAMOND_AXE);
        DIAMOND_UPGRADE.put(Items.IRON_HOE, Items.DIAMOND_HOE);
        DIAMOND_UPGRADE.put(Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE);
        DIAMOND_UPGRADE.put(Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL);
        DIAMOND_UPGRADE.put(Items.IRON_SWORD, Items.DIAMOND_SWORD);
        DIAMOND_UPGRADE.put(Items.IRON_SPEAR, Items.DIAMOND_SPEAR);
        DIAMOND_UPGRADE.put(Items.IRON_HELMET, Items.DIAMOND_HELMET);
        DIAMOND_UPGRADE.put(Items.IRON_CHESTPLATE, Items.DIAMOND_CHESTPLATE);
        DIAMOND_UPGRADE.put(Items.IRON_LEGGINGS, Items.DIAMOND_LEGGINGS);
        DIAMOND_UPGRADE.put(Items.IRON_BOOTS, Items.DIAMOND_BOOTS);
        DIAMOND_UPGRADE.put(Items.IRON_HORSE_ARMOR, Items.DIAMOND_HORSE_ARMOR);
        DIAMOND_UPGRADE.put(Items.IRON_NAUTILUS_ARMOR, Items.DIAMOND_NAUTILUS_ARMOR);
        NETHERITE_UPGRADE.put(Items.GOLDEN_AXE, Items.NETHERITE_AXE);
        NETHERITE_UPGRADE.put(Items.GOLDEN_HOE, Items.NETHERITE_HOE);
        NETHERITE_UPGRADE.put(Items.GOLDEN_PICKAXE, Items.NETHERITE_PICKAXE);
        NETHERITE_UPGRADE.put(Items.GOLDEN_SHOVEL, Items.NETHERITE_SHOVEL);
        NETHERITE_UPGRADE.put(Items.GOLDEN_SWORD, Items.NETHERITE_SWORD);
        NETHERITE_UPGRADE.put(Items.GOLDEN_SPEAR, Items.NETHERITE_SPEAR);
        NETHERITE_UPGRADE.put(Items.GOLDEN_HELMET, Items.NETHERITE_HELMET);
        NETHERITE_UPGRADE.put(Items.GOLDEN_CHESTPLATE, Items.NETHERITE_CHESTPLATE);
        NETHERITE_UPGRADE.put(Items.GOLDEN_LEGGINGS, Items.NETHERITE_LEGGINGS);
        NETHERITE_UPGRADE.put(Items.GOLDEN_BOOTS, Items.NETHERITE_BOOTS);
        NETHERITE_UPGRADE.put(Items.GOLDEN_HORSE_ARMOR, Items.NETHERITE_HORSE_ARMOR);
        NETHERITE_UPGRADE.put(Items.GOLDEN_NAUTILUS_ARMOR, Items.NETHERITE_NAUTILUS_ARMOR);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {
            @Override
            public void buildRecipes() {
                //Upgrade template recipes
                genUpgradeTemplate(RItems.IRON_UPGRADE_SMITHING_TEMPLATE, Items.IRON_INGOT, Blocks.IRON_BLOCK.asItem(), Items.LEATHER);
                genUpgradeTemplate(RItems.GOLD_UPGRADE_SMITHING_TEMPLATE, Items.GOLD_INGOT, Blocks.GOLD_BLOCK.asItem(), Items.LEATHER);
                genUpgradeTemplate(RItems.DIAMOND_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND, Blocks.DIAMOND_BLOCK.asItem(), Items.IRON_INGOT);
                //TODO: Probably change this recipe later
                genUpgradeTemplate(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_SCRAP, Items.NETHERITE_INGOT, Items.GOLD_INGOT);

                //Upgrade template usages & new recycling
                mapUpgrades();
                genSmithing(RItems.IRON_UPGRADE_SMITHING_TEMPLATE, IRON_UPGRADE, net.minecraft.tags.ItemTags.IRON_TOOL_MATERIALS, Items.IRON_INGOT, true);
                genSmithing(RItems.GOLD_UPGRADE_SMITHING_TEMPLATE, GOLD_UPGRADE, net.minecraft.tags.ItemTags.GOLD_TOOL_MATERIALS, Items.GOLD_INGOT, true);
                genSmithing(RItems.DIAMOND_UPGRADE_SMITHING_TEMPLATE, DIAMOND_UPGRADE, ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND, false);
                genSmithing(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, NETHERITE_UPGRADE, ItemTags.NETHERITE_TOOL_MATERIALS, Items.NETHERITE_INGOT, true);

                // Copper Horse & Nautilus Armour
                shaped(RecipeCategory.TOOLS, Items.COPPER_HORSE_ARMOR)
                        .pattern("###")
                        .pattern("#O#")
                        .pattern("###")
                        .define('#', Items.COPPER_INGOT)
                        .define('O', Items.LEATHER_HORSE_ARMOR)
                        .unlockedBy("has_material", has(Items.LEATHER_HORSE_ARMOR))
                        .save(recipeExporter);
                shaped(RecipeCategory.TOOLS, Items.COPPER_NAUTILUS_ARMOR)
                        .pattern("###")
                        .pattern("#O#")
                        .pattern("###")
                        .define('#', Items.COPPER_INGOT)
                        .define('O', Items.NAUTILUS_SHELL)
                        .unlockedBy("has_material", has(Items.NAUTILUS_SHELL))
                        .save(recipeExporter);

                chainmail();

                malachite();
                hematite();

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.GRASS_BLOCK)
                        .pattern("M")
                        .pattern("#")
                        .define('M', Blocks.SHORT_GRASS)
                        .define('#', Blocks.DIRT)
                        .unlockedBy("has_material", has(Blocks.SHORT_GRASS))
                        .save(recipeExporter);

                shaped(RecipeCategory.REDSTONE, Blocks.TNT)
                        .pattern("G#")
                        .pattern("#G")
                        .define('G', Items.GUNPOWDER)
                        .define('#', ItemTags.SAND)
                        .unlockedBy("has_material", has(Items.GUNPOWDER))
                        .save(recipeExporter);
            }

            //Chainmail Armor
            public void chainmail() {
                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_HELMET)
                        .pattern("###")
                        .pattern("# #")
                        .define('#', Items.IRON_CHAIN)
                        .unlockedBy("has_material", has(Items.IRON_INGOT))
                        .save(recipeExporter);
                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_CHESTPLATE)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .define('#', Items.IRON_CHAIN)
                        .unlockedBy("has_material", has(Items.IRON_INGOT))
                        .save(recipeExporter);
                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_LEGGINGS)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .define('#', Items.IRON_CHAIN)
                        .unlockedBy("has_material", has(Items.IRON_INGOT))
                        .save(recipeExporter);
                shaped(RecipeCategory.TOOLS, Items.CHAINMAIL_BOOTS)
                        .pattern("# #")
                        .pattern("# #")
                        .define('#', Items.IRON_CHAIN)
                        .unlockedBy("has_material", has(Items.IRON_INGOT))
                        .save(recipeExporter);
            }

            public void malachite() {
                shaped(RecipeCategory.BUILDING_BLOCKS, RBlocks.MALACHITE_BLOCK)
                        .pattern("##")
                        .pattern("##")
                        .define('#', RItems.MALACHITE_CHUNK)
                        .unlockedBy("has_material", has(RItems.MALACHITE_CHUNK))
                        .save(recipeExporter);
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(RItems.MALACHITE_CHUNK), RecipeCategory.MISC, CookingBookCategory.MISC, Items.COPPER_NUGGET, 0.5f, 200)
                        .unlockedBy("has_material", has(RItems.MALACHITE_CHUNK))
                        .save(recipeExporter, getSimpleRecipeName(Items.COPPER_NUGGET) + "_from_smelting_" + getSimpleRecipeName(RItems.MALACHITE_CHUNK));
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(RItems.MALACHITE_CHUNK), RecipeCategory.MISC, CookingBookCategory.MISC, Items.COPPER_NUGGET, 0.5f, 100)
                        .unlockedBy("has_material", has(RItems.MALACHITE_CHUNK))
                        .save(recipeExporter, getSimpleRecipeName(Items.COPPER_NUGGET) + "_from_blasting_" + getSimpleRecipeName(RItems.MALACHITE_CHUNK));
            }

            public void hematite() {
                shaped(RecipeCategory.BUILDING_BLOCKS, RBlocks.HEMATITE_BLOCK)
                        .pattern("##")
                        .pattern("##")
                        .define('#', RItems.HEMATITE_CHUNK)
                        .unlockedBy("has_material", has(RItems.HEMATITE_CHUNK))
                        .save(recipeExporter);
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(RItems.HEMATITE_CHUNK), RecipeCategory.MISC, CookingBookCategory.MISC, Items.IRON_NUGGET, 0.5f, 200)
                        .unlockedBy("has_material", has(RItems.HEMATITE_CHUNK))
                        .save(recipeExporter, getSimpleRecipeName(Items.IRON_NUGGET) + "_from_smelting_" + getSimpleRecipeName(RItems.HEMATITE_CHUNK));
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(RItems.HEMATITE_CHUNK), RecipeCategory.MISC, CookingBookCategory.MISC, Items.IRON_NUGGET, 0.5f, 100)
                        .unlockedBy("has_material", has(RItems.HEMATITE_CHUNK))
                        .save(recipeExporter, getSimpleRecipeName(Items.IRON_NUGGET) + "_from_blasting_" + getSimpleRecipeName(RItems.HEMATITE_CHUNK));
            }

            public void genUpgradeTemplate(Item template, Item material, Item core, Item other) {
                shaped(RecipeCategory.TOOLS, template)
                        .pattern("OMO")
                        .pattern("MXM")
                        .pattern("OMO")
                        .define('O', other)
                        .define('M', material)
                        .define('X', core)
                        .group(BuiltInRegistries.ITEM.getKey(template.asItem()).toString())
                        .unlockedBy("has_material", has(material))
                        .save(recipeExporter, getSimpleRecipeName(template)+"_core");
                shaped(RecipeCategory.TOOLS, template, 2)
                        .pattern("OMO")
                        .pattern("MXM")
                        .pattern("OMO")
                        .define('O', other)
                        .define('M', material)
                        .define('X', template)
                        .group(BuiltInRegistries.ITEM.getKey(template.asItem()).toString())
                        .unlockedBy("has_material", has(material))
                        .save(recipeExporter, getSimpleRecipeName(template)+"_duplication");
            }

            public void genSmithing(Item template, Map<Item, Item> upgrades, TagKey<Item> material) {
                //Create the smithing recipe for each item.
                for (Item key : upgrades.keySet()) {
                    SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(key), tag(material), RecipeCategory.TOOLS, upgrades.get(key))
                            .unlocks("has_template", has(template))
                            .save(recipeExporter, getSimpleRecipeName(upgrades.get(key))+"_smithing");
                }
            }

            public void genSmithing(Item template, Map<Item, Item> upgrades, TagKey<Item> material, Item matRep, boolean reduce) {
                genSmithing(template, upgrades, material);

                if (reduce) {
                    //Reducing back to material
                    SimpleCookingRecipeBuilder.smelting(Ingredient.of(upgrades.values().stream()), RecipeCategory.MISC, CookingBookCategory.MISC, matRep, 0.5f, 200)
                            .unlockedBy("has_material", has(template))
                            .save(recipeExporter, getSimpleRecipeName(matRep) + "_reduction_from_smelting");
                    SimpleCookingRecipeBuilder.blasting(Ingredient.of(upgrades.values().stream()), RecipeCategory.MISC, CookingBookCategory.MISC, matRep, 0.5f, 100)
                            .unlockedBy("has_material", has(template))
                            .save(recipeExporter, getSimpleRecipeName(matRep) + "_reduction_from_blasting");
                }
            }
        };
    }

    @Override
    public String getName() {
        return Revanil.MOD_ID+"_recipes";
    }
}
