package petstore.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import petstore.domain.Category;
import petstore.domain.Pet;
import petstore.domain.Tag;
import petstore.dto.CategoryDto;
import petstore.dto.PetDto;
import petstore.dto.TagDto;

@Component
public class PetMapper {
	private final AtomicInteger petCounter = new AtomicInteger();
	private final AtomicInteger categoryCounter = new AtomicInteger();
	private final AtomicInteger tagCounter = new AtomicInteger();
	
	public Pet mapToPet(PetDto petDto){
		Pet pet = new Pet();
		pet.setId(petCounter.incrementAndGet());
		
		Category category = new Category();
		category.setId(categoryCounter.incrementAndGet());
		category.setName(petDto.getCategory().getName());
		pet.setCategory(category);
		
		pet.setName(petDto.getName());
		pet.setPhotoUrls(petDto.getPhotoUrls());
		pet.setStatus(petDto.getStatus());
		
		List<Tag> tags = new ArrayList<Tag>();
		for(TagDto tagDto : petDto.getTags()){
			Tag tag = new Tag();
			tag.setId(tagCounter.incrementAndGet());
			tag.setName(tagDto.getName());
			tags.add(tag);
		}
		pet.setTags(tags);		
		return pet;
	}

	public PetDto mapToPetDto(Pet pet){
		PetDto petDto = new PetDto();
		
		petDto.setId(pet.getId());
		
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(pet.getCategory().getId());
		categoryDto.setName(pet.getCategory().getName());
		petDto.setCategory(categoryDto);
		
		petDto.setName(pet.getName());
		petDto.setPhotoUrls(pet.getPhotoUrls());
		petDto.setStatus(pet.getStatus());
		
		List<TagDto> tagDtos = new ArrayList<TagDto>();
		for(Tag tag : pet.getTags()){
			TagDto tagDto = new TagDto();
			tagDto.setId(tag.getId());
			tagDto.setName(tag.getName());
			tagDtos.add(tagDto);
		}
		petDto.setTags(tagDtos);		
		
		return petDto;
	}
}
