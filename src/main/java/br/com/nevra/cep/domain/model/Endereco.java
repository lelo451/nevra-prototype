package br.com.nevra.cep.domain.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class Endereco extends IBGE {

    private String cep;
    private String tipoLogradouro;
    private String logradouro;
    private String complemento;
    private String bairro;
}
