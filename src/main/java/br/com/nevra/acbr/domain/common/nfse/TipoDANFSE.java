package br.com.nevra.acbr.domain.common.nfse;

import java.util.HashMap;
import java.util.Map;

public enum TipoDANFSE {
    Retrato(0),
    Paisagem(1);
    
    private static final Map<Integer, TipoDANFSE> map;
    private final int enumValue;
    
    static {
        map = new HashMap<>();
        for (TipoDANFSE value : TipoDANFSE.values()) {
            map.put(value.asInt(), value);
        }
    }
    
    public static TipoDANFSE valueOf(int value){
        return map.get(value);
    }
    
    private TipoDANFSE(int id){
        this.enumValue = id;
    }
    
    public int asInt() {
        return enumValue;
    }
}
