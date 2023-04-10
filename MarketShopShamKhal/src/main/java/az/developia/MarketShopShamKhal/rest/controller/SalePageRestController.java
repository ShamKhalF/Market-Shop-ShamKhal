package az.developia.MarketShopShamKhal.rest.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public List<CustomerCheck> findAllOrder() {
		return checkService.findAll();
	}

	@GetMapping(path = "/find-by-barcode")
	public Optional<ProductForCashiers> searchByBarcode(
			@RequestParam(name = "barcode", required = false, defaultValue = "") Integer barcode) {
		return productService.findAllByBarcode(barcode);
	}

	@GetMapping(path = "/all-carts")
	public List<CartProduct> findAllCarts() {
		return cartService.findAll();
	}

	@PostMapping(path = "/save-cart")
	public CartProduct saveBasket(@RequestBody CartProduct cart) {
		return cartService.save(cart);
	}

	@PostMapping(path = "/save-check")
	public ResponseEntity<ResponseCheckDTO> saveCheck(@RequestBody CustomerCheckDTO checkDTO) {
		ResponseCheckDTO responseCheckDTO = new ResponseCheckDTO();
		Double totalPrice = checkService.getCartAmount(checkDTO.getCartItems());

		CustomerCheck customerCheck = new CustomerCheck(checkDTO.getCashierName(), checkDTO.getCartItems(), totalPrice);
		customerCheck = checkService.saveCheck(customerCheck);

		responseCheckDTO.setTotalPrice(totalPrice);
		responseCheckDTO.setDate(customerCheck.getDate());
		responseCheckDTO.setCheckId(customerCheck.getId());

		return ResponseEntity.ok(responseCheckDTO);
	}

//	@GetMapping(path = "/partial/begin/{begin}/length/{length}")
//	public List<Book> partialBooks(@PathVariable Integer begin, @PathVariable Integer length){
//	

	@GetMapping(path = "/search-by-date")
	List<CustomerCheck> findByMyDateBetween(@RequestParam("startDate") String startDateString,
			@RequestParam("endDate") String endDateString) {
		
		Timestamp startDate = Timestamp.valueOf(startDateString);
		Timestamp endDate = Timestamp.valueOf(endDateString);
		return checkRepository.findByDateBetween(startDate, endDate);
	}

}
