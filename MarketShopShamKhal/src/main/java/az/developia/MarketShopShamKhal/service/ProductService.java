package az.developia.MarketShopShamKhal.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.MarketShopShamKhal.Model.Product;
import az.developia.MarketShopShamKhal.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product save(Product product) {

		product.setRegisterDate((LocalDateTime.now()));
		product.setPercent(percent(product.getCost(), product.getPrice()));

		return productRepository.save(product);
		// id bazadan // name, barcode, price, description, cost, quantity post ile
		// gelecek
		// registerDate burdan gelir // updateDate eynisi amma edit zamani gelecek
		// percent mentiqini burada yazacam
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
		return productRepository.save(product);
	}

	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}
	
	public List<Product> findAllSearchAllFields(String search){
		return productRepository.findAllSearchAllFields(search);
	}
	
	public List<Product> findAllByBarcode(Integer barcode){
		return productRepository.findAllByBarcode(barcode);
	}
	
	

}
