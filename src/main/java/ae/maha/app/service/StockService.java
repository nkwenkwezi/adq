package ae.maha.app.service;

import ae.maha.app.entity.Stock;
import ae.maha.app.model.response.StockResponseDto;
import ae.maha.app.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;


    public StockResponseDto calculateTotalPrice(List<String> codes) {
        Map<String, Long> counts =
                codes.stream().collect(Collectors.groupingBy(code -> code, Collectors.counting()));
        StockResponseDto responseDto = new StockResponseDto();
        counts.forEach((key, value) -> {
            log.info("code {}, count {}", key, value);
            stockRepository.findByCode(key)
                    .ifPresent(record -> responseDto.setTotalPrice(calculateDiscount(value, record)));
        });
        log.info("Total price {}" , responseDto.getPrice());
        return responseDto;
    }
    protected double calculateDiscount(Long totalNumber, Stock stock) {
        BigDecimal totalPrice;
        BigDecimal discountedPrice;
        if(totalNumber.intValue() >=  stock.getNumDiscountUnits()) {
          totalPrice =  BigDecimal.valueOf(totalNumber  * stock.getUnitPrice() )  ;
          discountedPrice = totalPrice.multiply( BigDecimal.valueOf(stock.getDiscountPercentage() / 100) );
          return totalPrice.subtract(discountedPrice).setScale(0, RoundingMode.HALF_UP).doubleValue()  ;

        }
        return totalNumber * stock.getUnitPrice();
    }
}
