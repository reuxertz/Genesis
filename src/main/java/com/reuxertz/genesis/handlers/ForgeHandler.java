package com.reuxertz.genesis.handlers;

import com.reuxertz.genesis.mod.Genesis;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class ForgeHandler {

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickItem event){

//        if (event.getItemStack().getItem() instanceof CropSeedBase)
//        {
//            CropSeedBase seed = (CropSeedBase)event.getItemStack().getItem();
//            event.getWorld().setBlockState(event.getPos().down(), seed.getBlockCrop().withAge(0));
//            return;
//        }

        return;
    }

//    @SubscribeEvent
//    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
//        if (event.getWorld().isRemote) {
//            event.getEntityPlayer().swingArm(event.getHand());
//        } else {
//            event.getEntityPlayer().dropItem(new ItemStack(Blocks.COBBLESTONE, 1), false);
//        }
//    }

    @SubscribeEvent
    public void onInteraction(PlayerInteractEvent.RightClickBlock event) {
        ItemStack curItem = event.getEntityPlayer().getHeldItemMainhand();
        if (!event.getWorld().isRemote && event.getHand() == EnumHand.MAIN_HAND) {
            //if (curItem.getItem() instanceof ISemiBlockItem) {
               // boolean success = interact(event, curItem, event.getPos());

                //If the blocks can't be placed in the pos, then try to place it next to the blocks.
                //if(!success && event.getFace() != null)
                //    success = interact(event, curItem, event.getPos().offset(event.getFace()));

                //if(success) event.setCanceled(true);
            //}
//        } else if (event.getWorld().isRemote && curItem.getItem() instanceof ISemiBlockItem) {
//            event.setCancellationResult(EnumActionResult.SUCCESS);
//            event.setCanceled(true);

            return;
        }
    }


//    @SubscribeEvent
//    public void RightClickBlock(PlayerInteractEvent.RightClickBlock event)
//    {
//        boolean b1 = event.getHand() == EnumHand.OFF_HAND;
//        IBlockState bottomBlock = event.getWorld().getBlockState(event.getPos());
//        IBlockState topBlock = event.getWorld().getBlockState(event.getPos());
//        boolean b2 = BlockCropBase.canBlockSustainGenesisPlant(bottomBlock);
//        boolean b3 = topBlock.getBlock() == Blocks.AIR;
//
//        if (b1 && b2 && b3)
//        {
//            return;
//        }
//    }

}
