import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Operation implements Calculation {
    /* each enum constant provides its own implementation of the same operation */
    ADD {
        @Override
        public BigDecimal apply(BigDecimal left, BigDecimal right) {
            return left.add(right);
        }
    },
    SUBTRACT {
        @Override
        public BigDecimal apply(BigDecimal left, BigDecimal right) {
            return left.subtract(right);
        }
    },
    MULTIPLY {
        @Override
        public BigDecimal apply(BigDecimal left, BigDecimal right) {
            return left.multiply(right);
        }
    },
    DIVIDE {
        @Override
        public BigDecimal apply(BigDecimal left, BigDecimal right) {
            return left.divide(right, 2, RoundingMode.HALF_UP);
        }
    }
}
