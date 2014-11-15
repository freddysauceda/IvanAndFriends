 
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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainGooey extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JPanel bottomPanel;
	private JLabel foodPlace;
	private CustomPanel customPanel = new CustomPanel();
	
	private URI uri;
	
	JCheckBox offBox = new JCheckBox("Off Campus");
    JCheckBox onBox = new JCheckBox("On Campus");
    JCheckBox customBox = new JCheckBox("Custom");
    
    HashMap<String, String> offFoodMap;
    
    public MainGooey() throws URISyntaxException {
        
       setTitle("Food Roulette");
       setSize(300, 200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE); 
       
       JTabbedPane tabbedPane = new JTabbedPane();
       
       mainPanel = new JPanel(new GridLayout(2,0));
       tabbedPane.addTab("Main", null, mainPanel,
               "ADVENTURE AWAITS");
       tabbedPane.addTab("Custom", null, customPanel,
               "Mien List");
       
       foodPlace = new JLabel("Adventure Awaits", SwingConstants.CENTER);
       //foodPlace = linkify("Adventure Awaits", "http://www.google.com", "google");
       foodPlace.setFont(new Font("Sans-Serif", Font.BOLD, 25));
       mainPanel.add(foodPlace);
       
       bottomPanel = new JPanel(new GridLayout(2,0));
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
            	MainGooey ex = null;
				try {
					ex = new MainGooey();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ex.setVisible(true);
            }
        });
    }


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("choose")) {
			String place = getRandomFood();

			mainPanel.remove(foodPlace);
			mainPanel.remove(bottomPanel);
			
			if(offFoodMap.get(place) == null) {
				foodPlace = new JLabel(place);
			}
			else {
				foodPlace = linkify(place, offFoodMap.get(place), place);
			}
			
			foodPlace.setFont(new Font("Sans-Serif", Font.BOLD, 25));
			mainPanel.add(foodPlace);
			mainPanel.add(bottomPanel);
			
			mainPanel.revalidate();
			mainPanel.repaint(); 
		}
		
	}
	
	private String getRandomFood() {		
		ArrayList<String> onFoodPlaces = new ArrayList<String>();
		ArrayList<String> offFoodPlaces = new ArrayList<String>();
		ArrayList<String> customFoodPlaces = new ArrayList<String>();
		ArrayList<String> foodPlaces = new ArrayList<String>();
		
		offFoodMap = new HashMap<String, String>();
		
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
		    	offFoodMap.put(text, reader.readLine());
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
	
	public static JLabel linkify(final String text, String URL, String toolTip)
	{
	    URI temp = null;
	    try
	    {
	        temp = new URI(URL);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    final URI uri = temp;
	    final JLabel link = new JLabel();
	    link.setText("<HTML><FONT color=\"#000099\">"+text+"</FONT></HTML>");
	    if(!toolTip.equals(""))
	        link.setToolTipText(toolTip);
	    link.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    link.addMouseListener(new MouseListener()
	    {
	        public void mouseExited(MouseEvent arg0)
	        {
	            link.setText("<HTML><FONT color=\"#000099\">"+text+"</FONT></HTML>");
	        }

	        public void mouseEntered(MouseEvent arg0)
	        {
	            link.setText("<HTML><FONT color=\"#000099\"><U>"+text+"</U></FONT></HTML>");
	        }

	        public void mouseClicked(MouseEvent arg0)
	        {
	            if (Desktop.isDesktopSupported())
	            {
	                try
	                {
	                    Desktop.getDesktop().browse(uri);
	                }
	                catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	            else
	            {
	                JOptionPane pane = new JOptionPane("Could not open link.");
	                JDialog dialog = pane.createDialog(new JFrame(), "");
	                dialog.setVisible(true);
	            }
	        }

	        public void mousePressed(MouseEvent e)
	        {
	        }

	        public void mouseReleased(MouseEvent e)
	        {
	        }
	    });
	    return link;
	}
}