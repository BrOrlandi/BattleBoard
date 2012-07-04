package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Character.Fighter;
import Character.Ranger;

public class JBoard extends JDialog{
	
	private JScrollPane scrollPane;
	
	private ImageJPanel imagePane;
	private JPanel contentPane;
	
	private JButton attackButton;
	private JButton moveButton;
	
	private JLabel boardNameLabel;
	private JLabel currentPlayer;
	
	private JLabel 
	
	private JButton moveUp;
	private JButton moveDown;
	private JButton moveLeft;
	private JButton moveRight;
	
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
		boardTable.setCellSelectionEnabled(true);
		
		final DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setRowCount(10);
		tableModel.setColumnCount(10);
		boardTable.setModel(tableModel);
		imagePane.add(boardTable);
		
		
		Ranger r = new Ranger("Vinicius", 20);
		tableModel.setValueAt(r, 5, 5);
		
		Fighter f = new Fighter("Aehoo", 20);
		tableModel.setValueAt(f, 9, 3);
		
//		Ranger r2 = (Ranger) tableModel.getValueAt(5, 5);
//		System.out.println(r2);
		

		
		//botao de atacar
		attackButton = new JButton("Atacar!");
		attackButton.setBounds(70, 300, 120, 20);
		imagePane.add(attackButton);
			
		currentPlayer = new JLabel("Jogador da vez: Alpha");
		currentPlayer.setForeground(Color.WHITE);
		currentPlayer.setBounds(300, 250, 150, 20);
		imagePane.add(currentPlayer);
		
		moveUp = new JButton("Cima");
		moveUp.setBounds(600, 250, 30, 20);
		imagePane.add(moveUp);
		
		moveDown = new JButton("Baixo");
		moveDown.setBounds(600, 300, 30, 20);
		imagePane.add(moveDown);
		
		moveLeft = new JButton("Es");
		moveLeft.setBounds(570, 275, 30, 20);
		imagePane.add(moveLeft);
		
		moveRight = new JButton("Dir");
		moveRight.setBounds(630, 275, 30, 20);
		imagePane.add(moveRight);
		
		//Move pra cima no tabuleiro
		moveUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				try{
					if(tableModel.getValueAt(row, column) != null && tableModel.getValueAt(row-1, column) == null){
						
							tableModel.setValueAt(tableModel.getValueAt(row, column), row-1, column);
							tableModel.setValueAt(null, row, column);
							boardTable.setColumnSelectionInterval(column, column);
							boardTable.setRowSelectionInterval(row-1, row-1);
					}
				}catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "Movimento invalido", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Move pra baixo
		moveDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				try{
					if(tableModel.getValueAt(row, column) != null && tableModel.getValueAt(row+1, column) == null){
						
							tableModel.setValueAt(tableModel.getValueAt(row, column), row+1, column);
							tableModel.setValueAt(null, row, column);
							boardTable.setColumnSelectionInterval(column, column);
							boardTable.setRowSelectionInterval(row+1, row+1);
						
					}
				}catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "Movimento invalido", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Move pra Esquerda
		moveLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				
				try{
					if(tableModel.getValueAt(row, column) != null && tableModel.getValueAt(row, column - 1) == null){
						
							tableModel.setValueAt(tableModel.getValueAt(row, column), row, column-1);
							tableModel.setValueAt(null, row, column);
							boardTable.setColumnSelectionInterval(column-1, column-1);
							boardTable.setRowSelectionInterval(row, row);
					}
				}catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "Movimento invalido", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		//Move pra direita
		moveRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				try{
					if(tableModel.getValueAt(row, column) != null  && tableModel.getValueAt(row, column +1) == null){
						
							tableModel.setValueAt(tableModel.getValueAt(row, column), row, column+1);
							tableModel.setValueAt(null, row, column);
							
							boardTable.setColumnSelectionInterval(column+1, column+1);
							boardTable.setRowSelectionInterval(row, row);
					}
				}catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "Movimento invalido", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
	}
}
