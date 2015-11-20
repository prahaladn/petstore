package petstore.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import petstore.object.Pet;

public interface IPetStoreRepository extends MongoRepository<Pet, Serializable>{

}
