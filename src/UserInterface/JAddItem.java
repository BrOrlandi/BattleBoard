package UserInterface;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Item.ItemStore;
import Overview.Team;

public class JAddItem extends JDialog{
	
	private JPanel contentPane;
	
	private JList itemList;
	private JList characterList;
	
	private DefaultListModel itemListModel;
	private DefaultListModel characterListModel;
	
	private JButton buyItem;
	
	private ItemStore itemStore;
	private Team team;
	
	public JAddItem(){
		
		setSize(new Dimension(400, 400));
		setModal(true);
		setResizable(false);
		
		//Painel 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		//Lista com os itens que serao comprado
		itemListModel = new DefaultListModel();
		itemList = new JList(itemListModel);
		itemList.setBounds(20, 20, 180, 220);
		getContentPane().add(itemList);
		
		//lista com os personagens do time do jogador que esta comprando os itens
		characterListModel = new DefaultListModel();
		characterList = new JList(characterListModel);
		
		getContentPane().add(characterList);
	}

}
