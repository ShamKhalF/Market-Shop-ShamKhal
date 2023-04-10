package az.developia.MarketShopShamKhal.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class ResponseCheckDTO {

    private Double totalPrice;
    
    @CreationTimestamp
    private LocalDateTime date;
    
    private String cashier;
    
    private Integer checkId;

    
}
