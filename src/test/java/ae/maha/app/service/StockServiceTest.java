package ae.maha.app.service;
import ae.maha.app.data.TestData;
import ae.maha.app.entity.Stock;
import ae.maha.app.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    public void shouldTestCalculatePrice(){
        when(stockRepository.findByCode(ArgumentMatchers.anyString())).thenAnswer(getOptionalAnswer());
        double price = stockService.calculateTotalPrice(TestData.getAllCodes()).getPrice();
        Assertions.assertThat(price).isEqualTo(360);
    }

    private Answer<Optional<Stock>> getOptionalAnswer() {
        return (Answer<Optional<Stock>>) invocation -> {
            switch ((String) invocation.getArgument(0)) {
                case "001":
                    return getRolexStock();
                case "002":
                    return getMichaelKorsStock();
                case "003":
                    return getSwatchStock();
                case "004":
                    return getCasioStock();
            }
            return Optional.empty();
        };
    }

    @Test
    public void shouldCalculateFullPrice() {
        double price = stockService.calculateDiscount(1L, getMichaelKorsStock().get());
        Assertions.assertThat(price).isEqualTo(80);
    }
    @Test
    public void shouldCalculateStockDiscount() {
        double price = stockService.calculateDiscount(3L, getRolexStock().get());
        Assertions.assertThat(price).isEqualTo(200);
    }

    @Test
    public void shouldCalculateMichaelKorsDiscountedPrice() {
        double price = stockService.calculateDiscount(2L, getMichaelKorsStock().get());
        Assertions.assertThat(price).isEqualTo(120);
    }
    private Optional<Stock> getRolexStock() {
        Stock stock = new Stock();
        stock.setCode("001");
        stock.setUnitPrice(100d);
        stock.setNumDiscountUnits(3);
        stock.setDiscountPercentage(33.3);
        stock.setName("Rolex");
        return Optional.of(stock);
    }
    private Optional<Stock> getMichaelKorsStock() {
        Stock stock = new Stock();
        stock.setCode("002");
        stock.setUnitPrice(80d);
        stock.setNumDiscountUnits(2);
        stock.setDiscountPercentage(25);
        stock.setName("Michael Kors");
        return Optional.of(stock);
    }
    private Optional<Stock> getSwatchStock() {
        Stock stock = new Stock();
        stock.setCode("003");
        stock.setUnitPrice(50d);
        stock.setNumDiscountUnits(0);
        stock.setDiscountPercentage(0d);
        stock.setName("Swatch");
        return Optional.of(stock);
    }

    private Optional<Stock> getCasioStock() {
        Stock stock = new Stock();
        stock.setCode("004");
        stock.setUnitPrice(30d);
        stock.setNumDiscountUnits(0);
        stock.setDiscountPercentage(0d);
        stock.setName("Casio");
        return Optional.of(stock);
    }

}
