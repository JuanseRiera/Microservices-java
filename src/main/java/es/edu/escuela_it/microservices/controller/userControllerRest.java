package es.edu.escuela_it.microservices.controller;

import es.edu.escuela_it.microservices.model.accountDTO;
import es.edu.escuela_it.microservices.model.userDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.Link;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/getuser")
@Api(tags = "Usuario API Rest")

public class userControllerRest {


    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Respuseta ok si se encontro el usuario"),
            @ApiResponse(code = 404,message = "Respuesta not found si no se encontro el usuario")
    })
    @ApiOperation(notes="Retorna un usuario por su id",value="Obtener usuario por id")
    public ResponseEntity<userDTO> getUser(
            @ApiParam(example = "1",value = "Identificador del usuario",allowableValues = "1,2,3,4",required = true)
            @PathVariable Integer id){
        userDTO user = new userDTO(id,"Juanse");
        user.setLastName("Riera");

        //Se crea una url que permite obtener el objeto
        Link withSelfRel =
                linkTo(methodOn(userControllerRest.class).getUser(user.getId())).withSelfRel();
        user.add(withSelfRel);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<userDTO>> listAllUsers(@RequestParam(required = false) String name){
        List<userDTO> listUsers = List.of(new userDTO(1,"Juanse"),new userDTO(2,"Rafa"));
        //Filtro por nombre List<userDTO> list = listUsers.stream().filter(x -> x.getName().contains(name)).collect(Collectors.toList());

        //Se crea una url que permita obtener la lista de todos los usuarios
        Link link = linkTo(methodOn(userControllerRest.class).listAllUsers("")).withSelfRel();
        CollectionModel<userDTO> result = CollectionModel.of(listUsers, link);

        //Se crea un url para obtener ese usuario especifico
        listUsers.forEach(user -> {
            Link withSelfRel =
                    linkTo(methodOn(userControllerRest.class).getUser(user.getId())).withSelfRel();
            user.add(withSelfRel);
        });

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> createUser( @Valid @RequestBody userDTO user){
        System.out.println("creando usuario" + user.getName());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(user.getId().toString()).buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<userDTO> editUser(@RequestBody userDTO user){
        System.out.println("Editando usuario" + user.getName());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        System.out.println("eliminando usuario" + id);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<userDTO> updateUser(userDTO atribute,@PathVariable Integer id){
        userDTO user = new userDTO(id,"juanse");
        user.setAge(atribute.getAge());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<accountDTO>> getUserAccounts(@PathVariable Integer id){
        List<accountDTO> listAccounts = List.of(new accountDTO("Google"), new accountDTO("Twitter"));
        return ResponseEntity.ok(listAccounts);
    }

    @GetMapping("/{id}/accounts/{idaccount}")
    public ResponseEntity<accountDTO> getUserAccounts(@PathVariable Integer id,@PathVariable Integer idaccount){
        return ResponseEntity.ok(new accountDTO("Google"));
    }

}
