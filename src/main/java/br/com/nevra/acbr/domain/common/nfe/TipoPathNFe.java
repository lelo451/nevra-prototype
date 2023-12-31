package br.com.nevra.acbr.domain.common.nfe;

import java.util.HashMap;
import java.util.Map;

public enum TipoPathNFe {
    NFe(0),
    Inutilizacao(1),
    CCe(2),
    Cancelamento(3);
    
    private static final Map<Integer, TipoPathNFe> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (TipoPathNFe value : TipoPathNFe.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static TipoPathNFe valueOf(int value) {
        return map.get(value);
    }

    private TipoPathNFe(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}
