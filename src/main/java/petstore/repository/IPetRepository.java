package petstore.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import petstore.domain.Pet;

public interface IPetRepository extends MongoRepository<Pet, Serializable>{

}
