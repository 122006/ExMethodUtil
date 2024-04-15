package zircon.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import zircon.ExMethod;
import zircon.data.ThrowSupplier;

public class ExBigDecimal {

    @ExMethod
    public static BigDecimal add(BigDecimal bigDecimal, int number) {
        return bigDecimal.add(new BigDecimal(number));
    }

    @ExMethod
    public static BigDecimal add(BigDecimal bigDecimal, long number) {
        return bigDecimal.add(new BigDecimal(number));
    }

    @ExMethod
    public static BigDecimal add(BigDecimal bigDecimal, double number) {
        return bigDecimal.add(BigDecimal.valueOf(number));
    }

    @ExMethod
    public static BigDecimal divide(BigDecimal bigDecimal, int number, RoundingMode roundingMode) {
        return divide(bigDecimal, number, roundingMode,()->BigDecimal.ZERO);
    }

    @ExMethod
    public static BigDecimal divide(BigDecimal bigDecimal, int number, RoundingMode roundingMode, ThrowSupplier<BigDecimal> zero) {
        if (number == 0) {
            try {
                return zero.get();
            } catch (Exception e) {
                throw (RuntimeException) (e);
            }
        }
        return bigDecimal.divide(new BigDecimal(number), bigDecimal.scale(), roundingMode);
    }

    @ExMethod
    public static BigDecimal multiply(BigDecimal bigDecimal, int number) {
        return bigDecimal.multiply(new BigDecimal(number));
    }

    @ExMethod
    public static BigDecimal subtract(BigDecimal bigDecimal, int number) {
        return bigDecimal.subtract(new BigDecimal(number));
    }

    @ExMethod
    public static BigDecimal divide(BigDecimal bigDecimal, long number, RoundingMode roundingMode, ThrowSupplier<BigDecimal> zero) {
        if (number == 0) {
            try {
                return zero.get();
            } catch (Exception e) {
                throw (RuntimeException) (e);
            }
        }
        return bigDecimal.divide(new BigDecimal(number), bigDecimal.scale(), roundingMode);
    }

    @ExMethod
    public static BigDecimal multiply(BigDecimal bigDecimal, long number) {
        return bigDecimal.multiply(new BigDecimal(number));
    }

    @ExMethod
    public static BigDecimal subtract(BigDecimal bigDecimal, long number) {
        return bigDecimal.subtract(new BigDecimal(number));
    }

    @ExMethod
    public static BigDecimal divide(BigDecimal bigDecimal, double number, RoundingMode roundingMode, ThrowSupplier<BigDecimal> zero) {
        if (number == 0) {
            try {
                return zero.get();
            } catch (Exception e) {
                throw (RuntimeException) (e);
            }
        }
        return bigDecimal.divide(BigDecimal.valueOf(number), bigDecimal.scale(), roundingMode);
    }

    @ExMethod
    public static BigDecimal multiply(BigDecimal bigDecimal, double number) {
        return bigDecimal.multiply(BigDecimal.valueOf(number));
    }

    @ExMethod
    public static BigDecimal subtract(BigDecimal bigDecimal, double number) {
        return bigDecimal.subtract(BigDecimal.valueOf(number));
    }

    @ExMethod
    public static String toString(BigDecimal bigDecimal, int newScale, RoundingMode roundingMode) {
        return bigDecimal.setScale(newScale, roundingMode).toString();
    }

    @ExMethod
    public static String toString(BigDecimal bigDecimal, int newScale) {
        return bigDecimal.setScale(newScale, RoundingMode.HALF_UP).toString();
    }
}
