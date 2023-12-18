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
        vBC  = Double.parseDouble(produto.getVProd());
        vIPI = MathUtil.round2(vBC * (pIPI / 100));
        return IPI.builder()
                .pIPI(pIPI)
                .vBC(vBC)
                .vIPI(vIPI)
                .build();
    }

    public IPI calcularIPITotal(IPI produto, IPI total) {
        double pIPI, vBC, vIPI;
        pIPI = MathUtil.round2(produto.getPIPI() + total.getPIPI());
        vBC  = MathUtil.round2(produto.getVBC() + total.getVBC());
        vIPI = MathUtil.round2(produto.getVIPI() + total.getVIPI());
        return IPI.builder()
                .pIPI(pIPI)
                .vBC(vBC)
                .vIPI(vIPI)
                .build();
    }

    public COFINS calcularCOFINS(Produto produto) {
        double pCOFINS, vBC, vCOFINS;
        pCOFINS = Double.parseDouble(produto.getPCOFINS());
        vBC     = Double.parseDouble(produto.getVProd());
        vCOFINS = MathUtil.round2(vBC * (pCOFINS / 100));
        return COFINS.builder()
                .pCOFINS(pCOFINS)
                .vBC(vBC)
                .vCOFINS(vCOFINS)
                .build();
    }

    public COFINS calcularCOFINSTotal(COFINS produto, COFINS total) {
        double pCOFINS, vBC, vCOFINS;
        pCOFINS = MathUtil.round2(produto.getPCOFINS() + total.getPCOFINS());
        vBC     = MathUtil.round2(produto.getVBC() + total.getVBC());
        vCOFINS = MathUtil.round2(produto.getVCOFINS() + total.getVCOFINS());
        return COFINS.builder()
                .pCOFINS(pCOFINS)
                .vBC(vBC)
                .vCOFINS(vCOFINS)
                .build();
    }

    public PIS calcularPIS(Produto produto) {
        double pPIS, vBC, vPIS;
        pPIS = Double.parseDouble(produto.getPPIS());
        vBC  = Double.parseDouble(produto.getVProd());
        vPIS = MathUtil.round2(vBC * (pPIS / 100));
        return PIS.builder()
                .pPIS(pPIS)
                .vBC(vBC)
                .vPIS(vPIS)
                .build();
    }

    public PIS calcularPISTotal(PIS produto, PIS total) {
        double pPIS, vBC, vPIS;
        pPIS = MathUtil.round2(produto.getPPIS() + total.getPPIS());
        vBC  = MathUtil.round2(produto.getVBC() + total.getVBC());
        vPIS = MathUtil.round2(produto.getVPIS() + total.getVPIS());
        return PIS.builder()
                .pPIS(pPIS)
                .vBC(vBC)
                .vPIS(vPIS)
                .build();
    }

    public ICMS calcularICMS(Imposto imposto, Produto produto) {
        double pICMS, vBC, vICMS;
        pICMS = 1 - (Double.parseDouble(produto.getPICMS()) / 100);
        vBC   = MathUtil.round2(
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

    public ICMS calcularICMSTotal(ICMS produto, ICMS total) {
        double pICMS, vBC, vICMS;
        pICMS = MathUtil.round2(produto.getPICMS() + total.getPICMS());
        vBC   = MathUtil.round2(produto.getVBC() + total.getVBC());
        vICMS = MathUtil.round2(produto.getVICMS() + total.getVICMS());
        return ICMS.builder()
                .pICMS(pICMS)
                .vBC(vBC)
                .vICMS(vICMS)
                .build();
    }

    public Imposto calcularTotal(Imposto produto, Imposto total) {
        // Calculo IPI
        total.setIPI(calcularIPITotal(produto.getIPI(), total.getIPI()));

        // Calculo COFINS
        total.setCOFINS(calcularCOFINSTotal(produto.getCOFINS(), total.getCOFINS()));

        // Calculo PIS
        total.setPIS(calcularPISTotal(produto.getPIS(), total.getPIS()));

        // Calculo ICMS
        total.setICMS(calcularICMSTotal(produto.getICMS(), total.getICMS()));

        // Setando valores
        total.setVII(MathUtil.round2(produto.getVII() + total.getVII()));
        total.setVItem(MathUtil.round2(produto.getVItem() + total.getVItem()));

        return total;
    }

    public Imposto iniciarImpostoZerado() {
        return Imposto.builder()
                .IPI(iniciarIPIZerado())
                .COFINS(iniciarCOFINSZerado())
                .PIS(iniciarPISZerado())
                .ICMS(iniciarICMSZerado())
                .vII(0.0)
                .vItem(0.0)
                .build();
    }

    public IPI iniciarIPIZerado() {
        return IPI.builder()
                .pIPI(0.0)
                .vBC(0.0)
                .vIPI(0.0)
                .build();
    }

    public COFINS iniciarCOFINSZerado() {
        return COFINS.builder()
                .pCOFINS(0.0)
                .vBC(0.0)
                .vCOFINS(0.0)
                .build();
    }

    public PIS iniciarPISZerado() {
        return PIS.builder()
                .pPIS(0.0)
                .vBC(0.0)
                .vPIS(0.0)
                .build();
    }

    public ICMS iniciarICMSZerado() {
        return ICMS.builder()
                .pICMS(0.0)
                .vBC(0.0)
                .vICMS(0.0)
                .build();
    }
}
