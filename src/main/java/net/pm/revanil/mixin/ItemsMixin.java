package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.pm.revanil.world.item.component.RConsumables;
import net.pm.revanil.world.item.component.RFoods;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Items.class)
public class ItemsMixin {

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/ItemIds;BEETROOT:Lnet/minecraft/resources/ResourceKey;", ordinal = 0, opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$beetrootEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.BEETROOT);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/ItemIds;BEETROOT_SOUP:Lnet/minecraft/resources/ResourceKey;", ordinal = 0, opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$beetrootSoupEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.BEETROOT_SOUP);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/ItemIds;GLISTERING_MELON_SLICE:Lnet/minecraft/resources/ResourceKey;", ordinal = 0, opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/Item;", ordinal = 0))
    private static Item revanil$glisteringMelonSlice(ResourceKey<Item> id, Operation<Item> original) {
        return Items.registerItem(id, (new Item.Properties()).food(RFoods.GLISTERING_MELON_SLICE, RConsumables.GLISTERING_MELON_SLICE));
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/BlockItemIds;GLOW_BERRY_CROP:Lnet/minecraft/references/BlockItemId;", ordinal = 0, opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$glowBerriesEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.GLOW_BERRIES);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/ItemIds;GOLDEN_CARROT:Lnet/minecraft/resources/ResourceKey;", ordinal = 0, opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$goldenCarrotEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.GOLDEN_CARROT);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/references/ItemIds;POPPED_CHORUS_FRUIT:Lnet/minecraft/resources/ResourceKey;", ordinal = 0, opcode = Opcodes.GETSTATIC)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/Item;", ordinal = 0))
    private static Item revanil$poppedChorusFruit(ResourceKey<Item> id, Operation<Item> original) {
        return Items.registerItem(id, (new Item.Properties()).food(RFoods.POPPED_CHORUS_FRUIT, RConsumables.POPPED_CHORUS_FRUIT));
    }
}
