package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PriceService implements IPriceService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object retrieveCurrentExchangeRates() {
        final String fixerURL = "http://data.fixer.io/api/latest?access_key=c7c5a491cd074a819e1676dc47809873&symbols=USD,AUD,CAD,PLN,MXN,INR&format=1";
        ResponseEntity<Object> result = null;
        try {
            result = restTemplate.getForEntity(fixerURL, Object.class);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (result!=null && result.getStatusCodeValue() == 200) {
            return result.getBody();
        }
        else {
            return null;
        }
    }
}
