package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BattleBoardExceptions.CharacterFromSameTeamException;
import BattleBoardExceptions.CharacterNotFoundOnBoardException;
import BattleBoardExceptions.DeadCharacterException;
import BattleBoardExceptions.EmptyBoardPositionException;
import BattleBoardExceptions.OccupiedBoardPositionException;
import BattleBoardExceptions.OutOfRangeCharacterException;
import Character.*;
import Character.Character;
import Game.Game;
import Overview.Board;
import Utilities.Pair;

public class JBoard extends JDialog{
	
	private JScrollPane scrollPane;
	
	private DefaultListModel listModel;
	private JList logList;
	
	private ImageJPanel imagePane;
	private JPanel contentPane;
	
	private JButton attackButton;
	
	private JTextField firstSelection;
	private JTextField secondSelection;

	private JLabel logLabel;
	
	private JButton firstSelectionButton;
	private JButton secondSelectionButton;
	
	private Character firstSelectionCharacter;
	private Character secondSelectionCharacter;
	
	private JButton moveUp;
	private JButton moveDown;
	private JButton moveLeft;
	private JButton moveRight;
	
	private JTable boardTable;
	
	public JBoard(final Game game)
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
		
		//TODO fazer Exception de OutOfBounds da board
		
//		Ranger r2 = (Ranger) tableModel.getValueAt(5, 5);
//		System.out.println(r2);
		logLabel = new JLabel("Histórico");
		logLabel.setBounds(350, 330, 100, 20);
		logLabel.setForeground(Color.WHITE);
		imagePane.add(logLabel);
		
		
		listModel = new DefaultListModel();
		logList = new JList(listModel);
		logList.setBounds(270, 360, 400, 100);
		scrollPane = new JScrollPane(logList);
		scrollPane.setBounds(270, 360, 400, 100);
		scrollPane.setWheelScrollingEnabled(true);
		imagePane.add(scrollPane);
		
	

		
		//botao de atacar
		attackButton = new JButton("Atacar!");
		attackButton.setBounds(80, 350, 120, 20);
		imagePane.add(attackButton);
		
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
		
		firstSelectionButton = new JButton("Fixar");
		firstSelectionButton.setBounds(80, 275, 100, 20);
		imagePane.add(firstSelectionButton);
		
		secondSelectionButton = new JButton("Fixar");
		secondSelectionButton.setBounds(200, 275, 100, 20);
		imagePane.add(secondSelectionButton);
		
		
		firstSelection = new JTextField();
		firstSelection.setBounds(80, 250, 100, 20);
		imagePane.add(firstSelection);
		
		secondSelection = new JTextField();
		secondSelection.setBounds(200, 250, 100, 20);
		imagePane.add(secondSelection);
		
		Ranger r = new Ranger("Vinicius", 20);			
		Fighter f = new Fighter("Aehoo", 20);
		
		game.mJ1.getTeam().addCharacter(r);
		game.mJ2.getTeam().addCharacter(f);
		
		try {
			
			game.mBoard.setCharacterPosition(7, 7, r);
			tableModel.setValueAt(r, 7, 7);
		} catch (OccupiedBoardPositionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			game.mBoard.setCharacterPosition(9, 9, f);
			tableModel.setValueAt(f, 9, 9);
		} catch (OccupiedBoardPositionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//TODO criar função na board que move personagem
		
		//Move pra cima no tabuleiro
		moveUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				
					
				try {
					game.mBoard.moveUp(column, row);
					
					tableModel.setValueAt(tableModel.getValueAt(row, column), row-1, column);
					tableModel.setValueAt(null, row, column);
					boardTable.setColumnSelectionInterval(column, column);
					boardTable.setRowSelectionInterval(row-1, row-1);
					
				}catch (OccupiedBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição ocupada");
					e.printStackTrace();
				}catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "Posição fora do tabuleiro");
					e.printStackTrace();
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
		

		
		
		//Fixar primeiro personagem
		firstSelectionButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				//Pega posicao selecionada na tabela
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				
				//adiciona ao primeiro personagem escolhido
				try {
					firstSelectionCharacter = game.mBoard.getCharacter(column, row);
					firstSelection.setText(firstSelectionCharacter.getName());
				} catch (EmptyBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Escolha um personagem para mover!");
					e.printStackTrace();
				}
				
			}
		});		
		
		//Fixar segundo personagem do tabuleiro
		secondSelectionButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				//Pega posição selecionada na tabela
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();			
				
				//adiciona ao segundo personagem escolhido
				try {
					secondSelectionCharacter = game.mBoard.getCharacter(column, row);
					secondSelection.setText(secondSelectionCharacter.getName());
				} catch (EmptyBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Escolha uma posição com personagem!");
					e.printStackTrace();
				}
				
				
			}
		});
		
		attackButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				try {
					Pair<Integer, Integer> damage = firstSelectionCharacter.attackCharacter(secondSelectionCharacter, game.mBoard.getDistance(firstSelectionCharacter, secondSelectionCharacter));
					
					System.out.println("Passei aqui!");
					
					listModel.addElement(firstSelectionCharacter.getName() + "Atacou" + secondSelectionCharacter.getName() + "| Dano causado: " + 
					damage.getSecond() + "HP restante: " + secondSelectionCharacter.getHP());
					
					SwingUtilities.invokeLater(new Runnable() {  
                        public void run() {  
                            JScrollBar bar = scrollPane.getVerticalScrollBar();  
                            bar.setValue(bar.getMaximum());  
                        }  
                    });  
					
					
				} catch (DeadCharacterException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (CharacterFromSameTeamException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (OutOfRangeCharacterException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (CharacterNotFoundOnBoardException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				
				
			}
		});
	}
	
	public void refreshBoardTable(Board b){
        
		//TODO metodo que retorna as boardposition que contem personagem
		//percorrer e colocar na tabela visual
	}
}
