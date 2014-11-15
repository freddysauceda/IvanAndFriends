 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.Random;

public class MainGooey extends JFrame implements ActionListener {
	private JLabel foodPlace;
	private CustomPanel customPanel = new CustomPanel();
	
	JCheckBox offBox = new JCheckBox("Off Campus");
    JCheckBox onBox = new JCheckBox("On Campus");
    JCheckBox customBox = new JCheckBox("Custom");
    
    public MainGooey() {
        
       setTitle("Food Roulette");
       setSize(300, 200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE); 
       
       JTabbedPane tabbedPane = new JTabbedPane();
       
       JPanel mainPanel = new JPanel(new GridLayout(2,0));
       tabbedPane.addTab("Main", null, mainPanel,
               "ADVENTURE AWAITS");
       tabbedPane.addTab("Custom", null, customPanel,
               "Mien List");
       
       foodPlace = new JLabel("Food Adventure", SwingConstants.CENTER);
       foodPlace.setFont(new Font("Sans-Serif", Font.BOLD, 25));
       mainPanel.add(foodPlace);
       
       JPanel bottomPanel = new JPanel(new GridLayout(2,0));
       JPanel bottomRightPanel = new JPanel(new GridLayout(0,3));
       
       //////////
       bottomRightPanel.add(offBox);
       bottomRightPanel.add(onBox);
       bottomRightPanel.add(customBox);
       
       JButton randomButton = new JButton("Choose for me");
       randomButton.setActionCommand("choose");
       randomButton.addActionListener(this);
       
       bottomPanel.add(randomButton);
       bottomPanel.add(bottomRightPanel);
       mainPanel.add(bottomPanel);
       
       this.add(tabbedPane);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            
            public void run() {
            	MainGooey ex = new MainGooey();
                ex.setVisible(true);
            }
        });
    }


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("choose")) {
			foodPlace.setText(getRandomFood());
		}
		
	}
	
	private String getRandomFood() {		
		ArrayList<String> onFoodPlaces = new ArrayList<String>();
		ArrayList<String> offFoodPlaces = new ArrayList<String>();
		ArrayList<String> customFoodPlaces = new ArrayList<String>();
		ArrayList<String> foodPlaces = new ArrayList<String>();
		
		String filePath = new File("").getAbsolutePath();
        //System.out.println (filePath);

        BufferedReader reader = null;

		//parse file to ArrayList
		try {
			//read offcampus
		    reader = new BufferedReader(new FileReader(filePath + "/src/resources/offcampus"));
		    String text = null;

		    while ((text = reader.readLine()) != null) {
		    	offFoodPlaces.add(text);
		    }
		    
		    //read oncampus
		    reader = new BufferedReader(new FileReader(filePath + "/src/resources/oncampus"));

		    while ((text = reader.readLine()) != null) {
		    	onFoodPlaces.add(text);
		    }
		    
		    //read custom
		    customFoodPlaces.addAll(customPanel.exportList());
		    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}

		//return random foodPlace from arrayList
		if(offBox.isSelected()) {
			foodPlaces.addAll(offFoodPlaces);
		}
		if(onBox.isSelected()) {
			foodPlaces.addAll(onFoodPlaces);
		}
		if(customBox.isSelected()) {
			foodPlaces.addAll(customFoodPlaces);
		}
		Random randomGenerator = new Random();
		if(foodPlaces.size() > 0) {
			return foodPlaces.get(randomGenerator.nextInt(foodPlaces.size()));
		}
		else {
			return "";
		}
		
	}
}