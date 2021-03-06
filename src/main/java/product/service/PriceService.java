package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.model.Price;
import product.model.ProductException;

@Service
public class PriceService implements IPriceService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Retryable(maxAttempts=4, value = RuntimeException.class, backoff = @Backoff(delay = 15000, multiplier = 2))
    public Price retrieveCurrentExchangeRates() {
        System.out.println("Calling fixer API");
        final String fixerURL = "http://data.fixer.io/api/latest?access_key=c7c5a491cd074a819e1676dc47809873&symbols=USD,AUD,CAD,PLN,MXN,INR&format=1";
        ResponseEntity<Price> rawResult = null;
        try {
            rawResult = restTemplate.getForEntity(fixerURL, Price.class);
        }
        catch (Exception e) {
            throw new ProductException("problem occurred in calling fixer api", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (rawResult.getStatusCode() == HttpStatus.OK) {
            return rawResult.getBody();
        }
        else {
            System.out.println("problem occurred in calling fixer api");
            throw new ProductException("problem occurred in calling fixer api", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
