 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.util.Random;

public class MainGooey extends JFrame implements ActionListener {
	private JLabel foodPlace;

    public MainGooey() {
        
       setTitle("Food Roullete");
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
		String foodPlaces[] = {"place1", "place2", "place3", "place4", "place5"};
		Random randomGenerator = new Random();
		return foodPlaces[randomGenerator.nextInt(5)];
		
	}
}