package com.example.shoppinglist.service;

import com.example.shoppinglist.model.dto.AddProductDto;
import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.util.LoggedUser;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    private final CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository, LoggedUser loggedUser, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.categoryRepository = categoryRepository;
    }




    public boolean create(AddProductDto addProductDto) {

        Category category = this.categoryRepository.findByCategoryEnum(addProductDto.getCategoryEnum());
        Product product = new Product();
        product.setDescription(addProductDto.getDescription());
        product.setCategory(category);
        product.setNeededBefore(addProductDto.getNeededBefore());
        product.setName(addProductDto.getName());
        product.setPrice(addProductDto.getPrice());
        this.productRepository.save(product);


        return true;
    }

    public Set<Product> getFood() {

        return this.productRepository.findAllByCategory_CategoryEnum(CategoryEnum.FOOD);
    }

    public Set<Product> getDrink() {

        return this.productRepository.findAllByCategory_CategoryEnum(CategoryEnum.DRINK);
    }

    public Set<Product> getHousehold() {

        return this.productRepository.findAllByCategory_CategoryEnum(CategoryEnum.HOUSEHOLD);
    }

    public Set<Product> getOthers() {

        return this.productRepository.findAllByCategory_CategoryEnum(CategoryEnum.OTHER);
    }


    public void buyProduct(Long id) {
        this.productRepository.deleteById(id);

    }

    public Set<Product> getAllProducts() {

        return this.productRepository.findAll().stream().collect(Collectors.toSet());
    }

    public void buyAllProduct() {
        this.productRepository.deleteAll();
    }
}
