package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.IEventHandler;

import java.util.HashMap;

public class EventRegistry {

    private static final HashMap<String, IEventHandler> eventRegistry = new HashMap<>();

    public static void registerEventHandler(String name, IEventHandler eventHandler)
    {
        eventRegistry.put(name, eventHandler);
    }
}
