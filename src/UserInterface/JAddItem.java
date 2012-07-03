package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Item.ItemStore;
import Overview.Team;
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
	
	private ItemStore itemStore;
	private Team team;
	
	public JAddItem(Team t, ItemStore it){
		
		setSize(new Dimension(600, 600));
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		itemStore = it;
		team = t;
		
		//Painel 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setForeground(Color.GRAY);
		
		availableItem = new JLabel("Itens disponiveis na loja");
		availableItem.setBounds(223, 40, 140, 20);
		contentPane.add(availableItem);
		
		cashAvailable =  new JLabel("10000");
		cashAvailable.setBounds(314, 260, 75, 20);
		cashAvailable.setForeground(Color.GREEN);
		contentPane.add(cashAvailable);
		
		characterLabel = new JLabel("Escolha um de seus personagens");
		characterLabel.setBounds(201, 291, 201, 20);
		contentPane.add(characterLabel);
		
		cashLabel = new JLabel("Dinheiro Disponivel:");
		cashLabel.setBounds(204, 260, 132, 20);
		cashLabel.setForeground(Color.ORANGE);
		contentPane.add(cashLabel);
		
		//Lista com os itens que serao comprado
		itemListModel = new DefaultListModel();
		itemList = new JList(itemListModel);
		itemList.setBounds(99, 81, 396, 164);
		
		itemListScroll = new JScrollPane();
		itemListScroll.setViewportView(itemList);
		itemListScroll.setBounds(99, 81, 400, 165);
		getContentPane().add(itemListScroll);
		
		//lista com os personagens do time do jogador que esta comprando os itens
		characterListModel = new DefaultListModel();
		characterList = new JList(characterListModel);
		characterList.setBounds(99, 320, 396, 164);
		
		characterListScroll = new JScrollPane();
		characterListScroll.setBounds(100, 320, 400, 165);
		characterListScroll.add(characterList);
		getContentPane().add(characterListScroll);
		
		buyItem = new JButton("Comprar!");
		buyItem.setBounds(223, 499, 140, 20);
		getContentPane().add(buyItem);
		
		returnButton = new JButton("Voltar");
		returnButton.setBounds(239, 541, 100, 20);
		getContentPane().add(returnButton);
		
		buyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemList.getSelectedIndex();
	
			}
		});
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
