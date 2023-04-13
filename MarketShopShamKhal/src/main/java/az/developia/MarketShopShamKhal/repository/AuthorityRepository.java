package az.developia.MarketShopShamKhal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import az.developia.MarketShopShamKhal.Model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
	
	@Query(value = "select * from authorities where username like %?1%", nativeQuery = true)
	public List<Authority> findUsersAuthorization(String username);
	
	
	
}
