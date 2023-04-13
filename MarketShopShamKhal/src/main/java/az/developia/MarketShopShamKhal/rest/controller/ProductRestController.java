package az.developia.MarketShopShamKhal.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.MarketShopShamKhal.Model.Product;
import az.developia.MarketShopShamKhal.exception.MyProductSaveException;
import az.developia.MarketShopShamKhal.exception.MyUserExceptions;
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
	public Product save(@Valid @RequestBody Product product, BindingResult br) throws Exception {
		if(br.hasErrors()) {
			throw new MyProductSaveException(br);
		}
		
		Optional<Product> barcodeOptional = productService.findByBarcode(product.getBarcode());
		if(barcodeOptional.isPresent()) {
			throw new MyUserExceptions("'" + product.getBarcode() + "' nömrəli bu barcode-la məhsul artıq mövcuddur");
		} else {
			product.setId(null);
		return productService.save(product);
		}
		
		
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteById(@PathVariable Integer id) {
		productService.deleteById(id);
	}
	
	
	@PutMapping(path = "/edit")
	public Product edit(@Valid @RequestBody Product product, BindingResult br) throws Exception {
		if(br.hasErrors()) {
			throw new MyProductSaveException(br);
		}
			return productService.edit(product);
	}
	
	@GetMapping(path = "/search")
	public List<Product> findAllSearch(@RequestParam(name = "q", required = false, defaultValue = "") String search){
		return productService.findAllSearchAllFields(search);
	}
	
	
	
	
	
}
