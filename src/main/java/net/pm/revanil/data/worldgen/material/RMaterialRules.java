package net.pm.revanil.data.worldgen.material;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.pm.revanil.Revanil;

public class RMaterialRules {
    public static final ResourceKey<MaterialRule> OVERWORLD_SEAMS_SHALLOW = createKey("overworld_seams_shallow");
    public static final ResourceKey<MaterialRule> OVERWORLD_SEAMS_DEEP = createKey("overworld_seams_deep");
    public static final ResourceKey<MaterialRule> NETHER_SEAMS = createKey("nether_seams");
    public static final ResourceKey<MaterialRule> END_SEAMS = createKey("end_seams");

    private static final MaterialRule AIR = makeStateRule(Blocks.AIR);
    private static final MaterialRule ANDESITE = makeStateRule(Blocks.ANDESITE);
    private static final MaterialRule BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final MaterialRule CALCITE = makeStateRule(Blocks.CALCITE);
    private static final MaterialRule CLAY = makeStateRule(Blocks.CLAY);
    private static final MaterialRule DIORITE = makeStateRule(Blocks.DIORITE);
    private static final MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRule DRIPSTONE = makeStateRule(Blocks.DRIPSTONE_BLOCK);
    private static final MaterialRule GRANITE = makeStateRule(Blocks.GRANITE);
    private static final MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final MaterialRule MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);
    private static final MaterialRule OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final MaterialRule PRISMARINE = makeStateRule(Blocks.PRISMARINE);
    private static final MaterialRule RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final MaterialRule SCULK = makeStateRule(Blocks.SCULK);
    private static final MaterialRule SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);
    private static final MaterialRule SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final MaterialRule TUFF = makeStateRule(Blocks.TUFF);

    private static MaterialRule makeStateRule(Block block) {
        return MaterialRules.state(block.defaultBlockState());
    }


    private static ResourceKey<MaterialRule> createKey(final String name) {
        return ResourceKey.create(Registries.MATERIAL_RULE, Identifier.fromNamespaceAndPath(Revanil.MOD_ID, name));
    }

    public static void bootstrap(final BootstrapContext<MaterialRule> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(OVERWORLD_SEAMS_SHALLOW, MaterialRules.sequence(
                shallowStoneSeams(biomes),
                shallowSoilSeams(biomes)
        ));

        // Deep seams only where deepslate will be
        // we need to add checks so that it doesn't encroach on Bedrock or Stone... so here they are
        context.register(OVERWORLD_SEAMS_DEEP, MaterialRules.ifTrue(
                MaterialRules.not(
                        MaterialRules.verticalGradient("bedrock_floor_location",
                                VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5))),
                MaterialRules.ifTrue(
                        MaterialRules.verticalGradient(
                                "deep_seams",
                                VerticalAnchor.absolute(0),
                                VerticalAnchor.absolute(8)),
                        deepStoneSeams(biomes)))
        );

        context.register(NETHER_SEAMS, MaterialRules.sequence(
                netherStoneSeams(biomes),
                netherSoilSeams(biomes)
        ));

        context.register(END_SEAMS, MaterialRules.sequence(
                endStoneSeams(biomes),
                endSoilSeams(biomes)
        ));
    }

    // The alternative in the stone layer
    private static MaterialRule shallowStoneSeams(HolderGetter<Biome> biomes) {
        MaterialRule altForBiome = MaterialRules.sequence(
                //Calcite Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.CHERRY_GROVE,
                        Biomes.FROZEN_PEAKS,
                        Biomes.GROVE,
                        Biomes.JAGGED_PEAKS,
                        Biomes.STONY_PEAKS
                ), CALCITE),
                //Prismarine Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
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
                MaterialRules.ifTrue(MaterialRules.isBiome(
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
                MaterialRules.ifTrue(MaterialRules.isBiome(
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
                MaterialRules.ifTrue(MaterialRules.isBiome(
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
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, -0.0125, 0.0125), altForBiome),
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, -0.525, -0.5), altForBiome),
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, 0.5, 0.525), altForBiome)
        );
    }

    // Soil seams in the stone layer
    private static MaterialRule shallowSoilSeams(HolderGetter<Biome> biomes) {
        MaterialRule altForBiome = MaterialRules.sequence(
                //Sculk Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.DEEP_DARK
                ), SCULK),
                //Sandstone Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.DESERT
                ), SANDSTONE),
                //Red Sandstone Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.BADLANDS,
                        Biomes.ERODED_BADLANDS,
                        Biomes.WOODED_BADLANDS
                ), RED_SANDSTONE),
                //Clay Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.DRIPSTONE_CAVES,
                        Biomes.LUSH_CAVES,
                        Biomes.MANGROVE_SWAMP,
                        Biomes.SWAMP
                ), CLAY),
                //Dirt Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
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
        return MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.GRAVEL, -0.02, 0.02), altForBiome);
    }

    // The alternative in the deepslate layer
    private static MaterialRule deepStoneSeams(HolderGetter<Biome> biomes) {
        MaterialRule altForBiome = MaterialRules.sequence(
                //Smooth Basalt Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.CHERRY_GROVE,
                        Biomes.FROZEN_PEAKS,
                        Biomes.GROVE,
                        Biomes.JAGGED_PEAKS,
                        Biomes.STONY_PEAKS
                ), SMOOTH_BASALT),
