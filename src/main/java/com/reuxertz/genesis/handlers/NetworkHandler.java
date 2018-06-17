package com.reuxertz.genesis.handlers;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.handlers.packets.PacketBase;
import com.reuxertz.genesis.handlers.packets.PacketOrganismNBT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    private static int id = 1;

    public static void preInit() {
        registerMessage(PacketOrganismNBT.class, Side.CLIENT);
        registerMessage(PacketOrganismNBT.class, Side.SERVER);
    }

    private static void registerMessage(Class<? extends PacketBase> message, Side recievingSide) {
        Genesis.networkInstance.registerMessage((m, ctx) -> {
            IThreadListener thread = FMLCommonHandler.instance().getWorldThread(ctx.netHandler);
            thread.addScheduledTask(() -> m.process(ctx, ctx.side == Side.SERVER ? ctx.getServerHandler().player : FMLClientHandler.instance().getClientPlayerEntity()));
            return null;
        }, message, id++, recievingSide);
    }

    public static void sendToServer(IMessage message) {
        Genesis.networkInstance.sendToServer(message);
    }

    public static void sendToPlayer(EntityPlayer player, IMessage message) {
        if(!player.world.isRemote)
            Genesis.networkInstance.sendTo(message, (EntityPlayerMP) player);
    }

    public static void sendToPlayersInWorld(World world, IMessage message) {
        if(world == null)
            sendToAll(message);
        else if(!world.isRemote)
            Genesis.networkInstance.sendToDimension(message, world.provider.getDimension());
    }

    public static void sendToAll(IMessage message) {

        Genesis.networkInstance.sendToAll(message);
    }
}
