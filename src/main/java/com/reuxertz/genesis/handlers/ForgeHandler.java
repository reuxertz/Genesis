package com.reuxertz.genesis.handlers;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.items.BaseCropSeed;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class ForgeHandler {

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickItem event){

//        if (event.getItemStack().getItem() instanceof BaseCropSeed)
//        {
//            BaseCropSeed seed = (BaseCropSeed)event.getItemStack().getItem();
//            event.getWorld().setBlockState(event.getPos().down(), seed.getBlockCrop().withAge(0));
//            return;
//        }


        return;
    }

}
