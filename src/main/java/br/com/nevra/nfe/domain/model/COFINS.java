package br.com.nevra.nfe.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class COFINS {
    private Double pCOFINS;
    private Double vBC;
    private Double vCOFINS;
}
