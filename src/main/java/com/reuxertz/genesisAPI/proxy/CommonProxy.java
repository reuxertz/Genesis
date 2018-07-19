package com.reuxertz.genesisAPI.proxy;

import com.reuxertz.genesisAPI.internal.GenesisAPIHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {

        GenesisAPIHandler.register();

        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "genesisAPI.cfg"));
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {

        if (config.hasChanged())
            config.save();

    }
}
