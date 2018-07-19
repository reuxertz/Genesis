package com.reuxertz.genesisAPI.internal;

import com.reuxertz.genesisAPI.GenesisAPI;
import com.reuxertz.genesisAPI.GenesisPlugin;
import com.reuxertz.genesisAPI.IGenesisPlugin;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import java.util.ArrayList;
import java.util.Set;

public class GenesisApiHandler {
    public static final ArrayList<IGenesisPlugin> PLUGINS = new ArrayList<>();

    public static void loadPlugins(ASMDataTable table) {
	PLUGINS.clear(); //Should be empty anyway, but just to make sure plugins arn't double registered
	String annotationClassName = GenesisPlugin.class.getCanonicalName();
	Set<ASMDataTable.ASMData> asmDatas = table.getAll(annotationClassName);
	for (ASMDataTable.ASMData asmData : asmDatas)
		try {
			Class<?> asmClass = Class.forName(asmData.getClassName());
			Class<? extends IGenesisPlugin> asmInstanceClass = asmClass.asSubclass(IGenesisPlugin.class);
			IGenesisPlugin instance = asmInstanceClass.newInstance();
			PLUGINS.add(instance);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e) {
			GenesisAPI.logger.error("Failed to load plugin {}: {}", asmData.getClassName(), e.getLocalizedMessage());
		}
    }
    public static void register() {
	//Clear all current data. 
	
	for(IGenesisPlugin plugin : PLUGINS) {
	    long time = System.currentTimeMillis();
	    GenesisAPI.logger.info("Sending registry event to: {}" , plugin.getModID());
	    try {
		plugin.register(GenesisAPI.registry);
	    } catch (Throwable t) {
		throw new RuntimeException("Exception while loading Genesis plugin :" + plugin.getModID(), t);
	    }
		GenesisAPI.logger.info("Successfully registered Genesis plugin: {} (took {}ms)" , plugin.getModID(), System.currentTimeMillis() - time);

	}
    }
}
