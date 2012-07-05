package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;

import com.thoughtworks.xstream.XStreamException;

import Game.Game;
import Item.Item;
import Item.ItemStore;
import Utilities.XML;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class JMain extends JFrame {

	private JButton playButton;
	private JButton createItemButton;
	private JButton exitButton;
	
	private JPanel panel;
	private ImageJPanel imagePanel;
	
	
	private Game game;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMain frame = new JMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JMain() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("BattleBoard");
				
		try{
			game = new Game();
		}catch(Exception e){
			game.createItemStore("Item Store");
		}
		
		
		//Painel que contem os botoes
		panel = new JPanel();
		panel.setBounds(47, 94, 370, 118);
		panel.setLayout(null);
		
		/**BOTOES*/
		
		//botao de play
		playButton = new JButton("Play");
		playButton.setBounds(54, 77, 327, 23);
		panel.add(playButton);
		
		//Criar item
		createItemButton = new JButton("Item Store Editor");
		createItemButton.setBounds(54, 130, 327, 23);
		panel.add(createItemButton);
			
		//Sair
		exitButton = new JButton("Exit");
		exitButton.setBounds(54, 180, 327, 23);
		panel.add(exitButton);
		
		/**LABEL*/
		JLabel Names = new JLabel("Developed by Bruno Orlandi and Vinicius Lovato");
		Names.setBounds(147, 261, 248, 14);
		panel.add(Names);
		
		//Painel que contem a imagem
		String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		System.out.println(path+"imagem.jpg");
		
		try {
			imagePanel = new ImageJPanel(path+"Sword.jpg");
		
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error loading background image:"+path+"Sword.jpg", "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
		getContentPane().add(imagePanel, BorderLayout.CENTER);
		getContentPane().add(panel, BorderLayout.SOUTH);
		imagePanel.setPreferredSize(new Dimension(500, 100));
		panel.setPreferredSize(new Dimension(300,300));
		//setContentPane(imagePanel);
		//setContentPane(panel);
		
		/**LISTENERS*/
		
		//listener do botao sair
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//listener do botao de cria��o de item
		createItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JItemEditor jItemEditor = new JItemEditor(game.mItemStore);
				jItemEditor.setVisible(true);	
				
				if(jItemEditor.isVisible() == false){
					jItemEditor.dispose();
				}
			}
		});
				
		//listener do botao de jogar
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String name = JOptionPane.showInputDialog("Player One name:");
				String teamName = JOptionPane.showInputDialog(name +" team name:");

				if(name != null){
					game.setPlayerOne(name, teamName, Color.RED);
				}

				name = JOptionPane.showInputDialog("Player Two name:");
				teamName = JOptionPane.showInputDialog(name +" team name:");
				if(name != null){
					game.setPlayerTwo(name, teamName, Color.BLUE);
					JTeamEditor teamEditor =  new JTeamEditor(game);
					teamEditor.setVisible(true);
				}
			}
		});

	}
}
