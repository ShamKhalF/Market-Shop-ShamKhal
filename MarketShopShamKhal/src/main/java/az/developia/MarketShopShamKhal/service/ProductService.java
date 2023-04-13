package az.developia.MarketShopShamKhal.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.MarketShopShamKhal.Model.Product;
import az.developia.MarketShopShamKhal.Model.ProductForCashiers;
import az.developia.MarketShopShamKhal.repository.ProductForCashierRepository;
import az.developia.MarketShopShamKhal.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductForCashierRepository forCashierRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product save(Product product) {

		product.setRegisterDate((LocalDateTime.now()));
		product.setPercent(percent(product.getCost(), product.getPrice()));

		ProductForCashiers forCashiers = new ProductForCashiers();
		forCashiers.setId(product.getId());
		forCashiers.setName(product.getName());
		forCashiers.setBarcode(product.getBarcode());
		if (product.getAvailableQuantity() == null) {
			product.setAvailableQuantity(1.0);
		}
		forCashiers.setAvailableQuantity(product.getAvailableQuantity());
		forCashiers.setDescription(product.getDescription());
		forCashiers.setPrice(product.getPrice());
		forCashierRepository.save(forCashiers);
		return productRepository.save(product);

	}

	private static Double percent(Double cost, Double price) {
		// meselen cost = 50 azn, price = 60 azn, hesablanma mentiqi ise 60-50=10, (100%
		// * 10) / 50 = 20%
		Double percent1 = 100 * ((100 * (price - cost)) / cost);
		Double percent2 = 100 * percent1;
		Integer perint = percent2.intValue();
		Double p = (double) (perint / 100);

		return p / 100;
	}

	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	public Product edit(Product product) {
		product.setUpdateDate((LocalDateTime.now()));
		product.setPercent(percent(product.getCost(), product.getPrice()));

		ProductForCashiers forCashiers = new ProductForCashiers();
		forCashiers.setId(product.getId());
		forCashiers.setName(product.getName());
		forCashiers.setBarcode(product.getBarcode());
		forCashiers.setAvailableQuantity(product.getAvailableQuantity());
		forCashiers.setDescription(product.getDescription());
		forCashiers.setPrice(product.getPrice());
		forCashierRepository.save(forCashiers);

		return productRepository.save(product);
	}

	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}

	public List<Product> findAllSearchAllFields(String search) {
		return productRepository.findAllSearchAllFields(search);
	}

	public List<ProductForCashiers> findAllByBarcode(BigInteger barcode) {
		return forCashierRepository.findAllByBarcode(barcode);
	}

	public Optional<Product> findByBarcode(BigInteger barcode) {

		return productRepository.findByBarcode(barcode);
	}

}
