package UserInterface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Item.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.border.EtchedBorder;

public class JItemEditor extends JDialog {
	
	//Panel
	private JPanel contentPane;
	private ImageJPanel buttonPane;
	
	//Labels
	private JLabel itemNameLabel;
	private JLabel itemTypeLabel;
	private JLabel itemAttackLabel;
	private JLabel itemDefenseLabel;
	private JLabel itemPriceLabel;
	private JLabel itemFlexibilidadeLabel;
	private JLabel itemAlcanceLabel;
	private JLabel HealthReviveLabel;
	
	//TextFields
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField defenseTextField;
	private JTextField flexibilityTextField;
	private JTextField attackTextField;
	private JTextField rangeTextField;
	private JTextField pointsTextField;
	
	//Botoes
	private JButton returnButton;
	private JButton createButton;
	private JButton eraseButton;
	
	//Combo box
	private JComboBox typeComboBox;
	
	//Tabela dos itens
	private ItemTable table;
	
	//Scroll
	private JScrollPane scrollPane;
	
	private ItemStore itemStore;
	
	public JItemEditor(ItemStore it) {
		
		//setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 600);
		setLocationRelativeTo(null);
		setTitle("Ferreiro");
		setResizable(false);
		setModal(true);
		
		if(it.getNumItens() != 0)
		{
			String[] itemArray = it.getItemsString();
			System.out.println(itemArray[0]);
			System.out.println(itemArray[1]);
			//table.addItemTable(itemArray[0], itemArray[1], itemArray[2], itemArray[3], itemArray[4], itemArray[5], itemArray[6], itemArray[7]);		
		}
		
		itemStore = it;

		String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		System.out.println(path+"imagem.jpg");
		
		/**PANELS*/
		//panel que contem a tabela

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(600, 100));
		setContentPane(contentPane);
		
		
		//painel que contem botoes e caixas de entrada de texto
		try {
			buttonPane = new ImageJPanel(path+"Weapon.jpg");
			buttonPane.setForeground(Color.GRAY);
			buttonPane.setBounds(5, 157, 773, 400);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar imagem de fundo", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		contentPane.setLayout(null);
		buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonPane.setPreferredSize(new Dimension(700, 400));
		getContentPane().add(buttonPane);
		buttonPane.setLayout(null);
		

		/**LABELS*/
		
		//Label nome do item
		itemNameLabel = new JLabel("Nome do Item:");
		itemNameLabel.setBounds(37, 158, 97, 14);
		buttonPane.add(itemNameLabel);
		
		//label tipo do item
		itemTypeLabel = new JLabel("Tipo do Item:");
		itemTypeLabel.setBounds(37, 130, 97, 20);
		buttonPane.add(itemTypeLabel);
		
		//label valor de ataque
		itemAttackLabel = new JLabel("Ataque:");
		itemAttackLabel.setForeground(Color.GRAY);
		itemAttackLabel.setBounds(480, 132, 62, 17);
		buttonPane.add(itemAttackLabel);
		
		//label preco
		itemPriceLabel = new JLabel("Preço:");
		itemPriceLabel.setBounds(37, 183, 97, 17);
		buttonPane.add(itemPriceLabel);
		
		//label defesa
		itemDefenseLabel = new JLabel("Defesa:");
		itemDefenseLabel.setBounds(308, 130, 82, 20);
		buttonPane.add(itemDefenseLabel);
		
		//label flexibilidade
		itemFlexibilidadeLabel = new JLabel("Flexibilidade:");
		itemFlexibilidadeLabel.setBounds(308, 152, 82, 20);
		buttonPane.add(itemFlexibilidadeLabel);
		
		
		/**CAMPOS DE TEXTO*/
		
		//campo de texto do nome
		nameTextField = new JTextField();
		nameTextField.setBounds(144, 155, 138, 20);
		buttonPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		//campo de texto do preço
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		priceTextField.setBounds(144, 180, 138, 20);
		buttonPane.add(priceTextField);
		
		//campo de texto da defesa
		defenseTextField = new JTextField();
		defenseTextField.setColumns(10);
		defenseTextField.setBounds(388, 130, 82, 20);
		buttonPane.add(defenseTextField);
		
		//combo box com o tipo do item
		String[] types = {"Weapon", "Armor","Health Potion", "Revive Potion"};
		typeComboBox = new JComboBox(types);
		typeComboBox.setBounds(144, 133, 138, 20);	
		typeComboBox.setSelectedIndex(0);
		buttonPane.add(typeComboBox);
		
		//campo de texto flexibilidade
		flexibilityTextField = new JTextField();
		flexibilityTextField.setBounds(388, 152, 82, 20);
		buttonPane.add(flexibilityTextField);
		flexibilityTextField.setColumns(10);
		
		itemAlcanceLabel = new JLabel("Alcance:");
		itemAlcanceLabel.setForeground(Color.GRAY);
		itemAlcanceLabel.setBounds(480, 155, 62, 17);
		buttonPane.add(itemAlcanceLabel);
		
		attackTextField = new JTextField();
		attackTextField.setColumns(10);
		attackTextField.setBounds(531, 130, 82, 20);
		buttonPane.add(attackTextField);
		
		rangeTextField = new JTextField();
		rangeTextField.setColumns(10);
		rangeTextField.setBounds(531, 152, 82, 20);
		buttonPane.add(rangeTextField);
		

		/**BOTOES*/
		//criar item
		createButton = new JButton("Criar Item");
		createButton.setBounds(308, 180, 165, 23);
		buttonPane.add(createButton);
		
		//apagar item
		eraseButton = new JButton("Excluir Item");
		eraseButton.setBounds(480, 180, 133, 23);
		buttonPane.add(eraseButton);
		
		//voltar
		returnButton = new JButton("Voltar");
		returnButton.setBounds(343, 217, 106, 22);
		buttonPane.add(returnButton);
		
		HealthReviveLabel = new JLabel("Health/Revive Points");
		HealthReviveLabel.setBounds(639, 132, 123, 17);
		buttonPane.add(HealthReviveLabel);
		
		pointsTextField = new JTextField();
		pointsTextField.setBounds(655, 152, 86, 20);
		buttonPane.add(pointsTextField);
		pointsTextField.setColumns(10);
		buttonPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{typeComboBox, defenseTextField, attackTextField, nameTextField, flexibilityTextField, rangeTextField, pointsTextField, priceTextField, createButton, eraseButton, returnButton}));
		
		/**TABELA DOS ITENS*/
		
		//Adiciona tabela
		table = new ItemTable();
		table.setBounds(5, 5, 773, 152);
		table.setEnabled(false);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//scrollPane = new JScrollPane(table);
		//table.add(scrollPane);
		getContentPane().add(table);
		table.setLayout(new CardLayout(0, 0));
		
		/*LISTENERS**/
		
		//voltar
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(itemStore);
				setVisible(false);
			}
		});
		
		//criar item
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String type, String name, String attack, String range, String defense, String flexibility, String price)
				addItem(typeComboBox.getSelectedItem().toString(), nameTextField.getText(), attackTextField.getText(),
						rangeTextField.getText(), defenseTextField.getText(), flexibilityTextField.getText(), priceTextField.getText(), pointsTextField.getText());
			}
		});
		
		//apagar item
		eraseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Remove item da tabela e recupera indice que foi removido
				int position = table.removeSelectedRow();
				
				//Usa o indice que foi removido para remover o item da ItemStore (caso removido com sucesso)
				if(position != -1)
				{
					itemStore.removeItem(position);
				}
				
				
			}
		});
		
		//listener do combo box que ativa e desativa caixas de texto
		typeComboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event){
				JComboBox cb = (JComboBox)event.getSource();
		        String string = (String)cb.getSelectedItem();
		        
		        //Seta caixas de textos de acordo com opção do combo box
		        setTextFields(string);
			}
		});
		
	}
	
	/**
	 * Adiciona item na tabela apresentada na interface e adiciona no Objeto ItemStore
	 * 
	 * @param type
	 * @param name
	 * @param attack
	 * @param range
	 * @param defense
	 * @param flexibility
	 * @param price
	 * @param points
	 */
	public void addItem(String type, String name, String attack, String range, String defense, String flexibility, String price, String points)
	{	
		boolean insert = true;
		
		if(type.equals("Weapon")){	
			try{
				Weapon w = new Weapon(name, Double.parseDouble(price), Integer.parseInt(attack), Integer.parseInt(range));
				itemStore.addItem(w);
				
			}catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Preço, ataque e alcance devem ser numeros!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
				insert = false;
			}
			
			
		}
		else if(type.equals("Armor")){	
			try{
				Armor a = new Armor(name, Double.parseDouble(price), Integer.parseInt(defense), Integer.parseInt(flexibility));
				itemStore.addItem(a);
			
			}catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Preço, defesa e flexibilidade devem ser numeros!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
				insert = false;
			}
			
		}
		else if(type.equals("Health Potion")){
			//String name, double price, int restore)
			try{
				HealthPotion h = new HealthPotion(name, Double.parseDouble(price), Integer.parseInt(points));
				itemStore.addItem(h);
			
			}catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Preço e pontos devem ser numeros!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
				insert = false;
			}
			
		}
		else if(type.equals("Revive Potion")){
			//String name, double price, int revivepts) {
			try{
				RevivePotion r = new RevivePotion(name, Double.parseDouble(price), Integer.parseInt(points));
				itemStore.addItem(r);
				
			}catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null, "Preço e pontos devem ser numeros!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
				insert = false;
			}
			
		}
		
		//adiciona na tabela
		if(insert == true){
			table.addItemTable(type, name, attack, range, defense, flexibility, price, points);
		}
		
	}

	/**
	 * Seta caixa de textos de acordo com tipo de item escolhido
	 * @param string
	 */
	public void setTextFields(String string)
	{
		//se escolhido foi arma
        if(string.equals("Weapon")) {	     	
        	rangeTextField.setEnabled(true);
        	attackTextField.setEnabled(true);
        	flexibilityTextField.setEnabled(false);
        	defenseTextField.setEnabled(false);
        	pointsTextField.setEnabled(false);
	
        }
        //se escolihdo foi escudo
        else if (string.equals("Armor"))	{
        	flexibilityTextField.setEnabled(true);
        	defenseTextField.setEnabled(true);
        	rangeTextField.setEnabled(false);
        	attackTextField.setEnabled(false);
        	pointsTextField.setEnabled(false);
        }
        //se escolhido foi poçao de vida ou poçao de reviver
        else if (string.equals("Health Potion") || string.equals("Revive Potion")){
        	pointsTextField.setEnabled(true);
        	
        	flexibilityTextField.setEnabled(false);
        	rangeTextField.setEnabled(false);
        	attackTextField.setEnabled(false);
        	defenseTextField.setEnabled(false);
        }
	}

}

