package com.udacity.graphql.dogapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.graphql.dogapi.entity.Dog;
import com.udacity.graphql.dogapi.exception.DogNotFoundException;
import com.udacity.graphql.dogapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()){
            return optionalDog.get();
        }else {
            throw new DogNotFoundException("Dog not found", id);
        }
    }
}
