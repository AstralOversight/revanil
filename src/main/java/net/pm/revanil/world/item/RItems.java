package net.pm.revanil.world.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.pm.revanil.Revanil;

import java.util.function.Function;

public class RItems {

    public static final Item IRON_UPGRADE_SMITHING_TEMPLATE = register("iron_upgrade_smithing_template", SmithingTemplateItems::createIronUpgrade, new Item.Properties());
    public static final Item GOLD_UPGRADE_SMITHING_TEMPLATE = register("gold_upgrade_smithing_template", SmithingTemplateItems::createGoldUpgrade, new Item.Properties());
    public static final Item DIAMOND_UPGRADE_SMITHING_TEMPLATE = register("diamond_upgrade_smithing_template", SmithingTemplateItems::createDiamondUpgrade, new Item.Properties());
    public static final Item MALACHITE_CHUNK = register("malachite_chunk", Item::new, new Item.Properties());
    public static final Item HEMATITE_CHUNK = register("hematite_chunk", Item::new, new Item.Properties());


    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        return register(Identifier.fromNamespaceAndPath(Revanil.MOD_ID, name), itemFactory, settings);
    }

    public static Item register(Identifier id, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

        // Create the item instance.
        Item item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {
    }
}
