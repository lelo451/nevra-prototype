package br.com.nevra.acbr.domain.common.etq;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rften
 */
public enum  ETQBackFeed {
    bfNone(0),
    bfOn(1),
    bfOff(2);
    
    private static final Map<Integer, ETQBackFeed> map;
    private final int enumValue; 
    
    static {
        map = new HashMap<>();
        for (ETQBackFeed value : ETQBackFeed.values()) {
            map.put(value.asInt(), value);
        }
    }
    
    public static ETQBackFeed valueOf(int value) {
        return map.get(value);
    }
    
    private ETQBackFeed(int id) {
        this.enumValue = id;
    }
    
    public int asInt() {
        return enumValue;
    }
}
