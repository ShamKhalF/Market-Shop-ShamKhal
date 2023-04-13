package az.developia.MarketShopShamKhal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import az.developia.MarketShopShamKhal.Model.User;

public interface UserRepository extends JpaRepository<User, String> {

	
	@Query(value = "select * from users where username like %?1%", nativeQuery = true)
	public List<User> findByUsername(String username);
	
	public List<User> findByEnabled(Integer enabled);
	
	
}
