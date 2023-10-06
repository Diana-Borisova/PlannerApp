package com.example.shoppinglist.service;


import com.example.shoppinglist.model.dto.AddProductDto;
import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }







    public boolean create(AddProductDto addProductDto) {
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Product product = new Product();

            product.setName(addProductDto.getName());
            product.setDescription(addProductDto.getDescription());
            product.setCategory(this.categoryRepository.findByName(addProductDto.getCategoryEnum()));
            product.setNeededBefore(addProductDto.getNeededBefore());
            product.setPrice(addProductDto.getPrice());
            this.productRepository.save(product);
            return true;
        }

    public List<Product> getAllFoods() {

        return new ArrayList<>(this.productRepository.findAllByCategoryName(CategoryEnum.Food));
    }

   public String getTotalSum(){
        double sum = this.productRepository.findTotalProductsSum();
        return String.format("%.2f",sum);
   }

    public List<Product> getAllDrinks() {

        return new ArrayList<>(this.productRepository.findAllByCategoryName(CategoryEnum.Drink));
    }

    public List<Product> getAllHouseholds() {

        return new ArrayList<>(this.productRepository.findAllByCategoryName(CategoryEnum.Household));
    }

    public List<Product> getAllOthers() {

        return new ArrayList<>(this.productRepository.findAllByCategoryName(CategoryEnum.Other));
    }

    public void buyProduct( Long productId) {

        this.productRepository.deleteById(productId);
    }
//
//    public void removePost(Long use, Long postId) {
//        Post post = this.postRepository.findById(postId).orElseThrow();
//        this.postRepository.delete(post);
//    }
//
//    public void addLike(Long postId) {
//        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
//
//        Optional<Post> post = this.postRepository.findById(postId);
//
//        if (post.isPresent() && !post.get().getUserLikes().contains(user)){
//            post.get().setUserLikes(user);
//            this.postRepository.save(post.get());
//        }
//    }
}
