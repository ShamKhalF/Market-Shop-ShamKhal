package az.developia.MarketShopShamKhal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.MarketShopShamKhal.Model.CustomerCheck;

public interface CheckRepository extends JpaRepository<CustomerCheck, Integer> {

}
