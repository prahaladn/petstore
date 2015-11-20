package petstore.service;

import petstore.dto.PetDto;

public interface IPetStoreService {
	
	public PetDto create(PetDto petDto);
	public PetDto findById(int id);
	public PetDto delete(int id);

}
