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

import BattleBoardExceptions.CharacterCanNotConsumeItemException;
import BattleBoardExceptions.CharacterFromSameTeamException;
import BattleBoardExceptions.CharacterNotFoundOnBoardException;
import BattleBoardExceptions.DeadCharacterException;
import BattleBoardExceptions.EmptyBoardPositionException;
import BattleBoardExceptions.ItemNotFoundException;
import BattleBoardExceptions.OccupiedBoardPositionException;
import BattleBoardExceptions.OpposingTeamCharacterException;
import BattleBoardExceptions.OutOfRangeCharacterException;
import Character.*;
import Character.Character;
import Game.Game;
import Overview.Board;
import Overview.GameColor;
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
		
		//Modelo da tabela
		tableModel = new DefaultTableModel();
		tableModel.setRowCount(10);
		tableModel.setColumnCount(10);
		boardTable.setModel(tableModel);
		imagePane.add(boardTable);
		
		//Preenche jogadores na tabela visual
		initTable(game);
		
		//Rotulo
		logLabel = new JLabel("Histórico de eventos");
		logLabel.setBounds(400, 330, 150, 20);
		logLabel.setForeground(Color.WHITE);
		imagePane.add(logLabel);
		
		//Rotulo
		titleLabel = new JLabel ("Tabuleiro");
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
		attackButton = new JButton("Atacar!");
		attackButton.setBounds(80, 350, 120, 20);
		imagePane.add(attackButton);
		
		useConsumableButton = new JButton("Usar consumivel");
		useConsumableButton.setBounds(80, 400, 140, 20);
		imagePane.add(useConsumableButton);
		
		/**Botoes direcionais*/
		
		moveUp = new JButton("Cima");
		moveUp.setBounds(500, 250, 100, 20);
		imagePane.add(moveUp);
		
		moveDown = new JButton("Baixo");
		moveDown.setBounds(500, 300, 100, 20);
		imagePane.add(moveDown);
		
		moveLeft = new JButton("Esquerda");
		moveLeft.setBounds(400, 275, 100, 20);
		imagePane.add(moveLeft);
		
		moveRight = new JButton("Direita");
		moveRight.setBounds(600, 275, 100, 20);
		imagePane.add(moveRight);
		
		//Botao de personagem selecionado (atacante)
		firstSelectionButton = new JButton("Atacante");
		firstSelectionButton.setBounds(80, 275, 100, 20);
		imagePane.add(firstSelectionButton);
		
		//Botao de personagem selecionado (defensor)
		secondSelectionButton = new JButton("Defensor");
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
		
		JOptionPane.showMessageDialog(null, "Para atacar e usar itens, fixe um personagem em Atacante e outro em Defensor");
		
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
						
				} catch (OccupiedBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição ja esta ocupada!");
					e.printStackTrace();
				} catch (EmptyBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição atual esta vazia!");
					e.printStackTrace();
				} catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "Posição fora do tabuleiro");
					e.printStackTrace();
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
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Posição fora do tabuleiro");
					e.printStackTrace();
				} catch (OccupiedBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição ja esta ocupada!");
					e.printStackTrace();
				} catch (EmptyBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição atual esta vazia!");
					e.printStackTrace();
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
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Posição fora do tabuleiro");
					e.printStackTrace();
				} catch (OccupiedBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição ja esta ocupada!");
					e.printStackTrace();
				} catch (EmptyBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição atual esta vazia!");
					e.printStackTrace();
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
					
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Posição fora do tabuleiro");
					e.printStackTrace();
				} catch (OccupiedBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição ja esta ocupada!");
					e.printStackTrace();
				} catch (EmptyBoardPositionException e) {
					JOptionPane.showMessageDialog(null, "Posição atual esta vazia!");
					e.printStackTrace();
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
					JOptionPane.showMessageDialog(null, "Escolha uma posição com personagem!");
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
		
		//Botao de ataque
		attackButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				try {
					
					//Metodo de ataque retorna um pair, no qual primeiro valor é se foi qualidade do ataque e segundo valor o total de dano causado
					Pair<Integer, Integer> damage = game.attackCharacter(firstSelectionCharacter, secondSelectionCharacter);
					
					//verifica tipo do ataque
					String attackT = "";
					int attackType = damage.getFirst();
					if(attackType == 1){
						attackT = "MISS! ";
					}
					else if(attackType == 2){
						attackT = "CRITICAL HIT! ";
					}
					
					//Imprime da lista de log
					listModel.addElement(attackT + firstSelectionCharacter.getName() + " atacou " + secondSelectionCharacter.getName() + " e causou " + 
					damage.getSecond() + " de dano. HP restante: " + secondSelectionCharacter.getHP());
					
					//verifica se o jogador defensor foi morto
					if(secondSelectionCharacter.isDead()){
						listModel.addElement(secondSelectionCharacter.getName() + " foi morto em combate!");
					}
					
					//Move barra da log lis
					SwingUtilities.invokeLater(new Runnable() {  
                        public void run() {  
                            JScrollBar bar = scrollPane.getVerticalScrollBar();  
                            bar.setValue(bar.getMaximum());  
                        }  
                    });  
					
					//Se foi um ataque que matou o ultimo pesonagme vivo do outro time
					if(attackType == 4){
						JOptionPane.showMessageDialog(null, "Jogador " + firstSelectionCharacter.getColor() + " ganhou!");
						setVisible(false);
					}
					
					
						
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
		
		//Botao do item consumivel
		useConsumableButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					firstSelectionCharacter.useConsumable(secondSelectionCharacter,  game.mBoard.getDistance(firstSelectionCharacter, secondSelectionCharacter));
				} catch (CharacterCanNotConsumeItemException e1) {
					JOptionPane.showMessageDialog(null, "Personagem alvo nao pode usar o item");
					e1.printStackTrace();
				} catch (DeadCharacterException e1) {
					JOptionPane.showMessageDialog(null, "Personagem alvo esta morto!");
					e1.printStackTrace();
				} catch (OpposingTeamCharacterException e1) {
					JOptionPane.showMessageDialog(null, "Personagem alvo é de outro time!");
					e1.printStackTrace();
				} catch (OutOfRangeCharacterException e1) {
					JOptionPane.showMessageDialog(null, "Personagem alvo esta fora do alcance");
					e1.printStackTrace();
				} catch (CharacterNotFoundOnBoardException e1) {
					JOptionPane.showMessageDialog(null, "Não ha personagem alvo!");
					e1.printStackTrace();
				} catch (ItemNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Não ha item para ser usado!");
					e1.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < game.mJ2.getTeam().numCharacter(); i++){
			tableModel.setValueAt(game.mJ2.getTeam().getCharacter(i), i, 9);
			
			try {
				game.mBoard.setCharacterPosition(9, i, game.mJ2.getTeam().getCharacter(i));
			} catch (OccupiedBoardPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
