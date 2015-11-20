package petstore.dto;

public enum StatusEnum {
	AVAILABLE("AVAILABLE"),
	PENDING("PENDING"),
	SOLD("SOLD");
	
	StatusEnum(String value){
		this.statusValue = value;
	}
	
	private String statusValue;
	
	public String getStatusValue(){
		return statusValue;
	}
}
