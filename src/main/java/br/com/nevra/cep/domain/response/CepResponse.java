package br.com.nevra.cep.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CepResponse {

    private String webservice;
}
