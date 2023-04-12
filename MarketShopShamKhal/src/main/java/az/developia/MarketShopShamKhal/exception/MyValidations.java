package az.developia.MarketShopShamKhal.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyValidations {

	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleRuntimeException(RuntimeException e) {
		return e.getMessage();
	}
	
	
	@ExceptionHandler(MyUserExceptions.class)
	   public ResponseEntity<UserErrorResponse> handleCustomException(MyUserExceptions ex) {
	      UserErrorResponse errorResponse = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	   }
	
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public DataWrapper handleMyValidationException(MyProductSaveException e) {
		
		DataWrapper dataWrapper = new DataWrapper();
		
		List<ErrorResponse> errors = new ArrayList<>();
		BindingResult br = e.getBr();
		
		for (FieldError error : br.getFieldErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setField(error.getField());
			errorResponse.setMessage(error.getDefaultMessage());
			errors.add(errorResponse);
		}
		
		dataWrapper.setValidations(errors);
		return dataWrapper;
	}
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public DataWrapper handleMyValidationExceptions(MySaleException e) {
		
		DataWrapper dataWrapper = new DataWrapper();
		
		List<ErrorResponse> errors = new ArrayList<>();
		BindingResult br = e.getBr();
		
		for (FieldError error : br.getFieldErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setField(error.getField());
			errorResponse.setMessage(error.getDefaultMessage());
			errors.add(errorResponse);
		}
		
		dataWrapper.setValidations(errors);
		return dataWrapper;
	}
	
	
	
	
	
}
