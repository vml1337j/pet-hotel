package vml1337j.hotelapp.service.pet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vml1337j.hotelapp.service.pet.dto.PetDto;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PetController {
    public static final String CREATE_ANIMAL = "/pets";
    public static final String UPDATE_ANIMAL = "/pets/{pet_id}";
    public static final String DELETE_ANIMAL = "/pets/{pet_id}";

    public static final String FETCH_ANIMALS = "/pets";
    public static final String GET_ANIMAL = "/pets/{pet_id}";

    private final RestTemplate template;

    @Value("${api.pet-service}")
    private String PET_SERVICE;

    @GetMapping(FETCH_ANIMALS)
    public ResponseEntity<List<PetDto>> fetchUserAnimals(Principal principal) {

        List<PetDto> pets = List.of(template.getForObject(
                String.format(PET_SERVICE + "/owners/%s/pets", principal.getName()),
                PetDto[].class
        ));

        return ResponseEntity.ok(pets);
    }

    @GetMapping(GET_ANIMAL)
    public ResponseEntity<PetDto> getAnimalByOwner(@PathVariable("pet_id") Long petId,
                                                   Principal principal) {
        PetDto pet = template.getForObject(
                String.format(PET_SERVICE + "/owners/%s/pets/%d", principal.getName(), petId),
                PetDto.class
        );

        return ResponseEntity.ok(pet);
    }

    @PostMapping(CREATE_ANIMAL)
    public ResponseEntity createPet(@RequestBody PetDto newPet,
                                    Principal principal) {
        template.postForLocation(
                String.format(PET_SERVICE + "/owners/%s/pets", principal.getName()),
                newPet
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Pet created");
    }

    @PutMapping(UPDATE_ANIMAL)
    public ResponseEntity updatePet(@PathVariable("pet_id") Long petId,
                                    @RequestBody PetDto updatedPet,
                                    Principal principal) {
        template.put(
                String.format(PET_SERVICE + "/owners/%s/pets/%d", principal.getName(), petId),
                updatedPet
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(DELETE_ANIMAL)
    public ResponseEntity deletePet(@PathVariable("pet_id") Long petId,
                                    Principal principal) {
        template.delete(
                String.format(PET_SERVICE + "/owners/%s/pets/%d", principal.getName(),
                        petId)
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
