package br.com.nevra.nfe.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IPI {
    private Double pIPI;
    private Double vBC;
    private Double vIPI;
}
