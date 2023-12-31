package br.com.nevra.acbr.domain.common.etq;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rften
 */
public enum  ETQBarraExibeCodigo {
    becPadrao(0),
    becSIM(1),
    becNAO(2);
    
    private static final Map<Integer, ETQBarraExibeCodigo> map;
    private final int enumValue; 
    
    static {
        map = new HashMap<>();
        for (ETQBarraExibeCodigo value : ETQBarraExibeCodigo.values()) {
            map.put(value.asInt(), value);
        }
    }
    
    public static ETQBarraExibeCodigo valueOf(int value) {
        return map.get(value);
    }
    
    private ETQBarraExibeCodigo(int id) {
        this.enumValue = id;
    }
    
    public int asInt() {
        return enumValue;
    }
}
