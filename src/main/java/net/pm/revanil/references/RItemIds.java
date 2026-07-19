package net.pm.revanil.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.pm.revanil.Revanil;

public class RItemIds {
    public static final ResourceKey<Item> IRON_UPGRADE_SMITHING_TEMPLATE = create("iron_upgrade_smithing_template");
    public static final ResourceKey<Item> GOLD_UPGRADE_SMITHING_TEMPLATE = create("gold_upgrade_smithing_template");
    public static final ResourceKey<Item> DIAMOND_UPGRADE_SMITHING_TEMPLATE = create("diamond_upgrade_smithing_template");

    public static final ResourceKey<Item> MALACHITE_CHUNK = create("malachite_chunk");
    public static final ResourceKey<Item> HEMATITE_CHUNK = create("hematite_chunk");

    private static ResourceKey<Item> create(final String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, name));
    }

    private static ResourceKey<Item> createPotterySherd(final ResourceKey<DecoratedPotPattern> sherd) {
        return sherd.dependent(Registries.ITEM, "_pottery_sherd");
    }

    private static ResourceKey<Item> createArmorTrimSmithingTemplate(final ResourceKey<TrimPattern> template) {
        return template.dependent(Registries.ITEM, "_armor_trim_smithing_template");
    }

    private static ResourceKey<Item> createMusicDisc(final ResourceKey<JukeboxSong> music) {
        return music.dependent(Registries.ITEM, (path) -> "music_disc_" + path);
    }

    private static ResourceKey<Item> createSpawnEgg(final ResourceKey<EntityType<?>> entity) {
        return entity.dependent(Registries.ITEM, "_spawn_egg");
    }

    private static ColorCollection<ResourceKey<Item>> createSimpleColored(final String baseName) {
        return ColorCollection.prefixWithColor(ColorCollection.create(baseName)).map(RItemIds::create);
    }
}
