package net.pm.revanil.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.pm.revanil.Revanil;

public class RBlockItemIds {
    public static final BlockItemId MALACHITE_BLOCK = create("malachite_block");
    public static final BlockItemId HEMATITE_BLOCK = create("hematite_block");

    public static BlockItemId create(final String name) {
        return new BlockItemId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, name)), ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, name)));
    }
}
