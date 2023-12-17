package br.com.nevra.cep.domain.service;

import br.com.nevra.acbr.domain.common.ACBrSessao;
import br.com.nevra.acbr.infrastructure.ACBrCEP;
import br.com.nevra.cep.domain.model.CEPMapper;
import br.com.nevra.cep.domain.model.Endereco;
import br.com.nevra.cep.domain.request.CepRequest;
import br.com.nevra.cep.domain.request.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CepService {
    private ACBrCEP acbrCEP;
    Logger logger = LoggerFactory.getLogger(CepService.class);

    public CepService() {
        try {
            acbrCEP = new ACBrCEP();
            acbrCEP.configLer();
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
        }
    }

    public Endereco buscarPorCEP(String cep) throws Exception {
        return CEPMapper.fromStringToEntity(acbrCEP.buscarPorCEP(cep));
    }

    public CepRequest configurarWebService(Integer id) throws Exception {
        if (id < 0 || id > 13) {
            return CepRequest.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Nenhum webservice disponivel com esse id.")
                    .build();
        }

        acbrCEP.configGravarValor(ACBrSessao.CEP, "WebService", id);

        WebService webService = WebService.values()[id];
        return CepRequest.builder()
                .status(HttpStatus.OK)
                .message(webService.getMessagem())
                .build();
    }
}
