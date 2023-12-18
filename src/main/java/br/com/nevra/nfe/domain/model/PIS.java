package br.com.nevra.nfe.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PIS {
    private Double pPIS;
    private Double vBC;
    private Double vPIS;
}
