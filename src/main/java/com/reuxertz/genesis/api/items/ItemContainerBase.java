package com.reuxertz.genesis.api.items;

import com.reuxertz.genesis.api.containers.ContainerBase;
import com.reuxertz.genesis.api.gui.GuiContainerBase;
import com.reuxertz.genesis.api.containers.IGuiContainer;
import com.reuxertz.genesis.mod.Genesis;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ItemContainerBase extends ItemBase implements IGuiContainer
{
    protected ResourceLocation guiTexture = new ResourceLocation(Genesis.MODID, "textures/gui/<replace>.png");

    @Override
    public int getInventorySize()
    {
        return 27;
    }

    @Override
    public boolean hasPlayerInventory()
    {
        return true;
    }

    public ItemContainerBase(String name)
    {
        super(name);
        guiTexture = new ResourceLocation(guiTexture.getResourceDomain(), guiTexture.getResourcePath().replace("<replace>", name));
    }

    @Override
    public void construct(ContainerBase containerBase) {

        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 9; x++)
                containerBase.addSlot(new Slot(containerBase.inventory, x + y * 9, 8 + x * 18, 18 + y * 18));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.MAIN_HAND == !world.isRemote) {
            player.openGui(Genesis.instance, 0, world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
            return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItemMainhand());
        }

        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItemMainhand());
    }

    @Override
    public Container getContainer(EntityPlayer player, World world, Object guiContainerObject)
    {
        return new ContainerBase(player, this, guiContainerObject);
    }

    @Override
    public GuiContainer getGuiContainer(EntityPlayer player, World world, Object guiContainerObject)
    {
        return new GuiContainerBase(player, this, guiContainerObject);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return guiTexture;
    }

}
