package az.developia.MarketShopShamKhal.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import az.developia.MarketShopShamKhal.Model.ProductForCashiers;

@Repository
public interface ProductForCashierRepository extends JpaRepository<ProductForCashiers, Integer>{

	public Optional<ProductForCashiers> findByBarcode(BigInteger barcode);
	
	@Query(value = "select * from product_cashier where barcode like %?1%", nativeQuery = true)
	public List<ProductForCashiers> findAllByBarcode(BigInteger barcode);
	
}
