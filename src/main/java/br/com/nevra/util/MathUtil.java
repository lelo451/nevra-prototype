package br.com.nevra.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {

    public MathUtil() {
        throw new IllegalStateException("Utily Class");
    }

    public static Double round2(Double val) {
        return new BigDecimal(val.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
