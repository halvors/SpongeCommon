package org.spongepowered.common.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;

/**
 * todo World changes...
 */
public class DimensionManager {
    public static WorldProvider createProviderFor(int dimension) {
        return null;
    }

    public static boolean shouldLoadSpawn(int dimension) {
        return true;
    }

    public static void loadDimensionDataMap(NBTTagCompound compount) {

    }

    public static NBTTagCompound saveDimensionDataMap() {
        return null;
    }
}
