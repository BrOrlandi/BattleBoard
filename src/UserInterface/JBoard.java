package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Character.Ranger;

public class JBoard extends JDialog{
	
	private JScrollPane scrollPane;
	
	private ImageJPanel imagePane;
	private JPanel contentPane;
	
	private JButton attackButton;
	private JButton moveButton;
	
	private JLabel boardNameLabel;
	private JLabel currentPlayer;
	
	private JTable boardTable;
	
	public JBoard()
	{	
		setSize(new Dimension(800, 500));
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		
		//Painel que contem imagem de fundo
		try {
			imagePane = new ImageJPanel(path+"grass-background.jpg");
			imagePane.setBounds(0, 0, 800, 500);
			imagePane.setLayout(null);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Imagem nao encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		//Painel principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		//Adiciona painel com imagem de fundi
		getContentPane().add(imagePane);
		
		//Tabela com personagens
		boardTable = new JTable();
		
		boardTable.setBounds(80, 60, 650, 160);
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setRowCount(10);
		tableModel.setColumnCount(10);
		boardTable.setModel(tableModel);
		imagePane.add(boardTable);
//		Ranger r = new Ranger("Vinicius", 20);
//		tableModel.setValueAt(r, 5, 5);
//		
//		Ranger r2 = (Ranger) tableModel.getValueAt(5, 5);
//		System.out.println(r2);
//		

		
		//botao de atacar
		attackButton = new JButton("Atacar!");
		attackButton.setBounds(70, 300, 120, 20);
		imagePane.add(attackButton);
		
		//botao mover personagem
		moveButton = new JButton("Mover");
		moveButton.setBounds(220, 300, 120, 20);
		imagePane.add(moveButton);
		
		currentPlayer = new JLabel("Jogador da vez: Alpha");
		currentPlayer.setForeground(Color.WHITE);
		currentPlayer.setBounds(300, 250, 150, 20);
		imagePane.add(currentPlayer);
		
		
	}
}
