package az.developia.MarketShopShamKhal.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.MarketShopShamKhal.Model.Product;
import az.developia.MarketShopShamKhal.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sale-page")
public class SalePageRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(path = "/find-by-barcode")
	public List<Product> searchByBarcode(@RequestParam(name = "barcode", required = false, defaultValue = "") Integer barcode) {
		return productService.findAllByBarcode(barcode);
	}
	
}
