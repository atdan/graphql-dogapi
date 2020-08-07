package com.udacity.graphql.dogapi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.graphql.dogapi.entity.Dog;
import com.udacity.graphql.dogapi.exception.BreedNotFoundException;
import com.udacity.graphql.dogapi.exception.DogNotFoundException;
import com.udacity.graphql.dogapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }
    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        // Loop through all dogs to get their breed
        for (Dog d: allDogs){
            if (d.getBreed().equals(breed)){
                // Delete if breed is found
                dogRepository.delete(d);
                deleted = true;
            }

        }
        // Throw an exception if the breed doesn't exist
        if (!deleted){
            throw new BreedNotFoundException("Breed not found", breed);
        }
        return deleted;

    }

    public Dog updateDogName(String newName, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        }else {
            throw new DogNotFoundException("Dog not Found", id);
        }
    }
}
