package az.developia.MarketShopShamKhal.rest.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.MarketShopShamKhal.Model.CartProduct;
import az.developia.MarketShopShamKhal.Model.CustomerCheck;
import az.developia.MarketShopShamKhal.Model.ProductForCashiers;
import az.developia.MarketShopShamKhal.dto.CustomerCheckDTO;
import az.developia.MarketShopShamKhal.dto.ResponseCheckDTO;
import az.developia.MarketShopShamKhal.exception.MySaleException;
import az.developia.MarketShopShamKhal.repository.CheckRepository;
import az.developia.MarketShopShamKhal.service.CartService;
import az.developia.MarketShopShamKhal.service.CheckService;
import az.developia.MarketShopShamKhal.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/sale-page")
public class SalePageRestController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CheckService checkService;

	@Autowired
	private CheckRepository checkRepository;

	@GetMapping(path = "/all-checks")
	@PreAuthorize(value = "hasAnyAuthority('admin')")
	public List<CustomerCheck> findAllOrder() {
		return checkService.findAll();
	}

	@GetMapping(path = "/all-carts")
	@PreAuthorize(value = "hasAnyAuthority('admin')")
	public List<CartProduct> findAllCarts() {
		return cartService.findAll();
	}
	
	@GetMapping(path = "/search-by-date")
	@PreAuthorize(value = "hasAnyAuthority('admin')")
	List<CustomerCheck> findByMyDateBetween(@RequestParam("startDate") String startDateString,
			@RequestParam("endDate") String endDateString) {
		
		Timestamp startDate = Timestamp.valueOf(startDateString);
		Timestamp endDate = Timestamp.valueOf(endDateString);
		return checkRepository.findByDateBetween(startDate, endDate);
	}	
	
	
	
	@GetMapping(path = "/find-by-barcode")
	@PreAuthorize(value = "hasAnyAuthority('cashier')")
	public List<ProductForCashiers> searchByBarcode(
			@RequestParam(name = "barcode", required = false, defaultValue = "") BigInteger barcode) {
		return productService.findAllByBarcode(barcode);
	}


	@PostMapping(path = "/save-cart")
	@PreAuthorize(value = "hasAnyAuthority('cashier')")
	public CartProduct saveBasket(@RequestBody CartProduct cart) {
		return cartService.save(cart);
	}

	
	@PostMapping(path = "/save-check")
	@PreAuthorize(value = "hasAnyAuthority('cashier')")
	public ResponseEntity<ResponseCheckDTO> saveCheck(@Valid @RequestBody CustomerCheckDTO checkDTO, BindingResult br) {
		if(br.hasErrors()) {
			throw new MySaleException(br);
		}
		
//		Optional<Product> pList = productService.findByBarcode(checkDTO.getCartItems().get(0).getProductBarcode());
//		
//		if(!pList.get().getBarcode().equals(checkDTO.getCartItems().get(0).getProductBarcode())) {
//			List<String> messages = Arrays.asList("Error 1", "Error 2", "Error 3");
//			throw new MyCheckCartExceptions(messages);
//		}
		
		ResponseCheckDTO responseCheckDTO = new ResponseCheckDTO();
		Double totalPrice = checkService.getCartAmount(checkDTO.getCartItems());

		CustomerCheck customerCheck = new CustomerCheck(checkDTO.getCashierName(), checkDTO.getCartItems(), totalPrice);
		customerCheck = checkService.saveCheck(customerCheck);

		responseCheckDTO.setTotalPrice(totalPrice);
		responseCheckDTO.setDate(customerCheck.getDate());
		responseCheckDTO.setCheckId(customerCheck.getId());

		return ResponseEntity.ok(responseCheckDTO);
	}



}
