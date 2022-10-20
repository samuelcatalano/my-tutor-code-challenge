package co.uk.mytutor.code.challenge.codechallenge.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

public final class RoundUpValueUtils {

    /**
     * Round Up an value.
     * @param value the value to round up
     * @return
     */
    public static Double roundUpValue(Double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}