package net.pm.revanil.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.pm.revanil.Revanil;

import java.util.concurrent.CompletableFuture;

public class RItemTags extends FabricTagsProvider.ItemTagsProvider {
    public static final TagKey<Item> FIREPROOF = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "fireproof"));

    public RItemTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(ItemTags.GAZE_DISGUISE_EQUIPMENT)
                .add(BlockItemIds.DRAGON_HEAD.item());
    }

    public Identifier id(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }
}
