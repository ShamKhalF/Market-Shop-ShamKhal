package az.developia.MarketShopShamKhal.Model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotNull(message = "Məhsul adını boş qoymaq olmaz")
	@NotEmpty(message = "Məhsul adını boş qoymaq olmaz")
	@Size(min = 2, max = 30, message = "Məhsul adında 2-30 sayda simvol istifadə olunmalidir")
private String name;
	
	@NotNull(message = "Məhsulun barcode-nu boş qoymaq olmaz")
	@Min(message = "Məhsulun barcode-u qarşisina '-' yazmaq olmaz, yəni barcode mənfi ola bilməz və barcode maksimum 13 rəqəmli olmalıdır", value = 0)
	@Max(value = 9999999999999L, message = "Məhsulun barcode-u qarşisina '-' yazmaq olmaz, yəni barcode mənfi ola bilməz və barcode maksimum 13 rəqəmli olmalıdır")
private BigInteger barcode;	// 13 reqem // tekrarlanma olmayacaq // 
	
	@NotNull(message = "Məhsulun qiymətini boş qoymaq olmaz")
	@Min(message = "Məhsulun qiyməti minimum 0.10 azn olmalıdır", value = (long) 0.1)
private Double price;		// menfi olmayacaq
	
private String description;	// mehsul haqqinda melumat
	
	@Min(message = "Məhsulun maya dəyəri minimum 0.10 azn olmalıdır", value = (long) 0.1)
private Double cost;		// maya qiymeti
	
private LocalDateTime registerDate;	// sisteme elave edilme tarixi
	
private LocalDateTime updateDate;	// editQuantity editOthers tarixi // mehsul ilk elave edilende bos olacaq
	
	@Min(message = "Məhsulun mümkün miqdarı minimum 0 ola bilər. Mənfi sayda məhsul ola bilməz", value = 0)
private Double availableQuantity;	// mehsulun miqdari // editQuantity zamani artacaq // satis olunduqca azalacaq // menfi olmasin
	
	@Min(message = "Məhsulun cost-u (maya dəyəri) price-dan (satış qiyməti)-dən az olmamalıdır", value = (long) 0.1)
private Double percent;		
	// meselen cost = 50 azn, price = 60 azn, hesablanma mentiqi ise 60-50=10, (100% * 10) / 50  = 20%
	
	
	
}
