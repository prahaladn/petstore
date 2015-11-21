package petstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import petstore.dto.PetDto;
import petstore.exception.PetNotFoundException;
import petstore.service.IPetService;

import com.google.gson.Gson;

@RestController
@RequestMapping("v1/pet")
public class PetController {

	@Autowired
	IPetService petService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public  @ResponseBody String getAllPets() {
		List<PetDto> petDtos = petService.getAll();
		Gson gson = new Gson();
		String jsonData = gson.toJson(petDtos);
		//String jsonData = "[{\"id\":\"1\",\"name\":\"dog\",\"categoryname\":\"pluto\"},{\"id\":\"2\",\"name\":\"tom\",\"categoryname\":\"cat\"}]";
		return jsonData;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addNewPet(@Valid @RequestBody PetDto petDto){
		Map<String, Object> response = new HashMap<String, Object>();
		
		petDto = petService.create(petDto);
		
		response.put("message", "New pet " +petDto.getName()+ " added successfully");
		response.put("pet", petDto);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public PetDto getPetDetails(@PathVariable("id") int id){
		return petService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public Map<String, String> deletePet(@PathVariable("id") int id){
		Map<String, String> response = new HashMap<String, String>();
		
		petService.delete(id);

		response.put("message", "Pet deleted successfully");
		return response;
	}
	
	@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePetNotFound(PetNotFoundException ex) {
    }
}
