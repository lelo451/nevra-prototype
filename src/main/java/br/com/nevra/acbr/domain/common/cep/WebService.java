package br.com.nevra.acbr.domain.common.cep;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rften
 */
public enum  WebService {
    wsNenhum(0), 
    wsBuscarCep(1),
    wsCepLivre(2),
    wsRepublicaVirtual(3),
    wsBases4you(4),
    wsRNSolucoes(5),
    wsKingHost(6),
    wsByJG(7),
    wsCorreios(8),
    wsDevMedia(9),
    wsViaCep(10),
    wsCorreiosSIGEP(11),
    wsCepAberto(12),
    wsWSCep(13);
    
    private static final Map<Integer, WebService> map;
    private final int enumValue; 
    
    static {
        map = new HashMap<>();
        for (WebService value : WebService.values()) {
            map.put(value.asInt(), value);
        }
    }
    
    public static WebService valueOf(int value) {
        return map.get(value);
    }
    
    private WebService(int id) {
        this.enumValue = id;
    }
    
    public int asInt() {
        return enumValue;
    }
}
