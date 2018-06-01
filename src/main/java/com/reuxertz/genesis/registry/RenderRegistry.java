package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.client.renderers.EntityHumanRenderer;
import com.reuxertz.genesis.entities.EntityHuman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderRegistry {

    public static class RenderFactoryEntityHuman implements IRenderFactory<EntityHuman>
    {
        public final static RenderFactoryEntityHuman INSTANCE = new RenderFactoryEntityHuman();

        @Override
        public Render<EntityHuman> createRenderFor(RenderManager manager)
        {
            return new EntityHumanRenderer(manager);
        }


    }
}
