import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;


public class CustomPanel extends JPanel implements ActionListener{
	JList customList;
	JScrollPane listScroller;
	DefaultListModel listModel = new DefaultListModel();
	JTextField inputField;
	JButton inputButton;
	
	public CustomPanel() {
		super(new BorderLayout());
		
		customList = new JList(listModel); //data has type Object[]
		customList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		customList.setLayoutOrientation(JList.VERTICAL);
		customList.setVisibleRowCount(-1);
		
		listScroller = new JScrollPane(customList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		/**********************************************/
		JPanel inputPanel = new JPanel(new GridLayout(0,2));
		inputField = new JTextField();
		inputButton = new JButton("Add");
		inputButton.addActionListener(this);
		
		inputPanel.add(inputField);
		inputPanel.add(inputButton);
		
		this.add(listScroller, BorderLayout.NORTH);
		this.add(inputPanel, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		listModel.addElement(inputField.getText());
		customList.setModel(listModel);
		inputField.setText("");
	}
	
	public ArrayList<String> exportList() {
		ArrayList<String> returnList = new ArrayList<String>();
		for(int i=0; 1 < listModel.getSize(); i++) {
			returnList.add((String) listModel.get(i));
		}
		return returnList;
	}
}
