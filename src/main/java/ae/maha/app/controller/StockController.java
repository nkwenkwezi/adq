package ae.maha.app.controller;

import ae.maha.app.model.response.StockResponseDto;
import ae.maha.app.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping(value = "/checkout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockResponseDto> calculateTotalPrice(@RequestBody List<String> codes) {
        return ResponseEntity.ok(stockService.calculateTotalPrice(codes));
    }

}
