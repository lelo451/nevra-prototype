package br.com.nevra.cep.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class CepRequest {

    private HttpStatus status;
    private String message;
}
