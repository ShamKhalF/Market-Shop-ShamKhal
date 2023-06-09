package az.developia.MarketShopShamKhal.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.MarketShopShamKhal.Model.CartProduct;
import az.developia.MarketShopShamKhal.Model.CustomerCheck;
import az.developia.MarketShopShamKhal.Model.Product;
import az.developia.MarketShopShamKhal.Model.ProductForCashiers;
import az.developia.MarketShopShamKhal.repository.CheckRepository;
import az.developia.MarketShopShamKhal.repository.ProductForCashierRepository;
import az.developia.MarketShopShamKhal.repository.ProductRepository;

@Service
public class CheckService {

	@Autowired
	private CheckRepository checkRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductForCashierRepository forCashierRepository;

	public List<CustomerCheck> findAll() {
		return checkRepository.findAll();
	}
	

	public double getCartAmount(List<CartProduct> cartList) {

		double totalPrice = 0;
		double productTotalPrice = 0;
		double availableQuantity = 0;
		

		for (CartProduct cart : cartList) {
			
			BigInteger productBarcode = cart.getProductBarcode();
			Optional<Product> product = productRepository.findByBarcode(productBarcode);
			Optional<ProductForCashiers> forCashier = forCashierRepository.findByBarcode(productBarcode);
			
			if (product.isPresent() && forCashier.isPresent()) {
				Product product1 = product.get();
				ProductForCashiers forCashiers = forCashier.get();
				if (product1.getAvailableQuantity() < cart.getProductSaleQuantity()) {
					productTotalPrice = product1.getPrice() * product1.getAvailableQuantity();
					cart.setProductSaleQuantity(product1.getAvailableQuantity());
				} else {
					productTotalPrice = cart.getProductSaleQuantity() * product1.getPrice();
					availableQuantity = product1.getAvailableQuantity() - cart.getProductSaleQuantity();
				}
				totalPrice = totalPrice + productTotalPrice;
				product1.setAvailableQuantity(availableQuantity);
				forCashiers.setAvailableQuantity(availableQuantity);
				availableQuantity = 0;
				cart.setProductBarcode(product1.getBarcode());
				cart.setProductPrice(product1.getPrice());
				cart.setProductName(product1.getName());
				// cart.setProductId(product1.getId());
				cart.setProductTotalPrice(productTotalPrice);

				productRepository.save(product1);
			}
		}
		return totalPrice;
	}

	public CustomerCheck saveCheck(CustomerCheck check) {
		return checkRepository.save(check);
	}

}
