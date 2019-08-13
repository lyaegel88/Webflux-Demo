package com.lukes.webflux.demo.bootstrap;

import com.lukes.webflux.demo.domain.Category;
import com.lukes.webflux.demo.domain.Vendor;
import com.lukes.webflux.demo.repository.CategoryRepository;
import com.lukes.webflux.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by lyaegel on 08/11/2019
 */
@Component
public class DemoBootstrap implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count().block() == 0){
            //load data
            System.out.println("#### LOADING DATA ON BOOTSTRAP ####");

            categoryRepository.save(Category.builder()
                            .description("Fruits").build()).subscribe();

            categoryRepository.save(Category.builder()
                    .description("Nuts").build()).subscribe();

            categoryRepository.save(Category.builder()
                    .description("Breads").build()).subscribe();

            categoryRepository.save(Category.builder()
                    .description("Meats").build()).subscribe();

            categoryRepository.save(Category.builder()
                    .description("Eggs").build()).subscribe();

            System.out.println("Loaded Categories: " + categoryRepository.count().block());

            vendorRepository.save(Vendor.builder()
                    .firstName("Joe")
                    .lastName("Buck").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Michael")
                    .lastName("Weston").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Jessie")
                    .lastName("Waters").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Bill")
                    .lastName("Nershi").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Jimmy")
                    .lastName("Buffett").build()).block();

            System.out.println("Loaded Vendors: " + vendorRepository.count().block());

        }

    }
}
