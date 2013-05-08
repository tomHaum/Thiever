package DecentThiever.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;

import org.powerbot.game.api.wrappers.map.TilePath;

import DecentThiever.var;

public class WalkToSpot extends Node {

	@Override
	public boolean activate() {
		//at bank
		//banking false
		//not at spot
		return (!var.atSpot && !var.banking);
		
	}

	@Override
	public void execute() {
		TilePath path = Walking.newTilePath(var.toSpot);
		
		while(path.traverse()){
			sleep(1000,2000);
		}
		var.atBank = false; var.atSpot = true; 
	}
	
}
