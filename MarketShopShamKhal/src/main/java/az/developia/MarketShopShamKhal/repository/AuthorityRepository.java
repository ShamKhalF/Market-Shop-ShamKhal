package az.developia.MarketShopShamKhal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.MarketShopShamKhal.Model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
}
