package vml1337j.petservice.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vml1337j.petservice.store.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    boolean existsAnimalByName(String name);
}
