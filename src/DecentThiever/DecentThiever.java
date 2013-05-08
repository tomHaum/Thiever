package DecentThiever;

import DecentThiever.nodes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.awt.Color;
import java.awt.Graphics;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;

import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


@Manifest(authors = { "tbhizzle" }, description = "a somewhat better thiever", name = "Decent Thiever")
public class DecentThiever extends ActiveScript implements PaintListener {
	private Tree jobContainer = null;
	private List<Node> jobs = Collections.synchronizedList(new ArrayList<Node>());
	//paint image http://i.imgur.com/lH6haPX.png

	public void onStart() {
		OnStartGui gui = new OnStartGui();
		gui.setVisible(true);
		gui.setTitle("DecentThiever by tbizzle");
		
		//var.foodAmount = 1;
		
		
		var.startTime = System.currentTimeMillis();
		var.startXP = Skills.getExperience(Skills.THIEVING);
		
		provide(new WalkToBank(),
				new Banking(),
				new WalkToSpot(),
				new Steal());
				
	}

	@Override
	public int loop() {
		
		
		if(var.run){
			//System.out.println("good to run");
			var.currentHP = checkHP();
			if (jobContainer != null) {// tree
				
				final Node job = jobContainer.state();
				if (job != null) {
					jobContainer.set(job);
					getContainer().submit(job);
					job.join();
				}
			}
			if (var.close == true)stop();
		}
		
		return 100;
	}

	public synchronized final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			if (!this.jobs.contains(job)) {
				this.jobs.add(job);
			}
		}
		jobContainer = new Tree(this.jobs.toArray(new Node[this.jobs.size()]));
	}

	@Override
	public void onRepaint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 388, 519, 142);
		g.setColor(Color.BLACK);
		g.drawString("Decent Thiever by tbhizzle", 0,400);
		g.drawString(timeRunning(), 0, 415);
		g.drawString("xp gained: " + (Skills.getExperience(Skills.THIEVING)-var.startXP),
				0, 430);
		g.drawString("Success: " + var.successCount, 0, 445);
		g.drawString("Fail: " + var.failCount , 0 , 460);
		
		
	}
	public int checkHP(){
		if(Game.getClientState() == Game.INDEX_LOBBY_SCREEN) return -1;
		
		WidgetChild hp = new Widget(748).getChild(8);
		if(hp == null) return -1;
		int newHP = Integer.parseInt(hp.getText());
		//System.out.println("hp is: " +newHP);
		return newHP;
	}
	public String timeRunning(){
		long timeRan = System.currentTimeMillis() - var.startTime;
		int hour = (int)(timeRan/ 3600000L);
		int min = (int)(timeRan / 60000)%60;
		int sec = (int)(timeRan / 1000)%60;
		
		return String.format("Curent Time: %03d:%02d:%02d",hour,min,sec);
		
		
	}
}
