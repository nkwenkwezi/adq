package ae.maha.app.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StockDto {
    private String code;
    private String name;
    private double unitPrice;
    private String discountPercentage;
    private String numDiscountUnits;
}
