package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private static final String CUSTOMER_PATH_V1 ="/api/v1/customer/";
    private static final String BEER_PATH_V1 = "/api/v1/beer/";
    private final RestTemplate restTemplate;
    private String apiHost;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + uuid, BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(apiHost + BEER_PATH_V1 + uuid, beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apiHost + BEER_PATH_V1 + uuid);
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + uuid, CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(apiHost + CUSTOMER_PATH_V1 + uuid, customerDto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + uuid);
    }


    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
