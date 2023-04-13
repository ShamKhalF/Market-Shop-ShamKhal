package az.developia.MarketShopShamKhal.Model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
*/
@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Satilan məhsulun sayı olmalıdır")
	@Min(value = (long) 0.1, message = "Satılan məhsulun miqdarı 0 və ya mənfi olmamalıdır")
	private Double productSaleQuantity; // validasiya elave edilecek menfi sayda satis olmasin

	private String productName;

	@NotNull(message = "Satilan məhsulun barcode-u düzgün əlavə olmalıdır")
	private BigInteger productBarcode;

	private Double productPrice;

	private Double productTotalPrice;

	public CartProduct(String productName, Double productSaleQuantity, Double productTotalPrice) {
		this.productName = productName;
		this.productSaleQuantity = productSaleQuantity;
		this.productTotalPrice = productTotalPrice;
	}

	public CartProduct(Double productSaleQuantity) {
		this.productSaleQuantity = productSaleQuantity;
	}

}
