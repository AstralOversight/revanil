package net.pm.revanil.data;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.pm.revanil.Revanil;
import net.pm.revanil.world.level.levelgen.RSurfaceRules;

public class RLithoSurface {
    public static ResourceKey<WorldgenModifier> shallow_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "shallow_seams"));
    public static ResourceKey<WorldgenModifier> deep_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "deep_seams"));
    public static ResourceKey<WorldgenModifier> nether_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "nether_seams"));
    public static ResourceKey<WorldgenModifier> end_seams = ResourceKey.create(LithostitchedRegistries.WORLDGEN_MODIFIER, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, "end_seams"));

    // if you ever need to go see how Mojang does it, go look at net.minecraft.data.worldgen.SurfaceRuleData

    private static final SurfaceRules.RuleSource
            AIR = makeStateRule(Blocks.AIR);
    private static final SurfaceRules.RuleSource
            ANDESITE = makeStateRule(Blocks.ANDESITE);
    private static final SurfaceRules.RuleSource
            BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource
            CALCITE = makeStateRule(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource
            CLAY = makeStateRule(Blocks.CLAY);
    private static final SurfaceRules.RuleSource
            DIORITE = makeStateRule(Blocks.DIORITE);
    private static final SurfaceRules.RuleSource
            DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource
            DRIPSTONE = makeStateRule(Blocks.DRIPSTONE_BLOCK);
    private static final SurfaceRules.RuleSource
            GRANITE = makeStateRule(Blocks.GRANITE);
    private static final SurfaceRules.RuleSource
            GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource
            MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);
    private static final SurfaceRules.RuleSource
            OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final SurfaceRules.RuleSource
            PRISMARINE = makeStateRule(Blocks.PRISMARINE);
    private static final SurfaceRules.RuleSource
            RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final SurfaceRules.RuleSource
            SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource
            SCULK = makeStateRule(Blocks.SCULK);
    private static final SurfaceRules.RuleSource
            SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);
    private static final SurfaceRules.RuleSource
            SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
    private static final SurfaceRules.RuleSource
            TUFF = makeStateRule(Blocks.TUFF);

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static void bootstrap(BootstrapContext<WorldgenModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
//        //Base stone replacements
//        SurfaceRules.RuleSource stoneBiomeR = SurfaceRules.sequence(
//                //Dripstone Biomes
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(), DRIPSTONE),
//                //Sandstone Biomes
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(), SANDSTONE),
//                //Red Sandstone Biomes
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(), RED_SANDSTONE)
//        );

        context.register(shallow_seams,
                WorldgenModifier.builder().addSurfaceRule(Level.OVERWORLD, InjectionType.APPEND, SurfaceRules.sequence(
                        shallowStoneSeams(biomes, context),
                        shallowSoilSeams(biomes, context)
                )));

        // Deep seams only where deepslate will be
        // we need to add checks so that it doesn't encroach on Bedrock or Stone... so here they are
        context.register(deep_seams,
                WorldgenModifier.builder().addSurfaceRule(Level.OVERWORLD, InjectionType.PREPEND, SurfaceRules.ifTrue(
                        SurfaceRules.not(
                                SurfaceRules.verticalGradient("bedrock_floor_location",
                                        VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5))),
                        SurfaceRules.ifTrue(
                                SurfaceRules.verticalGradient(
                                        "deep_seams",
                                        VerticalAnchor.absolute(0),
                                        VerticalAnchor.absolute(8)),
                                deepStoneSeams(biomes, context)))));

