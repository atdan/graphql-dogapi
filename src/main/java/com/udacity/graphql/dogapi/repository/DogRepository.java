package com.udacity.graphql.dogapi.repository;

import com.udacity.graphql.dogapi.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
