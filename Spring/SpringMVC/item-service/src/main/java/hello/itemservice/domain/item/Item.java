package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data // 전부 생성 ==>Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode, Value ==> 위험

//@Getter @Setter
@Data // 실전이면 위처럼 근데 예제니까 date 쓰겟다
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer Quantity;

    public Item() {

    }

    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        Quantity = quantity;
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        Quantity = quantity;
    }
}
