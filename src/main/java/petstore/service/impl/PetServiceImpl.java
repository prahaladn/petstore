package petstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petstore.domain.Pet;
import petstore.dto.PetDto;
import petstore.exception.PetNotFoundException;
import petstore.mapper.PetMapper;
import petstore.repository.IPetRepository;
import petstore.service.IPetService;

@Service
public class PetServiceImpl implements IPetService {
	
	@Autowired
	IPetRepository petRepository;
	
	@Autowired
	PetMapper petMapper;
	

	public PetDto create(PetDto petDto) {
		Pet pet = petMapper.mapToPet(petDto);
		pet = petRepository.save(pet);
		return petMapper.mapToPetDto(pet);
	}

	public PetDto findById(int id) {
		Pet pet = findPetById(id);
		return petMapper.mapToPetDto(pet);
	}

	public PetDto delete(int id){
		Pet pet = findPetById(id);
		petRepository.delete(pet);
		return petMapper.mapToPetDto(pet);
	}
	
	public List<PetDto> getAll(){
		List<PetDto> petDtos = new ArrayList<PetDto>();
		List<Pet> pets = petRepository.findAll();
		for(Pet pet : pets){
			petDtos.add(petMapper.mapToPetDto(pet));
		}
		return petDtos;
	}
	
	private Pet findPetById(int id) {
		Pet pet = petRepository.findOne(id);
		if(null != pet){
			return pet;
		}else{
			throw new PetNotFoundException(id);
		}
	}
}
