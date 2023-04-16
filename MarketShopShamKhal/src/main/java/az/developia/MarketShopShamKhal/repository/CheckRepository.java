package az.developia.MarketShopShamKhal.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import az.developia.MarketShopShamKhal.Model.CustomerCheck;

public interface CheckRepository extends JpaRepository<CustomerCheck, Integer> {


	
	@Query(value = "select * from customer_checks where date between ?1 and ?2", nativeQuery = true)
	List<CustomerCheck> findByDateBetween(Timestamp startDate, Timestamp endDate);

	@Query(value = "select "
			+ "'kassir' || ': ' || r.cashier_name, "
			+ "'məhsulun adi' || ': ' || c.product_name, "
			+ "'mahsulun barkodu' || ': ' || c.product_barcode, "
			+ "'məhsulun qiyməti' || ': ' || c.product_price,  "
			+ "'satilan məhsulun sayi' || ': ' || sum(c.product_sale_quantity), "
//			+ "'satilan məhsulun ümumi qiyməti' || ': ' || (c.product_price * sum(c.product_sale_quantity)), "
			+ "'satilan məhsulun ümumi qiyməti' || ': ' || sum(c.product_total_price) + 0.001, "
			+ "'məhsulun satıldığı çek sayı' || ': ' || count(c.check_id) "
			+ "from carts c "
			+ "join customer_checks r on r.id=c.check_id "
			+ "where r.date between ?1 and ?2 "
			+ "group by c.product_barcode, "
			+ "c.product_price, "
			+ "c.product_name, "
			+ "r.cashier_name", nativeQuery = true)
	List<Object> findByDateCarts( Timestamp startDate, Timestamp endDate);

}
// (c.product_price * sum(c.product_sale_quantity))
// sum(c.product_total_price)