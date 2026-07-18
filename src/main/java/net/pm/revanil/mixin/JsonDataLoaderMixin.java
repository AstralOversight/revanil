package net.pm.revanil.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.*;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;

@Mixin(SimpleJsonResourceReloadListener.class)
public class JsonDataLoaderMixin {
    @Unique
    private static final LinkedHashSet<Identifier> banned = new LinkedHashSet<>();

    static {
        banRecipe("iron_axe");
        banRecipe("iron_hoe");
        banRecipe("iron_pickaxe");
        banRecipe("iron_shovel");
        banRecipe("iron_sword");
        banRecipe("iron_spear");
        banRecipe("iron_helmet");
        banRecipe("iron_chestplate");
        banRecipe("iron_leggings");
        banRecipe("iron_boots");
        banRecipe("iron_horse_armor");
        banRecipe("iron_nugget_from_smelting");
        banRecipe("iron_nugget_from_blasting");
        banRecipe("golden_axe");
        banRecipe("golden_hoe");
        banRecipe("golden_pickaxe");
        banRecipe("golden_shovel");
        banRecipe("golden_sword");
        banRecipe("golden_spear");
        banRecipe("golden_helmet");
        banRecipe("golden_chestplate");
        banRecipe("golden_leggings");
        banRecipe("golden_boots");
        banRecipe("golden_horse_armor");
        banRecipe("gold_nugget_from_smelting");
        banRecipe("gold_nugget_from_blasting");
        banRecipe("diamond_axe");
        banRecipe("diamond_hoe");
        banRecipe("diamond_pickaxe");
        banRecipe("diamond_shovel");
        banRecipe("diamond_sword");
        banRecipe("diamond_spear");
        banRecipe("diamond_helmet");
        banRecipe("diamond_chestplate");
        banRecipe("diamond_leggings");
        banRecipe("diamond_boots");
        banRecipe("netherite_upgrade_smithing_template");
        banRecipe("netherite_axe_smithing");
        banRecipe("netherite_hoe_smithing");
        banRecipe("netherite_pickaxe_smithing");
        banRecipe("netherite_shovel_smithing");
        banRecipe("netherite_sword_smithing");
        banRecipe("netherite_spear_smithing");
        banRecipe("netherite_helmet_smithing");
        banRecipe("netherite_chestplate_smithing");
        banRecipe("netherite_leggings_smithing");
        banRecipe("netherite_boots_smithing");
        banRecipe("netherite_horse_armor_smithing");
        banRecipe("netherite_nautilus_armor_smithing");
        banRecipe("tnt");
    }

    @Unique
    private static void banRecipe(String name) {
        banRecipe("minecraft", name);
    }
    @Unique
    private static void banRecipe(String namespace, String name) {
        banned.add(Identifier.fromNamespaceAndPath(namespace, "recipe/" + name + ".json"));
    }

    @WrapOperation(method = "scanDirectory(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/FileToIdConverter;Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/Codec;Ljava/util/Map;)V", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;"))
    private static <E> E revanil$makeSkip(Iterator<E> instance, Operation<E> original) {
        E next = original.call(instance);
        while (banned.contains(((Map.Entry<Identifier, Resource>) next).getKey())) {
            //FIXME: If the next element is the last element that will probably cause issues.
            next = instance.next();
        }
        return next;
    }
}
