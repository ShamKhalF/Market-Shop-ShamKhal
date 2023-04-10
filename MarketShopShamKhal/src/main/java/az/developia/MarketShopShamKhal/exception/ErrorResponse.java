package az.developia.MarketShopShamKhal.exception;

import lombok.Data;

@Data
public class ErrorResponse {

	private String field;
	private String message;
	
}
