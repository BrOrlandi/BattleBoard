package UserInterface;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SimpleTable extends JPanel{
	
	JTable jTable;
	DefaultTableModel tableModel;  
	
	public SimpleTable()
	{

		jTable = new JTable();
		tableModel = new DefaultTableModel(); 
		
		String[] identifiers = new String[] {"Tipo", "Nome", "Ataque, Distancia", "Defesa", "Flexibilidade", "Preço", "Pontos"};
		tableModel.setColumnIdentifiers(identifiers);
        jTable.setModel(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(jTable);
        add(scrollPane);    
	}	
		
	/**
	 * 
	 * @param name Nome do item		
	 * @param type Tipo do item	
	 * @param attack Pontos de Ataque do item
	 * @param defense Pontos Defesa do item
	 * @param price Preço do item
	 */
	public void addItemTable(String type, String name, String attack, String range, String defense, String flexibility, String price, String points)
	{
		String[] add = new String[] {type, name, attack, range, defense, flexibility, price, points};
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
			JOptionPane.showMessageDialog(null, "Selecione um Item!", "Erro ao remover", JOptionPane.ERROR_MESSAGE);
			position = -1;
		}
		return position;
	}
}
