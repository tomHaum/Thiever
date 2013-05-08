package DecentThiever.nodes;

import org.powerbot.core.script.job.state.Node;

import org.powerbot.game.api.methods.widget.Bank;


import DecentThiever.var;

public class Banking extends Node{

	@Override
	public boolean activate() {
		
		return (var.atBank && var.banking) ;
	}

	@Override
	public void execute() {
		
		while(!Bank.open()){sleep(100);}
		Bank.depositInventory();
		Bank.depositInventory();
		Bank.setCurrentTab(Bank.Tab.ALL);
		if(Bank.getItemCount(var.foodID) > 0 ){
			Bank.setWithdrawNoted(false);
			Bank.withdraw(var.foodID, var.foodAmount);
			var.banking = false;
		}
		else{
			Bank.close();
			System.out.println("no selected food in bank, shutting down");
			var.close = true;
		}
		Bank.close();
		
	}
	
	

}