//        // kinda buggy
//        context.register(nether_seams,
//                WorldgenModifier.builder().addSurfaceRule(LevelStem.NETHER, InjectionType.PREPEND, SurfaceRules.sequence(
//                        netherStoneSeams(),
//                        netherSoilSeams()
//                )));

        //technically still has the same issues as the other ones too but I like it sooooooo
        context.register(end_seams,
                WorldgenModifier.builder().addSurfaceRule(Level.END, InjectionType.PREPEND, SurfaceRules.sequence(
                        endStoneSeams(biomes, context),
                        endSoilSeams(biomes, context)
                )));
    }

    // The alternative in the stone layer
    private static SurfaceRules.RuleSource shallowStoneSeams(HolderGetter<Biome> biomes, BootstrapContext<WorldgenModifier> context) {
        SurfaceRules.RuleSource altForBiome = SurfaceRules.sequence(
                //Calcite Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.CHERRY_GROVE,
                        Biomes.FROZEN_PEAKS,
                        Biomes.GROVE,
                        Biomes.JAGGED_PEAKS,
                        Biomes.STONY_PEAKS), CALCITE),
                //Prismarine Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.COLD_OCEAN,
                        Biomes.DEEP_COLD_OCEAN,
                        Biomes.DEEP_DARK,
                        Biomes.DEEP_FROZEN_OCEAN,
                        Biomes.DEEP_LUKEWARM_OCEAN,
                        Biomes.DEEP_OCEAN,
                        Biomes.FROZEN_OCEAN,
                        Biomes.LUKEWARM_OCEAN,
                        Biomes.MUSHROOM_FIELDS,
                        Biomes.OCEAN,
                        Biomes.WARM_OCEAN
                ), PRISMARINE),
                //Granite Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.BADLANDS,
                        Biomes.DESERT,
                        Biomes.DRIPSTONE_CAVES,
                        Biomes.ERODED_BADLANDS,
                        Biomes.JUNGLE,
                        Biomes.OLD_GROWTH_PINE_TAIGA,
                        Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                        Biomes.PLAINS,
                        Biomes.SAVANNA,
                        Biomes.SAVANNA_PLATEAU,
                        Biomes.SPARSE_JUNGLE,
                        Biomes.SUNFLOWER_PLAINS,
                        Biomes.WINDSWEPT_SAVANNA,
                        Biomes.WOODED_BADLANDS
                ), GRANITE),
                //Andesite Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.BAMBOO_JUNGLE,
                        Biomes.DARK_FOREST,
                        Biomes.FLOWER_FOREST,
                        Biomes.FOREST,
                        Biomes.FROZEN_RIVER,
                        Biomes.LUSH_CAVES,
                        Biomes.MANGROVE_SWAMP,
                        Biomes.MEADOW,
                        Biomes.RIVER,
                        Biomes.STONY_SHORE,
                        Biomes.SWAMP,
                        Biomes.TAIGA,
                        Biomes.WINDSWEPT_FOREST,
                        Biomes.WINDSWEPT_GRAVELLY_HILLS
                ), ANDESITE),
                //Diorite Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.BEACH,
                        Biomes.BIRCH_FOREST,
                        Biomes.ICE_SPIKES,
                        Biomes.OLD_GROWTH_BIRCH_FOREST,
                        Biomes.PALE_GARDEN,
                        Biomes.SNOWY_BEACH,
                        Biomes.SNOWY_PLAINS,
                        Biomes.SNOWY_SLOPES,
                        Biomes.SNOWY_TAIGA,
                        Biomes.WINDSWEPT_HILLS
                ), DIORITE)
        );

        // TODO: Give it's own noise
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, -0.0125, 0.0125), altForBiome),
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, -0.525, -0.5), altForBiome),
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, 0.5, 0.525), altForBiome)
        );
    }

    // Soil seams in the stone layer
    private static SurfaceRules.RuleSource shallowSoilSeams(HolderGetter<Biome> biomes, BootstrapContext<WorldgenModifier> context) {
        SurfaceRules.RuleSource altForBiome = SurfaceRules.sequence(
                //Sculk Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.DEEP_DARK
                ), SCULK),
                //Sandstone Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.DESERT
                ), SANDSTONE),
                //Red Sandstone Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.BADLANDS,
                        Biomes.ERODED_BADLANDS,
                        Biomes.WOODED_BADLANDS
                ), RED_SANDSTONE),
                //Clay Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.DRIPSTONE_CAVES,
                        Biomes.LUSH_CAVES,
                        Biomes.MANGROVE_SWAMP,
                        Biomes.SWAMP
                ), CLAY),
                //Dirt Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.BAMBOO_JUNGLE,
                        Biomes.BIRCH_FOREST,
                        Biomes.CHERRY_GROVE,
                        Biomes.DARK_FOREST,
                        Biomes.FLOWER_FOREST,
                        Biomes.FOREST,
                        Biomes.GROVE,
                        Biomes.JUNGLE,
                        Biomes.MEADOW,
                        Biomes.OLD_GROWTH_BIRCH_FOREST,
                        Biomes.PLAINS,
                        Biomes.SAVANNA,
                        Biomes.SAVANNA_PLATEAU,
                        Biomes.SNOWY_TAIGA,
                        Biomes.SPARSE_JUNGLE,
                        Biomes.SUNFLOWER_PLAINS,
                        Biomes.TAIGA
                ), DIRT),
                //Default
                GRAVEL
        );

        // TODO: Give it's own noise
        return SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.GRAVEL, -0.02, 0.02), altForBiome);
    }

    // The alternative in the deepslate layer
    private static SurfaceRules.RuleSource deepStoneSeams(HolderGetter<Biome> biomes, BootstrapContext<WorldgenModifier> context) {
        SurfaceRules.RuleSource altForBiome = SurfaceRules.sequence(
                //Smooth Basalt Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.CHERRY_GROVE,
                        Biomes.FROZEN_PEAKS,
                        Biomes.GROVE,
                        Biomes.JAGGED_PEAKS,
                        Biomes.STONY_PEAKS
                ), SMOOTH_BASALT),
