package com.github.kvncont.todocosmosdb;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CosmosRepository<ToDo, String> {
    List<ToDo> findAllByUserId(String userId);
}
