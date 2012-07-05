package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import BattleBoardExceptions.DeadCharacterException;
import BattleBoardExceptions.EmptyBoardPositionException;
import BattleBoardExceptions.OccupiedBoardPositionException;
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
	private JLabel titleLabel;
	
	private JButton firstSelectionButton;
	private JButton secondSelectionButton;
	
	private Character firstSelectionCharacter;
	private Character secondSelectionCharacter;
	
	private JButton moveUp;
	private JButton moveDown;
	private JButton moveLeft;
	private JButton moveRight;
	
	private JButton useConsumableButton;
	
	private JTable boardTable;
	private  DefaultTableModel tableModel;
	
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
			JOptionPane.showMessageDialog(null, "Image not found: "+path+"grass-background.jpg", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		//Modelo da tabela
		tableModel = new DefaultTableModel();
		tableModel.setRowCount(10);
		tableModel.setColumnCount(10);
		boardTable.setModel(tableModel);
		imagePane.add(boardTable);
		
		//Preenche jogadores na tabela visual
		initTable(game);
		
		//Rotulo
		logLabel = new JLabel("Events History:");
		logLabel.setBounds(400, 330, 150, 20);
		logLabel.setForeground(Color.WHITE);
		imagePane.add(logLabel);
		
		//Rotulo
		titleLabel = new JLabel ("Board");
		titleLabel.setBounds(360, 25, 100, 20);
		titleLabel.setForeground(Color.BLACK);
		imagePane.add(titleLabel);
		
		//Modelo da lista de log
		listModel = new DefaultListModel();
		
		//Lista de log
		logList = new JList(listModel);
		logList.setBounds(270, 360, 450, 100);
		
		//Scroll do log
		scrollPane = new JScrollPane(logList);
		scrollPane.setBounds(270, 360, 450, 100);
		scrollPane.setWheelScrollingEnabled(true);
		imagePane.add(scrollPane);
		
		//botao de atacar
		attackButton = new JButton("Attack!");
		attackButton.setBounds(80, 350, 120, 20);
		imagePane.add(attackButton);
		
		useConsumableButton = new JButton("Use consumable");
		useConsumableButton.setBounds(80, 400, 140, 20);
		imagePane.add(useConsumableButton);
		
		/**Botoes direcionais*/
		
		moveUp = new JButton("Up");
		moveUp.setBounds(500, 250, 100, 20);
		imagePane.add(moveUp);
		
		moveDown = new JButton("Down");
		moveDown.setBounds(500, 300, 100, 20);
		imagePane.add(moveDown);
		
		moveLeft = new JButton("Left");
		moveLeft.setBounds(400, 275, 100, 20);
		imagePane.add(moveLeft);
		
		moveRight = new JButton("Right");
		moveRight.setBounds(600, 275, 100, 20);
		imagePane.add(moveRight);
		
		//Botao de personagem selecionado (atacante)
		firstSelectionButton = new JButton("Attacker");
		firstSelectionButton.setBounds(80, 275, 100, 20);
		imagePane.add(firstSelectionButton);
		
		//Botao de personagem selecionado (defensor)
		secondSelectionButton = new JButton("Victim");
		secondSelectionButton.setBounds(200, 275, 100, 20);
		imagePane.add(secondSelectionButton);
		
		//Atacante selecionado
		firstSelection = new JTextField();
		firstSelection.setBounds(80, 250, 100, 20);
		imagePane.add(firstSelection);
		
		//Defensor selecionado
		secondSelection = new JTextField();
		secondSelection.setBounds(200, 250, 100, 20);
		imagePane.add(secondSelection);
		
		JOptionPane.showMessageDialog(null, "To attack and use items, fix two characters to make the action.");
		
		//Move pra cima no tabuleiro
		moveUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				//Pega posição atual
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				
				try {
					
					//Tenta mover o personagem, se nao conseguir, trata possiveis exceções
					game.mBoard.moveUp(column, row);
					tableModel.setValueAt(tableModel.getValueAt(row, column), row-1, column);
					tableModel.setValueAt(null, row, column);
					boardTable.setColumnSelectionInterval(column, column);
					boardTable.setRowSelectionInterval(row-1, row-1);
						
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		//Move pra baixo
		moveDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//pega posição atual
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
								
				try {
					//tenta mover personagem, se nao conseguir trata as exceções
					game.mBoard.moveDown(column, row);
					tableModel.setValueAt(tableModel.getValueAt(row, column), row+1, column);
					tableModel.setValueAt(null, row, column);
					boardTable.setColumnSelectionInterval(column, column);
					boardTable.setRowSelectionInterval(row+1, row+1);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		
		//Move pra Esquerda
		moveLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				
				try {
					game.mBoard.moveLeft(column, row);
					tableModel.setValueAt(tableModel.getValueAt(row, column), row, column-1);
					tableModel.setValueAt(null, row, column);
					boardTable.setColumnSelectionInterval(column-1, column-1);
					boardTable.setRowSelectionInterval(row, row);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				
			}
		});
		
		//Move pra direita
		moveRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int column = boardTable.getSelectedColumn();
				int row = boardTable.getSelectedRow();
				
				try {
					game.mBoard.moveRight(column, row);
					
					tableModel.setValueAt(tableModel.getValueAt(row, column), row, column+1);
					tableModel.setValueAt(null, row, column);
					boardTable.setColumnSelectionInterval(column+1, column+1);
					boardTable.setRowSelectionInterval(row, row);
					
				}  catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
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
					JOptionPane.showMessageDialog(null, "The selected position is empty.\nSelect a character on the Board.");
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
					JOptionPane.showMessageDialog(null, "The selected position is empty.\nSelect a character on the Board.");
					e.printStackTrace();
				}
				
				
			}
		});
		
		//Botao de ataque
		attackButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				try {
					
					//Metodo de ataque retorna um pair, no qual primeiro valor é se foi qualidade do ataque e segundo valor o total de dano causado
					Pair<Integer, Integer> damage = game.attackCharacter(firstSelectionCharacter, secondSelectionCharacter);
					
					//verifica tipo do ataque
					String attackT = "";
					int attackType = damage.getFirst();
					if(attackType == Character.MISS_ATTACK){
						attackT = " MISS ";
					}
					else if(attackType == Character.CRITICAL_ATTACK){
						attackT = " CRITICAL attacks ";
					}
					else{
						attackT = " attacks ";
					}
					
					StringBuilder sb = new StringBuilder();
					sb.append(firstSelectionCharacter.getName() + attackT + secondSelectionCharacter.getName());
					if(attackType != 1)
					{
						sb.append(". Damage: " + damage.getSecond());
					}
					
					//Imprime da lista de log
					listModel.addElement(sb.toString());
					
					//verifica se o jogador defensor foi morto
					if(attackType == Character.KILL_ATTACK){
						listModel.addElement(secondSelectionCharacter.getName() + " was killed by "+ firstSelectionCharacter.getName());
					}
					
					//Move barra da log lis
					SwingUtilities.invokeLater(new Runnable() {  
                        public void run() {  
                            JScrollBar bar = scrollPane.getVerticalScrollBar();  
                            bar.setValue(bar.getMaximum());  
                        }  
                    });  
					
					//Se foi um ataque que matou o ultimo pesonagme vivo do outro time
					if(attackType == Character.WIN_ATTACK){
						JOptionPane.showMessageDialog(null, game.mBoard.getTeam(firstSelectionCharacter.getColor()).getName()+"'s "+firstSelectionCharacter.getColor()+"Team wons the Battle!");
						setVisible(false);
					}
					
					
						
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		//Botao do item consumivel
		useConsumableButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					firstSelectionCharacter.useConsumable(secondSelectionCharacter,  game.mBoard.getDistance(firstSelectionCharacter, secondSelectionCharacter));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
			
		});
	}
	
	public void initTable(Game game){
			
		for(int i = 0; i < game.mJ1.getTeam().numCharacter(); i++){
			tableModel.setValueAt(game.mJ1.getTeam().getCharacter(i), i, 0);
			
			try {
				game.mBoard.setCharacterPosition(0, i, game.mJ1.getTeam().getCharacter(i));
			} catch (OccupiedBoardPositionException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < game.mJ2.getTeam().numCharacter(); i++){
			tableModel.setValueAt(game.mJ2.getTeam().getCharacter(i), i, 9);
			
			try {
				game.mBoard.setCharacterPosition(9, i, game.mJ2.getTeam().getCharacter(i));
			} catch (OccupiedBoardPositionException e) {
				e.printStackTrace();
			}
		}
	}
}
