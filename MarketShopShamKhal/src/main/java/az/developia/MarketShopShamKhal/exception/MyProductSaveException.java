package az.developia.MarketShopShamKhal.exception;

import org.springframework.validation.BindingResult;

public class MyProductSaveException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	private BindingResult br;

	public BindingResult getBr() {
		return br;
	}

	public void setBr(BindingResult br) {
		this.br = br;
	}

	public MyProductSaveException(BindingResult br) {
		super();
		this.br = br;
	}
}
