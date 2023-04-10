package az.developia.MarketShopShamKhal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.MarketShopShamKhal.Model.CartProduct;
import az.developia.MarketShopShamKhal.Model.Product;
import az.developia.MarketShopShamKhal.repository.CartRepository;
import az.developia.MarketShopShamKhal.repository.ProductRepository;

@Service
public class CartService {

//	@Autowired
//	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	public CartProduct save(CartProduct cart) {

		List<Product> products = productRepository.findAll();
		CartProduct bp = new CartProduct();
		List<CartProduct> cartProducts = new ArrayList<>();
		for (Product productsss : products) {
			if (cart.getProductBarcode().equals(productsss.getBarcode())) {
				bp.setProductBarcode(productsss.getBarcode());
				// bp.setProduct(productsss);
				// bp.setProductId(productsss.getId());
				bp.setProductName(productsss.getName());
				bp.setProductPrice(productsss.getPrice());
				bp.setProductSaleQuantity(cart.getProductSaleQuantity());
				if (bp.getProductSaleQuantity() > productsss.getAvailableQuantity()) {
					bp.setProductTotalPrice(productsss.getPrice() * productsss.getAvailableQuantity());
					bp.setProductSaleQuantity(productsss.getAvailableQuantity());
					productsss.setAvailableQuantity(0.0);
				} else {
					bp.setProductTotalPrice(productsss.getPrice() * cart.getProductSaleQuantity());
					productsss.setAvailableQuantity(productsss.getAvailableQuantity() - cart.getProductSaleQuantity());
				}
				break;
			}
		}

		cartProducts.add(bp);

		return cartRepository.save(bp);
	}

	public List<CartProduct> findAll() {

		return cartRepository.findAll();
	}

//	public Order getOrderDetail(int orderId) {
//        Optional<Order> order = this.orderRepository.findById(orderId);
//        return order.isPresent() ? order.get() : null;
//    }

}
