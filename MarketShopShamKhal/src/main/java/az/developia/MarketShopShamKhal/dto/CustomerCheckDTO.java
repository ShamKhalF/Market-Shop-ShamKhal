package az.developia.MarketShopShamKhal.dto;

import java.util.List;

import az.developia.MarketShopShamKhal.Model.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCheckDTO {

    private String cashierName;
    private List<CartProduct> cartItems;
   

    

    

  

   
}
