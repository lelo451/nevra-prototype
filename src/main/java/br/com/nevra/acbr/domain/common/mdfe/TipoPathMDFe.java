package br.com.nevra.acbr.domain.common.mdfe;

import java.util.HashMap;
import java.util.Map;

public enum TipoPathMDFe {
    MDFe(0),
    Cancelamento(1);
    
    private static final Map<Integer, TipoPathMDFe> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (TipoPathMDFe value : TipoPathMDFe.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static TipoPathMDFe valueOf(int value) {
        return map.get(value);
    }

    private TipoPathMDFe(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}
