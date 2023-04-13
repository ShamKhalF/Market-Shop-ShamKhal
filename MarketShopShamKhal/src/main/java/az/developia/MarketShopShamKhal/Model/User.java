package az.developia.MarketShopShamKhal.Model;

import javax.persistence.Entity;
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
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@NotNull(message = "istifadəçi adını boş saxlamaq olmaz")
	@NotEmpty(message = "istifadəçi adını boş saxlamaq olmaz")
	@Size(min = 3, max = 15, message = "İstifadəçi adı 3-15 simvoldan ibarət olmalıdır")
	private String username;

	private String password;

	@Min(value = 0, message = "İstifadəçinin aktivliyi 1 və ya 0 olmalıdır")
	@Max(value = 1, message = "İstifadəçinin aktivliyi 1 və ya 0 olmalıdır")
	private Integer enabled;

}
