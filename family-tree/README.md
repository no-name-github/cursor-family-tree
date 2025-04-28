# Family Tree Microservice

A Spring Boot microservice for managing family relationships and genealogy data. This service provides a REST API for creating and managing family trees, including relationships between family members.

## Features

- Create and manage family members
- Establish parent-child relationships
- Set mother and father relationships
- Manage spouse relationships (current and former)
- Search family members by name
- Track birth and death dates
- Store additional information like occupation and life stories

## Technology Stack

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- JUnit 5
- JaCoCo for test coverage

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Building the Project

```bash
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on port 8080.

## REST API Documentation

### Person Management

#### Create a new person
```bash
curl -X POST http://localhost:8080/api/person \
-H "Content-Type: application/json" \
-d '{
    "firstName": "John",
    "lastName": "Doe",
    "occupation": "Engineer",
    "birthPlace": "New York",
    "bornDate": "1980-01-01"
}'
```

#### Get person by ID
```bash
curl -X GET http://localhost:8080/api/person/{id}
```

#### Update person
```bash
curl -X PUT http://localhost:8080/api/person \
-H "Content-Type: application/json" \
-d '{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "occupation": "Senior Engineer",
    "birthPlace": "New York",
    "bornDate": "1980-01-01"
}'
```

#### Delete person
```bash
curl -X DELETE http://localhost:8080/api/person/{id}
```

### Relationship Management

#### Add child to person
```bash
curl -X POST http://localhost:8080/api/person/{parentId}/child \
-H "Content-Type: application/json" \
-d '{
    "firstName": "Child",
    "lastName": "Doe",
    "occupation": "Student",
    "birthPlace": "New York",
    "bornDate": "2000-01-01"
}'
```

#### Set mother
```bash
curl -X POST http://localhost:8080/api/person/{personId}/mother/{motherId}
```

#### Set father
```bash
curl -X POST http://localhost:8080/api/person/{personId}/father/{fatherId}
```

#### Set spouse
```bash
curl -X POST http://localhost:8080/api/person/{personId}/spouse/{spouseId}
```

#### Delete spouse
```bash
curl -X DELETE http://localhost:8080/api/person/{personId}/spouse
```

#### Add former spouse
```bash
curl -X POST http://localhost:8080/api/person/{personId}/former-spouse \
-H "Content-Type: application/json" \
-d '{
    "firstName": "Former",
    "lastName": "Spouse",
    "occupation": "Artist",
    "birthPlace": "Chicago",
    "bornDate": "1985-01-01"
}'
```

### Search Operations

#### Find by first name
```bash
curl -X GET http://localhost:8080/api/person/first-name/{firstName}
```

#### Find by last name
```bash
curl -X GET http://localhost:8080/api/person/last-name/{lastName}
```

## Service API

The service layer provides the following main operations:

### PersonService
- `createNewPerson(PersonDTO personDTO)`: Create a new person
- `updatePerson(PersonDTO personDTO)`: Update an existing person
- `getPerson(Long personId)`: Get a person by ID
- `deletePerson(Long personId)`: Delete a person
- `findPersonByFirstName(String firstName)`: Find persons by first name
- `findPersonByLastName(String lastName)`: Find persons by last name

### Relationship Management
- `addChild(Long personId, PersonDTO childDTO)`: Add a child to a person
- `setMother(Long personId, Long motherId)`: Set a person's mother
- `setFather(Long personId, Long fatherId)`: Set a person's father
- `setSpouse(Long personId, Long spouseId)`: Set a person's spouse
- `deleteSpouse(Long personId)`: Delete a person's spouse
- `addFormerSpouse(Long personId, PersonDTO formerSpouseDTO)`: Add a former spouse

## Integration Tests

The project includes comprehensive integration tests covering:

### Controller Layer Tests
- CRUD operations for persons
- Relationship management operations
- Search operations
- Error handling scenarios

### Repository Layer Tests
- Database operations
- Custom query methods
- Relationship mappings
- Transaction management

### Test Coverage
The project uses JaCoCo for test coverage tracking. The current coverage requirements:
- Minimum 80% line coverage
- Coverage reports are generated in `target/site/jacoco/index.html`

To run the tests:
```bash
mvn clean test
```

## Database

The application uses H2 in-memory database with the following configuration:
- Database URL: `jdbc:h2:mem:familytree`
- Username: `sa`
- Password: `password`
- H2 Console: Available at `http://localhost:8080/h2-console` 