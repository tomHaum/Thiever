package DecentThiever;

import org.powerbot.game.api.wrappers.Tile;

public enum Targets {
	FallyGuard(new int[] { 9 }, "Guard", "Fally", "Pickpocket", new Tile[] {
			new Tile(2946, 3370, 0), new Tile(2951, 3377, 0),
			new Tile(2962, 3384, 0), new Tile(2966, 3393, 0) }),
	DraynorMasterFarmer(new int[] {2234,3299}, "Master Farmer" , "Draynor", "Pickpocket",new Tile[]{
			new Tile(3093,3244,0), new Tile(3084,3249,0),
			new Tile (3077,3253,0)});

	private final int[] ids;
	private final String targetName;
	private final String location;
	private final String action;
	private final Tile[] pathToBank;

	Targets(int[] ids, String targetName, String location, String action,
			Tile[] pathToBank) {
		this.ids = ids;
		this.targetName = targetName;
		this.location = location;
		this.action = action;
		this.pathToBank = pathToBank;
	}

	public int[] getIDs() {
		return ids;
	}

	public String getTargetName() {
		return targetName;
	}

	public String getLocation() {
		return location;
	}

	public String getAction() {
		return action;
	}

	public Tile[] getPath() {
		return pathToBank;
	}

	public void setTargets() {
		System.out.println("Theiving "+ targetName + " at "+location);
		var.targetIDs = ids;
		var.targetAction = action;
		var.toSpot = pathToBank;
	}

}