//                // These never show up in practice
//                //Obsidian Biomes
//                MaterialRules.ifTrue(MaterialRules.isBiome(
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
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, -0.425, -0.4), altForBiome),
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, 0.4, 0.425), altForBiome)
        );
    }

    // The alternative stones in the nether
    private static MaterialRule netherStoneSeams(HolderGetter<Biome> biomes) {
        MaterialRule altForBiome = MaterialRules.sequence(
                //Blackstone Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.BASALT_DELTAS,
                        Biomes.SOUL_SAND_VALLEY
                ), BLACKSTONE),
                //Magma Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.CRIMSON_FOREST,
                        Biomes.NETHER_WASTES,
                        Biomes.WARPED_FOREST
                ), MAGMA)
        );

        // TODO: Give it's own noise
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, -0.0125, 0.0125), altForBiome),
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, -0.525, -0.5), altForBiome),
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, 0.5, 0.525), altForBiome)
        );
    }

    // Soil seams in the nether
    private static MaterialRule netherSoilSeams(HolderGetter<Biome> biomes) {
        MaterialRule altForBiome = MaterialRules.sequence(
                //Soul Sand Biomes
                MaterialRules.ifTrue(MaterialRules.isBiome(
                        biomes,
                        Biomes.NETHER_WASTES,
                        Biomes.SOUL_SAND_VALLEY
                ), SOUL_SOIL)
//                // Causes... Issues
//                //Default
//                GRAVEL
        );

        // TODO: Give it's own noise
        return MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.GRAVEL, -0.02, 0.02), altForBiome);
    }

    // Alternate stone in the end
    private static MaterialRule endStoneSeams(HolderGetter<Biome> biomes) {
//        // Since it's jst obsidian we can disable this right now
        MaterialRule altForBiome = OBSIDIAN; // MaterialRules.sequence(
//                //Obsidian Biomes
//                MaterialRules.ifTrue(MaterialRules.isBiome(
//                        Biomes.END_BARRENS,
//                        Biomes.END_HIGHLANDS,
//                        Biomes.END_MIDLANDS,
//                        Biomes.SMALL_END_ISLANDS,
//                        Biomes.THE_END
//                ), OBSIDIAN)
//        );

        // TODO: Give it's own noise
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, -0.0125, 0.0125), altForBiome),
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, -0.525, -0.5), altForBiome),
                MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.CALCITE, 0.5, 0.525), altForBiome)
        );
    }

    // What would be soil, if the end had any...
    private static MaterialRule endSoilSeams(HolderGetter<Biome> biomes) {
        // since it's all air, I'll just go and make it easy...
        MaterialRule altForBiome = AIR; // MaterialRules.sequence(
//                //Air Biomes
//                MaterialRules.ifTrue(MaterialRules.isBiome(
//                        Biomes.END_BARRENS,
//                        Biomes.END_HIGHLANDS,
//                        Biomes.END_MIDLANDS,
//                        Biomes.SMALL_END_ISLANDS,
//                        Biomes.THE_END,
//                        Biomes.THE_VOID
//                ), AIR)
//        );

        // TODO: Give it's own noise
        return MaterialRules.ifTrue(MaterialRules.noiseCondition3d(Noises.GRAVEL, -0.02, 0.02), altForBiome);
    }
}
