package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;


public class JTeamEditor extends JFrame {

	private JPanel contentPane;
    private JImagePanel leftImagePane;
    private JImagePanel rightImagePane;
    private JImagePanel backgroundImagePane;
    
    private JList leftJList;
    private JList rightJList;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    


    
	public JTeamEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Painel que contem a imagem
		final String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		
		try {
			leftImagePane = new JImagePanel(path+"warrior.jpg");
			leftImagePane.setBounds(79, 43, 150, 150);
			leftImagePane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			rightImagePane = new JImagePanel(path+"ranger.jpg");
			rightImagePane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			rightImagePane.setBounds(576, 43, 150, 150);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			backgroundImagePane = new JImagePanel(path+"background.jpg");
			backgroundImagePane.setBounds(0, 0, 900, 600);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		contentPane.setLayout(null);
		backgroundImagePane.setLayout(null);
		getContentPane().add(backgroundImagePane);
		
		backgroundImagePane.add(leftImagePane);
		backgroundImagePane.add(rightImagePane);
		
		textField = new JTextField();
		textField.setBounds(71, 336, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(71, 378, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(71, 292, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
	    
		//leftJList = new JList()
	}
}
