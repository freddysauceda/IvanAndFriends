 
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
       
       JPanel mainPanel = new JPanel(new BorderLayout());
       
       foodPlace = new JLabel("something");
       mainPanel.add(foodPlace, BorderLayout.NORTH);
       
       JButton randomButton = new JButton("Choose for me");
       randomButton.addActionListener(this);
       mainPanel.add(randomButton, BorderLayout.SOUTH);
       
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

		//return random foodPlace from arrayList
		Random randomGenerator = new Random();
		return foodPlaces.get(randomGenerator.nextInt(foodPlaces.size()));
		
	}
}