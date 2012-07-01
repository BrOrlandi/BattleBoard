package UserInterface;


import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class JTeamEditor extends JFrame {
	
	//Painel principal
	private JPanel contentPane;
	 
	//painel capaz de carregar uma imagem
    private JImagePanel backgroundImagePane;
 
    /**
     * Construtor
     */
	public JTeamEditor() {
		
		//Algumas inicizalicaçoes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//Panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Caminho da imagem que sera carregada
		final String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		
		//Instancia Panel que contem imagem
		try{
			backgroundImagePane = new JImagePanel(path+"background.jpg");
			backgroundImagePane.setBounds(0, 0, 900, 600);
		} catch(IOException e){
			e.printStackTrace();
		}	
		
		//adiciona sobre o panel principal
		contentPane.setLayout(null);
		backgroundImagePane.setLayout(null);
		getContentPane().add(backgroundImagePane);

		
	}
}
