package UserInterface;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import Character.Character;
import Character.Fighter;
import Character.Ranger;
import Overview.Team;

public class JCharacterEditor extends JDialog {
	
	//Panel que suporta imagem de fundo
	private ImageJPanel imagePane;
	private JPanel backgroundPane;
	
	//Botoes de mais e menos
	private JButton leftButton;
	private JButton rightButton;
	private JButton returnButton;
	
	private JButton plusStrenght;
	private JButton minusStrenght;
	
	private JButton plusDexterity;
	private JButton minusDexterity;
	
	private JButton plusSpeed;
	private JButton minusSpeed;
	
	private JButton plusConstitution;
	private JButton minusConstitution;
	
	private JButton plusAcurracy;
	private JButton minusAcurracy;
	
	private JButton plusPower;
	private JButton minusPower;
	
	private JButton createButton;
	
	//Rotulos
	private JLabel nameJLabel;
	private JLabel strengthJLabel;
	private JLabel dexterityJLabel;
	private JLabel speedJLabel;
	private JLabel constitutionJLabel;
	private JLabel acurracyJLabel;
	private JLabel powerJLabel;
	private JLabel StatusJLabel;
	private JLabel skillPointsCounter;
	
	//Caixas de texto
	private JTextField nameTextField;
	private JTextField strenghtTextField;
	private JTextField dexterityTextField;
	private JTextField speedTextField;
	private JTextField constitutionTextField;
	private JTextField powerTextField;
	private JTextField accuraryTextField;
	private JTextField skillPointsTextField;
	
	//Constantes
	private final int FIGHTER = 0;
	private final int RANGER = 1;
	
	//personagem que sera criado (atual)
	private int selected = RANGER;
	
	private ArrayList<Character> allCharacters;
	
