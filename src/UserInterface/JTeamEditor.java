package UserInterface;

import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Character.Fighter;
import Game.Game;
import Item.ItemStore;
import Overview.Team;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class JTeamEditor extends JDialog {
	
	//Painel principal
	private JPanel contentPane;
	 
	//painel capaz de carregar uma imagem
    private ImageJPanel backgroundImagePane;
    
    private JList characterList;
    
    //Lista que contera personagens de cada time
    private JList leftList;
    private JList rightList;
    
    //Modelo de cada lista
    private DefaultListModel leftModel;
    private DefaultListModel rightModel;
    private DefaultListModel characterModel;
    
    //Scroll de cada lista
    private JScrollPane leftScroll;
    private JScrollPane rightScroll;
    private JScrollPane characterListScroll;
    
    //Botoes para adicionar e remover personagens
    private JButton leftAddCharacterButton;
    private JButton leftRemoveCharacterButton;
    private JButton rightAddCharacterButton;
    private JButton rightRemoveCharacterButton;
    
    private JButton playButton;
    private JButton returnButton;
    
    private JButton newCharacterTableButton;
    private JButton removeCharacterTableButton;
    
    private JButton leftBuyItem;
    private JButton rightBuyItem;
    
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JLabel centerLabel;
    private JLabel leftMoneyLabel;
    private JLabel rightMoneyLabel;
    private JLabel leftMoneyValueLabel;
    private JLabel rightMoneyValueLabel;
    
    private Team alpha;
    private Team bravo;
    
    private Team allCharacters;
 
    /**
     * Construtor
     */
	public JTeamEditor(final Game game) {
		
		//Algumas inicizalicaçoes
		setBounds(100, 100, 900, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		
		alpha = new Team("Alpha", Overview.Color.Red);
		bravo = new Team("Bravo", Overview.Color.Blue);
		
		allCharacters = new Team("All", Overview.Color.White);
		
		//Panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Caminho da imagem que sera carregada
		final String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		
		//Instancia Panel que contem imagem
		try{
			backgroundImagePane = new ImageJPanel(path+"background.jpg");
			backgroundImagePane.setBounds(0, 0, 900, 600);
		} catch(IOException e){
			e.printStackTrace();
		}	
		
		//adiciona sobre o panel principal
		contentPane.setLayout(null);
		backgroundImagePane.setLayout(null);
		getContentPane().add(backgroundImagePane);
		
		characterModel = new DefaultListModel();
		characterList = new JList(characterModel);
		
		characterList.setBounds(100, 40, 700, 200);
		characterList.setLayout(new CardLayout(0, 0));
		characterListScroll =  new JScrollPane(characterList);
		characterListScroll.setBounds(100, 40, 700, 200);
		backgroundImagePane.add(characterListScroll);
		
		//Adcionova painel de personagens do time 1 (lado esquerdo)
		leftModel = new DefaultListModel();
		leftList = new JList(leftModel);
		leftScroll = new JScrollPane(leftList);
		leftScroll.setBounds(20, 300, 200, 250);
		backgroundImagePane.add(leftScroll);
		
		//adiciona painel de personagens do time 2 (lado direito)
		rightModel = new DefaultListModel();
		rightList = new JList(rightModel);
		rightScroll = new JScrollPane(rightList);
		rightScroll.setBounds(670, 300, 200, 250);
		backgroundImagePane.add(rightScroll);
		
		//Botoes de adicioanar e remover personagem do time 1 (lado esquerdo)
		leftAddCharacterButton = new JButton("Adicionar");
		leftAddCharacterButton.setBounds(240, 350, 100, 20);
		backgroundImagePane.add(leftAddCharacterButton);
		
		leftRemoveCharacterButton = new JButton("Remover");
		leftRemoveCharacterButton.setBounds(240, 400, 100, 20);
		backgroundImagePane.add(leftRemoveCharacterButton);
		
		//Botoes de adicionar e remover personagens do time 2 (lado direito)
		rightAddCharacterButton = new JButton("Adicionar");
		rightAddCharacterButton.setBounds(550, 350, 100, 20);
		backgroundImagePane.add(rightAddCharacterButton);
		
		rightRemoveCharacterButton = new JButton("Remover");
		rightRemoveCharacterButton.setBounds(550, 400, 100, 20);
		backgroundImagePane.add(rightRemoveCharacterButton);
		
		
		//Botao jogar no centro da tela
		playButton = new JButton("Jogar!");
		playButton.setBounds(400, 350, 100, 60);
		backgroundImagePane.add(playButton);
		
		//botao voltar na parte inferior da tela
		returnButton = new JButton("Voltar");
		returnButton.setBounds(400, 530, 100, 20);
		backgroundImagePane.add(returnButton);
		
		//botao de criar novo personagem (que ficara disponivel para ser colocado no time)
		newCharacterTableButton = new JButton("Criar Novo Personagem");
		newCharacterTableButton.setBounds(230, 250, 180, 20);
		backgroundImagePane.add(newCharacterTableButton);
		
		//botao de apagar personagem permanentemente da lista
		removeCharacterTableButton = new JButton("Apagar personagem");
		removeCharacterTableButton.setBounds(480, 250, 180, 20);
		backgroundImagePane.add(removeCharacterTableButton);
		
		//Label
		centerLabel = new JLabel("Personagens Disponíveis");
		centerLabel.setBounds(370, 15, 200, 20);
		centerLabel.setForeground(Color.WHITE);
		backgroundImagePane.add(centerLabel);
		
		//Label time 1
		leftLabel = new JLabel("Time Alpha");
		leftLabel.setBounds(80, 270, 100, 20);
		leftLabel.setForeground(Color.RED);
		backgroundImagePane.add(leftLabel);
		
		//Label time 2
		rightLabel = new JLabel("Time Bravo");
		rightLabel.setBounds(740, 270, 100, 20);
		rightLabel.setForeground(Color.BLUE);
		backgroundImagePane.add(rightLabel);
		
		leftMoneyLabel = new JLabel("Dinheiro Restante:");
		leftMoneyLabel.setBounds(240, 450, 120, 20);
		leftMoneyLabel.setForeground(Color.ORANGE);
		backgroundImagePane.add(leftMoneyLabel);
		
		leftMoneyValueLabel = new JLabel("10000");
		leftMoneyValueLabel.setBounds(275, 470, 120, 20);
		leftMoneyValueLabel.setForeground(Color.GREEN);
		backgroundImagePane.add(leftMoneyValueLabel);
		
		rightMoneyLabel = new JLabel("Dinheiro Restante:");
		rightMoneyLabel.setBounds(550, 450, 120, 20);
		rightMoneyLabel.setForeground(Color.ORANGE);
		backgroundImagePane.add(rightMoneyLabel);
		
		rightMoneyValueLabel = new JLabel("10000");
		rightMoneyValueLabel.setBounds(585, 470, 120, 20);
		rightMoneyValueLabel.setForeground(Color.GREEN);
		backgroundImagePane.add(rightMoneyValueLabel);
		
		leftBuyItem = new JButton("Comprar itens");
		leftBuyItem.setBounds(230, 500, 120, 20);
		backgroundImagePane.add(leftBuyItem);
		
		rightBuyItem = new JButton("Comprar itens");
		rightBuyItem.setBounds(540, 500, 120, 20);
		backgroundImagePane.add(rightBuyItem);

		//Adicionar novo personagem
		newCharacterTableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event){
				
				JCharacterEditor characterEditor = new JCharacterEditor(allCharacters);
				characterEditor.setVisible(true);
				
				if(characterEditor.isVisible() == false){
					refreshCharacterList(allCharacters);
					characterEditor.dispose();
				}
			}
		});
		
		//Remove da lista de disponiveis (apaga permantentemente)
		removeCharacterTableButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				int pos = characterList.getSelectedIndex();
				characterModel.remove(pos);
				allCharacters.removeCharacter(pos);
				
			}
		});
		
		//Adicionar personagem ao time alpha
		leftAddCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				//Pega posicoa na lista de disponiveis
				int pos = characterList.getSelectedIndex();
				
				//Adiciona na tabela do time alpha
				leftModel.addElement(allCharacters.getCharacter(pos));
				
				//Remove da tabela de disponiveis
				characterModel.remove(pos);
				
				//adiciona ao time alpha
				game.mJ1.getTeam().addCharacter(allCharacters.getCharacter(pos));
				allCharacters.removeCharacter(pos);
				
			}
		});
		
		//Remove character do time alpha
		leftRemoveCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				//Pega posicao para remover
				int pos = leftList.getSelectedIndex();
				
				//Adiciona de volta a lista de disponiveis
				characterModel.addElement(game.mJ1.getTeam().getCharacter(pos));
				
				//remove da tabela da esquerda
				leftModel.remove(pos);
				
				allCharacters.addCharacter(game.mJ1.getTeam().getCharacter(pos));
				game.mJ1.getTeam().removeCharacter(pos);
			}
		});
		
		//Adiciona personagem no time Bravo
		rightAddCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){

				//Pega posicoa na lista de disponiveis
				int pos = characterList.getSelectedIndex();
				
				//Adiciona na tabela do time bravo
				rightModel.addElement(allCharacters.getCharacter(pos));
				
				//Remove da tabela de disponiveis
				characterModel.remove(pos);
				
				//adiciona ai time bravo
				game.mJ2.getTeam().addCharacter(allCharacters.getCharacter(pos));
				allCharacters.removeCharacter(pos);		
			}
		});
		
		//Remove character do time bravo
		rightRemoveCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				//Pega posicao para remover
				int pos = rightList.getSelectedIndex();
				
				//Adiciona de volta a lista de disponiveis
				characterModel.addElement(game.mJ2.getTeam().getCharacter(pos));
				
				//remove da tabela da esquerda
				rightModel.remove(pos);
				
				allCharacters.addCharacter(game.mJ2.getTeam().getCharacter(pos));
				game.mJ2.getTeam().removeCharacter(pos);	}
		});
		
		//botao jogar!
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JBoard board = new JBoard(game);
				setVisible(false);
				board.setVisible(true);
				
			}
		});
		
		//botao voltar
		returnButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
			}
		});
		
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == leftBuyItem){
					JAddItem jadd = new JAddItem(game.mJ1.getTeam(), game.mItemStore);
					jadd.setVisible(true);
				}
				else if (event.getSource() == rightBuyItem){
					JAddItem jadd = new JAddItem(game.mJ2.getTeam(), game.mItemStore);
					jadd.setVisible(true);
				}
				
			}
		};
		
		leftBuyItem.addActionListener(listener);
		rightBuyItem.addActionListener(listener);
	}
	
	//Atualiza item dos personagns disponiveis
	public void refreshCharacterList(Team allCharacters){
		characterModel.removeAllElements();
		
		for(int i = 0; i <allCharacters.numCharacter(); i++){
				characterModel.addElement(allCharacters.getCharacter(i).toString());		
		}	
	}
	
	
}
