package br.com.nevra.nfe.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class Imposto {

    private Double pIPI;
    private Double vBCIPI;
    private Double vIPI;
    private Double vII;
    private Double vItem;
    private Double pCOFINS;
    private Double vBCCOFINS;
    private Double vCOFINS;
    private Double pPIS;
    private Double vBCPIS;
    private Double vPIS;
    private Double pICMS;
    private Double vBCICMS;
    private Double vICMS;

    public Imposto() {
        this.pIPI = 0.0;
        this.vBCIPI = 0.0;
        this.vIPI = 0.0;
        this.vII = 0.0;
        this.vItem = 0.0;
        this.pCOFINS = 0.0;
        this.vBCCOFINS = 0.0;
        this.vCOFINS = 0.0;
        this.pPIS = 0.0;
        this.vBCPIS = 0.0;
        this.vPIS = 0.0;
        this.pICMS = 0.0;
        this.vBCICMS = 0.0;
        this.vICMS = 0.0;
    }

    public Double round2(Double val) {
        return new BigDecimal(val.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
