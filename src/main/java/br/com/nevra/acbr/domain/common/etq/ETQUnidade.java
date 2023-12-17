package br.com.nevra.acbr.domain.common.etq;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rften
 */
public enum  ETQUnidade {
    etqMilimetros(0),
    etqPolegadas(1),
    etqDots(2),
    etqDecimoDeMilimetros(3);
    
    private static final Map<Integer, ETQUnidade> map;
    private final int enumValue; 
    
    static {
        map = new HashMap<>();
        for (ETQUnidade value : ETQUnidade.values()) {
            map.put(value.asInt(), value);
        }
    }
    
    public static ETQUnidade valueOf(int value) {
        return map.get(value);
    }
    
    private ETQUnidade(int id) {
        this.enumValue = id;
    }
    
    public int asInt() {
        return enumValue;
    }
}
