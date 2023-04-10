package az.developia.MarketShopShamKhal.exception;

import org.springframework.validation.BindingResult;

public class MySaleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private BindingResult br;

	public BindingResult getBr() {
		return br;
	}

	public void setBr(BindingResult br) {
		this.br = br;
	}

	public MySaleException(BindingResult br) {
		super();
		this.br = br;
	}
}
