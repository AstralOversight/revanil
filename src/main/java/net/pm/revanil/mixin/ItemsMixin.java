package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.pm.revanil.world.item.component.RConsumables;
import net.pm.revanil.world.item.component.RFoods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Items.class)
public class ItemsMixin {

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value="CONSTANT", args="stringValue=beetroot", ordinal = 0)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$beetrootEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.BEETROOT);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value="CONSTANT", args="stringValue=beetroot_soup", ordinal = 0)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$beetrootSoupEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.BEETROOT_SOUP);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value="CONSTANT", args="stringValue=glistering_melon_slice", ordinal = 0)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;)Lnet/minecraft/world/item/Item;", ordinal = 0))
    private static Item revanil$glisteringMelonSlice(String string, Operation<Item> original) {
        return Items.registerItem(string, (new Item.Properties()).food(RFoods.GLISTERING_MELON_SLICE, RConsumables.GLISTERING_MELON_SLICE));
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value="CONSTANT", args="stringValue=glow_berries", ordinal = 0)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$glowBerriesEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.GLOW_BERRIES);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value="CONSTANT", args="stringValue=golden_carrot", ordinal = 0)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;", ordinal = 0))
    private static Item.Properties revanil$goldenCarrotEffect(Item.Properties instance, FoodProperties foodProperties, Operation<Item.Properties> original) {
        return instance.food(foodProperties, RConsumables.GOLDEN_CARROT);
    }

    @WrapOperation(method = "<clinit>", slice = @Slice(from = @At(value="CONSTANT", args="stringValue=popped_chorus_fruit", ordinal = 0)), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;)Lnet/minecraft/world/item/Item;", ordinal = 0))
    private static Item revanil$poppedChorusFruit(String name, Operation<Item> original) {
        return Items.registerItem(name, (new Item.Properties()).food(RFoods.POPPED_CHORUS_FRUIT, RConsumables.POPPED_CHORUS_FRUIT));
    }
}
