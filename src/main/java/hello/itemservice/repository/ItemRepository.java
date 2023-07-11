package hello.itemservice.repository;

import hello.itemservice.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

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
     * @param cond
     * @return
     */
    List<Item> findAll(ItemSearchCond cond);

}
