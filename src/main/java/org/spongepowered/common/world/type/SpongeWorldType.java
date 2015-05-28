package org.spongepowered.common.world.type;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.gen.ChunkProviderDebug;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.FlatGeneratorInfo;

import java.util.Arrays;

public abstract class SpongeWorldType extends WorldType {

    private static int getNextID()
    {
        for (int x = 0; x < worldTypes.length; x++)
        {
            if (worldTypes[x] == null)
            {
                return x;
            }
        }

        int oldLen = worldTypes.length;
        worldTypes = Arrays.copyOf(worldTypes, oldLen + 16);
        return oldLen;
    }

    protected SpongeWorldType(String name) {
        super(getNextID(), name);
    }

    public net.minecraft.world.biome.WorldChunkManager getChunkManager(World world) {
        if (this == FLAT) {
            final FlatGeneratorInfo flatgeneratorinfo = FlatGeneratorInfo.createFlatGeneratorFromString(world.getWorldInfo().getGeneratorOptions());
            return new WorldChunkManagerHell(
                    BiomeGenBase.getBiomeFromBiomeList(flatgeneratorinfo.getBiome(), net.minecraft.world.biome.BiomeGenBase.field_180279_ad), 0.5F);
        }
        else if (this == DEBUG_WORLD) {
            return new WorldChunkManagerHell(net.minecraft.world.biome.BiomeGenBase.plains, 0.0F);
        }
        else {
            return new WorldChunkManager(world);
        }
    }

    public net.minecraft.world.chunk.IChunkProvider getChunkGenerator(World world, String generatorOptions) {
        if (this == FLAT) {
            return new ChunkProviderFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(),
                    generatorOptions);
        }
        if (this == DEBUG_WORLD) {
            return new ChunkProviderDebug(world);
        }
        return new ChunkProviderGenerate(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }
}
