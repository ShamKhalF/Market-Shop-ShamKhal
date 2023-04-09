package az.developia.MarketShopShamKhal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.MarketShopShamKhal.Model.CartProduct;

public interface CartRepository extends JpaRepository<CartProduct, Integer> {

}
