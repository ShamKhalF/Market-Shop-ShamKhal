package az.developia.MarketShopShamKhal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.MarketShopShamKhal.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
