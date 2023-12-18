package br.com.nevra.nfe.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ICMS {
    private Double pICMS;
    private Double vBC;
    private Double vICMS;
}
