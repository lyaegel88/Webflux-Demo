package com.lukes.webflux.demo.controllers;

import com.lukes.webflux.demo.domain.Category;
import com.lukes.webflux.demo.repository.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by lyaegel on 08/11/2019
 */
@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/v1/categories")
    Flux<Category> list(){
        return categoryRepository.findAll();
    }

    @GetMapping("/api/v1/categories/{id}")
    Mono<Category> getById(@PathVariable String id){
        return categoryRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/categories")
    Mono<Void> create(@RequestBody Publisher<Category> categoryStream){
        return categoryRepository.saveAll(categoryStream).then();
    }

    @PutMapping("/api/v1/categories/{id}")
    Mono<Category> update(@PathVariable String id, @RequestBody Category category){
        category.setId(id);
        return categoryRepository.save(category);
    }

    @PatchMapping("/api/v1/categories/{id}")
    Mono<Category> patch(@PathVariable String id, @RequestBody Category category){

        Category foundCategory = categoryRepository.findById(id).block();

        if (foundCategory.getDescription() != (category.getDescription())){
            foundCategory.setDescription((category.getDescription()));
            return categoryRepository.save(foundCategory);
        }

        return Mono.just(foundCategory);
    }

    @DeleteMapping("/api/v1/categories/{id}")
    Mono<Void> delete(@PathVariable String id){
        return categoryRepository.deleteById(id);
    }
}
