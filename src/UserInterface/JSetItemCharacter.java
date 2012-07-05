package UserInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Character.Character;
import Item.*;

import javax.swing.border.EtchedBorder;

public class JSetItemCharacter extends JDialog{
	
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
	
	private int[] arrayKeys; ///< Vetor com as chaves
	private String[] arrayString; ///< Lista com os itens
	
	
	public JSetItemCharacter(final Character character){
		
		setTitle("Character Inventory");
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		System.out.println("PASSOU 3");
		
		//Panel
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		//Rotulo
		Weapon w = character.getWeapon();
		String weaponStr;
		if(w == null)
			weaponStr = "";
		else
			weaponStr = w.getName();
		weaponLabel = new JTextField(weaponStr);
		weaponLabel.setBounds(195, 155, 163, 20);
		contentPanel.add(weaponLabel);
		
		//Rotulo
		Armor a = character.getArmor();
		String armorStr;
		if(a == null)
			armorStr = "";
		else
			armorStr = a.getName();
		armorLabel = new JTextField(armorStr);
		armorLabel.setBounds(195, 185, 163, 20);
		contentPanel.add(armorLabel);
		
		//RotuloArmor
		Item it = ((Item)character.getConsumable());
		String consumablerStr;
		if(it == null)
			consumablerStr = "";
		else
			consumablerStr = it.getName();
		consumableLabel = new JTextField(consumablerStr);
		consumableLabel.setBounds(195, 216, 163, 20);
		contentPanel.add(consumableLabel);	

		System.out.println("PASSOU 4");
		weaponButton = new JButton("Set Weapon");
		weaponButton.setBounds(29, 155, 156, 20);
		weaponButton.setEnabled(true);
		contentPanel.add(weaponButton);
		
		armorButton = new JButton("Set Armor");
		armorButton.setBounds(29, 185, 156, 20);
		armorButton.setEnabled(true);
		contentPanel.add(armorButton);
		
		consumableButton = new JButton("Set Consumable");
		consumableButton.setBounds(29, 216, 156, 20);
		consumableButton.setEnabled(true);
		contentPanel.add(consumableButton);
		
		returnButton = new JButton("Back");
		returnButton.setBounds(147, 247, 120, 20);
		contentPanel.add(returnButton);
		
		//Modelo da lista e lista visual
		listModel = new DefaultListModel();
		itemList = new JList(listModel);
		itemList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		itemList.setBounds(29, 43, 329, 101);
		contentPanel.add(itemList);
		
		itensLabel = new JLabel("Inventory:");
		itensLabel.setBounds(113, 18, 168, 14);
		contentPanel.add(itensLabel);
		
		//Preenche lista com os itens
		arrayString = character.getItemsStringArray();
		for(int i = 0; i <arrayString.length; i++){
			listModel.addElement(arrayString[i]);
		}
		System.out.println("PASSOU 1");
		arrayKeys = character.getItemKeyArray();
		
		
		ActionListener ListenerItemsSet = new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				int selected = itemList.getSelectedIndex();
				if(event.getSource() == weaponButton){
					try {
						character.setWeapon(arrayKeys[selected]);
						weaponLabel.setText(character.getWeapon().getName());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						//e.printStackTrace();
					}
				}
				else if(event.getSource() == armorButton){
					try {
						character.setArmor(arrayKeys[selected]);
						armorLabel.setText(character.getArmor().getName());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						//e.printStackTrace();
					}
				}
				else if(event.getSource() == consumableButton){
					try {
						character.setConsumable(arrayKeys[selected]);
						consumableLabel.setText(((Item)character.getConsumable()).getName());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						//e.printStackTrace();
					}
				}
				
				arrayKeys = character.getItemKeyArray();
				arrayString = character.getItemsStringArray();
				listModel.clear();
				for(int i = 0; i <arrayString.length; i++){
					listModel.addElement(arrayString[i]);
				}
			}
		};
		System.out.println("PASSOU 2");
		
		//Listeners:
		weaponButton.addActionListener(ListenerItemsSet);
		armorButton.addActionListener(ListenerItemsSet);
		consumableButton.addActionListener(ListenerItemsSet);
		
		returnButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
			}
		});
	}
}
