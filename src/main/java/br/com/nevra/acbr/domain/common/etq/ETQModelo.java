package br.com.nevra.acbr.domain.common.etq;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rften
 */
public enum  ETQModelo {
    etqNenhum(0),
    etqPpla(1),
    etqPplb(2),
    etqZPLII(3),
    etqEpl2(4);
    
    private static final Map<Integer, ETQModelo> map;
    private final int enumValue; 
    
    static {
        map = new HashMap<>();
        for (ETQModelo value : ETQModelo.values()) {
            map.put(value.asInt(), value);
        }
    }
    
    public static ETQModelo valueOf(int value) {
        return map.get(value);
    }
    
    private ETQModelo(int id) {
        this.enumValue = id;
    }
    
    public int asInt() {
        return enumValue;
    }
}
