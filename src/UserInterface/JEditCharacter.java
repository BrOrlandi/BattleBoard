package UserInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import BattleBoardExceptions.DeadCharacterException;
import BattleBoardExceptions.ItemNotFoundException;
import BattleBoardExceptions.NotArmorItemException;
import BattleBoardExceptions.NotConsumableItem;
import BattleBoardExceptions.NotWeaponItemException;
import Character.Character;
import javax.swing.border.EtchedBorder;

public class JEditCharacter extends JDialog{
	
	private JPanel contentPanel;
	
	private JList itemList;
	private DefaultListModel listModel;
	
	private JTextField weaponLabel;
	private JTextField armorLabel;
	private JTextField consumableLabel;
	
	
	private JLabel itensLabel;
	
	private JButton weaponButton;
	private JButton armorButton;
	private JButton consumableButton;
	private JButton returnButton;
	
	
	public JEditCharacter(final Character character){
		
		setTitle("Inventario do personagem");
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		//Panel
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		//Rotulo
		weaponLabel = new JTextField();
		weaponLabel.setBounds(195, 155, 163, 20);
		contentPanel.add(weaponLabel);
		
		//Rotulo
		armorLabel = new JTextField();
		armorLabel.setBounds(195, 185, 163, 20);
		contentPanel.add(armorLabel);
		
		//Rotulo
		consumableLabel = new JTextField();
		consumableLabel.setBounds(195, 216, 163, 20);
		contentPanel.add(consumableLabel);	
		
		weaponButton = new JButton("Escolher arma");
		weaponButton.setBounds(29, 155, 156, 20);
		weaponButton.setEnabled(false);
		contentPanel.add(weaponButton);
		
		armorButton = new JButton("Escolher armadura");
		armorButton.setBounds(29, 185, 156, 20);
		armorButton.setEnabled(false);
		contentPanel.add(armorButton);
		
		consumableButton = new JButton("Escolher consumivel");
		consumableButton.setBounds(29, 216, 156, 20);
		consumableButton.setEnabled(false);
		contentPanel.add(consumableButton);
		
		returnButton = new JButton("Voltar");
		returnButton.setBounds(147, 247, 120, 20);
		contentPanel.add(returnButton);
		
		//Modelo da lista e lista visual
		listModel = new DefaultListModel();
		itemList = new JList(listModel);
		itemList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		itemList.setBounds(29, 43, 329, 101);
		contentPanel.add(itemList);
		
		itensLabel = new JLabel("Itens no invetário do personagem");
		itensLabel.setBounds(113, 18, 168, 14);
		contentPanel.add(itensLabel);
		
		//Preenche lista com os itens
		 final String[] arrayString = character.getItemsStringArray();
		for(int i = 0; i <arrayString.length; i++){
			listModel.addElement(arrayString[i]);
		}
		
		//Carrega vetor com as chaves
		final int[] arrayKeys = character.getItemKeyArray();
		
		
		//Botao de setar weapon
		weaponButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				int selected = itemList.getSelectedIndex();
				
				try {
					character.setWeapon(arrayKeys[selected]);
					weaponLabel.setText(arrayString[selected]);
				} catch (DeadCharacterException e) {
					//Personagem nunca estara morto
					e.printStackTrace();
				} catch (ItemNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Item nao encontrado!");
					e.printStackTrace();
				} catch (NotWeaponItemException e) {
					JOptionPane.showMessageDialog(null, "Item nao é uma arma!");
					e.printStackTrace();
				}
			}
		});
		
		//Botao de setar weapon
		armorButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				int selected = itemList.getSelectedIndex();
				
				try {
					character.setArmor(arrayKeys[selected]);
					armorLabel.setText(arrayString[selected]);
				} catch (DeadCharacterException e) {
					//Personagem nunca estara morto
					e.printStackTrace();
				} catch (ItemNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Item nao encontrado!");
					e.printStackTrace();
				} catch (NotArmorItemException e) {
					JOptionPane.showMessageDialog(null, "Item nao é uma armadura!");
					e.printStackTrace();
				}
			}
		});
		
		//Botao de setar weapon
		consumableButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				int selected = itemList.getSelectedIndex();
				
				try {
					character.setConsumable(arrayKeys[selected]);
					consumableLabel.setText(arrayString[selected]);
				} catch (DeadCharacterException e) {
					//Personagem nunca estara morto
					e.printStackTrace();
				} catch (ItemNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Item nao encontrado!");
					e.printStackTrace();
				} catch (NotConsumableItem e) {
					JOptionPane.showMessageDialog(null, "Item nao é do tipo consumivel!");
					e.printStackTrace();
				}
			}
		});
		
		returnButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
			}
		});
	}
}
