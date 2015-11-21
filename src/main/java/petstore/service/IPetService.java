package petstore.service;

import java.util.List;

import petstore.dto.PetDto;

public interface IPetService {
	
	public PetDto create(PetDto petDto);
	public PetDto findById(int id);
	public PetDto delete(int id);
	public List<PetDto> getAll();

}
