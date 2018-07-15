package com.reuxertz.genesis.util;

import java.util.HashMap;

public class IDHelper {

    public static HashMap<String, Integer> IDHashMap = new HashMap<>();
    public static int getNextID(String name) {
        if (!IDHashMap.containsKey(name))
            IDHashMap.put(name, 0);

        int result = IDHashMap.get(name);
        IDHashMap.replace(name, result + 1);
        return result;
    }
}
