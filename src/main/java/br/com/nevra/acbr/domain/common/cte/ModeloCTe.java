package br.com.nevra.acbr.domain.common.cte;

import java.util.HashMap;
import java.util.Map;

public enum ModeloCTe {
    moCTe(0),
    moCTeOS(1);
    
    private static final Map<Integer, ModeloCTe> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (ModeloCTe value : ModeloCTe.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static ModeloCTe valueOf(int value) {
        return map.get(value);
    }

    private ModeloCTe(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}
