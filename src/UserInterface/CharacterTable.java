package UserInterface;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Character.Fighter;
import Overview.Team;

public class CharacterTable extends JPanel{

	JTable jTable;
	DefaultTableModel tableModel;  
	
	public CharacterTable()
	{

		jTable = new JTable();
		tableModel = new DefaultTableModel(); 
		
		String[] identifiers = new String[] {"Tipo", "Nome", "Força", "Destreza", "Velocidade", "Constituição", "Poder", "Precisão"};
		tableModel.setColumnIdentifiers(identifiers);
        jTable.setModel(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(jTable);
        add(scrollPane);    
	}	
	
	public void addCharacter(String type, String name, String force, String dexterity, String speed, String constitution, String power, String accuracy)
	{
		String[] add = new String[] {type, name, force, dexterity, speed, constitution, power, accuracy};
        tableModel.addRow(add);
	}
	
	/**
	 * 
	 * @return Retorna posicao da tabela removida 
	 */
	public int removeSelectedRow()
	{	
		int position = 0;
		
		try
		{	
			position = jTable.getSelectionModel().getLeadSelectionIndex();
			tableModel.removeRow(jTable.getSelectionModel().getLeadSelectionIndex());
			
		}catch(ArrayIndexOutOfBoundsException ex)
		{
			JOptionPane.showMessageDialog(null, "Selecione um Personagem para remover!", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
			position = -1;
		}
		return position;
	}

}
