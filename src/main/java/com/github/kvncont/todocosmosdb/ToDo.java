package com.github.kvncont.todocosmosdb;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Container(containerName = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDo {
    @Id
    @GeneratedValue
    private String id;

    @PartitionKey
    @NotNull(message = "User Id can't be null")
    @NotBlank(message = "User Id is mandatory")
    private String userId;

    @NotNull(message = "Username can't be null")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotNull(message = "Description can't be null")
    @NotBlank(message = "Description is mandatory")
    private String description;

    private boolean isCompleted;
}
