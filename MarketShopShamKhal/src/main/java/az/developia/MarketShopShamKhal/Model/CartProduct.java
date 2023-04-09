package az.developia.MarketShopShamKhal.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    private Double productSaleQuantity;
	
	private String productName;
	
	private Integer productBarcode;
	
	private Double productPrice;
	
	private Double productTotalPrice;
	
	private Integer productId;
	    

    public CartProduct(int productId, String productName, Double productSaleQuantity, Double productTotalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productSaleQuantity = productSaleQuantity;
        this.productTotalPrice = productTotalPrice;
    }

    public CartProduct(int productId, Double productSaleQuantity) {
        this.productId = productId;
        this.productSaleQuantity = productSaleQuantity;
    }

    

    
}
