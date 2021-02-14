package ae.maha.app.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name ="stock")
public class Stock {
    @Id
    @Column(name ="code", nullable = false)
    private String code;
    @Column(name ="name", nullable = false)
    private String name;
    @Column(name ="unit_price", nullable = false)
    private double unitPrice;
    @Column(name ="discount_percentage", nullable = false)
    private double discountPercentage;
    @Column(name ="num_discount_unit", nullable = false)
    private int numDiscountUnits;
}
