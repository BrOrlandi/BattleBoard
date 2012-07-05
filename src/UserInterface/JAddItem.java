package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Game.Game;
import Game.NotEnoughMoneyException;
import Game.Player;
import Item.Item;
import Item.ItemStore;
import Overview.Team;
import Character.Character;
import javax.swing.border.EtchedBorder;

public class JAddItem extends JDialog{
	
	private JPanel contentPane;
	
	private JList itemList;
	private JList characterList;
	
	private DefaultListModel itemListModel;
	private DefaultListModel characterListModel;
	
	private JButton buyItem;
	private JButton returnButton;
	
	private JLabel availableItem;
	private JLabel cashAvailable;
	private JLabel cashLabel;
	
	private JScrollPane itemListScroll;
	private JScrollPane characterListScroll;
	
	private JLabel characterLabel;
	

	
	public JAddItem(final Game game, final Player player){
		
		setSize(new Dimension(600, 600));
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		//Painel 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setForeground(Color.GRAY);
		
		//Rotulo
		availableItem = new JLabel("Available items on Store.");
		availableItem.setBounds(223, 40, 140, 20);
		contentPane.add(availableItem);
		
		//Rotulo de dinheiro disponivel
		cashAvailable =  new JLabel(String.valueOf(player.getMoney()));
		cashAvailable.setBounds(327, 260, 75, 20);
		cashAvailable.setForeground(Color.GREEN);
		contentPane.add(cashAvailable);
		
		//Rotulo
		characterLabel = new JLabel("Select a character.");
		characterLabel.setBounds(201, 291, 201, 20);
		contentPane.add(characterLabel);
		
		//Rotulo
		cashLabel = new JLabel("Money:");
		cashLabel.setBounds(186, 260, 132, 20);
		cashLabel.setForeground(Color.ORANGE);
		contentPane.add(cashLabel);
		
		//Lista com os itens que serao comprado
		itemListModel = new DefaultListModel();
		itemList = new JList(itemListModel);
		itemList.setBounds(99, 81, 396, 164);
		
		//barra de rolagem da lista de items
		itemListScroll = new JScrollPane();
		itemListScroll.setViewportView(itemList);
		itemListScroll.setBounds(99, 81, 400, 165);
		getContentPane().add(itemListScroll);
		
		//lista com os personagens do time do jogador que esta comprando os itens
		characterListModel = new DefaultListModel();
		characterList = new JList(characterListModel);
		characterList.setBounds(100, 320, 400, 165);
		
		//Barra de rolagem da lista de personagens
		characterListScroll = new JScrollPane();
		characterListScroll.setViewportView(characterList);
		characterListScroll.setBounds(100, 320, 400, 165);
		getContentPane().add(characterListScroll);
		
		//botao comprar
		buyItem = new JButton("Buy!");
		buyItem.setBounds(223, 499, 140, 20);
		getContentPane().add(buyItem);
		
		//botao voltar
		returnButton = new JButton("Back");
		returnButton.setBounds(239, 541, 100, 20);
		getContentPane().add(returnButton);
		
		//Preenche a lista com os itens
		Item[] itemArray = game.mItemStore.getItemArray();
		for(int i = 0; i < game.mItemStore.getNumItens(); i++){
			itemListModel.addElement(itemArray[i]);
		}
		
		//Preenche a lista com os personagens do time
		Character[] characterArray = player.getTeam().getCharactersArray();
		for(int i = 0; i < player.getTeam().numCharacter(); i++){
			characterListModel.addElement(characterArray[i]);
		}
		
		//Botao de comprar item
		buyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Pega indice do item e personagem que o jogador escolheu
				int itemSelected = itemList.getSelectedIndex();
				int characterSelected = characterList.getSelectedIndex();
				
				try {
					game.buyItem(player, itemSelected, characterSelected);
					JOptionPane.showMessageDialog(null, "Item purchased!");
					cashAvailable.setText(String.valueOf(player.getMoney()));
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Select an Item and a Character.");
					e.printStackTrace();
				} catch (NotEnoughMoneyException e) {
					JOptionPane.showMessageDialog(null, "You don't have money to buy this! :(");
					e.printStackTrace();
				}
			}
		});
		
		//Botao para comprar
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
