package com.github.kvncont.todocosmosdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createToDo(@Valid @RequestBody ToDo toDo){
        ToDo savedToDo = toDoRepository.save(toDo);
        final Optional<ToDo> result = toDoRepository.findById(toDo.getId());

        return new ResponseEntity<Optional<ToDo>>(result, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity getAllToDo() {
        Iterable<ToDo> toDo = toDoRepository.findAll();
        List<ToDo> result =
                StreamSupport.stream(toDo.spliterator(), false)
                        .collect(Collectors.toList());
        return new ResponseEntity<List<ToDo>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity getAllToDoByUser(@PathVariable String userId) {
        List<ToDo> toDo = toDoRepository.findAllByUserId(userId);
        return new ResponseEntity<List<ToDo>>(toDo, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateToDoById(@Valid @RequestBody ToDo toDo){

        Optional<ToDo> currentToDo = toDoRepository.findById(toDo.getId());

        ToDo updatingToDo = new ToDo();
        updatingToDo.setId(toDo.getId());
        updatingToDo.setUserId(toDo.getUserId());
        updatingToDo.setUsername(toDo.getUsername());
        updatingToDo.setDescription(toDo.getDescription());
        updatingToDo.setCompleted(toDo.isCompleted());

        if (!currentToDo.isEmpty())
            toDoRepository.delete(currentToDo.get());

        ToDo result = toDoRepository.save(updatingToDo);

        return new ResponseEntity<ToDo>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{toDoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteToDoById(@PathVariable String toDoId){
        String message = "{\"message\": \"ToDo no se encuentra\"}";
        HttpStatus status = HttpStatus.NOT_FOUND;

        Optional<ToDo> currentToDo = toDoRepository.findById(toDoId);

        if (!currentToDo.isEmpty()) {
            System.out.println("Si Entro Inicio..........");
            message = "{\"message\": \"ToDo borrado exitosamente\"}";
            toDoRepository.delete(currentToDo.get());
            status = HttpStatus.OK;
            System.out.println("Si Entro Fin..........");
        }

        return new ResponseEntity(message, status);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAllToDo() {
        toDoRepository.deleteAll();
        return new ResponseEntity("{\"message\":\"Todos los items se han eliminado\"}", HttpStatus.OK);
    }
}
