package az.developia.MarketShopShamKhal.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@ToString
@Entity
@Table(name = "customerChecks")
@Data
public class CustomerCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cashierName;
    
    private String date;

    private Double totalPrice;
    

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = CartProduct.class)
    @JoinColumn(name = "check_id", referencedColumnName = "id")
    private List<CartProduct> cartItems;

    public CustomerCheck() {
    }

    public CustomerCheck(String cashierName, List<CartProduct> cartItems, String date, Double totalPrice) {
        this.cashierName = cashierName;
        this.cartItems = cartItems;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    
}
