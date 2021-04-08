package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository { // item  저장소

    private static final Map<Long, Item> store = new HashMap<>();
    // Long type인 이유는 item class에서 long으로 저장했기 때문
    private static long sequence = 0L; //static

    // 실무에서는 hashMap 안씀
    // 실제론 멀티 쓰레드 환경에서 여러개 가 스토어로 접근할때 쓰면안됨
    //싱글톤으로 접근하기 때문에 만약 쓰고 싶으면 ConcurrentHashMap을 쓰는게 좋다/

    //저장 기능
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // ID 로 검색
    public Item findById(Long id) {
        return store.get(id);
    }
    // all find by id
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
        //srote.vlaue로 바로 반환해도 되는데 감싸서 보내면 array list에 값을 넣어서 보내도 실제 스토어에는 변함 없기 때문 (안전)
        // 그리고 type도 바꿔야하고
    }

    // update
    // 포르젝트가 작으니까 이렇게 하지 규모가 커지면 project DTO를 만들어서 하는게 좋다 !
    // 중복과
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

    public void clearStore() {
        store.clear();  // 이렇게 하면 hahMap 다 날아간다!
    }



}
