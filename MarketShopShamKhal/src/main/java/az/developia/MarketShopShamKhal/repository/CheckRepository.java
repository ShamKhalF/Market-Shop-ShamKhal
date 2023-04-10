package az.developia.MarketShopShamKhal.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import az.developia.MarketShopShamKhal.Model.CustomerCheck;

public interface CheckRepository extends JpaRepository<CustomerCheck, Integer> {
	
//	@Query(value = "select * from books limit ?1, ?2", nativeQuery = true)
//	List<Book> partialBooks(Integer begin, Integer length);
//	
	
	@Query(value = "select * from customer_checks where date between ?1 and ?2", nativeQuery = true)
	List<CustomerCheck> findByDateBetween(Timestamp startDate, Timestamp endDate);


}
