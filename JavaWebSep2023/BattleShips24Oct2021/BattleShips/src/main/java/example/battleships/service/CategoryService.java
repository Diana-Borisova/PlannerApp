package example.battleships.service;

import example.battleships.model.entity.Category;
import example.battleships.model.entity.ShipType;
import example.battleships.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public Category findByName(ShipType name){
        return this.categoryRepository.findByName(name).orElseThrow();
    }


}
