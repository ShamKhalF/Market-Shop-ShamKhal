package az.developia.MarketShopShamKhal.Model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productCashier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForCashiers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private BigInteger barcode;
	
	private Double price;
	
	private String description;
	
	private Double availableQuantity;
	
	
}
