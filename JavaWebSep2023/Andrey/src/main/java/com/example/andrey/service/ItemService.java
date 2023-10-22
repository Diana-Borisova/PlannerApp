package com.example.andrey.service;

import com.example.andrey.model.dto.AddItemDto;
import com.example.andrey.model.entity.Category;
import com.example.andrey.model.entity.CategoryEnum;
import com.example.andrey.model.entity.Item;
import com.example.andrey.model.entity.User;
import com.example.andrey.repository.CategoryRepository;
import com.example.andrey.repository.ItemRepository;
import com.example.andrey.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final LoggedUser loggedUser;
   private final UserService userService;
   private final CategoryRepository categoryRepository;

    public ItemService(ItemRepository itemRepository, LoggedUser loggedUser, UserService userService, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.categoryRepository = categoryRepository;

    }

    public boolean create(AddItemDto addItemDto) {
        Optional<User> user = this.userService.findById(this.loggedUser.getId());
        Category category = this.categoryRepository.findByName(addItemDto.getCategoryEnum());
        Item item = new Item();
        item.setCategory(category);
        item.setDescription(addItemDto.getDescription());
        item.setName(addItemDto.getName());
        item.setPrice(addItemDto.getPrice());
        item.setGenderEnum(addItemDto.getGenderEnum());
        item.setUser(user.get());
        this.itemRepository.save(item);
        return true;
    }

    public Integer getCountOfAllItems() {
        return this.itemRepository.findAll().size();
    }

    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }


    public void viewDetails(Long id) {
            this.itemRepository.findById(id);
    }

    public void removeItem(Long id) {
        this.itemRepository.deleteById(id);
    }
}
