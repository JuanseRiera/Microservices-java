package es.edu.escuela_it.microservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@ToString
@RequiredArgsConstructor
@ApiModel(description = "Modelo de usuarios")
public class userDTO  extends RepresentationModel<userDTO> {

    @ToString.Exclude
    @NonNull
    @NotNull
    @ApiModelProperty(notes = "Identificador unico para el usuario", example = "1", required = true, position = 0)
    private Integer id;

    @NonNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String name;

    @NotBlank
    @Size(min = 6, max = 20)
    private String lastName;

    @ToString.Exclude
    @Min(18)
    private int age;

    @Email
    private String email;

    @PastOrPresent
    private LocalDate fechaCreacion;

}
