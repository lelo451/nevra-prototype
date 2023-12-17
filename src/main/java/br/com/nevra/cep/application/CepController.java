package br.com.nevra.cep.application;

import br.com.nevra.cep.domain.model.Endereco;
import br.com.nevra.cep.domain.request.CepRequest;
import br.com.nevra.cep.domain.response.CepResponse;
import br.com.nevra.cep.domain.service.CepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> buscarPorCEP(@PathVariable String cep) throws Exception {
        Endereco endereco = cepService.buscarPorCEP(cep);
        if (endereco == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

    @PostMapping(value = "/webservice")
    public ResponseEntity<CepResponse> configurarWebService(@RequestBody Integer id) throws Exception {
        CepRequest request = cepService.configurarWebService(id);
        return ResponseEntity.status(request.getStatus()).body(
                CepResponse.builder()
                        .webservice(request.getMessage())
                        .build()
        );
    }
}
