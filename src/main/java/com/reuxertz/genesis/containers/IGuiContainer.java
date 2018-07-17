package com.reuxertz.genesis.containers;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public interface IGuiContainer {

    Container getContainer(EntityPlayer player, World world, Object guiContainerObject);

    GuiContainer getGuiContainer(EntityPlayer player, World world, Object guiContainerObject);

    ResourceLocation getTexture();

    int getInventorySize();

    boolean hasPlayerInventory();

    void construct(ContainerBase containerBase);
}
