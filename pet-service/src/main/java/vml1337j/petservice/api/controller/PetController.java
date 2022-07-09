package vml1337j.petservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vml1337j.petservice.api.exception.owner.OwnerException;
import vml1337j.petservice.api.exception.pet.PetException;
import vml1337j.petservice.store.entities.Owner;
import vml1337j.petservice.store.entities.Pet;
import vml1337j.petservice.store.repository.OwnerRepository;
import vml1337j.petservice.store.repository.PetRepository;

import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class PetController {
    public static final String CREATE_ANIMAL = "/api/v1/pet-service/owners/{owner_name}/pets";
    public static final String UPDATE_ANIMAL = "/api/v1/pet-service/owners/{owner_name}/pets/{pet_id}";
    public static final String DELETE_ANIMAL = "/api/v1/pet-service/owners/{owner_name}/pets/{pet_id}";

    public static final String FETCH_ANIMALS = "/api/v1/pet-service/owners/{owner_name}/pets";
    public static final String GET_ANIMAL = "/api/v1/pet-service/owners/{owner_name}/pets/{pet_id}";

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @GetMapping(FETCH_ANIMALS)
    public ResponseEntity<Collection<Pet>> fetchOwnerPets(@PathVariable("owner_name") String ownerName) {
        Owner owner = ownerRepository.findByLogin(ownerName)
                .filter(o -> o.getPets().size() != 0)
                .orElseThrow(() -> new OwnerException(
                        String.format("User with name \"%s\" haven`t pets", ownerName)
                ));

        return ResponseEntity.ok(owner.getPets());
    }

    @GetMapping(GET_ANIMAL)
    public ResponseEntity<Pet> getOwnerPetById(@PathVariable("owner_name") String ownerName,
                                               @PathVariable("pet_id") Long petId) {
        Owner owner = ownerRepository.findByLogin(ownerName)
                .filter(o -> o.getPets().size() != 0)
                .orElseThrow(() -> new OwnerException(
                        String.format("User with name \"%s\" haven`t pets", ownerName)
                ));

        Pet pet = petRepository.findById(petId)
                .filter(p -> Objects.equals(p.getOwner().getId(), owner.getId()))
                .orElseThrow(() -> new OwnerException(
                        String.format("User haven`t pet with id \"%d\"", petId)
                ));

        return ResponseEntity.ok(pet);
    }

    @PostMapping(CREATE_ANIMAL)
    public ResponseEntity<Pet> createPet(@PathVariable("owner_name") String ownerName,
                                         @RequestBody Pet newAnimal) {
        if (petRepository.existsAnimalByName(newAnimal.getName())) {
            throw new PetException(
                    String.format("Pet with name \"%s\" already exists", newAnimal.getName())
            );
        }

        petRepository.saveAndFlush(newAnimal);

        Owner owner = ownerRepository.findByLogin(ownerName)
                .orElseGet(() -> Owner.builder()
                        .login(ownerName)
                        .build());

        owner.addPet(newAnimal);
        ownerRepository.save(owner);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newAnimal);
    }

    @PutMapping(UPDATE_ANIMAL)
    public ResponseEntity<Pet> updatePet(@PathVariable("owner_name") String ownerName,
                                         @PathVariable("pet_id") Long petId,
                                         @RequestBody Pet updatedPet) {
        Owner owner = ownerRepository.findByLogin(ownerName)
                .filter(o -> o.getPets().size() != 0)
                .orElseThrow(() -> new OwnerException(
                        String.format("User with name \"%s\" haven`t pets", ownerName)
                ));

        Pet oldPet = petRepository.findById(petId)
                .filter(p -> Objects.equals(p.getOwner().getId(), owner.getId()))
                .orElseThrow(() -> new OwnerException(
                        String.format("User haven`t pet with id \"%d\"", petId)
                ));

        if (!Objects.equals(oldPet.getName(), updatedPet.getName())
                && petRepository.existsAnimalByName(updatedPet.getName())) {
            throw new PetException(
                    String.format("Pet with name \"%s\" already exists", updatedPet.getName())
            );
        }

        updatedPet.setId(petId);
        updatedPet.setOwner(oldPet.getOwner());
        return ResponseEntity.ok(petRepository.save(updatedPet));
    }

    @DeleteMapping(DELETE_ANIMAL)
    public ResponseEntity deletePet(@PathVariable("owner_name") String ownerName,
                                    @PathVariable("pet_id") Long petId) {
        Owner owner = ownerRepository.findByLogin(ownerName)
                .filter(o -> o.getPets().size() != 0)
                .orElseThrow(() -> new OwnerException(
                        String.format("User with name \"%s\" haven`t pets", ownerName)
                ));

        Pet pet = petRepository.findById(petId)
                .filter(p -> Objects.equals(p.getOwner().getId(), owner.getId()))
                .orElseThrow(() -> new OwnerException(
                        String.format("User haven`t pet with id \"%d\"", petId)
                ));

        petRepository.delete(pet);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
