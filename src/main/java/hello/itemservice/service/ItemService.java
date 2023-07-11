package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    /**
     * Item 저장
     * @param item
     * @return
     */
    Item save(Item item);

    /**
     * Item 수정
     * @param itemId
     * @param updateParam
     */
    void update(Long itemId, ItemUpdateDto updateParam);

    /**
     * Item 단건 조회
     * @param id
     * @return
     */
    Optional<Item> findById(Long id);

    /**
     * Item 목록 조회
     * @param itemSearch
     * @return
     */
    List<Item> findItems(ItemSearchCond itemSearch);
}
