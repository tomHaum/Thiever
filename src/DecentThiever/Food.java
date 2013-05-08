package DecentThiever;

public enum Food {
	Tuna(361 , 600), 
	Lobster(379 , 800);
	
	
	private final int id;
	private final int restore;
	
	Food(int id, int restore){
		this.id = id;
		this.restore = restore;
	}
	
	public int getID(){
		return id;
	}
	
	public int getRestore(){
		return restore;
	}
	public void setFood(){
		var.foodID = id;
		System.out.println(this + " selected");
	}
}
