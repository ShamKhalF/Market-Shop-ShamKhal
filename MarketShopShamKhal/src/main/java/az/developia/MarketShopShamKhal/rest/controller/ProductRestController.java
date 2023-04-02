package az.developia.MarketShopShamKhal.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.MarketShopShamKhal.Model.Product;
import az.developia.MarketShopShamKhal.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> findAll() {
		return productService.findAll();
	}
	
	@PostMapping(path = "/save")
	public Product save(@RequestBody Product product) {
		return productService.save(product);
	}
	
}
