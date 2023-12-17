package br.com.nevra.nfe.domain.service;

import br.com.nevra.acbr.infrastructure.ACBrNFe;
import br.com.nevra.nfe.domain.model.Imposto;
import br.com.nevra.nfe.domain.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.text.DecimalFormat;

@Service
public class NFeService {

    private ACBrNFe acbrNFe;
    Logger logger = LoggerFactory.getLogger(NFeService.class);

    public NFeService() {
        try {
            acbrNFe = new ACBrNFe();
            acbrNFe.configLer();
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
        }
    }

    public void carregarXML(Path path) throws Exception {
        acbrNFe.limparLista();
        acbrNFe.carregarXml(path.toFile().getAbsolutePath());
        System.out.println();
    }

    public String obterXML() throws Exception {
        return acbrNFe.obterXml(0);
    }

    public Imposto calcularImposto(Produto produto) {
        Imposto imposto = new Imposto();
        Double pICMS;
        imposto.setPIPI(Double.parseDouble(produto.getPIPI()));
        imposto.setVBCIPI(Double.parseDouble(produto.getVProd()));
        imposto.setVIPI(imposto.round2(imposto.getVBCIPI() * (imposto.getPIPI() / 100)));

        // Calculo COFINS
        imposto.setPCOFINS(Double.parseDouble(produto.getPCOFINS()));
        imposto.setVBCCOFINS(Double.parseDouble(produto.getVProd()));
        imposto.setVCOFINS(imposto.round2(imposto.getVBCCOFINS() * (Double.parseDouble(produto.getPCOFINS()) / 100)));


        // Calculo PIS
        imposto.setPPIS(Double.parseDouble(produto.getPPIS()));
        imposto.setVBCPIS(Double.parseDouble(produto.getVProd()));
        imposto.setVPIS(imposto.round2(imposto.getVBCPIS() * (Double.parseDouble(produto.getPPIS()) / 100)));

        // Calculo ICMS
        pICMS = 1 - (Double.parseDouble(produto.getPICMS()) / 100);
        imposto.setPICMS(Double.parseDouble(produto.getPICMS()));
        imposto.setVBCICMS(imposto.round2((Double.parseDouble(produto.getVProd()) +
                Double.parseDouble(produto.getVII()) +
                imposto.getVIPI() +
                imposto.getVPIS() +
                imposto.getVCOFINS() +
                Double.parseDouble(produto.getVOutro())) /
                pICMS));
        imposto.setVICMS(imposto.round2(imposto.getVBCICMS() * (imposto.getPICMS() / 100)));
        imposto.setVII(Double.parseDouble(produto.getVII()));
        imposto.setPPIS(Double.parseDouble(produto.getPPIS()));

        imposto.setVItem(Double.parseDouble(produto.getVProd()));

        return imposto;
    }

//    public Imposto calcularTotal(Imposto imposto, Imposto total) {
//        // Calculo IPI
//        total.setPIPI(total.getPIPI() + imposto.getPIPI());
//        total.setVBCIPI(imposto.round2(total.getVBCIPI() + imposto.getVBCIPI()));
//        total.setVIPI(total.round2(total.getVIPI() + imposto.getVIPI()));
//
//        // Calculo COFINS
//        total.setPCOFINS(imposto.round2(total.getPCOFINS() + imposto.getPCOFINS()));
//        total.setVBCCOFINS(imposto.round2(total.getVBCCOFINS() + imposto.getVBCCOFINS()));
//        total.setVCOFINS(total.getVCOFINS() + imposto.getVCOFINS());
//
//
//        // Calculo PIS
//        total.setPPIS(total.getPPIS() + imposto.getPPIS());
//        total.setVBCPIS(imposto.round2(total.getVBCPIS() + imposto.getVBCPIS()));
//        total.setVPIS(total.round2(total.getVPIS() + imposto.getVPIS()));
//
//        // Calculo ICMS
//        total.setPICMS(total.getPICMS() + imposto.getPICMS());
//        total.setVBCICMS(total.round2(total.getVBCICMS() + imposto.getVBCICMS()));
//        total.setVICMS(total.round2(total.getVICMS() + imposto.getVICMS()));
//        total.setVII(total.getVII() + imposto.getVII());
//        total.setPPIS(total.getPPIS() + imposto.getPPIS());
//
//        total.setVItem(imposto.round2(total.getVItem() + imposto.getVItem()));
//
//        return total;
//    }
}
