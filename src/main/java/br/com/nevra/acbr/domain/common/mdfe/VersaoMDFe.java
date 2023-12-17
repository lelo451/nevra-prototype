package br.com.nevra.acbr.domain.common.mdfe;

import java.util.HashMap;
import java.util.Map;

public enum VersaoMDFe {
    ve100(0),
    ve300(1);
    
    private static final Map<Integer, VersaoMDFe> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (VersaoMDFe value : VersaoMDFe.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static VersaoMDFe valueOf(int value) {
        return map.get(value);
    }

    private VersaoMDFe(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}
