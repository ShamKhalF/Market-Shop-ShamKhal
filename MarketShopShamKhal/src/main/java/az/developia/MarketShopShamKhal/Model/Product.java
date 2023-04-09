package az.developia.MarketShopShamKhal.Model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;		// mehsulun adi
	
	private Integer barcode;	// 13 reqem // tekrarlanma olmayacaq // 
	
	private Double price;		// menfi olmayacaq
	
	private String description;	// mehsul haqqinda melumat
	
	private Double cost;		// maya qiymeti
	
	private LocalDateTime registerDate;	// sisteme elave edilme tarixi
	
	private LocalDateTime updateDate;	// editQuantity editOthers tarixi // mehsul ilk elave edilende bos olacaq
	
	private Double availableQuantity;	// mehsulun miqdari // editQuantity zamani artacaq // satis olunduqca azalacaq // menfi olmasin
	
	private Double percent;		
	// meselen cost = 50 azn, price = 60 azn, hesablanma mentiqi ise 60-50=10, (100% * 10) / 50  = 20%
	
	
	
}
