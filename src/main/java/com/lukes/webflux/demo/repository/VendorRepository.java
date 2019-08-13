package com.lukes.webflux.demo.repository;

import com.lukes.webflux.demo.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by lyaegel on 08/11/2019
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
