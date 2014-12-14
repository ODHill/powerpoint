package model;

public enum FindTypeEnum {
	
	BY_TITLE("BY_TITLE"),BY_WORDS("BY_WORDS"),ALL_FILES("ALL_FILES");
	
	private String desc; 	
		
	FindTypeEnum(String desc){
		this.setDesc(desc);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
