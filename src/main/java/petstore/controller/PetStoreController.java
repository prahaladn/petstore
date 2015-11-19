package petstore.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import petstore.object.Pet;
import petstore.repository.IPetStoreRepository;

@RestController
@RequestMapping("v1/pet")
public class PetStoreController {

	@Autowired
	IPetStoreRepository petStoreRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addNewPet(@RequestBody Pet pet){
		Map<String, Object> response = new HashMap<String, Object>();
		
		petStoreRepository.save(pet);
		
		response.put("message", "new pet added successfully");
		response.put("pet", pet);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public Pet getPetDetails(@PathVariable("id") int id){
		return petStoreRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public Map<String, String> deletePet(@PathVariable("id") String id){
		Map<String, String> response = new HashMap<String, String>();
		
		petStoreRepository.delete(id);

		response.put("message", "Pet deleted successfully");
		return response;
	}
}
