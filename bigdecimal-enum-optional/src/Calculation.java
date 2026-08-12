import java.math.BigDecimal;

public interface Calculation {
    /* an interface declares a behavior without limiting how many implementations exist */
    BigDecimal apply(BigDecimal left, BigDecimal right);
}
