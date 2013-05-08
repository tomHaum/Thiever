package DecentThiever.nodes;

import DecentThiever.var;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;


import org.powerbot.game.api.wrappers.map.TilePath;



public class WalkToBank extends Node {

	@Override
	public boolean activate() {
		
		return (!var.atBank && var.banking);
	}

	@Override
	public void execute() {
		
		TilePath path = Walking.newTilePath(var.toSpot);
		path = path.reverse();
		while(path.traverse()){
			sleep(1000,2000);
		}
		var.atBank = true ; var.atSpot = false;
	}

}
