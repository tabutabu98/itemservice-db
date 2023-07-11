package hello.itemservice.repository.memory;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    @Override
    public Item save(Item item) {
        item.setId(++sequence); // 식별자 증가
        store.put(item.getId(), item); // Item 저장
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = findById(itemId).orElseThrow();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();
        return store.values().stream()
                .filter(item -> {
                    if (ObjectUtils.isEmpty(itemName)) { // Item의 상품명이 비어있는 경우
                        return true; // Item 반환
                    }
                    return item.getItemName().contains(itemName); // 입력한 상품명을 포함하는 Item만 반환
                }).filter(item -> {
                    if (maxPrice == null) { // Item의 가격제한이 걸려있지 않은 경우
                        return true; // Item 반환
                    }
                    return item.getPrice() <= maxPrice; // 입력한 가격제한 이하의 Item만 반환
                })
                .collect(Collectors.toList()); // Item List 반환
    }

    /**
     * Map 객체 클리어
     */
    public void clearStore() {
        store.clear();
    }

}
