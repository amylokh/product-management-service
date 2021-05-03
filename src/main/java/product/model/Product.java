package product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Product")
public class Product {

    @NotBlank(message = "ID is mandatory")
    private String id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    @NotBlank(message = "Price is mandatory")
    private Float price;
    private String currency;
    private Object exchangedPrices;

    public Product() {
    }

    public Product(String id, String name, String description, Float price) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getExchangedPrices() {
        return exchangedPrices;
    }

    public void setExchangedPrices(Object exchangedPrices) {
        this.exchangedPrices = exchangedPrices;
    }

}
