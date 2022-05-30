package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.persistence.ItemPersistance;
import com.kreitek.store.infrastructure.specs.ItemSpecification;
import com.kreitek.store.infrastructure.specs.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemPersistanceImpl implements ItemPersistance {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemPersistanceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public List<Item> getAllItemsByCategory(Long itemId) {
        return this.itemRepository.findAllByCategoryId(itemId);
    }

    @Override
    public Optional<Item> getItemById(Long itemId) {
        return this.itemRepository.findById(itemId);
    }

    @Override
    public Item saveItem(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long itemId) {
        this.itemRepository.deleteById(itemId);
    }


    @Override
    public Page<Item> findAll(Pageable pageable, String filters) {
        ItemSpecification specification = new ItemSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.itemRepository.findAll(specification, pageable);
    }
}
