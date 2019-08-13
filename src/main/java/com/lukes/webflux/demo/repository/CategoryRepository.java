package com.lukes.webflux.demo.repository;

import com.lukes.webflux.demo.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by lyaegel on 08/11/2019
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

}
