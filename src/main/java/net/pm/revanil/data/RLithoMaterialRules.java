package net.pm.revanil.data;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.material.EndMaterialRules;
import net.minecraft.data.worldgen.material.NetherMaterialRules;
import net.minecraft.data.worldgen.material.OverworldMaterialRules;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.pm.revanil.Revanil;
import net.pm.revanil.data.worldgen.material.RMaterialRules;

public class RLithoMaterialRules {
    public static ResourceKey<WorldgenModifier> shallow_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "shallow_seams"));
    public static ResourceKey<WorldgenModifier> deep_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "deep_seams"));
    public static ResourceKey<WorldgenModifier> nether_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "nether_seams"));
    public static ResourceKey<WorldgenModifier> end_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "end_seams"));

    public static void bootstrap(BootstrapContext<WorldgenModifier> context) {
        HolderGetter<MaterialRule> rules = context.lookup(Registries.MATERIAL_RULE);

        context.register(shallow_seams,
                WorldgenModifier.builder().setMaterialRule(rules.get(OverworldMaterialRules.OVERWORLD).get(), rules.get(RMaterialRules.OVERWORLD_SEAMS_SHALLOW).get(), InjectionType.APPEND));

        context.register(deep_seams,
                WorldgenModifier.builder().setMaterialRule(rules.get(OverworldMaterialRules.OVERWORLD).get(), rules.get(RMaterialRules.OVERWORLD_SEAMS_DEEP).get(), InjectionType.PREPEND));

        // kinda buggy
        context.register(nether_seams,
                WorldgenModifier.builder().setMaterialRule(rules.get(NetherMaterialRules.NETHER).get(), rules.get(RMaterialRules.NETHER_SEAMS).get(), InjectionType.PREPEND));

        //technically still has the same issues as the other ones too but I like it sooooooo
        context.register(end_seams,
                WorldgenModifier.builder().setMaterialRule(rules.get(EndMaterialRules.END).get(), rules.get(RMaterialRules.END_SEAMS).get(), InjectionType.PREPEND));
    }

}
