package UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;

import Game.Game;
import Overview.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class JMain extends JFrame {

	private JButton playButton;
	private JButton createItemButton;
	private JButton exitButton;
	
	private JPanel panel;
	private ImageJPanel imagePanel;
	
	//private ItemStore itemStore;
	
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
		setTitle("The Ultimate Board Game - aeho!");
		
		//itemStore = new ItemStore("Loja");
		
		try{
			game = new Game();
		}catch(Exception e){
			game.createItemStore("Loja");
		}
		
		
		//Painel que contem os botoes
		panel = new JPanel();
		panel.setBounds(47, 94, 370, 118);
		panel.setLayout(null);
		
		/**BOTOES*/
		
		//botao de play
		playButton = new JButton("Jogar");
		playButton.setBounds(54, 77, 327, 23);
		panel.add(playButton);
		
		//Criar item
		createItemButton = new JButton("Edição de Itens");
		createItemButton.setBounds(54, 173, 327, 23);
		panel.add(createItemButton);
			
		//Sair
		exitButton = new JButton("Sair");
		exitButton.setBounds(54, 223, 327, 23);
		panel.add(exitButton);
		
		/**LABEL*/
		JLabel Names = new JLabel("Feito por Bruno Orlandi e Vinicius Lovato");
		Names.setBounds(147, 261, 248, 14);
		panel.add(Names);
		
		//Painel que contem a imagem
		String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		System.out.println(path+"imagem.jpg");
		
		try {
			imagePanel = new ImageJPanel(path+"sword.jpg");
		
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar imagem de fundo", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		//listener do botao de criação de item
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
				
				String name = JOptionPane.showInputDialog("Digite o nome do jogador 1");
				game.setPlayerOne(name, "Alpha", Color.Red);
				name = JOptionPane.showInputDialog("Digite o nome do jogador 2");
				game.setPlayerTwo(name, "Bravo", Color.Blue);
				
				JTeamEditor teamEditor =  new JTeamEditor(game);
				teamEditor.setVisible(true);
		
				//Board board = new Board();
				//board.setVisible(true);
			}
		});

	}
}
