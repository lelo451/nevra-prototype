package br.com.nevra.cep.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebService {
    NENHUM("Nenhum WebService foi configurado"),
    BUSCA_CEP("WebService Busca CEP foi configurado"),
    CEP_LIVRE("WebService CEP Livre foi configurado"),
    REPUBLICA_VIRTUAL("WebService Republica Virtual foi configurado"),
    BASES_4YOU("WebService Bases 4 you foi configurado"),
    RN_SOLUCOES("WebService RN Solucoes foi configurado"),
    KING_HOST("WebService King Host foi configurado"),
    BY_JG("WebService By JG foi configurado"),
    CORREIOS("WebService Correios foi configurado"),
    DEVMEDIA("WebService DevMedia foi configurado"),
    VIA_CEP("WebService Via Cep foi configurado"),
    CORREIOS_SIGEP("WebService Correios SIGEP foi configurado"),
    CEP_ABERTO("WebService Cep Aberto foi configurado"),
    WS_CEP("WebService WS Cep foi configurado");

    private final String messagem;
}
