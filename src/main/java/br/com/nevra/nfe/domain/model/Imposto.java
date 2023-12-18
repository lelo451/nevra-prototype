package br.com.nevra.nfe.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Imposto {
    private Double vII;
    private Double vItem;
    private IPI IPI;
    private PIS PIS;
    private COFINS COFINS;
    private ICMS ICMS;
}
