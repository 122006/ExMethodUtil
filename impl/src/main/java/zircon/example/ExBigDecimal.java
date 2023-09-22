package zircon.example;

import zircon.ExMethod;

import java.math.BigDecimal;

public class ExBigDecimal {
    @ExMethod
    public boolean gt(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) > 0;
    }

    @ExMethod
    public boolean ge(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) >= 0;
    }

    @ExMethod
    public boolean eq(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) == 0;
    }

    @ExMethod
    public boolean lt(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) < 0;
    }

    @ExMethod
    public boolean le(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) <= 0;
    }
}