	private int skillPointsLeft = 100;
	/**
	 * Construtor
	 * @param allCharacters2 
	 */
	public JCharacterEditor(ArrayList<Character> all) {
		
		//Inicializacao do frame
		setSize(new Dimension(700, 580));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Criar Novo Personagem");
		setModal(true);
		
		allCharacters = all;
		
		//String com o caminho até a pasta atual
		final String path = System.getProperty("user.dir")+"/";
		path.replace(" ", "\\ ");
		
		/**LABELS*/

		StatusJLabel = new JLabel("Status do Personagem:");
		StatusJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		StatusJLabel.setBounds(486, 34, 161, 14);
		
		nameJLabel = new JLabel("Nome:");
		nameJLabel.setBounds(420, 75, 145, 20);
		
		strengthJLabel = new JLabel("Força:");
		strengthJLabel.setBounds(420, 106, 56, 20);
		
		dexterityJLabel = new JLabel("Destreza:");
		dexterityJLabel.setBounds(420, 136, 77, 19);
		
		constitutionJLabel = new JLabel("Constituição:");
		constitutionJLabel.setBounds(420, 160, 145, 20);
		
		speedJLabel = new JLabel("Velocidade");
		speedJLabel.setBounds(420, 188, 145, 20);
		
		acurracyJLabel = new JLabel("Precisão:");
		acurracyJLabel.setBounds(420, 244, 78, 24);
		
		powerJLabel = new JLabel("Poder:");
		powerJLabel.setBounds(420, 219, 56, 14);
		
		skillPointsCounter = new JLabel("Pontos Restantes");
		skillPointsCounter.setBounds(420, 287, 107, 20);
		
		backgroundPane = new JPanel();
	
		getContentPane().setLayout(null);
		getContentPane().add(backgroundPane);
		backgroundPane.setSize(new Dimension(700, 580));
		
		//Adicionando labels ao painel
		backgroundPane.add(nameJLabel);
		backgroundPane.add(strengthJLabel);
		backgroundPane.add(speedJLabel);
		backgroundPane.add(dexterityJLabel);
		backgroundPane.add(constitutionJLabel);
		backgroundPane.add(acurracyJLabel);
		backgroundPane.add(StatusJLabel);
		backgroundPane.add(powerJLabel);
		backgroundPane.add(skillPointsCounter);
		
		/**TEXTFIELDS*/
		
		nameTextField = new JTextField();
		nameTextField.setBounds(497, 75, 150, 20);

		strenghtTextField = new JTextField("0");
		strenghtTextField.setBounds(497, 106, 30, 20);
		strenghtTextField.setEditable(false);
		
		dexterityTextField = new JTextField("0");
		dexterityTextField.setBounds(497, 133, 30, 20);
		dexterityTextField.setEditable(false);
		
		constitutionTextField = new JTextField("0");
		constitutionTextField.setBounds(497, 160, 30, 20);
		constitutionTextField.setEditable(false);
		
		speedTextField = new JTextField("0");
		speedTextField.setBounds(497, 188, 30, 20);
		speedTextField.setEditable(false);
		
		accuraryTextField = new JTextField("0");
		accuraryTextField.setBounds(497, 246, 30, 20);
		accuraryTextField.setEditable(false);
		
		powerTextField = new JTextField("0");
		powerTextField.setBounds(497, 219, 30, 20);
		powerTextField.setEditable(false);
		
		skillPointsTextField = new JTextField(String.valueOf(skillPointsLeft));
		skillPointsTextField.setBounds(547, 287, 100, 20);
		skillPointsTextField.setEditable(false);
		
		
		//Adiciona caixas de texto ao painel principal
		backgroundPane.add(nameTextField);
		backgroundPane.add(strenghtTextField);
		backgroundPane.add(dexterityTextField);
		backgroundPane.add(constitutionTextField);
		backgroundPane.add(speedTextField);
		backgroundPane.add(accuraryTextField);
		backgroundPane.add(powerTextField);
		backgroundPane.add(skillPointsTextField);
		
		//Instancia novos botoes de mais e menos (com o listener)
        plusStrenght = new JButton("+");
        plusStrenght.setBounds(540, 106, 50, 20);
        plusStrenght.addActionListener(plusAndMinusListener);
         
	    minusStrenght = new JButton("-");
	    minusStrenght.setBounds(600, 106, 50, 20);
	    minusStrenght.addActionListener(plusAndMinusListener);
	    	
	    plusDexterity = new JButton("+");
	    plusDexterity.setBounds(540, 135, 50, 20);
	    plusDexterity.addActionListener(plusAndMinusListener);
	        
	    minusDexterity = new JButton("-");
	    minusDexterity.setBounds(600, 135, 50, 20);
	    minusDexterity.addActionListener(plusAndMinusListener);
	   
	    plusSpeed = new JButton("+");
	    plusSpeed.setBounds(540, 188, 50, 20);
	    plusSpeed.addActionListener(plusAndMinusListener);
	    
	    minusSpeed = new JButton("-");
	    minusSpeed.setBounds(600, 188, 50, 20);
	    minusSpeed.addActionListener(plusAndMinusListener);
			
	    plusConstitution = new JButton("+");
	    plusConstitution.setBounds(540, 160, 50, 20);
	    plusConstitution.addActionListener(plusAndMinusListener);
		    
	    minusConstitution = new JButton("-");
	    minusConstitution.setBounds(600, 160, 50, 20);	  
	    minusConstitution.addActionListener(plusAndMinusListener);
		
		plusAcurracy = new JButton("+");
	    plusAcurracy.setBounds(540, 246, 50, 20);
	    plusAcurracy.addActionListener(plusAndMinusListener);
	    
	    minusAcurracy = new JButton("-");
	    minusAcurracy.setBounds(600, 246, 50, 20);
	    minusAcurracy.addActionListener(plusAndMinusListener);
		
		plusPower = new JButton("+");
	    plusPower.setBounds(540, 216, 50, 20);
	    plusPower.addActionListener(plusAndMinusListener);
		
		minusPower = new JButton("-");
	    minusPower.setBounds(600, 216, 50, 20);
	    minusPower.addActionListener(plusAndMinusListener);

		leftButton = new JButton("Fighter");
		leftButton.setBounds(80, 500, 100, 20);
		
		rightButton = new JButton("Ranger");
		rightButton.setBounds(230, 500, 100, 20);
		
		returnButton = new JButton("Voltar");
		returnButton.setBounds(564, 500, 100, 20);
		
		createButton = new JButton("Criar Personagem");
		createButton.setBounds(402, 500, 145, 20);
		
		//Adicionando botoes que mudam a imagem do character	
		backgroundPane.add(leftButton);
		backgroundPane.add(rightButton);
		backgroundPane.add(returnButton);
	    backgroundPane.add(plusStrenght);
	    backgroundPane.add(minusStrenght);
	    backgroundPane.add(plusDexterity);
	    backgroundPane.add(minusDexterity);
	    backgroundPane.add(minusDexterity); 
	    backgroundPane.add(plusSpeed);
	    backgroundPane.add(minusSpeed);
	    backgroundPane.add(plusConstitution);
	    backgroundPane.add(minusConstitution);
	    backgroundPane.add(plusAcurracy);
	    backgroundPane.add(minusAcurracy);  
	    backgroundPane.add(plusPower);
	    backgroundPane.add(minusPower);
	    backgroundPane.add(createButton);
	    
		//Painel com a imagem do character
		try {
			imagePane = new ImageJPanel(path+"fighter2.jpg");
			selected = FIGHTER;
	
			plusPower.setEnabled(true);
			minusPower.setEnabled(true);

			//Desabilidata campo do ranger e habilita de guerreiro
			plusAcurracy.setEnabled(false);
			minusAcurracy.setEnabled(false);
			accuraryTextField.setEditable(false);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar imagem", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		//Adicionar painel  de imagem ao painel principal do frame
		
		
		backgroundPane.setLayout(null);
		backgroundPane.add(imagePane);
		imagePane.setLayout(null);
		imagePane.setBounds(20, 20, 380, 475);
		
		//setar nova Imagem (outro character)
		leftButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {		
				try
				{
					imagePane.setImage(path+"fighter2.jpg");
					selected = FIGHTER;
					
					plusPower.setEnabled(true);
					minusPower.setEnabled(true);

					//Desabilidata botoes
					plusAcurracy.setEnabled(false);
					minusAcurracy.setEnabled(false);
					
					skillPointsLeft = skillPointsLeft + Integer.parseInt(accuraryTextField.getText());
					accuraryTextField.setText("0");
					skillPointsTextField.setText(String.valueOf(skillPointsLeft));
					
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
				
			}
		});
		
		//Setar nova imagem(outro character)
		rightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				try
				{
					imagePane.setImage(path+"ranger2.jpg");
					selected = RANGER;
					plusAcurracy.setEnabled(true);
					minusAcurracy.setEnabled(true);
					
					//Desabilita botoes
					plusPower.setEnabled(false);
					minusPower.setEnabled(false);

					skillPointsLeft = skillPointsLeft + Integer.parseInt(powerTextField.getText());
					powerTextField.setText("0");
					skillPointsTextField.setText(String.valueOf(skillPointsLeft));
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
				
			}
		});
		
		//Botao para voltar
		returnButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
			
			}
		});

		//Listener do botao de criar personagem
		createButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				//Cria persongaem e adiciona no time generico, fora no JTeameditor o time generico é adicionado a tabela
				if(selected == FIGHTER){
					Fighter f = new Fighter(nameTextField.getText(), Integer.parseInt(powerTextField.getText()));
					allCharacters.add(f);
					
				}else if(selected == RANGER){
					Ranger r = new Ranger(nameTextField.getText(), Integer.parseInt(accuraryTextField.getText()));
					allCharacters.add(r);
				}
				setVisible(false);
			}
		});
	}
	
	//Listener dos botoes de adicionar e remover pontos de atributos do personagem
	ActionListener plusAndMinusListener = new ActionListener(){
		public void actionPerformed(ActionEvent event) {
			
			//Caso seja botao relativo a força
			if(event.getSource() == plusStrenght && skillPointsLeft > 0){
				strenghtTextField.setText(String.valueOf(Integer.parseInt(strenghtTextField.getText())+1));
				skillPointsLeft--;
			}
			else if(event.getSource() == minusStrenght  && !strenghtTextField.getText().equals("0")){
				strenghtTextField.setText(String.valueOf(Integer.parseInt(strenghtTextField.getText())-1));
				skillPointsLeft++;
			}
			
			//Caso seja botao de destreza
			else if(event.getSource() == plusDexterity && skillPointsLeft > 0){
				dexterityTextField.setText(String.valueOf(Integer.parseInt(dexterityTextField.getText())+1));
				skillPointsLeft--;
			}
			else if(event.getSource() == minusDexterity  && !dexterityTextField.getText().equals("0")){
				dexterityTextField.setText(String.valueOf(Integer.parseInt(dexterityTextField.getText())-1));
				skillPointsLeft++;
			}
			
			//Caso seja botao de velocidade
			else if(event.getSource() == plusSpeed && skillPointsLeft > 0){
				speedTextField.setText(String.valueOf(Integer.parseInt(speedTextField.getText())+1));
				skillPointsLeft--;
			}
			else if(event.getSource() == minusSpeed  &&  !speedTextField.getText().equals("0")){
				speedTextField.setText(String.valueOf(Integer.parseInt(speedTextField.getText())-1));
				skillPointsLeft++;
			}
			
			//Caso seja botao de constituição
			else if(event.getSource() == plusConstitution && skillPointsLeft > 0){
				constitutionTextField.setText(String.valueOf(Integer.parseInt(constitutionTextField.getText())+1));
				skillPointsLeft--;
			}
			else if(event.getSource() == minusConstitution   && !constitutionTextField.getText().equals("0")){
				constitutionTextField.setText(String.valueOf(Integer.parseInt(constitutionTextField.getText())-1));
				skillPointsLeft++;
			}
			
			//Caso seja botao de precisao
			else if(event.getSource() == plusAcurracy && skillPointsLeft > 0 && Integer.parseInt(accuraryTextField.getText()) < 30){
				accuraryTextField.setText(String.valueOf(Integer.parseInt(accuraryTextField.getText())+1));
				skillPointsLeft--;
			}
			else if(event.getSource() == minusAcurracy  && !accuraryTextField.getText().equals("0")){
				accuraryTextField.setText(String.valueOf(Integer.parseInt(accuraryTextField.getText())-1));
				skillPointsLeft++;
			}
			
			//Caso seja botao de poder
			else if(event.getSource() == plusPower && skillPointsLeft > 0 && Integer.parseInt(powerTextField.getText()) < 30){
				powerTextField.setText(String.valueOf(Integer.parseInt(powerTextField.getText())+1));
				skillPointsLeft--;
			}
			else if(event.getSource() == minusPower  && !powerTextField.getText().equals("0")){
				powerTextField.setText(String.valueOf(Integer.parseInt(powerTextField.getText())-1));
				skillPointsLeft++;
			}
			
			skillPointsTextField.setText(String.valueOf(skillPointsLeft));
		}
	};

}
