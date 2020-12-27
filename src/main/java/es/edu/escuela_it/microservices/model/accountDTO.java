package es.edu.escuela_it.microservices.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class accountDTO {

    private Integer id;
    @NonNull
    private String name;

}
