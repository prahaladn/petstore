package petstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petstore.domain.Pet;
import petstore.dto.PetDto;
import petstore.exception.PetNotFoundException;
import petstore.mapper.PetMapper;
import petstore.repository.IPetStoreRepository;
import petstore.service.IPetStoreService;

@Service
public class PetStoreServiceImpl implements IPetStoreService {
	
	@Autowired
	IPetStoreRepository petStoreRepository;
	
	@Autowired
	PetMapper petMapper;
	

	public PetDto create(PetDto petDto) {
		Pet pet = petMapper.mapToPet(petDto);
		pet = petStoreRepository.save(pet);
		return petMapper.mapToPetDto(pet);
	}

	public PetDto findById(int id) {
		Pet pet = findPetById(id);
		return petMapper.mapToPetDto(pet);
	}

	public PetDto delete(int id){
		Pet pet = findPetById(id);
		petStoreRepository.delete(pet);
		return petMapper.mapToPetDto(pet);
	}
	
	private Pet findPetById(int id) {
		Pet pet = petStoreRepository.findOne(id);
		if(null != pet){
			return pet;
		}else{
			throw new PetNotFoundException(id);
		}
	}
}
