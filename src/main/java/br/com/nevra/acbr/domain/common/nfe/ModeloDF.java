package br.com.nevra.acbr.domain.common.nfe;

import java.util.HashMap;
import java.util.Map;

public enum ModeloDF {
    moNFe(0),
    moNFCe(1);
    
    private static final Map<Integer, ModeloDF> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (ModeloDF value : ModeloDF.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static ModeloDF valueOf(int value) {
        return map.get(value);
    }

    private ModeloDF(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}
