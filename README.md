# ToDo Cosmos DB
Proyecto CRUD para probar las funcionalidades de CosmosDB

## Paso 1: Creación Cosmos DB
Seguir la guía de microsoft para crear un base de datos [Cosmos DB](https://docs.microsoft.com/en-us/azure/cosmos-db/create-cosmosdb-resources-portal)

## Paso 2: Exportar configuración
```
export COSMOS_URI=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
export COSMOS_DATABASE=xxxxxxxxxxxxxxxxxxxxxxxxxxxx
export COSMOS_KEY=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

## Paso 3: Ejecutar la aplicación
```
./mvnw spring-boot:run
```

## Paso 4: Probar las diferentes peticiones

### POST
```
URL:
    http://localhost:8080/todo

Request body:
    {
        "userId": "403240432",
        "username": "Don Ramon",
        "description": "Pagar la renta"
    }
```
### GET
```
URL:
    http://localhost:8080/todo
    http://localhost:8080/todo/user/{userId}
```
### PUT
```
URL:
    http://localhost:8080/todo
Request body:
    {
        "id": "61433322-7f31-4078-ad77-0c5e0ce22ecc",
        "userId": "103920432",
        "username": "Doki",
        "description": "Clases perrunas",
        "isCompleted": true
    }
```
### DELETE
```
URL:
    http://localhost:8080/todo/{id}
```


## Tareas pendientes:
- [x] Actualizar la llave isCompleted de forma correcta
- [x] Permitir eliminar todos los items
- [x] Validar y enviar un mensaje más amistoso cuando se intenta borrar un item no existente
- [ ] Refactorizar el código
- [ ] Agregar pruebas de unidad
