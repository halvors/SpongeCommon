/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.mixin.core.block.tiles;

import static org.spongepowered.api.data.DataQuery.of;

import com.google.common.collect.Lists;
import net.minecraft.util.IChatComponent;
import org.spongepowered.api.block.tile.Sign;
import org.spongepowered.api.block.tile.TileEntityType;
import org.spongepowered.api.block.tile.TileEntityTypes;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(net.minecraft.tileentity.TileEntitySign.class)
public abstract class MixinTileEntitySign extends MixinTileEntity implements Sign {

    @Shadow public IChatComponent[] signText;

    @Override
    public TileEntityType getType() {
        return TileEntityTypes.SIGN;
    }

    @Override
    public DataContainer toContainer() {
        DataContainer container = super.toContainer();
        List<String> lines = Lists.newArrayListWithExpectedSize(4);
        for (IChatComponent line : this.signText) {
            lines.add(IChatComponent.Serializer.componentToJson(line));
        }
        container.set(of("Lines"), lines);
        return container;
    }
}