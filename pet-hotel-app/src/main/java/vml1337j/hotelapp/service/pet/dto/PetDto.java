package vml1337j.hotelapp.service.pet.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetDto {
    private Long id;

    private String type;

    private LocalDate birthday;

    private String gender;

    private String name;
}
