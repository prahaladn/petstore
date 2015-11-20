package petstore.exception;

public class PetNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public PetNotFoundException(int id) {
		super(String.valueOf(id));
	}

}
