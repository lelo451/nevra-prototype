package br.com.nevra.acbr.domain.common.nfse;

import java.util.HashMap;
import java.util.Map;

public enum LayoutNFSe {
    Provedor(0),
    PadraoNacional(1);
    
    private static final Map<Integer, LayoutNFSe> map;
    private final int enumValue;
    
    static {
        map = new HashMap<>();
        for (LayoutNFSe value : LayoutNFSe.values()) {
            map.put(value.asInt(), value);
        }
    }
    
    public static LayoutNFSe valueOf(int value){
        return map.get(value);
    }
    
    private LayoutNFSe(int id){
        this.enumValue = id;
    }
    
    public int asInt() {
        return enumValue;
    }
}
