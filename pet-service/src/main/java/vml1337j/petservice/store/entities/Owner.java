package vml1337j.petservice.store.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @Builder.Default
    @OneToMany(mappedBy="owner")
    private List<Pet> pets = new ArrayList<>();

    public void addPet(Pet pet) {
        pet.setOwner(this);
        getPets().add(pet);
    }
}
