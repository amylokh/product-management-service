package product.model;

import java.io.Serializable;
import java.util.Map;

public class Price implements Serializable {
    private String base;
    private Map<String, Float> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }
}
