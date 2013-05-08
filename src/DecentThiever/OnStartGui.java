package DecentThiever;


import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;


import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;


public class OnStartGui extends JFrame{
   
	private boolean locPushed;
    private boolean foodPushed;
    private String input;
	
    private JLabel locLabel = new JLabel();
    private JLabel foodLabel = new JLabel();
    private JLabel amountLabel = new JLabel();
    
    private ButtonGroup locGroup = new ButtonGroup();
    private JRadioButton fallyButton = new JRadioButton();
    private JRadioButton drayButton = new JRadioButton();
    
    private ButtonGroup foodGroup = new ButtonGroup();
    private JRadioButton tunaButton = new JRadioButton();
    private JRadioButton lobsterButton = new JRadioButton();
    
    private JSpinner foodSpin = new JSpinner();
    
    private JButton startButton = new JButton();
    
    
    public OnStartGui() {
    	
       // startButton.setSize(100,100);

        locLabel.setText("Locations : Target");
        foodLabel.setText("Food Types");
        amountLabel.setText("Amount Of Food");
        
        
       
		ActionListener locListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				locPushed = true;
				switch (action) {
				case "Fally":
					Targets.FallyGuard.setTargets();
					break;
				case "Dray":
					Targets.DraynorMasterFarmer.setTargets();
					break;
				default:
					locPushed = false;
				}
			}
		};
        
        locGroup.add(fallyButton);
        fallyButton.setText("Fally : Guards");
        fallyButton.setActionCommand("Fally");
        fallyButton.addActionListener(locListener);

        locGroup.add(drayButton);
        drayButton.setText("Draynor: MasterGardner");
        drayButton.setActionCommand("Dray");
        drayButton.addActionListener(locListener);

		ActionListener foodListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedFood = e.getActionCommand();
				foodPushed = true;
				switch (selectedFood) {
				case "Tuna":
					Food.Tuna.setFood();
					break;
				case "Lobster":
					Food.Lobster.setFood();
					break;
				default:
					foodPushed = false;
				}
			}
		};
        
        foodGroup.add(tunaButton);
        tunaButton.setText("Tuna");
        tunaButton.setActionCommand("Tuna");
        tunaButton.addActionListener(foodListener);

        foodGroup.add(lobsterButton);
        lobsterButton.setText("Lobster");
        lobsterButton.setActionCommand("Lobster");
        lobsterButton.addActionListener(foodListener);
        
        foodSpin.setValue(1);
        foodSpin.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent e) {
				int num = (int) foodSpin.getValue();
				if(num < 0) foodSpin.setValue(0);
				if(num > 28) foodSpin.setValue(28);
				var.foodAmount = (int) foodSpin.getValue();
				
				
			}
        	
        });
        
        startButton.setText("Start");
        startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (locPushed && foodPushed) {
					System.out.println("starting");
					var.guiVisible = false;
					var.run = true;
					
					dispose();
				}
			}
        });
        
        

        //netbeans generated V
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drayButton)
                            .addComponent(fallyButton)
                            .addComponent(locLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tunaButton)
                                .addComponent(lobsterButton))
                            .addComponent(foodLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(foodSpin)
                            .addComponent(amountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locLabel)
                    .addComponent(foodLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fallyButton)
                    .addComponent(tunaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drayButton)
                    .addComponent(lobsterButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(amountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foodSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startButton))
                .addContainerGap())
        );
        //netbeans generated ^

       pack();
    }
  	
  	
}
