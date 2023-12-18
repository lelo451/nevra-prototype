package br.com.nevra.nfe.application;

import br.com.nevra.nfe.domain.model.Imposto;
import br.com.nevra.nfe.domain.model.Produto;
import br.com.nevra.nfe.domain.service.NFeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nfe")
public class NFeController {

    private final NFeService nFeService;

    public NFeController(NFeService nFeService) {
        this.nFeService = nFeService;
    }

    @GetMapping
    public ResponseEntity<String> carregarXML(@RequestBody String path) throws Exception {
        Path p = Paths.get(path);
        nFeService.carregarXML(p);
        return ResponseEntity.status(HttpStatus.OK).body(nFeService.obterXML());
    }

    @GetMapping("/calculo/impostos")
    public ResponseEntity<List<Imposto>> calcularImposto(@RequestBody List<Produto> produtos) {
        List<Imposto> impostos = new ArrayList<>();
        for (Produto p : produtos) {
            impostos.add(nFeService.calcularImposto(p));
        }
        Imposto total = nFeService.iniciarImpostoZerado();
        for (Imposto i : impostos) {
           total = nFeService.calcularTotal(i, total);
        }
        impostos.add(total);
        return ResponseEntity.status(HttpStatus.OK).body(impostos);
    }
}
