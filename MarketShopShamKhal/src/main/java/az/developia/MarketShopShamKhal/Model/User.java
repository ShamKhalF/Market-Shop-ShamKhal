package az.developia.MarketShopShamKhal.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String username;
	
	private String password;
	
	private Integer enabled;
	
//	@OneToMany
//	@JoinTable(name = "authorities",
//	joinColumns = @JoinColumn(name="username"),
//	inverseJoinColumns = @JoinColumn(name="id"))		
//	private List<Authority> authorities;
//	
}
