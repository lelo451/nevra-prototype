package br.com.nevra.cep.domain.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

public class CEPMapper {

    private static final Properties p = new Properties();

    public CEPMapper() {
        throw new IllegalStateException("Utily Class");
    }

    public static Endereco fromStringToEntity(String result) {
        try {
            p.load(new ByteArrayInputStream(result.getBytes()));
            return Endereco.builder()
                    .cep(p.getProperty("CEP"))
                    .tipoLogradouro(p.getProperty("Tipo_Logradouro"))
                    .logradouro(p.getProperty("Logradouro"))
                    .complemento(p.getProperty("Complemento"))
                    .bairro(p.getProperty("Bairro"))
                    .municipio(p.getProperty("Municipio"))
                    .UF(p.getProperty("UF"))
                    .codMunicipio(p.getProperty("IBGE_Municipio"))
                    .codUF(p.getProperty("IBGE_UF"))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
