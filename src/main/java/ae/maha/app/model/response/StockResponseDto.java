package ae.maha.app.model.response;

import lombok.Data;

@Data
public class StockResponseDto {
    private double price;

    public void setTotalPrice(double price) {
        this.price += price;
    }
}