//                // These never show up in practice
//                //Obsidian Biomes
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(
//                        Biomes.BASALT_DELTAS,
//                        Biomes.CRIMSON_FOREST,
//                        Biomes.END_BARRENS,
//                        Biomes.END_HIGHLANDS,
//                        Biomes.END_MIDLANDS,
//                        Biomes.NETHER_WASTES,
//                        Biomes.SMALL_END_ISLANDS,
//                        Biomes.SOUL_SAND_VALLEY,
//                        Biomes.THE_END,
//                        Biomes.WARPED_FOREST
//                ), OBSIDIAN),
                //Default
                TUFF
        );

        // TODO: Give it's own noise
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, -0.425, -0.4), altForBiome),
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, 0.4, 0.425), altForBiome)
        );
    }

    // The alternative stones in the nether
    private static SurfaceRules.RuleSource netherStoneSeams(HolderGetter<Biome> biomes, BootstrapContext<WorldgenModifier> context) {
        SurfaceRules.RuleSource altForBiome = SurfaceRules.sequence(
                //Blackstone Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.BASALT_DELTAS,
                        Biomes.SOUL_SAND_VALLEY
                ), BLACKSTONE),
                //Magma Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.CRIMSON_FOREST,
                        Biomes.NETHER_WASTES,
                        Biomes.WARPED_FOREST
                ), MAGMA)
        );

        // TODO: Give it's own noise
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, -0.0125, 0.0125), altForBiome),
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, -0.525, -0.5), altForBiome),
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, 0.5, 0.525), altForBiome)
        );
    }

    // Soil seams in the nether
    private static SurfaceRules.RuleSource netherSoilSeams(HolderGetter<Biome> biomes, BootstrapContext<WorldgenModifier> context) {
        SurfaceRules.RuleSource altForBiome = SurfaceRules.sequence(
                //Soul Sand Biomes
                SurfaceRules.ifTrue(SurfaceRules.isBiome(
                        biomes,
                        Biomes.NETHER_WASTES,
                        Biomes.SOUL_SAND_VALLEY
                ), SOUL_SAND)
//                // Causes... Issues
//                //Default
//                GRAVEL
        );

        // TODO: Give it's own noise
        return SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.GRAVEL, -0.02, 0.02), altForBiome);
    }

    // Alternate stone in the end
    private static SurfaceRules.RuleSource endStoneSeams(HolderGetter<Biome> biomes, BootstrapContext<WorldgenModifier> context) {
//        // Since it's jst obsidian we can disable this right now
        SurfaceRules.RuleSource altForBiome = OBSIDIAN; // SurfaceRules.sequence(
//                //Obsidian Biomes
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(
//                        Biomes.END_BARRENS,
//                        Biomes.END_HIGHLANDS,
//                        Biomes.END_MIDLANDS,
//                        Biomes.SMALL_END_ISLANDS,
//                        Biomes.THE_END
//                ), OBSIDIAN)
//        );

        // TODO: Give it's own noise
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, -0.0125, 0.0125), altForBiome),
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, -0.525, -0.5), altForBiome),
                SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.CALCITE, 0.5, 0.525), altForBiome)
        );
    }

    // What would be soil, if the end had any...
    private static SurfaceRules.RuleSource endSoilSeams(HolderGetter<Biome> biomes, BootstrapContext<WorldgenModifier> context) {
        // since it's all air, I'll just go and make it easy...
        SurfaceRules.RuleSource altForBiome = AIR; // SurfaceRules.sequence(
//                //Air Biomes
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(
//                        Biomes.END_BARRENS,
//                        Biomes.END_HIGHLANDS,
//                        Biomes.END_MIDLANDS,
//                        Biomes.SMALL_END_ISLANDS,
//                        Biomes.THE_END,
//                        Biomes.THE_VOID
//                ), AIR)
//        );

        // TODO: Give it's own noise
        return SurfaceRules.ifTrue(RSurfaceRules.noise3dCondition(Noises.GRAVEL, -0.02, 0.02), altForBiome);
    }

}
