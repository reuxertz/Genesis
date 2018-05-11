package com.reuxertz.genesis.internal;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.registry.GenesisRegistry;
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
			Genesis.logger.error("Failed to load plugin {}: {}", asmData.getClassName(), e.getLocalizedMessage());
		}
    }
    public static void register() {
	//Clear all current data. 
	
	for(IGenesisPlugin plugin : PLUGINS) {
	    long time = System.currentTimeMillis();
	    Genesis.logger.info("Sending registry event to: {}" , plugin.getModID());
	    try {
		plugin.register(new GenesisRegistry(plugin.getModID()));
	    } catch (Throwable t) {
		throw new RuntimeException("Exception while loading Genesis plugin :" + plugin.getModID(), t);
	    }
	    Genesis.logger.info("Successfully registered Genesis plugin: {} (took {}ms)" , plugin.getModID(), System.currentTimeMillis() - time);

	}
    }
}
