package UserInterface;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Character.*;
import Character.Character;
import Game.Game;
import Game.NotEnoughMoneyException;
import Item.ItemStore;

import Overview.GameColor;
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
    
    private JButton leftEditCharacter;
    private JButton rightEditCharacter;
    
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
    
    
    private ArrayList<Character> allCharacters;
 
    /**
     * Construtor
     */
	public JTeamEditor(final Game game) {
		
		//Algumas inicizalica�oes
		setBounds(100, 100, 900, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		
		allCharacters = new ArrayList<Character>();
		
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
		leftAddCharacterButton.setBounds(240, 370, 100, 20);
		backgroundImagePane.add(leftAddCharacterButton);
		
		leftRemoveCharacterButton = new JButton("Remover");
		leftRemoveCharacterButton.setBounds(240, 400, 100, 20);
		backgroundImagePane.add(leftRemoveCharacterButton);
		
		//Botoes de adicionar e remover personagens do time 2 (lado direito)
		rightAddCharacterButton = new JButton("Adicionar");
		rightAddCharacterButton.setBounds(550, 370, 100, 20);
		backgroundImagePane.add(rightAddCharacterButton);
		
		rightRemoveCharacterButton = new JButton("Remover");
		rightRemoveCharacterButton.setBounds(550, 400, 100, 20);
		backgroundImagePane.add(rightRemoveCharacterButton);
		
		leftEditCharacter = new JButton("Editar personagem");
		leftEditCharacter.setBounds(240, 340, 145, 20);
		backgroundImagePane.add(leftEditCharacter);
		
		rightEditCharacter = new JButton("Editar personagem");
		rightEditCharacter.setBounds(505, 340, 145, 20);
		backgroundImagePane.add(rightEditCharacter);
		
		//Botao jogar no centro da tela
		playButton = new JButton("Jogar!");
		playButton.setBounds(395, 350, 100, 60);
		backgroundImagePane.add(playButton);
		
		//botao voltar na parte inferior da tela
		returnButton = new JButton("Voltar");
		returnButton.setBounds(395, 530, 100, 20);
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
		centerLabel = new JLabel("Personagens Dispon�veis - Mil dinheiros cada!");
		centerLabel.setBounds(330, 15, 280, 20);
		centerLabel.setForeground(Color.WHITE);
		backgroundImagePane.add(centerLabel);
		
		//Label time 1
		leftLabel = new JLabel("Time Alpha (" + game.mJ1.getName() + ")");
		leftLabel.setBounds(60, 270, 120, 20);
		leftLabel.setForeground(Color.RED);
		backgroundImagePane.add(leftLabel);
		
		//Label time 2
		rightLabel = new JLabel("Time Bravo (" + game.mJ2.getName() + ")");
		rightLabel.setBounds(720, 270, 140, 20);
		rightLabel.setForeground(Color.BLUE);
		backgroundImagePane.add(rightLabel);
		
		leftMoneyLabel = new JLabel("Dinheiro Restante:");
		leftMoneyLabel.setBounds(240, 450, 120, 20);
		leftMoneyLabel.setForeground(Color.ORANGE);
		backgroundImagePane.add(leftMoneyLabel);
		
		leftMoneyValueLabel = new JLabel(String.valueOf(game.mJ1.getMoney()));
		leftMoneyValueLabel.setBounds(275, 470, 120, 20);
		leftMoneyValueLabel.setForeground(Color.GREEN);
		backgroundImagePane.add(leftMoneyValueLabel);
		
		rightMoneyLabel = new JLabel("Dinheiro Restante:");
		rightMoneyLabel.setBounds(550, 450, 120, 20);
		rightMoneyLabel.setForeground(Color.ORANGE);
		backgroundImagePane.add(rightMoneyLabel);
		
		rightMoneyValueLabel = new JLabel(String.valueOf(game.mJ2.getMoney()));
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
				allCharacters.remove(pos);
				
			}
		});
		
		//Adicionar personagem ao time alpha
		leftAddCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
	
				try {
					
					//Pega posicoa na lista de disponiveis
					int pos = characterList.getSelectedIndex();
					if (pos != -1){
						
						game.buyCharacter(game.mJ1);
						leftMoneyValueLabel.setText(String.valueOf(game.mJ1.getMoney()));
						//Adiciona na tabela do time alpha
						leftModel.addElement(allCharacters.get(pos));
						
						//Remove da tabela de disponiveis
						characterModel.remove(pos);
						
						//adiciona ao time alpha
						
						game.mJ1.getTeam().addCharacter(allCharacters.get(pos));
						allCharacters.remove(pos);
					}else {
						JOptionPane.showConfirmDialog(null, "Selecione um personagem para comprar");
					}
					
					
				} catch (NotEnoughMoneyException e) {
					JOptionPane.showMessageDialog(null, "Voce nao tem dinheiro para comprar o personagem!");
					e.printStackTrace();
				}
				
			}
		});
		
		//Remove character do time alpha
		leftRemoveCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				//Pega posicao para remover
				int pos = leftList.getSelectedIndex();
				
				//Adiciona de volta a lista de disponiveis
				characterModel.addElement(game.mJ1.getTeam().getCharacter(pos).fullDescription());
				
				//remove da tabela da esquerda
				leftModel.remove(pos);
				
				allCharacters.add(game.mJ1.getTeam().getCharacter(pos));
				game.mJ1.getTeam().removeCharacter(pos);
			}
		});
		
		//Adiciona personagem no time Bravo
		rightAddCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				try {

					
					//Pega posicoa na lista de disponiveis
					int pos = characterList.getSelectedIndex();
					if(pos != -1){
						game.buyCharacter(game.mJ2);
						rightMoneyValueLabel.setText(String.valueOf(game.mJ2.getMoney()));
						
						//Adiciona na tabela do time bravo
						rightModel.addElement(allCharacters.get(pos));
						
						//Remove da tabela de disponiveis
						characterModel.remove(pos);
						
						//adiciona ai time bravo
						
						game.mJ2.getTeam().addCharacter(allCharacters.get(pos));
						allCharacters.remove(pos);	
					}else{
						JOptionPane.showConfirmDialog(null, "Selecione um personagem para comprar");
					}

				} catch (NotEnoughMoneyException e) {
					JOptionPane.showMessageDialog(null, "Voce nao tem dinheiro para comprar o personagem!");
					e.printStackTrace();
				}
	
			}
		});
		
		//Remove character do time bravo
		rightRemoveCharacterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				//Pega posicao para remover
				int pos = rightList.getSelectedIndex();
				
				//Adiciona de volta a lista de disponiveis
				characterModel.addElement(game.mJ2.getTeam().getCharacter(pos).fullDescription());
				
				//remove da tabela da esquerda
				rightModel.remove(pos);
				
				allCharacters.add(game.mJ2.getTeam().getCharacter(pos));
				game.mJ2.getTeam().removeCharacter(pos);	}
		});
		
		//botao jogar!
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				game.mBoard.addTeam(game.mJ1.getTeam());
				game.mBoard.addTeam(game.mJ2.getTeam());
				
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
		
		//ActionListener dos botoes de compra de item
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == leftBuyItem){
					JAddItem jadd = new JAddItem(game, game.mJ1);
					jadd.setVisible(true);
				}
				else if (event.getSource() == rightBuyItem){
					JAddItem jadd = new JAddItem(game, game.mJ2);
					jadd.setVisible(true);
				}
				
			}
		};
		//Adiciona listeners
		leftBuyItem.addActionListener(listener);
		rightBuyItem.addActionListener(listener);
		
		//Botao de editar o personagem (seta qual item sera usado)
		leftEditCharacter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int pos = leftList.getSelectedIndex();
				JCharacterEditor jedit = new JCharacterEditor(game.mJ1.getTeam().getCharacter(pos));
				jedit.setVisible(true);
				
			}
		});
		
		//Botao de editar o personagem (seta qual item sera usado)
		rightEditCharacter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int pos = rightList.getSelectedIndex();
				JCharacterEditor jedit = new JCharacterEditor(game.mJ2.getTeam().getCharacter(pos));
				jedit.setVisible(true);
				
			}
		});		
	}
	
	
	
	//Atualiza item dos personagns disponiveis
	public void refreshCharacterList(ArrayList<Character> allCharacters){
		characterModel.removeAllElements();
		
		for(int i = 0; i <allCharacters.size(); i++){
				characterModel.addElement(allCharacters.get(i).fullDescription());		
		}	
	}
	
	
}
