package br.com.nevra.acbr.domain.common.cte;

import java.util.HashMap;
import java.util.Map;

public enum VersaoCTe {
    ve200(0),
    ve300(1);
    
    private static final Map<Integer, VersaoCTe> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (VersaoCTe value : VersaoCTe.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static VersaoCTe valueOf(int value) {
        return map.get(value);
    }

    private VersaoCTe(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}
