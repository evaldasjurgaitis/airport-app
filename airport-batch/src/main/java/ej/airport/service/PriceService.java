package ej.airport.service;

import ej.airport.feign.AirportPriceClient;
import ej.airport.model.Price;
import ej.airport.model.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PriceService {

    private final AirportPriceClient airportPriceClient;
    private final TaskExecutor taskExecutor;

    public PriceService(AirportPriceClient airportPriceClient, @Qualifier("jobTaskExecutor") TaskExecutor taskExecutor) {
        this.airportPriceClient = airportPriceClient;
        this.taskExecutor = taskExecutor;
    }

    public Price getPrice(String provider, Long airportId) {
        return airportPriceClient.getPrice(provider, airportId);
    }

    public Provider getProvider(String provider, Long airportId) {
        Price price = getPrice(provider, airportId);
        return new Provider(provider, price.getCurrency(), price.getValue(), airportId);
    }

    public List<Provider> getProvidersWitPrice(List<String> providers, Long airportId) throws ExecutionException, InterruptedException {
        List<CompletableFuture<Provider>> providersFuture = providers.stream()
                .map(provider -> CompletableFuture.supplyAsync(() -> getProvider(provider, airportId), taskExecutor))
                .collect(Collectors.<CompletableFuture<Provider>>toList());

        return allOf(providersFuture, taskExecutor).get().stream()
                .collect(Collectors.toList());
    }

    private static <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futures, TaskExecutor taskExecutor) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApplyAsync(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList()), taskExecutor
        );
    }

}
