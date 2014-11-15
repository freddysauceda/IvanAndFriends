 
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

    public MainGooey() {
        
       setTitle("Food Roulette");
       setSize(300, 200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE); 
       
       JPanel mainPanel = new JPanel(new GridLayout(2,0));
       
       foodPlace = new JLabel("Food Adventure", SwingConstants.CENTER);
       foodPlace.setFont(new Font("Sans-Serif", Font.BOLD, 25));
       mainPanel.add(foodPlace);
       
       JPanel bottomPanel = new JPanel(new GridLayout(2,0));
       JPanel bottomRightPanel = new JPanel(new GridLayout(0,2));
       
       JCheckBox offBox = new JCheckBox("Off Campus");
       JCheckBox onBox = new JCheckBox("On Campus");
       bottomRightPanel.add(offBox);
       bottomRightPanel.add(onBox);
       
       JButton randomButton = new JButton("Choose for me");
       randomButton.addActionListener(this);
       
       bottomPanel.add(randomButton);
       bottomPanel.add(bottomRightPanel);
       mainPanel.add(bottomPanel);
       
       this.add(mainPanel);
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
		foodPlace.setText(getRandomFood());
		
	}
	
	private String getRandomFood() {
		//String foodPlaces[] = {"place1", "place2", "place3", "place4", "place5"};
		
		ArrayList<String> foodPlaces = new ArrayList<String>();

		String filePath = new File("").getAbsolutePath();
        System.out.println (filePath);

        BufferedReader reader = null;

		//parse file to ArrayList
		try {
		    reader = new BufferedReader(new FileReader(filePath + "/resources/offcampus"));
		    String text = null;

		    while ((text = reader.readLine()) != null) {
		    	foodPlaces.add(text);
		    }
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
		for(String place : foodPlaces) {
			System.out.println(place);
		}

		//return random foodPlace from arrayList
		Random randomGenerator = new Random();
		return foodPlaces.get(randomGenerator.nextInt(foodPlaces.size()));
		
	}
}