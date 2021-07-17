package ej.airport.feign;

import ej.airport.model.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "AirportPriceService", url = "${airport-price-service.url}")
public interface AirportPriceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/item/price/{provider}/{id}", consumes = "application/json")
    Price getPrice(@PathVariable String provider, @PathVariable Long id);

}
