package JuDBu.custommaster.entity.order;

import JuDBu.custommaster.entity.account.Account;
import JuDBu.custommaster.entity.product.Product;
import JuDBu.custommaster.entity.shop.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime pickUpDate;
    private LocalDateTime ordTime;
    private Integer totalPrice;

    @Setter
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.OFFERED;

    public enum Status {
        OFFERED,
        DECLINED,
        CONFIRMED
    }
}
