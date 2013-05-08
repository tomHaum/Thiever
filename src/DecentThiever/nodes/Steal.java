package DecentThiever.nodes;

import DecentThiever.var;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Steal extends Node {

	@Override
	public boolean activate() {

		return (var.atSpot);
	}

	@Override
	public void execute() {
		/*
		 * get nearest npcget healthpickpocketcheck healthFOR EVEN BETTER
		 * SCRIPT...determine success/failwait for message
		 */

		var.currentHP = checkHP();

		int theiveCount = 0;
		int eatCount = 0;
		int tempXP = Skills.getExperience(Skills.THIEVING);


		if (var.currentHP < var.lowestHP){
			
			while(!eatFood() && eatCount < 5){
				
				sleep(500,100);
				eatCount++;
			}
			if(eatCount > 4){
				var.banking = true;
				}
		}
		else {

			NPC target = getClosest(var.targetIDs);

			if (target == null) {
				var.atSpot = false;
			} else {
				if (!target.isOnScreen())
					Camera.turnTo(target);
				while (!target.interact(var.targetAction) && theiveCount < 5) {
					//System.out.println("pickpocketing");
					sleep(500, 1000);
					theiveCount++;
				}

			}
		
		while (tempXP == Skills.getExperience(Skills.THIEVING)
				&& (var.currentHP == checkHP())) sleep(300);
		
		if (tempXP < Skills.getExperience(Skills.THIEVING))
			var.successCount++;
		else if(var.currentHP > checkHP()){
			var.failCount++;
			sleep(3500);}
		else{}
		}

	}

	private NPC getClosest(int[] targetIDs) {
		NPC[] potential = NPCs.getLoaded(targetIDs);
		if (potential == null)
			return null;
		double min = Double.MAX_VALUE;
		int index = -1;

		for (int i = 0; i < potential.length; i++) {
			if (!potential[i].getLocation().canReach())
				continue;
			double temp = potential[i].getLocation().distanceTo();
			if (temp < min && potential[i].getAnimation() == -1) {
				min = temp;
				index = i;
			}
		}
		return potential[index];
	}

	private int checkHP() {
		WidgetChild hp = new Widget(748).getChild(8);
		if (hp == null)
			return -1;
		int newHP = Integer.parseInt(hp.getText());
		
		return newHP;
	}
	public boolean eatFood(){
		if(Inventory.contains(var.foodID)){
			WidgetChild food = Inventory.getItem(var.foodID).getWidgetChild();
			if(food == null) return false;
			return food.interact("Eat");
		}
		return false;
	}
}

