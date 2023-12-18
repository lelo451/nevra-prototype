package br.com.nevra.nfe.domain.service;

import br.com.nevra.acbr.infrastructure.ACBrNFe;
import br.com.nevra.nfe.domain.model.*;
import br.com.nevra.util.MathUtil;
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
        Imposto imposto = Imposto.builder().build();
        Double pICMS;
        // Calcular IPI
        imposto.setIPI(calcularIPI(produto));

        // Calculo COFINS
        imposto.setCOFINS(calcularCOFINS(produto));

        // Calculo PIS
        imposto.setPIS(calcularPIS(produto));

        // Calculo ICMS
        imposto.setICMS(calcularICMS(imposto, produto));

        // Setando valores
        imposto.setVII(Double.parseDouble(produto.getVII()));
        imposto.setVItem(Double.parseDouble(produto.getVProd()));

        return imposto;
    }

    public IPI calcularIPI(Produto produto) {
        double pIPI, vBC, vIPI;
        pIPI = Double.parseDouble(produto.getPIPI());
        vBC = Double.parseDouble(produto.getVProd());
        vIPI = MathUtil.round2(vBC * (pIPI / 100));
        return IPI.builder()
                .pIPI(pIPI)
                .vBC(vBC)
                .vIPI(vIPI)
                .build();
    }

    public COFINS calcularCOFINS(Produto produto) {
        double pCOFINS, vBC, vCOFINS;
        pCOFINS = Double.parseDouble(produto.getPCOFINS());
        vBC = Double.parseDouble(produto.getVProd());
        vCOFINS = MathUtil.round2(vBC * (pCOFINS / 100));
        return COFINS.builder()
                .pCOFINS(pCOFINS)
                .vBC(vBC)
                .vCOFINS(vCOFINS)
                .build();
    }

    public PIS calcularPIS(Produto produto) {
        double pPIS, vBC, vPIS;
        pPIS = Double.parseDouble(produto.getPPIS());
        vBC = Double.parseDouble(produto.getVProd());
        vPIS = MathUtil.round2(vBC * (pPIS / 100));
        return PIS.builder()
                .pPIS(pPIS)
                .vBC(vBC)
                .vPIS(vPIS)
                .build();
    }

    public ICMS calcularICMS(Imposto imposto, Produto produto) {
        double pICMS, vBC, vICMS;
        pICMS = 1 - (Double.parseDouble(produto.getPICMS()) / 100);
        vBC = MathUtil.round2(
                (Double.parseDouble(produto.getVProd()) +
                Double.parseDouble(produto.getVII()) +
                imposto.getIPI().getVIPI() +
                imposto.getPIS().getVPIS() +
                imposto.getCOFINS().getVCOFINS() +
                Double.parseDouble(produto.getVOutro())) /
                pICMS);
        vICMS = MathUtil.round2(vBC * (Double.parseDouble(produto.getPICMS()) / 100));
        return ICMS.builder()
                .pICMS(Double.parseDouble(produto.getPICMS()))
                .vBC(vBC)
                .vICMS(vICMS)
                .build();
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
