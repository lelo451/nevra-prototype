package br.com.nevra.cep.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
public class IBGE {
    private String UF;
    private String codUF;
    private String municipio;
    private String codMunicipio;
    private BigDecimal areaMunicipio;
}
