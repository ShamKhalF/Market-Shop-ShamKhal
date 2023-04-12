package az.developia.MarketShopShamKhal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.MarketShopShamKhal.Model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
